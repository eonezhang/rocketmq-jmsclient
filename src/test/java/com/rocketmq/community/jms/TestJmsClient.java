package com.rocketmq.community.jms;

import com.alibaba.rocketmq.broker.BrokerStartup;
import com.alibaba.rocketmq.namesrv.NamesrvStartup;
import com.rocketmq.community.jms.helper.JmsConsumerAsync;
import com.rocketmq.community.jms.helper.JmsProducer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextLoader;
import org.springframework.test.context.support.GenericXmlContextLoader;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class TestJmsClient {
    private JmsConsumerAsync messageListener;
    private JmsProducer producer;
    private Object waitListnerAvalibleLock = new Object();

    @Before
    public void setup() {
        // Name Server, Broker会在case执行完被关闭（因为JVM的关闭，它们的shutdown hook会被调用)
        NamesrvStartup.main(null);
        BrokerStartup.main(null);
    }


    @Test
    public void TestSendTextMessagge_MultiThread() {
        sendReceiveMessageMultiThread(MessageType.TextMessage);

        try {
            Assert.assertEquals(producer.textMessage, ((TextMessage)messageListener.getMessage()).getText());
        } catch (JMSException ex) {
            System.out.println(ex.getMessage() + "\nStack: " + ex.getStackTrace());
        }
    }

    private void sendReceiveMessageMultiThread(final MessageType msgType) {
        ExecutorService producerService = Executors.newSingleThreadExecutor();
        ExecutorService consumerService = Executors.newSingleThreadExecutor();
        int timeout = 500;

        consumerService.execute(new Runnable() {
            @Override
            public void run() {
                receiveMessage();
            }
        });

        try {
            synchronized (waitListnerAvalibleLock) {
                while(messageListener == null) {
                    waitListnerAvalibleLock.wait();
                }
            }

            boolean queueEmpty = false;
            while(!queueEmpty) {
                // 消费队列里剩余的消息. 如果500ms没有消息，认为队列已经清空。
                synchronized (messageListener.getLock()) {
                    long start = 0;
                    while(messageListener.getMessage() == null) {
                        start = System.currentTimeMillis();
                        messageListener.getLock().wait(timeout);
                        messageListener.setMessage(null);
                        long end = System.currentTimeMillis();
                        if (end - start > timeout) {
                            queueEmpty = true;
                            break;
                        }
                    }
                }
            }

            // 发送新消息
            producerService.execute(new Runnable() {
                @Override
                public void run() {
                    sendMessage(msgType);
                }
            });

            // 等待新消息到达
            synchronized (messageListener.getLock()) {
                while(messageListener.getMessage() == null) {
                    messageListener.getLock().wait();
                }
            }
        }catch (InterruptedException ex) {
            System.out.println(ex.getMessage() + "\nStack: " + ex.getStackTrace());
        }

        producerService.shutdown();
        consumerService.shutdown();
    }

    private void sendMessage(MessageType messageType) {
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("producer-context.xml");
        JmsProducer jmsProducer = (JmsProducer)ctx.getBean("jmsProducer");
        producer = jmsProducer;
        try {
            switch (messageType) {
                case TextMessage:
                    jmsProducer.sendTextMessage();
                    break;
                case BytesMessage:
                    jmsProducer.sendBytesMessage();
                    break;
                case StreamMessage:
                    jmsProducer.sendStreamMessage();
                    break;
                case MapMessage:
                    jmsProducer.sendMapMessage();
                    break;
                default:
                    break;

            }

        } catch (Exception ex) {
            System.out.print(ex.getMessage() + "\nStack: " + ex.getStackTrace());
        }
    }

    private void receiveMessage() {
        synchronized (waitListnerAvalibleLock) {
            ApplicationContext ctx =
                    new ClassPathXmlApplicationContext("producer-context.xml", "consumer-context.xml");
            messageListener = (JmsConsumerAsync)ctx.getBean("messageListener");
            waitListnerAvalibleLock.notifyAll();
        }
    }

    private enum MessageType {
        TextMessage,
        BytesMessage,
        MapMessage,
        StreamMessage,
        ObjectMessage
    }
}
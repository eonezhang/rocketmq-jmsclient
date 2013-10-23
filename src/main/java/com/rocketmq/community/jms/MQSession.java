package com.rocketmq.community.jms;

import com.alibaba.rocketmq.client.consumer.DefaultMQPullConsumer;
import com.alibaba.rocketmq.client.exception.MQClientException;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.MQProducer;
import com.alibaba.rocketmq.client.producer.TransactionMQProducer;
import com.rocketmq.community.jms.message.BytesMessageImpl;
import com.rocketmq.community.jms.message.MapMessageImpl;
import com.rocketmq.community.jms.message.StreamMessageImpl;
import com.rocketmq.community.jms.message.TextMessageImpl;

import javax.jms.*;
import java.io.Serializable;
import java.util.UUID;

public class MQSession implements Session, QueueSession {
    private MQConnection connection;
    int acknowledgeMode;

    public MQSession(MQConnection connection, int acknowledgeMode) {
        this.connection = connection;
        this.acknowledgeMode = acknowledgeMode;
    }

    // Implement Session
    @Override
    public BytesMessage createBytesMessage() throws JMSException {
        return new BytesMessageImpl();
    }

    @Override
    public MapMessage createMapMessage() throws JMSException {
        return new MapMessageImpl();
    }

    @Override
    public Message createMessage() throws JMSException {
        throw new JMSException(Thread.currentThread().getStackTrace()[1].getMethodName() + ": Not supported");
    }

    @Override
    public ObjectMessage createObjectMessage() throws JMSException {
        throw new JMSException(Thread.currentThread().getStackTrace()[1].getMethodName() + ": Not supported");
    }

    @Override
    public ObjectMessage createObjectMessage(Serializable object) throws JMSException {
        throw new JMSException(Thread.currentThread().getStackTrace()[1].getMethodName() + ": Not supported");
    }

    @Override
    public StreamMessage createStreamMessage() throws JMSException {
        return new StreamMessageImpl();
    }

    @Override
    public TextMessage createTextMessage() throws JMSException {
        throw new JMSException(Thread.currentThread().getStackTrace()[1].getMethodName() + ": Not supported");
    }

    @Override
    public TextMessage createTextMessage(String text) throws JMSException {
        TextMessageImpl message = new TextMessageImpl(text);
        return message;
    }

    @Override
    public boolean getTransacted() throws JMSException {
        // TODO 添加具体实现
        return false;
    }

    @Override
    public int getAcknowledgeMode() throws JMSException {
        return acknowledgeMode;
    }

    @Override
    public void commit() throws JMSException {
        throw new JMSException(Thread.currentThread().getStackTrace()[1].getMethodName() + ": Not supported");
    }

    @Override
    public void rollback() throws JMSException {
        throw new JMSException(Thread.currentThread().getStackTrace()[1].getMethodName() + ": Not supported");
    }

    @Override
    public void close() throws JMSException {
        throw new JMSException(Thread.currentThread().getStackTrace()[1].getMethodName() + ": Not supported");
    }

    @Override
    public void recover() throws JMSException {
        throw new JMSException(Thread.currentThread().getStackTrace()[1].getMethodName() + ": Not supported");
    }

    @Override
    public MessageListener getMessageListener() throws JMSException {
        throw new JMSException(Thread.currentThread().getStackTrace()[1].getMethodName() + ": Not supported");
    }

    @Override
    public void setMessageListener(MessageListener listener) throws JMSException {
        throw new JMSException(Thread.currentThread().getStackTrace()[1].getMethodName() + ": Not supported");
    }

    @Override
    public void run() {
        return;
    }

    @Override
    public MessageProducer createProducer(Destination destination) throws JMSException {
        MQProducer producer;
        String name = destination.toString() + "_" + UUID.randomUUID();

        if (!getTransacted()) {
            producer = new DefaultMQProducer(destination.toString());
        } else {
            producer = new TransactionMQProducer(destination.toString());
        }

        // TransactionMQProducer继承DefaultMQProducer，所以这么来设instance name.
        if (((DefaultMQProducer)producer).getInstanceName().equalsIgnoreCase("DEFAULT")) {
            // instance name没被人为设置过
            ((DefaultMQProducer)producer).setInstanceName(name);
        }

        try {
            producer.start();
            return new MQMessageProducer(producer, destination);
        } catch (Exception ex) {
            throw new JMSException(ex.getMessage() + "\nStack: " + ex.getStackTrace());
        }
    }

    @Override
    public MessageConsumer createConsumer(Destination destination) throws JMSException {
        throw new JMSException(Thread.currentThread().getStackTrace()[1].getMethodName() + ": Not supported");
    }

    @Override
    public MessageConsumer createConsumer(
            Destination destination,
            String messageSelector)
            throws JMSException {
        String name = destination.toString() + "_" + UUID.randomUUID();
        DefaultMQPullConsumer target = new DefaultMQPullConsumer(destination.toString());

        if (target.getInstanceName().equalsIgnoreCase("DEFAULT")) {
            // instance name没被人为设置过
            target.setInstanceName(name);
        }

        try {
            target.start();
        } catch (MQClientException ex) {
            throw new JMSException(ex.getMessage() + "\nStack: " + ex.getStackTrace());
        }
        return new MQMessageConsumer(target, destination.toString());
    }

    @Override
    public MessageConsumer createConsumer(
            Destination destination,
            String messageSelector,
            boolean NoLocal)
            throws JMSException {
        throw new JMSException(Thread.currentThread().getStackTrace()[1].getMethodName() + ": Not supported");
    }

    @Override
    public Queue createQueue(String queueName) throws JMSException {
        throw new JMSException(Thread.currentThread().getStackTrace()[1].getMethodName() + ": Not supported");
    }

    @Override
    public Topic createTopic(String topicName) throws JMSException {
        throw new JMSException(Thread.currentThread().getStackTrace()[1].getMethodName() + ": Not supported");
    }

    @Override
    public TopicSubscriber createDurableSubscriber(Topic topic, String name)
            throws JMSException {
        throw new JMSException(Thread.currentThread().getStackTrace()[1].getMethodName() + ": Not supported");
    }

    @Override
    public TopicSubscriber createDurableSubscriber(
            Topic topic,
            String name,
            String messageSelector,
            boolean noLocal)
            throws JMSException {
        throw new JMSException(Thread.currentThread().getStackTrace()[1].getMethodName() + ": Not supported");
    }

    @Override
    public QueueBrowser createBrowser(Queue queue) throws JMSException {
        throw new JMSException(Thread.currentThread().getStackTrace()[1].getMethodName() + ": Not supported");
    }

    @Override
    public QueueBrowser createBrowser(Queue queue, String messageSelector)
            throws JMSException {
        throw new JMSException(Thread.currentThread().getStackTrace()[1].getMethodName() + ": Not supported");
    }

    @Override
    public TemporaryQueue createTemporaryQueue() throws JMSException {
        throw new JMSException(Thread.currentThread().getStackTrace()[1].getMethodName() + ": Not supported");
    }

    @Override
    public TemporaryTopic createTemporaryTopic() throws JMSException {
        throw new JMSException(Thread.currentThread().getStackTrace()[1].getMethodName() + ": Not supported");
    }

    @Override
    public void unsubscribe(String name) throws JMSException {
        throw new JMSException(Thread.currentThread().getStackTrace()[1].getMethodName() + ": Not supported");
    }

    // Implement QueueSession

    @Override
    public QueueReceiver createReceiver(Queue queue) throws JMSException {
        throw new JMSException(Thread.currentThread().getStackTrace()[1].getMethodName() + ": Not supported");
    }

    @Override
    public QueueReceiver createReceiver(Queue queue, String messageSelector) throws JMSException {
        throw new JMSException(Thread.currentThread().getStackTrace()[1].getMethodName() + ": Not supported");
    }

    @Override
    public QueueSender createSender(Queue queue) throws JMSException {
        throw new JMSException(Thread.currentThread().getStackTrace()[1].getMethodName() + ": Not supported");
    }
}

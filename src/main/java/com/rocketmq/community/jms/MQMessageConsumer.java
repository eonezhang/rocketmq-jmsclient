package com.rocketmq.community.jms;

import com.alibaba.rocketmq.client.consumer.DefaultMQPullConsumer;
import com.alibaba.rocketmq.client.consumer.PullResult;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.alibaba.rocketmq.common.message.MessageQueue;
import com.alibaba.rocketmq.remoting.protocol.RemotingSerializable;
import com.rocketmq.community.jms.message.*;
import com.rocketmq.community.jms.util.JMSExceptionSupport;
import com.rocketmq.community.jms.util.RemotingSerializableEx;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import java.util.HashMap;
import java.util.Map;

public class MQMessageConsumer implements MessageConsumer {
    private DefaultMQPullConsumer targetConsumer;
    private MessageQueue[] mqs;
    private int mqIndex = 0;
    final private int batchSize = 32;
    private int consumingMsgIndex = 0;
    private PullResult pullResult;
    private String topic;

    private static final Map<MessageQueue, Long> offseTable = new HashMap<MessageQueue, Long>();

    MQMessageConsumer(DefaultMQPullConsumer consumer, String topic) throws JMSException {
        targetConsumer = consumer;
        this.topic = topic;
        try {
            mqs = new MessageQueue[1];
            mqs = targetConsumer.fetchSubscribeMessageQueues(topic).toArray(mqs);
        } catch (MQClientException ex) {
            throw JMSExceptionSupport.create(ex);
        }
    }

    @Override
    public String getMessageSelector() throws JMSException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public MessageListener getMessageListener() throws JMSException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setMessageListener(MessageListener listener) throws JMSException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Message receive() throws JMSException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Message receive(long timeout) throws JMSException {
        if (mqs == null || mqs.length == 0) {
            return null;
        }

        MessageQueue mq = mqs[mqIndex++];
        mqIndex %= mqs.length;
        Message result = null;
        System.out.println("Consume from the queue: " + mq);

        try {
            if (consumingMsgIndex == 0) {
                // 所有之前批量拉取的消息已经处理完了，重新拉取。
                pullResult = targetConsumer.pull(mq, MessageBase.JMS_SOURCE, getMessageQueueOffset(mq), batchSize);
            }

            if (pullResult != null) {
                putMessageQueueOffset(mq, pullResult.getNextBeginOffset());
                switch (pullResult.getPullStatus()) {
                    case FOUND:
                        result = convertToJmsMessage(pullResult.getMsgFoundList().get(consumingMsgIndex));
                        consumingMsgIndex++;
                        consumingMsgIndex = consumingMsgIndex < pullResult.getMsgFoundList().size() ? consumingMsgIndex : 0;
                        break;
                    case NO_MATCHED_MSG:
                        break;
                    case NO_NEW_MSG:
                        break;
                    case OFFSET_ILLEGAL:
                        break;
                    default:
                        break;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    private Message convertToJmsMessage(MessageExt rawMessage) throws JMSException {
        String msgType = rawMessage.getProperty(MessageBase.MSG_TYPE_NAME);
        if (msgType == null || rawMessage.getBody() == null) {
            return null;
        }

        Message message = null;
        if (msgType.equalsIgnoreCase(MessageBase.MessageTypeEnum.TextMessage.toString())) {
            String content = new String(rawMessage.getBody());
            message = new TextMessageImpl(content, true);
        } else if (msgType.equalsIgnoreCase(MessageBase.MessageTypeEnum.MapMessage.toString())) {
            message = new MapMessageImpl(rawMessage.getBody(), true);
        } else if (msgType.equalsIgnoreCase(MessageBase.MessageTypeEnum.BytesMessage.toString())) {
            if (rawMessage.getBody() != null) {
                message = new BytesMessageImpl(rawMessage.getBody(), true);
            }
        } else if (msgType.equalsIgnoreCase(MessageBase.MessageTypeEnum.StreamMessage.toString())) {
            if (rawMessage.getBody() != null) {
                message = new StreamMessageImpl(rawMessage.getBody(), true);
            }
        } else if (msgType.equalsIgnoreCase(MessageBase.MessageTypeEnum.ObjectMessage.toString())) {
            if (rawMessage.getBody() != null) {
                message = new ObjectMessageImpl(rawMessage.getBody(), true);
            }
        }

        return message;
    }

    @Override
    public Message receiveNoWait() throws JMSException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void close() throws JMSException {
        targetConsumer.shutdown();
    }


    private static void putMessageQueueOffset(MessageQueue mq, long offset) {
        offseTable.put(mq, offset);
    }


    private static long getMessageQueueOffset(MessageQueue mq) {
        Long offset = offseTable.get(mq);
        if (offset != null)
            return offset;

        return 0;
    }
}

package com.rocketmq.community.jms;

import com.alibaba.rocketmq.client.producer.MQProducer;
import com.rocketmq.community.jms.message.MessageBase;
import com.rocketmq.community.jms.util.JMSExceptionSupport;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;

public class MQMessageProducer implements MessageProducer {
    private MQProducer targetProducer;
    private Destination destination;

    MQMessageProducer(MQProducer producer, Destination dest) {
        targetProducer = producer;
        destination = dest;
    }

    @Override
    public void setDisableMessageID(boolean value) throws JMSException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean getDisableMessageID() throws JMSException {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setDisableMessageTimestamp(boolean value) throws JMSException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean getDisableMessageTimestamp() throws JMSException {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setDeliveryMode(int deliveryMode) throws JMSException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getDeliveryMode() throws JMSException {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setPriority(int defaultPriority) throws JMSException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getPriority() throws JMSException {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setTimeToLive(long timeToLive) throws JMSException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public long getTimeToLive() throws JMSException {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Destination getDestination() throws JMSException {
        return destination;
    }

    @Override
    public void close() throws JMSException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void send(Message message) throws JMSException {
    }

    @Override
    public void send(Message message, int deliveryMode, int priority, long timeToLive) throws JMSException {
        message.setJMSDestination(destination);
        com.alibaba.rocketmq.common.message.Message convertedMsg = ((MessageBase)message).convert();
        try {
            targetProducer.send(convertedMsg);
        } catch (Exception ex) {
            throw JMSExceptionSupport.create(ex);
        }
    }

    @Override
    public void send(Destination destination, Message message) throws JMSException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void send(Destination destination, Message message, int deliveryMode, int priority, long timeToLive) throws JMSException {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}

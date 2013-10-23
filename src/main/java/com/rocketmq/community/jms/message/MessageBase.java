package com.rocketmq.community.jms.message;

import com.alibaba.rocketmq.common.message.Message;

import javax.jms.Destination;
import javax.jms.JMSException;
import java.util.Enumeration;

public abstract class MessageBase implements javax.jms.Message {
    private Destination destination;
    final static public String JMS_SOURCE = "JMS_SOURCE";
    final static public String MSG_TYPE_NAME = "MSG_TYPE";

    @Override
    public String getJMSMessageID() throws JMSException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setJMSMessageID(String id) throws JMSException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public long getJMSTimestamp() throws JMSException {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setJMSTimestamp(long timestamp) throws JMSException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public byte[] getJMSCorrelationIDAsBytes() throws JMSException {
        return new byte[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setJMSCorrelationIDAsBytes(byte[] correlationID) throws JMSException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setJMSCorrelationID(String correlationID) throws JMSException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getJMSCorrelationID() throws JMSException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Destination getJMSReplyTo() throws JMSException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setJMSReplyTo(Destination replyTo) throws JMSException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Destination getJMSDestination() throws JMSException {
        return destination;
    }

    @Override
    public void setJMSDestination(Destination destination) throws JMSException {
        this.destination = destination;
    }

    @Override
    public int getJMSDeliveryMode() throws JMSException {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setJMSDeliveryMode(int deliveryMode) throws JMSException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean getJMSRedelivered() throws JMSException {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setJMSRedelivered(boolean redelivered) throws JMSException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getJMSType() throws JMSException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setJMSType(String type) throws JMSException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public long getJMSExpiration() throws JMSException {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setJMSExpiration(long expiration) throws JMSException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getJMSPriority() throws JMSException {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setJMSPriority(int priority) throws JMSException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void clearProperties() throws JMSException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean propertyExists(String name) throws JMSException {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean getBooleanProperty(String name) throws JMSException {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public byte getByteProperty(String name) throws JMSException {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public short getShortProperty(String name) throws JMSException {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getIntProperty(String name) throws JMSException {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public long getLongProperty(String name) throws JMSException {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public float getFloatProperty(String name) throws JMSException {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public double getDoubleProperty(String name) throws JMSException {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getStringProperty(String name) throws JMSException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object getObjectProperty(String name) throws JMSException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Enumeration getPropertyNames() throws JMSException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setBooleanProperty(String name, boolean value) throws JMSException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setByteProperty(String name, byte value) throws JMSException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setShortProperty(String name, short value) throws JMSException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setIntProperty(String name, int value) throws JMSException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setLongProperty(String name, long value) throws JMSException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setFloatProperty(String name, float value) throws JMSException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setDoubleProperty(String name, double value) throws JMSException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setStringProperty(String name, String value) throws JMSException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setObjectProperty(String name, Object value) throws JMSException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void acknowledge() throws JMSException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void clearBody() throws JMSException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public abstract Message convert() throws JMSException ;

    public enum MessageTypeEnum {
        TextMessage,
        StreamMessage,
        MapMessage,
        ObjectMessage,
        BytesMessage
    }
}

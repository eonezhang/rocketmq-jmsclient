package com.rocketmq.community.jms.message;

import com.alibaba.rocketmq.common.message.Message;
import com.rocketmq.community.jms.util.RemotingSerializableEx;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageNotWriteableException;
import java.util.Enumeration;

public abstract class MessageBase implements javax.jms.Message {
    public static final byte NULL = 0;
    public static final byte BOOLEAN_TYPE = 1;
    public static final byte BYTE_TYPE = 2;
    public static final byte CHAR_TYPE = 3;
    public static final byte SHORT_TYPE = 4;
    public static final byte INTEGER_TYPE = 5;
    public static final byte LONG_TYPE = 6;
    public static final byte DOUBLE_TYPE = 7;
    public static final byte FLOAT_TYPE = 8;
    public static final byte STRING_TYPE = 9;
    public static final byte BYTE_ARRAY_TYPE = 10;
    public static final byte MAP_TYPE = 11;
    public static final byte LIST_TYPE = 12;
    public static final byte BIG_STRING_TYPE = 13;
    protected static final int TYPE_NOT_AVAILABLE = -100;

    private Destination destination;
    final static public String JMS_SOURCE = "JMS_SOURCE";
    final static public String MSG_TYPE_NAME = "MSG_TYPE";
    protected boolean readOnly;

    protected byte[] content;

    protected MessageBase() {
    }

    protected MessageBase(byte[] content, Boolean readOnly) {
        this.content = content;
        this.readOnly = readOnly;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public byte[] getContent() {
        return content;
    }

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
        readOnly = false;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public boolean isReadOnly() {
        return readOnly;
    }
    public abstract Message convert() throws JMSException ;

    public enum MessageTypeEnum {
        TextMessage,
        StreamMessage,
        MapMessage,
        ObjectMessage,
        BytesMessage
    }

    protected void checkReadOnly() throws MessageNotWriteableException {
        if (readOnly) {
            throw new MessageNotWriteableException("Message body is read-only");
        }
    }

    public MessageBase copy() {
        String json = RemotingSerializableEx.toJsonWithClass(this);
        return RemotingSerializableEx.fromJson(json, this.getClass());
    }
}


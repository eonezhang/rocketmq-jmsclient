package com.rocketmq.community.jms.message;

import com.alibaba.rocketmq.common.message.Message;
import com.rocketmq.community.jms.util.JMSExceptionSupport;
import com.rocketmq.community.jms.util.RemotingSerializableEx;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import java.io.*;

public class ObjectMessageImpl extends MessageBase implements ObjectMessage {
    private byte[] content;

    public ObjectMessageImpl() {
        this(null, false);
    }

    public ObjectMessageImpl(byte[] content, boolean readOnly) {
        this.content = content;
        this.readOnly = readOnly;
    }

    @Override
    public Message convert() throws JMSException {
        if (content == null) {
            return null;
        }

        Message message = new Message(this.getJMSDestination().toString(), // topic
                JMS_SOURCE,  // tag
                content);  // body
        message.putProperty(MSG_TYPE_NAME, MessageTypeEnum.ObjectMessage.toString());

        return message;
    }

    @Override
    public void setObject(Serializable object) throws JMSException {
        if (object != null) {
            content = RemotingSerializableEx.encodeWithClass(object);
        }
    }

    @Override
    public Serializable getObject() throws JMSException {
        Serializable object = RemotingSerializableEx.decode(content, Serializable.class);
        return object;
    }

    @Override
    public void clearBody() throws JMSException {
        super.clearBody();
        content = null;
    }
}

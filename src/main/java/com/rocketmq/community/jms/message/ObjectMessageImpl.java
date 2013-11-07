package com.rocketmq.community.jms.message;

import com.alibaba.rocketmq.common.message.Message;
import com.rocketmq.community.jms.util.JMSExceptionSupport;
import com.rocketmq.community.jms.util.RemotingSerializableEx;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import java.io.*;

public class ObjectMessageImpl extends MessageBase implements ObjectMessage {
    public ObjectMessageImpl() {
    }

    public ObjectMessageImpl(byte[] content, boolean readOnly) {
        super(content, readOnly);
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
        checkReadOnly();
        if (object != null) {
            content = RemotingSerializableEx.encodeWithClass(object);
        }
    }

    @Override
    public Serializable getObject() throws JMSException {
        Serializable object = null;
        try {
          object = RemotingSerializableEx.decode(content, Serializable.class);

        } catch (Exception ex) {
            JMSExceptionSupport.create(ex);
        }

        return object;
    }

    @Override
    public void clearBody() throws JMSException {
        super.clearBody();
        content = null;
    }
}

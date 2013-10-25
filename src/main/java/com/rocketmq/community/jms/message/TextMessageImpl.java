package com.rocketmq.community.jms.message;

import com.alibaba.rocketmq.common.message.Message;

import javax.jms.JMSException;
import javax.jms.TextMessage;

public class TextMessageImpl extends MessageBase implements TextMessage {
    private String text;

    public TextMessageImpl(String text, boolean readOnly) {
        this.text = text;
        this.readOnly = readOnly;
    }

    @Override
    public void setText(String string) throws JMSException {
        text = string;
    }

    @Override
    public String getText() throws JMSException {
        return text;
    }

    @Override
    public void clearBody() throws JMSException {
        super.clearBody();
        text = null;
    }

    @Override
    public Message convert() throws JMSException {
        if (text == null) {
            return null;
        }

        Message message = new Message(this.getJMSDestination().toString(), // topic
                JMS_SOURCE,  // tag
                text.getBytes());  // body
        message.putProperty(MSG_TYPE_NAME, MessageTypeEnum.TextMessage.toString());
        return message;
    }
}

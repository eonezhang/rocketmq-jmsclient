package com.rocketmq.community.jms.message;

import com.alibaba.rocketmq.common.message.Message;

import javax.jms.BytesMessage;
import javax.jms.JMSException;

public class BytesMessageImpl extends SequenceMessageImpl implements BytesMessage {
    private long length;

    public BytesMessageImpl() {
        super(null, false);
    }

    public BytesMessageImpl(byte[] content, boolean readOnly) {
        super(content, readOnly);
    }

    public long getBodyLength() throws JMSException {
        return length;
    }

    @Override
    protected void setMessageTypeProperty(Message message) {
        message.putProperty(MSG_TYPE_NAME, MessageTypeEnum.BytesMessage.toString());
    }

    protected void initializeReading() throws JMSException {
        super.initializeReading();
        if (content != null) {
            length = content.length;
        }
    }
}

package com.rocketmq.community.jms.message;

import com.alibaba.rocketmq.common.message.Message;

import javax.jms.BytesMessage;

public class BytesMessageImpl extends SequenceMessageImpl implements BytesMessage {
    public BytesMessageImpl() {
        super(null);
    }

    public BytesMessageImpl(byte[] content) {
        super(content);
    }

    @Override
    protected void setMessageTypeProperty(Message message) {
        message.putProperty(MSG_TYPE_NAME, MessageTypeEnum.BytesMessage.toString());
    }
}

package com.rocketmq.community.jms.message;

import com.alibaba.rocketmq.common.message.Message;

import javax.jms.JMSException;
import javax.jms.StreamMessage;
import java.io.IOException;

public class StreamMessageImpl extends SequenceMessageImpl implements StreamMessage {
    public StreamMessageImpl() {
        super(null);
    }

    public StreamMessageImpl(byte[] content) {
        super(content);
    }

    @Override
    public double readDouble() throws JMSException {
        initializeReading();

        try {
            return dataIn.readDouble();
        } catch (IOException ex) {
            throw new JMSException(ex.getMessage() + "\nStack: " + ex.getStackTrace());
        }
    }

    @Override
    public String readString() throws JMSException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object readObject() throws JMSException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void writeString(String value) throws JMSException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void preWriteProcess(Byte valueType) throws IOException {
        dataOut.writeByte(valueType);
    }

    @Override
    protected void setMessageTypeProperty(Message message) {
        message.putProperty(MSG_TYPE_NAME, MessageTypeEnum.StreamMessage.toString());
    }
}

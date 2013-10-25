package com.rocketmq.community.jms.message;

import com.alibaba.rocketmq.common.message.Message;
import com.rocketmq.community.jms.util.JMSExceptionSupport;

import javax.jms.JMSException;
import javax.jms.MessageEOFException;
import javax.jms.MessageFormatException;
import javax.jms.StreamMessage;
import java.io.EOFException;
import java.io.IOException;

public class StreamMessageImpl extends SequenceMessageImpl implements StreamMessage {
    public StreamMessageImpl() {
        super(null, false);
    }

    public StreamMessageImpl(byte[] content, boolean readOnly) {
        super(content, readOnly);
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

    public double readDouble() throws JMSException {
        initializeReading();

        try {
            int type = dataIn.read();

            if (type == -1) {
                throw new MessageEOFException("reached end of data");
            }
            if (type == DOUBLE_TYPE) {
                return this.dataIn.readDouble();
            }
            if (type == FLOAT_TYPE) {
                return this.dataIn.readFloat();
            }
            if (type == STRING_TYPE) {
                return Double.valueOf(this.dataIn.readUTF()).doubleValue();
            }
            if (type == NULL) {
                this.dataIn.reset();
                throw new NullPointerException("Cannot convert NULL value to double.");
            } else {
                this.dataIn.reset();
                throw new MessageFormatException(" not a double type");
            }
        } catch (EOFException ex) {
            throw JMSExceptionSupport.create(ex);
        } catch (IOException ex) {
            throw JMSExceptionSupport.create(ex);
        }
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

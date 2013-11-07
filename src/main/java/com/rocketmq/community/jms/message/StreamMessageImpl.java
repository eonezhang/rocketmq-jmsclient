package com.rocketmq.community.jms.message;

import com.alibaba.rocketmq.common.message.Message;
import com.rocketmq.community.jms.util.JMSExceptionSupport;
import com.rocketmq.community.jms.util.RemotingSerializableEx;

import javax.jms.JMSException;
import javax.jms.MessageEOFException;
import javax.jms.MessageFormatException;
import javax.jms.StreamMessage;
import java.io.EOFException;
import java.io.IOException;

public class StreamMessageImpl extends SequenceMessageImpl implements StreamMessage {
    public StreamMessageImpl() {
    }

    public StreamMessageImpl(byte[] content, boolean readOnly) {
        super(content, readOnly);
    }

    @Override
    public String readString() throws JMSException {
        initializeReading();
        try {

            this.dataIn.mark(65);
            int type = this.dataIn.read();
            if (type == -1) {
                throw new MessageEOFException("reached end of data");
            }
            if (type == NULL) {
                return null;
            }
            if (type == BIG_STRING_TYPE) {
                return readBigString();
            }
            if (type == STRING_TYPE) {
                return this.dataIn.readUTF();
            }
            if (type == LONG_TYPE) {
                return new Long(this.dataIn.readLong()).toString();
            }
            if (type == INTEGER_TYPE) {
                return new Integer(this.dataIn.readInt()).toString();
            }
            if (type == SHORT_TYPE) {
                return new Short(this.dataIn.readShort()).toString();
            }
            if (type == BYTE_TYPE) {
                return new Byte(this.dataIn.readByte()).toString();
            }
            if (type == FLOAT_TYPE) {
                return new Float(this.dataIn.readFloat()).toString();
            }
            if (type == DOUBLE_TYPE) {
                return new Double(this.dataIn.readDouble()).toString();
            }
            if (type == BOOLEAN_TYPE) {
                return (this.dataIn.readBoolean() ? Boolean.TRUE : Boolean.FALSE).toString();
            }
            if (type == CHAR_TYPE) {
                return new Character(this.dataIn.readChar()).toString();
            } else {
                this.dataIn.reset();
                throw new MessageFormatException(" not a String type");
            }
        } catch (NumberFormatException mfe) {
            try {
                this.dataIn.reset();
            } catch (IOException ioe) {
                throw JMSExceptionSupport.create(ioe);
            }
            throw mfe;

        } catch (EOFException ex) {
            throw JMSExceptionSupport.create(ex);
        } catch (IOException ex) {
            throw JMSExceptionSupport.create(ex);
        }
    }

    @Override
    public Object readObject() throws JMSException {
        initializeReading();
        try {
            this.dataIn.mark(65);
            int type = this.dataIn.read();
            if (type == -1) {
                throw new MessageEOFException("reached end of data");
            }
            if (type == NULL) {
                return null;
            }
            if (type == BIG_STRING_TYPE) {
                return readBigString();
            }
            if (type == STRING_TYPE) {
                return this.dataIn.readUTF();
            }
            if (type == LONG_TYPE) {
                return Long.valueOf(this.dataIn.readLong());
            }
            if (type == INTEGER_TYPE) {
                return Integer.valueOf(this.dataIn.readInt());
            }
            if (type == SHORT_TYPE) {
                return Short.valueOf(this.dataIn.readShort());
            }
            if (type == BYTE_TYPE) {
                return Byte.valueOf(this.dataIn.readByte());
            }
            if (type == FLOAT_TYPE) {
                return new Float(this.dataIn.readFloat());
            }
            if (type == DOUBLE_TYPE) {
                return new Double(this.dataIn.readDouble());
            }
            if (type == BOOLEAN_TYPE) {
                return this.dataIn.readBoolean() ? Boolean.TRUE : Boolean.FALSE;
            }
            if (type == CHAR_TYPE) {
                return Character.valueOf(this.dataIn.readChar());
            }
            if (type == BYTE_ARRAY_TYPE) {
                int len = this.dataIn.readInt();
                byte[] value = new byte[len];
                this.dataIn.readFully(value);
                return value;
            } else {
                this.dataIn.reset();
                throw new MessageFormatException("unknown type");
            }
        } catch (NumberFormatException mfe) {
            try {
                this.dataIn.reset();
            } catch (IOException ioe) {
                throw JMSExceptionSupport.create(ioe);
            }
            throw mfe;

        } catch (EOFException ex) {
            JMSException jmsEx = new MessageEOFException(ex.getMessage());
            jmsEx.setLinkedException(ex);
            throw jmsEx;
        } catch (IOException ex) {
            JMSException jmsEx = new MessageFormatException(ex.getMessage());
            jmsEx.setLinkedException(ex);
            throw jmsEx;
        }
    }

    @Override
    public void writeString(String value) throws JMSException {
        initializeWriting();
        try {
            if (value == null) {
                preWriteProcess(NULL);
            } else {
                if (value.length() < Short.MAX_VALUE / 4) {
                    dataOut.writeByte(STRING_TYPE);
                    dataOut.writeUTF(value);
                } else {
                    dataOut.writeByte(BIG_STRING_TYPE);
                    byte[] encodedValue = value.getBytes();;
                    dataOut.writeInt(encodedValue.length);
                    dataOut.write(encodedValue);
                }
            }
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

    @Override
    protected int getType() throws IOException {
        return dataIn.read();
    }

    private String readBigString() throws IOException {
        int length = this.dataIn.readInt();
        byte[] encodedValue = new byte[length];

        for(int i = 0; i < length; i++) {
            encodedValue[i] = this.dataIn.readByte();
        }
        return new String(encodedValue);
    }
}


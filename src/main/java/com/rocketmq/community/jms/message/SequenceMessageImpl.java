package com.rocketmq.community.jms.message;

import com.alibaba.rocketmq.common.message.Message;

import javax.jms.JMSException;
import java.io.*;

public abstract class SequenceMessageImpl extends MessageBase {
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

    protected DataOutputStream dataOut;
    protected ByteArrayOutputStream bytesOut;
    protected DataInputStream dataIn;
    private byte[] content;

    protected SequenceMessageImpl(byte[] content) {
        setContent(content);
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public long getBodyLength() throws JMSException {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean readBoolean() throws JMSException {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public byte readByte() throws JMSException {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public int readUnsignedByte() throws JMSException {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public short readShort() throws JMSException {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public int readUnsignedShort() throws JMSException {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public char readChar() throws JMSException {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public int readInt() throws JMSException {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public long readLong() throws JMSException {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public float readFloat() throws JMSException {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public double readDouble() throws JMSException {
        initializeReading();

        try {
            preReadProcess();
            return dataIn.readDouble();
        } catch (IOException ex) {
            throw new JMSException(ex.getMessage() + "\nStack: " + ex.getStackTrace());
        }
    }

    public String readUTF() throws JMSException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public int readBytes(byte[] value) throws JMSException {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public int readBytes(byte[] value, int length) throws JMSException {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void writeBoolean(boolean value) throws JMSException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void writeByte(byte value) throws JMSException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void writeShort(short value) throws JMSException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void writeChar(char value) throws JMSException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void writeInt(int value) throws JMSException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void writeLong(long value) throws JMSException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void writeFloat(float value) throws JMSException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void writeDouble(double value) throws JMSException {
        initializeWriting();

        try {
            preWriteProcess(DOUBLE_TYPE);
            dataOut.writeDouble(value);
        } catch (IOException ex) {
            throw new JMSException(ex.getMessage() + "\nStack: " + ex.getStackTrace());
        }
    }

    public void writeUTF(String value) throws JMSException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void writeBytes(byte[] value) throws JMSException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void writeBytes(byte[] value, int offset, int length) throws JMSException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void writeObject(Object value) throws JMSException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void reset() throws JMSException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    protected void initializeWriting() throws JMSException {
        if (this.dataOut == null) {
            this.bytesOut = new ByteArrayOutputStream();
            OutputStream os = bytesOut;

            this.dataOut = new DataOutputStream(os);
        }
    }

    protected void initializeReading() throws JMSException {
        if (dataIn == null) {
            InputStream is = new ByteArrayInputStream(content);
            dataIn = new DataInputStream(is);
        }
    }

    protected void preWriteProcess(Byte valueType) throws IOException {
    }

    protected int preReadProcess() throws IOException {
        return dataIn.read();
    }

    @Override
    public Message convert() throws JMSException {
        if (bytesOut == null) {
            return null;
        }

        Message message = new Message(this.getJMSDestination().toString(), // topic
                JMS_SOURCE,  // tag
                bytesOut.toByteArray());  // body
        setMessageTypeProperty(message);

        return message;
    }

    protected abstract void setMessageTypeProperty(Message message);
}

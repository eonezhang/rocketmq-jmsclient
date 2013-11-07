package com.rocketmq.community.jms.message;

import com.alibaba.rocketmq.common.message.Message;
import com.rocketmq.community.jms.util.JMSExceptionSupport;

import javax.jms.*;
import java.io.*;

public abstract class SequenceMessageImpl extends MessageBase {
    protected DataOutputStream dataOut;
    protected ByteArrayOutputStream bytesOut;
    protected DataInputStream dataIn;
    protected transient int remainingBytes = -1;

    protected SequenceMessageImpl() {
    }

    protected SequenceMessageImpl(byte[] content, boolean readOnly) {
        super(content, readOnly);
    }

    public boolean readBoolean() throws JMSException {
        initializeReading();
        try {
            this.dataIn.mark(10);

            int type = getType();
            if (type == TYPE_NOT_AVAILABLE) {
                type = BOOLEAN_TYPE;
            }

            if (type == -1) {
                throw new MessageEOFException("reached end of data");
            }
            if (type == BOOLEAN_TYPE) {
                return this.dataIn.readBoolean();
            }
            if (type == STRING_TYPE) {
                return Boolean.valueOf(this.dataIn.readUTF()).booleanValue();
            }
            if (type == NULL) {
                this.dataIn.reset();
                throw new NullPointerException("Cannot convert NULL value to boolean.");
            } else {
                this.dataIn.reset();
                throw new MessageFormatException(" not a boolean type");
            }
        } catch (EOFException ex) {
            throw JMSExceptionSupport.create(ex);
        } catch (IOException ex) {
            throw JMSExceptionSupport.create(ex);
        }
    }

    public byte readByte() throws JMSException {
        initializeReading();
        try {
            this.dataIn.mark(10);

            int type = getType();
            if (type == TYPE_NOT_AVAILABLE) {
                type = BYTE_TYPE;
            }

            if (type == -1) {
                throw new MessageEOFException("reached end of data");
            }
            if (type == BYTE_TYPE) {
                return this.dataIn.readByte();
            }
            if (type == STRING_TYPE) {
                return Byte.valueOf(this.dataIn.readUTF()).byteValue();
            }
            if (type == NULL) {
                this.dataIn.reset();
                throw new NullPointerException("Cannot convert NULL value to byte.");
            } else {
                this.dataIn.reset();
                throw new MessageFormatException(" not a byte type");
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

    public int readUnsignedByte() throws JMSException {
        initializeReading();
        int value;

        try {
            value = this.dataIn.readUnsignedByte();
        } catch (EOFException ex) {
            throw JMSExceptionSupport.create(ex);
        } catch (IOException ex) {
            throw JMSExceptionSupport.create(ex);
        }

        return value;
    }

    public short readShort() throws JMSException {
        initializeReading();
        try {

            this.dataIn.mark(17);
            int type = getType();
            if (type == TYPE_NOT_AVAILABLE) {
                type = SHORT_TYPE;
            }

            if (type == -1) {
                throw new MessageEOFException("reached end of data");
            }
            if (type == SHORT_TYPE) {
                return this.dataIn.readShort();
            }
            if (type == BYTE_TYPE) {
                return this.dataIn.readByte();
            }
            if (type == STRING_TYPE) {
                return Short.valueOf(this.dataIn.readUTF()).shortValue();
            }
            if (type == NULL) {
                this.dataIn.reset();
                throw new NullPointerException("Cannot convert NULL value to short.");
            } else {
                this.dataIn.reset();
                throw new MessageFormatException(" not a short type");
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

    public int readUnsignedShort() throws JMSException {
        initializeReading();
        int value;

        try {
            value = this.dataIn.readUnsignedShort();
        } catch (EOFException ex) {
            throw JMSExceptionSupport.create(ex);
        } catch (IOException ex) {
            throw JMSExceptionSupport.create(ex);
        }

        return value;
    }

    public char readChar() throws JMSException {
        initializeReading();
        try {
            int type = getType();
            if (type == TYPE_NOT_AVAILABLE) {
                type = CHAR_TYPE;
            }

            if (type == -1) {
                throw new MessageEOFException("reached end of data");
            }
            if (type == CHAR_TYPE) {
                return this.dataIn.readChar();
            }
            if (type == NULL) {
                this.dataIn.reset();
                throw new NullPointerException("Cannot convert NULL value to char.");
            } else {
                this.dataIn.reset();
                throw new MessageFormatException(" not a char type");
            }
        } catch (NumberFormatException mfe) {
            try {
                this.dataIn.reset();
            } catch (IOException ioe) {
                throw JMSExceptionSupport.create(ioe);
            }
            throw mfe;
        } catch (EOFException e) {
            throw JMSExceptionSupport.create(e);
        } catch (IOException e) {
            throw JMSExceptionSupport.create(e);
        }
    }

    public int readInt() throws JMSException {
        initializeReading();
        try {
            this.dataIn.mark(33);

            int type = getType();
            if (type == TYPE_NOT_AVAILABLE) {
                type = INTEGER_TYPE;
            }
            if (type == -1) {
                throw new MessageEOFException("reached end of data");
            }
            if (type == INTEGER_TYPE) {
                return this.dataIn.readInt();
            }
            if (type == SHORT_TYPE) {
                return this.dataIn.readShort();
            }
            if (type == BYTE_TYPE) {
                return this.dataIn.readByte();
            }
            if (type == STRING_TYPE) {
                return Integer.valueOf(this.dataIn.readUTF()).intValue();
            }
            if (type == NULL) {
                this.dataIn.reset();
                throw new NullPointerException("Cannot convert NULL value to int.");
            } else {
                this.dataIn.reset();
                throw new MessageFormatException(" not an int type");
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

    public long readLong() throws JMSException {
        initializeReading();
        try {

            this.dataIn.mark(65);
            int type = getType();
            if (type == TYPE_NOT_AVAILABLE) {
                type = LONG_TYPE;
            }
            if (type == -1) {
                throw new MessageEOFException("reached end of data");
            }
            if (type == LONG_TYPE) {
                return this.dataIn.readLong();
            }
            if (type == INTEGER_TYPE) {
                return this.dataIn.readInt();
            }
            if (type == SHORT_TYPE) {
                return this.dataIn.readShort();
            }
            if (type == BYTE_TYPE) {
                return this.dataIn.readByte();
            }
            if (type == STRING_TYPE) {
                return Long.valueOf(this.dataIn.readUTF()).longValue();
            }
            if (type == NULL) {
                this.dataIn.reset();
                throw new NullPointerException("Cannot convert NULL value to long.");
            } else {
                this.dataIn.reset();
                throw new MessageFormatException(" not a long type");
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

    public float readFloat() throws JMSException {
        initializeReading();
        try {
            this.dataIn.mark(33);
            int type = getType();
            if (type == TYPE_NOT_AVAILABLE) {
                type = FLOAT_TYPE;
            }
            if (type == -1) {
                throw new MessageEOFException("reached end of data");
            }
            if (type == FLOAT_TYPE) {
                return this.dataIn.readFloat();
            }
            if (type == STRING_TYPE) {
                return Float.valueOf(this.dataIn.readUTF()).floatValue();
            }
            if (type == NULL) {
                this.dataIn.reset();
                throw new NullPointerException("Cannot convert NULL value to float.");
            } else {
                this.dataIn.reset();
                throw new MessageFormatException(" not a float type");
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

    public double readDouble() throws JMSException {
        initializeReading();

        try {
            int type = getType();
            if (type == TYPE_NOT_AVAILABLE) {
                type = DOUBLE_TYPE;
            }

            this.dataIn.mark(65);
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

    public String readUTF() throws JMSException {
        initializeReading();
        try {
            return this.dataIn.readUTF();
        } catch (EOFException ex) {
            throw JMSExceptionSupport.create(ex);
        } catch (IOException ex) {
            throw JMSExceptionSupport.create(ex);
        }
    }

    public int readBytes(byte[] value) throws JMSException {
        initializeReading();
        try {
            if (value == null) {
                throw new NullPointerException();
            }

            if (remainingBytes == -1) {
                int type = getType();
                if (type == TYPE_NOT_AVAILABLE) {
                    type = BYTE_ARRAY_TYPE;
                }

                this.dataIn.mark(value.length + 1);

                if (type == -1) {
                    throw new MessageEOFException("reached end of data");
                }
                if (type != BYTE_ARRAY_TYPE) {
                    throw new MessageFormatException("Not a byte array");
                }
                remainingBytes = this.dataIn.readInt();
            } else if (remainingBytes == 0) {
                remainingBytes = -1;
                return -1;
            }

            if (value.length <= remainingBytes) {
                // small buffer
                remainingBytes -= value.length;
                this.dataIn.readFully(value);
                return value.length;
            } else {
                // big buffer
                int rc = this.dataIn.read(value, 0, remainingBytes);
                remainingBytes = 0;
                return rc;
            }

        } catch (EOFException e) {
            JMSException jmsEx = new MessageEOFException(e.getMessage());
            jmsEx.setLinkedException(e);
            throw jmsEx;
        } catch (IOException e) {
            JMSException jmsEx = new MessageFormatException(e.getMessage());
            jmsEx.setLinkedException(e);
            throw jmsEx;
        }
    }

    public int readBytes(byte[] value, int length) throws JMSException {
        initializeReading();
        try {
            if (value == null) {
                throw new NullPointerException();
            }

            if (remainingBytes == -1) {
                int type = getType();
                if (type == TYPE_NOT_AVAILABLE) {
                    type = BYTE_ARRAY_TYPE;
                }
                this.dataIn.mark(value.length + 1);

                if (type == -1) {
                    throw new MessageEOFException("reached end of data");
                }
                if (type != BYTE_ARRAY_TYPE) {
                    throw new MessageFormatException("Not a byte array");
                }
                remainingBytes = this.dataIn.readInt();
            } else if (remainingBytes == 0) {
                remainingBytes = -1;
                return -1;
            }

            if (length <= remainingBytes) {
                // small buffer
                remainingBytes -= length;
                this.dataIn.readFully(value);
                return length;
            } else {
                // big buffer
                int rc = this.dataIn.read(value, 0, remainingBytes);
                remainingBytes = 0;
                return rc;
            }

        } catch (EOFException e) {
            JMSException jmsEx = new MessageEOFException(e.getMessage());
            jmsEx.setLinkedException(e);
            throw jmsEx;
        } catch (IOException e) {
            JMSException jmsEx = new MessageFormatException(e.getMessage());
            jmsEx.setLinkedException(e);
            throw jmsEx;
        }
    }

    public void writeBoolean(boolean value) throws JMSException {
        initializeWriting();

        try {
            preWriteProcess(BOOLEAN_TYPE);
            dataOut.writeBoolean(value);
        } catch (IOException ex) {
            throw JMSExceptionSupport.create(ex);
        }
    }

    public void writeByte(byte value) throws JMSException {
        initializeWriting();
        try {
            preWriteProcess(BYTE_TYPE);
            dataOut.writeByte(value);
        } catch (IOException ex) {
            throw JMSExceptionSupport.create(ex);
        }
    }

    public void writeShort(short value) throws JMSException {
        initializeWriting();
        try {
            preWriteProcess(SHORT_TYPE);
            dataOut.writeShort(value);
        } catch (IOException ex) {
            throw JMSExceptionSupport.create(ex);
        }
    }

    public void writeChar(char value) throws JMSException {
        initializeWriting();
        try {
            preWriteProcess(CHAR_TYPE);
            this.dataOut.writeChar(value);
        } catch (IOException ex) {
            throw JMSExceptionSupport.create(ex);
        }
    }

    public void writeInt(int value) throws JMSException {
        initializeWriting();
        try {
            preWriteProcess(INTEGER_TYPE);
            this.dataOut.writeInt(value);
        } catch (IOException ex) {
            throw JMSExceptionSupport.create(ex);
        }
    }

    public void writeLong(long value) throws JMSException {
        initializeWriting();
        try {
            preWriteProcess(LONG_TYPE);
            this.dataOut.writeLong(value);
        } catch (IOException ex) {
            throw JMSExceptionSupport.create(ex);
        }
    }

    public void writeFloat(float value) throws JMSException {
        initializeWriting();
        try {
            preWriteProcess(FLOAT_TYPE);
            this.dataOut.writeFloat(value);
        } catch (IOException ex) {
            throw JMSExceptionSupport.create(ex);
        }
    }

    public void writeDouble(double value) throws JMSException {
        initializeWriting();

        try {
            preWriteProcess(DOUBLE_TYPE);
            dataOut.writeDouble(value);
        } catch (IOException ex) {
            throw JMSExceptionSupport.create(ex);
        }
    }

    public void writeUTF(String value) throws JMSException {
        initializeWriting();
        try {
            preWriteProcess(STRING_TYPE);
            this.dataOut.writeUTF(value);
        } catch (IOException ex) {
            throw JMSExceptionSupport.create(ex);
        }
    }

    public void writeBytes(byte[] value) throws JMSException {
        initializeWriting();
        try {
            preWriteProcess(BYTE_ARRAY_TYPE);
            dataOut.writeInt(value.length);
            this.dataOut.write(value);
        } catch (IOException ex) {
            throw JMSExceptionSupport.create(ex);
        }
    }

    public void writeBytes(byte[] value, int offset, int length) throws JMSException {
        initializeWriting();
        try {
            preWriteProcess(BYTE_ARRAY_TYPE);
            dataOut.writeInt(length);
            this.dataOut.write(value, offset, length);
        } catch (IOException ex) {
            throw JMSExceptionSupport.create(ex);
        }
    }

    public void writeObject(Object value) throws JMSException {
        if (value == null) {
            throw new NullPointerException();
        }
        initializeWriting();
        if (value instanceof Boolean) {
            writeBoolean(((Boolean)value).booleanValue());
        } else if (value instanceof Character) {
            writeChar(((Character)value).charValue());
        } else if (value instanceof Byte) {
            writeByte(((Byte)value).byteValue());
        } else if (value instanceof Short) {
            writeShort(((Short)value).shortValue());
        } else if (value instanceof Integer) {
            writeInt(((Integer)value).intValue());
        } else if (value instanceof Long) {
            writeLong(((Long)value).longValue());
        } else if (value instanceof Float) {
            writeFloat(((Float)value).floatValue());
        } else if (value instanceof Double) {
            writeDouble(((Double)value).doubleValue());
        } else if (value instanceof String) {
            writeUTF(value.toString());
        } else if (value instanceof byte[]) {
            writeBytes((byte[])value);
        } else {
            throw new MessageFormatException("Cannot write non-primitive type:" + value.getClass());
        }
    }

    public void reset() throws JMSException {
        storeContent();
        this.bytesOut = null;
        if (dataIn != null) {
            try {
                dataIn.close();
            } catch (Exception e) {
            }
        }
        this.dataIn = null;
        this.dataOut = null;
        setReadOnly(true);
    }

    protected void initializeWriting() throws JMSException {
        if (readOnly) {
            throw new MessageNotWriteableException("Message body is read-only");
        }

        if (this.dataOut == null) {
            this.bytesOut = new ByteArrayOutputStream();
            OutputStream os = bytesOut;

            this.dataOut = new DataOutputStream(os);
        }
    }

    protected void initializeReading() throws JMSException {
        if (!readOnly) {
            throw new MessageNotReadableException("Message body is write-only");
        }

        if (dataIn == null) {
            InputStream is = new ByteArrayInputStream(content);
            dataIn = new DataInputStream(is);
        }
    }

    protected abstract void preWriteProcess(Byte valueType) throws IOException;

    @Override
    public void clearBody() throws JMSException {
        super.clearBody();
        content = null;
        this.dataOut = null;
        this.dataIn = null;
        this.bytesOut = null;
        this.remainingBytes = -1;
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

    public void storeContent() {
        if (dataOut != null) {
            try {
                dataOut.close();
                setContent(bytesOut.toByteArray());
                bytesOut = null;
                dataOut = null;
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    protected abstract void setMessageTypeProperty(Message message);
    protected abstract int getType() throws IOException ;
}

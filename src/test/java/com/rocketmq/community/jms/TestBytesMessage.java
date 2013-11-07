package com.rocketmq.community.jms;

import com.rocketmq.community.jms.message.BytesMessageImpl;
import org.junit.Assert;
import org.junit.Test;

import javax.jms.JMSException;
import javax.jms.MessageFormatException;
import javax.jms.MessageNotReadableException;
import javax.jms.MessageNotWriteableException;

public class TestBytesMessage {
    @Test
    public void testGetBodyLength() {
        BytesMessageImpl msg = new BytesMessageImpl();
        int len = 10;
        try {
            for (int i = 0; i < len; i++) {
                msg.writeLong(5L);
            }
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
        try {
            msg.reset();
            Assert.assertTrue(msg.getBodyLength() == (len * 8));
        } catch (Throwable e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testReadBoolean() {
        BytesMessageImpl msg = new BytesMessageImpl();
        try {
            msg.writeBoolean(true);
            msg.reset();
            Assert.assertTrue(msg.readBoolean());
        } catch (JMSException jmsEx) {
            jmsEx.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testReadByte() {
        BytesMessageImpl msg = new BytesMessageImpl();
        try {
            msg.writeByte((byte) 2);
            msg.reset();
            Assert.assertTrue(msg.readByte() == 2);
        } catch (JMSException jmsEx) {
            jmsEx.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testReadUnsignedByte() {
        BytesMessageImpl msg = new BytesMessageImpl();
        try {
            msg.writeByte((byte) 2);
            msg.reset();
            Assert.assertTrue(msg.readUnsignedByte() == 2);
        } catch (JMSException jmsEx) {
            jmsEx.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testReadShort() {
        BytesMessageImpl msg = new BytesMessageImpl();
        try {
            msg.writeShort((short) 3000);
            msg.reset();
            Assert.assertTrue(msg.readShort() == 3000);
        } catch (JMSException jmsEx) {
            jmsEx.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testReadUnsignedShort() {
        BytesMessageImpl msg = new BytesMessageImpl();
        try {
            msg.writeShort((short) 3000);
            msg.reset();
            Assert.assertTrue(msg.readUnsignedShort() == 3000);
        } catch (JMSException jmsEx) {
            jmsEx.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testReadChar() {
        BytesMessageImpl msg = new BytesMessageImpl();
        try {
            msg.writeChar('a');
            msg.reset();
            Assert.assertTrue(msg.readChar() == 'a');
        } catch (JMSException jmsEx) {
            jmsEx.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testReadInt() {
        BytesMessageImpl msg = new BytesMessageImpl();
        try {
            msg.writeInt(3000);
            msg.reset();
            Assert.assertTrue(msg.readInt() == 3000);
        } catch (JMSException jmsEx) {
            jmsEx.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testReadLong() {
        BytesMessageImpl msg = new BytesMessageImpl();
        try {
            msg.writeLong(3000);
            msg.reset();
            Assert.assertTrue(msg.readLong() == 3000);
        } catch (JMSException jmsEx) {
            jmsEx.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testReadFloat() {
        BytesMessageImpl msg = new BytesMessageImpl();
        try {
            msg.writeFloat(3.3f);
            msg.reset();
            Assert.assertTrue(msg.readFloat() == 3.3f);
        } catch (JMSException jmsEx) {
            jmsEx.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testReadDouble() {
        BytesMessageImpl msg = new BytesMessageImpl();
        try {
            msg.writeDouble(3.3d);
            msg.reset();
            Assert.assertTrue(msg.readDouble() == 3.3d);
        } catch (JMSException jmsEx) {
            jmsEx.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testReadUTF() {
        BytesMessageImpl msg = new BytesMessageImpl();
        try {
            String str = "this is a test";
            msg.writeUTF(str);
            msg.reset();
            Assert.assertTrue(msg.readUTF().equals(str));
        } catch (JMSException jmsEx) {
            jmsEx.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testReadBytesbyteArray() {
        BytesMessageImpl msg = new BytesMessageImpl();
        try {
            byte[] data = new byte[50];
            for (int i = 0; i < data.length; i++) {
                data[i] = (byte) i;
            }
            msg.writeBytes(data);
            msg.reset();
            byte[] test = new byte[data.length];
            msg.readBytes(test);
            for (int i = 0; i < test.length; i++) {
                Assert.assertTrue(test[i] == i);
            }
        } catch (JMSException jmsEx) {
            jmsEx.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testWriteObject() throws JMSException {
        BytesMessageImpl msg = new BytesMessageImpl();
        try {
            msg.writeObject("fred");
            msg.writeObject(Boolean.TRUE);
            msg.writeObject(Character.valueOf('q'));
            msg.writeObject(Byte.valueOf((byte) 1));
            msg.writeObject(Short.valueOf((short) 3));
            msg.writeObject(Integer.valueOf(3));
            msg.writeObject(Long.valueOf(300L));
            msg.writeObject(new Float(3.3f));
            msg.writeObject(new Double(3.3));
            msg.writeObject(new byte[3]);
        } catch (MessageFormatException mfe) {
            Assert.fail("objectified primitives should be allowed");
        }
        try {
            msg.writeObject(new Object());
            Assert.fail("only objectified primitives are allowed");
        } catch (MessageFormatException mfe) {
        }
    }


    @Test
    public void testClearBody() throws JMSException {
        BytesMessageImpl bytesMessage = new BytesMessageImpl();
        try {
            bytesMessage.writeInt(1);
            bytesMessage.clearBody();
            Assert.assertFalse(bytesMessage.isReadOnly());
            bytesMessage.writeInt(1);
            bytesMessage.readInt();
        } catch (MessageNotReadableException mnwe) {
        } catch (MessageNotWriteableException mnwe) {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testReset() throws JMSException {
        BytesMessageImpl message = new BytesMessageImpl();
        try {
            message.writeDouble(24.5);
            message.writeLong(311);
        } catch (MessageNotWriteableException mnwe) {
            Assert.fail("should be writeable");
        }
        message.reset();
        try {
            Assert.assertTrue(message.isReadOnly());
            Assert.assertEquals(message.readDouble(), 24.5, 0);
            Assert.assertEquals(message.readLong(), 311);
        } catch (MessageNotReadableException mnre) {
            Assert.fail("should be readable");
        }
        try {
            message.writeInt(33);
            Assert.fail("should throw exception");
        } catch (MessageNotWriteableException mnwe) {
        }
    }

    @Test
    public void testReadOnlyBody() throws JMSException {
        BytesMessageImpl message = new BytesMessageImpl();
        try {
            message.writeBoolean(true);
            message.writeByte((byte) 1);
            message.writeByte((byte) 1);
            message.writeBytes(new byte[1]);
            message.writeBytes(new byte[3], 0, 2);
            message.writeChar('a');
            message.writeDouble(1.5);
            message.writeFloat((float) 1.5);
            message.writeInt(1);
            message.writeLong(1);
            message.writeObject("stringobj");
            message.writeShort((short) 1);
            message.writeShort((short) 1);
            message.writeUTF("utfstring");
        } catch (MessageNotWriteableException mnwe) {
            Assert.fail("Should be writeable");
        }
        message.reset();
        try {
            message.readBoolean();
            message.readByte();
            message.readUnsignedByte();
            message.readBytes(new byte[1]);
            message.readBytes(new byte[2], 2);
            message.readChar();
            message.readDouble();
            message.readFloat();
            message.readInt();
            message.readLong();
            message.readUTF();
            message.readShort();
            message.readUnsignedShort();
            message.readUTF();
        } catch (MessageNotReadableException mnwe) {
            Assert.fail("Should be readable");
        }
        try {
            message.writeBoolean(true);
            Assert.fail("Should have thrown exception");
        } catch (MessageNotWriteableException mnwe) {
        }
        try {
            message.writeByte((byte) 1);
            Assert.fail("Should have thrown exception");
        } catch (MessageNotWriteableException mnwe) {
        }
        try {
            message.writeBytes(new byte[1]);
            Assert.fail("Should have thrown exception");
        } catch (MessageNotWriteableException mnwe) {
        }
        try {
            message.writeBytes(new byte[3], 0, 2);
            Assert.fail("Should have thrown exception");
        } catch (MessageNotWriteableException mnwe) {
        }
        try {
            message.writeChar('a');
            Assert.fail("Should have thrown exception");
        } catch (MessageNotWriteableException mnwe) {
        }
        try {
            message.writeDouble(1.5);
            Assert.fail("Should have thrown exception");
        } catch (MessageNotWriteableException mnwe) {
        }
        try {
            message.writeFloat((float) 1.5);
            Assert.fail("Should have thrown exception");
        } catch (MessageNotWriteableException mnwe) {
        }
        try {
            message.writeInt(1);
            Assert.fail("Should have thrown exception");
        } catch (MessageNotWriteableException mnwe) {
        }
        try {
            message.writeLong(1);
            Assert.fail("Should have thrown exception");
        } catch (MessageNotWriteableException mnwe) {
        }
        try {
            message.writeObject("stringobj");
            Assert.fail("Should have thrown exception");
        } catch (MessageNotWriteableException mnwe) {
        }
        try {
            message.writeShort((short) 1);
            Assert.fail("Should have thrown exception");
        } catch (MessageNotWriteableException mnwe) {
        }
        try {
            message.writeUTF("utfstring");
            Assert.fail("Should have thrown exception");
        } catch (MessageNotWriteableException mnwe) {
        }
    }

    @Test
    public void testWriteOnlyBody() throws JMSException {
        BytesMessageImpl message = new BytesMessageImpl();
        message.clearBody();
        try {
            message.writeBoolean(true);
            message.writeByte((byte) 1);
            message.writeByte((byte) 1);
            message.writeBytes(new byte[1]);
            message.writeBytes(new byte[3], 0, 2);
            message.writeChar('a');
            message.writeDouble(1.5);
            message.writeFloat((float) 1.5);
            message.writeInt(1);
            message.writeLong(1);
            message.writeObject("stringobj");
            message.writeShort((short) 1);
            message.writeShort((short) 1);
            message.writeUTF("utfstring");
        } catch (MessageNotWriteableException mnwe) {
            Assert.fail("Should be writeable");
        }
        try {
            message.readBoolean();
            Assert.fail("Should have thrown exception");
        } catch (MessageNotReadableException mnwe) {
        }
        try {
            message.readByte();
            Assert.fail("Should have thrown exception");
        } catch (MessageNotReadableException e) {
        }
        try {
            message.readUnsignedByte();
            Assert.fail("Should have thrown exception");
        } catch (MessageNotReadableException e) {
        }
        try {
            message.readBytes(new byte[1]);
            Assert.fail("Should have thrown exception");
        } catch (MessageNotReadableException e) {
        }
        try {
            message.readBytes(new byte[2], 2);
            Assert.fail("Should have thrown exception");
        } catch (MessageNotReadableException e) {
        }
        try {
            message.readChar();
            Assert.fail("Should have thrown exception");
        } catch (MessageNotReadableException e) {
        }
        try {
            message.readDouble();
            Assert.fail("Should have thrown exception");
        } catch (MessageNotReadableException e) {
        }
        try {
            message.readFloat();
            Assert.fail("Should have thrown exception");
        } catch (MessageNotReadableException e) {
        }
        try {
            message.readInt();
            Assert.fail("Should have thrown exception");
        } catch (MessageNotReadableException e) {
        }
        try {
            message.readLong();
            Assert.fail("Should have thrown exception");
        } catch (MessageNotReadableException e) {
        }
        try {
            message.readUTF();
            Assert.fail("Should have thrown exception");
        } catch (MessageNotReadableException e) {
        }
        try {
            message.readShort();
            Assert.fail("Should have thrown exception");
        } catch (MessageNotReadableException e) {
        }
        try {
            message.readUnsignedShort();
            Assert.fail("Should have thrown exception");
        } catch (MessageNotReadableException e) {
        }
        try {
            message.readUTF();
            Assert.fail("Should have thrown exception");
        } catch (MessageNotReadableException e) {
        }
    }
}

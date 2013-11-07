package com.rocketmq.community.jms;

import com.rocketmq.community.jms.message.StreamMessageImpl;
import org.junit.Assert;
import org.junit.Test;

import javax.jms.JMSException;
import javax.jms.MessageFormatException;
import javax.jms.MessageNotReadableException;
import javax.jms.MessageNotWriteableException;

public class TestStreamMessage {
    @Test
    public void testReadBoolean() {
        StreamMessageImpl msg = new StreamMessageImpl();
        try {
            msg.writeBoolean(true);
            msg.reset();
            Assert.assertTrue(msg.readBoolean());
            msg.reset();
            Assert.assertTrue(msg.readString().equals("true"));
            msg.reset();
            try {
                msg.readByte();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readShort();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readInt();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readLong();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readFloat();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readDouble();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readChar();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readBytes(new byte[1]);
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
        } catch (JMSException jmsEx) {
            jmsEx.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testreadByte() {
        StreamMessageImpl msg = new StreamMessageImpl();
        try {
            byte test = (byte)4;
            msg.writeByte(test);
            msg.reset();
            Assert.assertTrue(msg.readByte() == test);
            msg.reset();
            Assert.assertTrue(msg.readShort() == test);
            msg.reset();
            Assert.assertTrue(msg.readInt() == test);
            msg.reset();
            Assert.assertTrue(msg.readLong() == test);
            msg.reset();
            Assert.assertTrue(msg.readString().equals(new Byte(test).toString()));
            msg.reset();
            try {
                msg.readBoolean();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readFloat();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readDouble();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readChar();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readBytes(new byte[1]);
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
        } catch (JMSException jmsEx) {
            jmsEx.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testReadShort() {
        StreamMessageImpl msg = new StreamMessageImpl();
        try {
            short test = (short)4;
            msg.writeShort(test);
            msg.reset();
            Assert.assertTrue(msg.readShort() == test);
            msg.reset();
            Assert.assertTrue(msg.readInt() == test);
            msg.reset();
            Assert.assertTrue(msg.readLong() == test);
            msg.reset();
            Assert.assertTrue(msg.readString().equals(new Short(test).toString()));
            msg.reset();
            try {
                msg.readBoolean();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readByte();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readFloat();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readDouble();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readChar();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readBytes(new byte[1]);
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
        } catch (JMSException jmsEx) {
            jmsEx.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testReadChar() {
        StreamMessageImpl msg = new StreamMessageImpl();
        try {
            char test = 'z';
            msg.writeChar(test);
            msg.reset();
            Assert.assertTrue(msg.readChar() == test);
            msg.reset();
            Assert.assertTrue(msg.readString().equals(new Character(test).toString()));
            msg.reset();
            try {
                msg.readBoolean();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readByte();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readShort();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readInt();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readLong();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readFloat();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readDouble();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readBytes(new byte[1]);
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
        } catch (JMSException jmsEx) {
            jmsEx.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testReadInt() {
        StreamMessageImpl msg = new StreamMessageImpl();
        try {
            int test = 4;
            msg.writeInt(test);
            msg.reset();
            Assert.assertTrue(msg.readInt() == test);
            msg.reset();
            Assert.assertTrue(msg.readLong() == test);
            msg.reset();
            Assert.assertTrue(msg.readString().equals(new Integer(test).toString()));
            msg.reset();
            try {
                msg.readBoolean();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readByte();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readShort();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readFloat();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readDouble();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readChar();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readBytes(new byte[1]);
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
        } catch (JMSException jmsEx) {
            jmsEx.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testReadLong() {
        StreamMessageImpl msg = new StreamMessageImpl();
        try {
            long test = 4L;
            msg.writeLong(test);
            msg.reset();
            Assert.assertTrue(msg.readLong() == test);
            msg.reset();
            Assert.assertTrue(msg.readString().equals(Long.valueOf(test).toString()));
            msg.reset();
            try {
                msg.readBoolean();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readByte();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readShort();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readInt();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readFloat();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readDouble();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readChar();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readBytes(new byte[1]);
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg = new StreamMessageImpl();
            msg.writeObject(new Long("1"));
            // reset so it's readable now
            msg.reset();
            Assert.assertEquals(new Long("1"), msg.readObject());
        } catch (JMSException jmsEx) {
            jmsEx.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testReadFloat() {
        StreamMessageImpl msg = new StreamMessageImpl();
        try {
            float test = 4.4f;
            msg.writeFloat(test);
            msg.reset();
            Assert.assertTrue(msg.readFloat() == test);
            msg.reset();
            Assert.assertTrue(msg.readDouble() == test);
            msg.reset();
            Assert.assertTrue(msg.readString().equals(new Float(test).toString()));
            msg.reset();
            try {
                msg.readBoolean();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readByte();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readShort();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readInt();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readLong();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readChar();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readBytes(new byte[1]);
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
        } catch (JMSException jmsEx) {
            jmsEx.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testReadDouble()  {
        StreamMessageImpl msg = new StreamMessageImpl();
        try {
            double test = 4.4d;
            msg.writeDouble(test);
            msg.reset();
            Assert.assertTrue(msg.readDouble() == test);
            msg.reset();
            Assert.assertTrue(msg.readString().equals(new Double(test).toString()));
            msg.reset();
            try {
                msg.readBoolean();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readByte();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readShort();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readInt();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readLong();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readFloat();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readChar();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readBytes(new byte[1]);
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
        } catch (JMSException jmsEx) {
            jmsEx.printStackTrace();
            Assert.assertTrue(false);
        }

    }

    @Test
    public void testReadString() {
        StreamMessageImpl msg = new StreamMessageImpl();
        try {
            byte testByte = (byte)2;
            msg.writeString(new Byte(testByte).toString());
            msg.reset();
            Assert.assertTrue(msg.readByte() == testByte);
            msg.clearBody();
            short testShort = 3;
            msg.writeString(new Short(testShort).toString());
            msg.reset();
            Assert.assertTrue(msg.readShort() == testShort);
            msg.clearBody();
            int testInt = 4;
            msg.writeString(new Integer(testInt).toString());
            msg.reset();
            Assert.assertTrue(msg.readInt() == testInt);
            msg.clearBody();
            long testLong = 6L;
            msg.writeString(new Long(testLong).toString());
            msg.reset();
            Assert.assertTrue(msg.readLong() == testLong);
            msg.clearBody();
            float testFloat = 6.6f;
            msg.writeString(new Float(testFloat).toString());
            msg.reset();
            Assert.assertTrue(msg.readFloat() == testFloat);
            msg.clearBody();
            double testDouble = 7.7d;
            msg.writeString(new Double(testDouble).toString());
            msg.reset();
            Assert.assertTrue(msg.readDouble() == testDouble);
            msg.clearBody();
            msg.writeString("true");
            msg.reset();
            Assert.assertTrue(msg.readBoolean());
            msg.clearBody();
            msg.writeString("a");
            msg.reset();
            try {
                msg.readChar();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException e) {
            }
            msg.clearBody();
            msg.writeString("777");
            msg.reset();
            try {
                msg.readBytes(new byte[3]);
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException e) {
            }

        } catch (JMSException jmsEx) {
            jmsEx.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testReadBigString() {
        StreamMessageImpl msg = new StreamMessageImpl();
        try {
            // Test with a 1Meg String
            StringBuffer bigSB = new StringBuffer(1024 * 1024);
            for (int i = 0; i < 1024 * 1024; i++) {
                bigSB.append((char)'a' + i % 26);
            }
            String bigString = bigSB.toString();

            msg.writeString(bigString);
            msg.reset();
            Assert.assertEquals(bigString, msg.readString());

        } catch (JMSException jmsEx) {
            jmsEx.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testReadBytes() {
        StreamMessageImpl msg = new StreamMessageImpl();
        try {
            byte[] test = new byte[50];
            for (int i = 0; i < test.length; i++) {
                test[i] = (byte)i;
            }
            msg.writeBytes(test);
            msg.reset();
            byte[] valid = new byte[test.length];
            msg.readBytes(valid);
            for (int i = 0; i < valid.length; i++) {
                Assert.assertTrue(valid[i] == test[i]);
            }
            msg.reset();
            try {
                msg.readByte();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readShort();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readInt();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readLong();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readFloat();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readChar();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
            msg.reset();
            try {
                msg.readString();
                Assert.fail("Should have thrown exception");
            } catch (MessageFormatException mfe) {
            }
        } catch (JMSException jmsEx) {
            jmsEx.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testReadObject() {
        StreamMessageImpl msg = new StreamMessageImpl();
        try {
            byte testByte = (byte)2;
            msg.writeByte(testByte);
            msg.reset();
            Assert.assertTrue(((Byte)msg.readObject()).byteValue() == testByte);
            msg.clearBody();

            short testShort = 3;
            msg.writeShort(testShort);
            msg.reset();
            Assert.assertTrue(((Short)msg.readObject()).shortValue() == testShort);
            msg.clearBody();

            int testInt = 4;
            msg.writeInt(testInt);
            msg.reset();
            Assert.assertTrue(((Integer)msg.readObject()).intValue() == testInt);
            msg.clearBody();

            long testLong = 6L;
            msg.writeLong(testLong);
            msg.reset();
            Assert.assertTrue(((Long)msg.readObject()).longValue() == testLong);
            msg.clearBody();

            float testFloat = 6.6f;
            msg.writeFloat(testFloat);
            msg.reset();
            Assert.assertTrue(((Float)msg.readObject()).floatValue() == testFloat);
            msg.clearBody();

            double testDouble = 7.7d;
            msg.writeDouble(testDouble);
            msg.reset();
            Assert.assertTrue(((Double)msg.readObject()).doubleValue() == testDouble);
            msg.clearBody();

            char testChar = 'z';
            msg.writeChar(testChar);
            msg.reset();
            Assert.assertTrue(((Character)msg.readObject()).charValue() == testChar);
            msg.clearBody();

            byte[] data = new byte[50];
            for (int i = 0; i < data.length; i++) {
                data[i] = (byte)i;
            }
            msg.writeBytes(data);
            msg.reset();
            byte[] valid = (byte[])msg.readObject();
            Assert.assertTrue(valid.length == data.length);
            for (int i = 0; i < valid.length; i++) {
                Assert.assertTrue(valid[i] == data[i]);
            }
            msg.clearBody();
            msg.writeBoolean(true);
            msg.reset();
            Assert.assertTrue(((Boolean)msg.readObject()).booleanValue());

        } catch (JMSException jmsEx) {
            jmsEx.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testClearBody() throws JMSException {
        StreamMessageImpl streamMessage = new StreamMessageImpl();
        try {
            streamMessage.writeObject(new Long(2));
            streamMessage.clearBody();
            Assert.assertFalse(streamMessage.isReadOnly());
            streamMessage.writeObject(new Long(2));
            streamMessage.readObject();
            Assert.fail("should throw exception");
        } catch (MessageNotReadableException mnwe) {
        } catch (MessageNotWriteableException mnwe) {
            Assert.fail("should be writeable");
        }
    }

    @Test
    public void testReset() throws JMSException {
        StreamMessageImpl streamMessage = new StreamMessageImpl();
        try {
            streamMessage.writeDouble(24.5);
            streamMessage.writeLong(311);
        } catch (MessageNotWriteableException mnwe) {
            Assert.fail("should be writeable");
        }
        streamMessage.reset();
        try {
            Assert.assertTrue(streamMessage.isReadOnly());
            Assert.assertEquals(streamMessage.readDouble(), 24.5, 0);
            Assert.assertEquals(streamMessage.readLong(), 311);
        } catch (MessageNotReadableException mnre) {
            Assert.fail("should be readable");
        }
        try {
            streamMessage.writeInt(33);
            Assert.fail("should throw exception");
        } catch (MessageNotWriteableException mnwe) {
        }
    }

    @Test
    public void testReadOnly() throws JMSException {
        StreamMessageImpl message = new StreamMessageImpl();
        try {
            message.writeBoolean(true);
            message.writeByte((byte)1);
            message.writeBytes(new byte[1]);
            message.writeBytes(new byte[3], 0, 2);
            message.writeChar('a');
            message.writeDouble(1.5);
            message.writeFloat((float)1.5);
            message.writeInt(1);
            message.writeLong(1);
            message.writeObject("stringobj");
            message.writeShort((short)1);
            message.writeString("string");
        } catch (MessageNotWriteableException mnwe) {
            Assert.fail("Should be writeable");
        }
        message.reset();
        try {
            message.readBoolean();
            message.readByte();
            Assert.assertEquals(1, message.readBytes(new byte[10]));
            Assert.assertEquals(-1, message.readBytes(new byte[10]));
            Assert.assertEquals(2, message.readBytes(new byte[10]));
            Assert.assertEquals(-1, message.readBytes(new byte[10]));
            message.readChar();
            message.readDouble();
            message.readFloat();
            message.readInt();
            message.readLong();
            message.readString();
            message.readShort();
            message.readString();
        } catch (MessageNotReadableException mnwe) {
            Assert.fail("Should be readable");
        }
        try {
            message.writeBoolean(true);
            Assert.fail("Should have thrown exception");
        } catch (MessageNotWriteableException mnwe) {
        }
        try {
            message.writeByte((byte)1);
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
            message.writeFloat((float)1.5);
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
            message.writeShort((short)1);
            Assert.fail("Should have thrown exception");
        } catch (MessageNotWriteableException mnwe) {
        }
        try {
            message.writeString("string");
            Assert.fail("Should have thrown exception");
        } catch (MessageNotWriteableException mnwe) {
        }
    }

    @Test
    public void testWriteOnly() throws JMSException {
        StreamMessageImpl message = new StreamMessageImpl();
        message.clearBody();
        try {
            message.writeBoolean(true);
            message.writeByte((byte)1);
            message.writeBytes(new byte[1]);
            message.writeBytes(new byte[3], 0, 2);
            message.writeChar('a');
            message.writeDouble(1.5);
            message.writeFloat((float)1.5);
            message.writeInt(1);
            message.writeLong(1);
            message.writeObject("stringobj");
            message.writeShort((short)1);
            message.writeString("string");
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
            message.readBytes(new byte[1]);
            Assert.fail("Should have thrown exception");
        } catch (MessageNotReadableException e) {
        }
        try {
            message.readBytes(new byte[2]);
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
            message.readString();
            Assert.fail("Should have thrown exception");
        } catch (MessageNotReadableException e) {
        }
        try {
            message.readShort();
            Assert.fail("Should have thrown exception");
        } catch (MessageNotReadableException e) {
        }
        try {
            message.readString();
            Assert.fail("Should have thrown exception");
        } catch (MessageNotReadableException e) {
        }
    }

    @Test
    public void testWriteObject() {
        try {
            StreamMessageImpl message = new StreamMessageImpl();
            message.clearBody();
            message.writeObject("test");
            message.writeObject(new Character('a'));
            message.writeObject(new Boolean(false));
            message.writeObject(new Byte((byte) 2));
            message.writeObject(new Short((short) 2));
            message.writeObject(new Integer(2));
            message.writeObject(new Long(2l));
            message.writeObject(new Float(2.0f));
            message.writeObject(new Double(2.0d));
        }catch(Exception e) {
            Assert.fail(e.getMessage());
        }
        try {
            StreamMessageImpl message = new StreamMessageImpl();
            message.clearBody();
            message.writeObject(new Object());
            Assert.fail("should throw an exception");
        }catch(MessageFormatException e) {

        }catch(Exception e) {
            Assert.fail(e.getMessage());
        }
    }
}

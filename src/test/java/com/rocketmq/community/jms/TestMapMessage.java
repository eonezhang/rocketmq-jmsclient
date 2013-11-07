package com.rocketmq.community.jms;

import com.rocketmq.community.jms.message.MapMessageImpl;
import org.junit.Assert;
import org.junit.Test;

import javax.jms.JMSException;
import javax.jms.MessageFormatException;
import javax.jms.MessageNotReadableException;
import javax.jms.MessageNotWriteableException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class TestMapMessage {
    private String name = "testName";

    @Test
    public void testBytesConversion() throws JMSException, IOException {
        MapMessageImpl msg = new MapMessageImpl();
        msg.setBoolean("boolean", true);
        msg.setByte("byte", (byte)1);
        msg.setBytes("bytes", new byte[] {10, 20, 30, 40, 50, 60});
        msg.setChar("char", 'a');
        msg.setDouble("double", 1.5);
        msg.setFloat("float", 1.5f);
        msg.setInt("int", 1);
        msg.setLong("long", 1);
        msg.setObject("object", "stringObj");
        msg.setShort("short", (short)1);
        msg.setString("string", "string");

        // Test with a 1Meg String
        StringBuffer bigSB = new StringBuffer(1024 * 1024);
        for (int i = 0; i < 1024 * 1024; i++) {
            bigSB.append((char)'a' + i % 26);
        }
        String bigString = bigSB.toString();

        msg.setString("bigString", bigString);

        msg = (MapMessageImpl)msg.copy();

        Assert.assertEquals(msg.getBoolean("boolean"), true);
        Assert.assertEquals(msg.getByte("byte"), (byte)1);
        Assert.assertEquals(msg.getBytes("bytes").length, 6);
        Assert.assertEquals(msg.getChar("char"), 'a');
        Assert.assertEquals(msg.getDouble("double"), 1.5, 0);
        Assert.assertEquals(msg.getFloat("float"), 1.5f, 0);
        Assert.assertEquals(msg.getInt("int"), 1);
        Assert.assertEquals(msg.getLong("long"), 1);
        Assert.assertEquals(msg.getObject("object"), "stringObj");
        Assert.assertEquals(msg.getShort("short"), (short)1);
        Assert.assertEquals(msg.getString("string"), "string");
        Assert.assertEquals(msg.getString("bigString"), bigString);
    }

    @Test
    public void testGetBoolean() throws JMSException {
        MapMessageImpl msg = new MapMessageImpl();
        msg.setBoolean(name, true);
        msg.setReadOnly(true);
        Assert.assertTrue(msg.getBoolean(name));
        msg.clearBody();
        msg.setString(name, "true");

        msg = (MapMessageImpl)msg.copy();

        Assert.assertTrue(msg.getBoolean(name));
    }

    @Test
    public void testGetByte() throws JMSException {
        MapMessageImpl msg = new MapMessageImpl();
        msg.setByte(this.name, (byte)1);
        msg = (MapMessageImpl)msg.copy();
        Assert.assertTrue(msg.getByte(this.name) == (byte)1);
    }

    @Test
    public void testGetShort() {
        MapMessageImpl msg = new MapMessageImpl();
        try {
            msg.setShort(this.name, (short)1);
            msg = (MapMessageImpl)msg.copy();
            Assert.assertTrue(msg.getShort(this.name) == (short)1);
        } catch (JMSException jmsEx) {
            jmsEx.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testGetChar() {
        MapMessageImpl msg = new MapMessageImpl();
        try {
            msg.setChar(this.name, 'a');
            msg = (MapMessageImpl)msg.copy();
            Assert.assertTrue(msg.getChar(this.name) == 'a');
        } catch (JMSException jmsEx) {
            jmsEx.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testGetInt() {
        MapMessageImpl msg = new MapMessageImpl();
        try {
            msg.setInt(this.name, 1);
            msg = (MapMessageImpl)msg.copy();
            Assert.assertTrue(msg.getInt(this.name) == 1);
        } catch (JMSException jmsEx) {
            jmsEx.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testGetLong() {
        MapMessageImpl msg = new MapMessageImpl();
        try {
            msg.setLong(this.name, 1);
            msg = (MapMessageImpl)msg.copy();
            Assert.assertTrue(msg.getLong(this.name) == 1);
        } catch (JMSException jmsEx) {
            jmsEx.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testGetFloat() {
        MapMessageImpl msg = new MapMessageImpl();
        try {
            msg.setFloat(this.name, 1.5f);
            msg = (MapMessageImpl)msg.copy();
            Assert.assertTrue(msg.getFloat(this.name) == 1.5f);
        } catch (JMSException jmsEx) {
            jmsEx.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testGetDouble() {
        MapMessageImpl msg = new MapMessageImpl();
        try {
            msg.setDouble(this.name, 1.5);
            msg = (MapMessageImpl)msg.copy();
            Assert.assertTrue(msg.getDouble(this.name) == 1.5);
        } catch (JMSException jmsEx) {
            jmsEx.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testGetString() {
        MapMessageImpl msg = new MapMessageImpl();
        try {
            String str = "test";
            msg.setString(this.name, str);
            msg = (MapMessageImpl)msg.copy();
            Assert.assertEquals(msg.getString(this.name), str);
        } catch (JMSException jmsEx) {
            jmsEx.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testGetBytes() {
        MapMessageImpl msg = new MapMessageImpl();
        try {
            byte[] bytes1 = new byte[3];
            byte[] bytes2 = new byte[2];
            System.arraycopy(bytes1, 0, bytes2, 0, 2);
            msg.setBytes(this.name, bytes1);
            msg.setBytes(this.name + "2", bytes1, 0, 2);
            msg = (MapMessageImpl)msg.copy();
            Assert.assertTrue(Arrays.equals(msg.getBytes(this.name), bytes1));
            Assert.assertEquals(msg.getBytes(this.name + "2").length, bytes2.length);
        } catch (JMSException jmsEx) {
            jmsEx.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testGetObject() throws JMSException {
        MapMessageImpl msg = new MapMessageImpl();
        Boolean booleanValue = Boolean.TRUE;
        Byte byteValue = Byte.valueOf("1");
        byte[] bytesValue = new byte[3];
        Character charValue = new Character('a');
        Double doubleValue = Double.valueOf("1.5");
        Float floatValue = Float.valueOf("1.5");
        Integer intValue = Integer.valueOf("1");
        Long longValue = Long.valueOf("1");
        Short shortValue = Short.valueOf("1");
        String stringValue = "string";

        try {
            msg.setObject("boolean", booleanValue);
            msg.setObject("byte", byteValue);
            msg.setObject("bytes", bytesValue);
            msg.setObject("char", charValue);
            msg.setObject("double", doubleValue);
            msg.setObject("float", floatValue);
            msg.setObject("int", intValue);
            msg.setObject("long", longValue);
            msg.setObject("short", shortValue);
            msg.setObject("string", stringValue);
        } catch (MessageFormatException mfe) {
            mfe.printStackTrace();
            Assert.fail("object formats should be correct");
        }

        msg = (MapMessageImpl)msg.copy();

        Assert.assertTrue(msg.getObject("boolean") instanceof Boolean);
        Assert.assertEquals(msg.getObject("boolean"), booleanValue);
        Assert.assertEquals(msg.getBoolean("boolean"), booleanValue.booleanValue());
        Assert.assertTrue(msg.getObject("byte") instanceof Byte);
        Assert.assertEquals(msg.getObject("byte"), byteValue);
        Assert.assertEquals(msg.getByte("byte"), byteValue.byteValue());
        Assert.assertTrue(msg.getObject("bytes") instanceof byte[]);
        Assert.assertEquals(((byte[])msg.getObject("bytes")).length, bytesValue.length);
        Assert.assertEquals(msg.getBytes("bytes").length, bytesValue.length);
        Assert.assertTrue(msg.getObject("char") instanceof Character);
        Assert.assertEquals(msg.getObject("char"), charValue);
        Assert.assertEquals(msg.getChar("char"), charValue.charValue());
        Assert.assertTrue(msg.getObject("double") instanceof Double);
        Assert.assertEquals(msg.getObject("double"), doubleValue);
        Assert.assertEquals(msg.getDouble("double"), doubleValue.doubleValue(), 0);
        Assert.assertTrue(msg.getObject("float") instanceof Float);
        Assert.assertEquals(msg.getObject("float"), floatValue);
        Assert.assertEquals(msg.getFloat("float"), floatValue.floatValue(), 0);
        Assert.assertTrue(msg.getObject("int") instanceof Integer);
        Assert.assertEquals(msg.getObject("int"), intValue);
        Assert.assertEquals(msg.getInt("int"), intValue.intValue());
        Assert.assertTrue(msg.getObject("long") instanceof Long);
        Assert.assertEquals(msg.getObject("long"), longValue);
        Assert.assertEquals(msg.getLong("long"), longValue.longValue());
        Assert.assertTrue(msg.getObject("short") instanceof Short);
        Assert.assertEquals(msg.getObject("short"), shortValue);
        Assert.assertEquals(msg.getShort("short"), shortValue.shortValue());
        Assert.assertTrue(msg.getObject("string") instanceof String);
        Assert.assertEquals(msg.getObject("string"), stringValue);
        Assert.assertEquals(msg.getString("string"), stringValue);

        msg.clearBody();
        try {
            msg.setObject("object", new Object());
            Assert.fail("should have thrown exception");
        } catch (MessageFormatException e) {
        }

    }

    @Test
    public void testGetMapNames() throws JMSException {
        MapMessageImpl msg = new MapMessageImpl();
        msg.setBoolean("boolean", true);
        msg.setByte("byte", (byte)1);
        msg.setBytes("bytes1", new byte[1]);
        msg.setBytes("bytes2", new byte[3], 0, 2);
        msg.setChar("char", 'a');
        msg.setDouble("double", 1.5);
        msg.setFloat("float", 1.5f);
        msg.setInt("int", 1);
        msg.setLong("long", 1);
        msg.setObject("object", "stringObj");
        msg.setShort("short", (short)1);
        msg.setString("string", "string");

        msg = (MapMessageImpl)msg.copy();

        Enumeration<String> mapNamesEnum = msg.getMapNames();
        List<String> mapNamesList = Collections.list(mapNamesEnum);

        Assert.assertEquals(mapNamesList.size(), 12);
        Assert.assertTrue(mapNamesList.contains("boolean"));
        Assert.assertTrue(mapNamesList.contains("byte"));
        Assert.assertTrue(mapNamesList.contains("bytes1"));
        Assert.assertTrue(mapNamesList.contains("bytes2"));
        Assert.assertTrue(mapNamesList.contains("char"));
        Assert.assertTrue(mapNamesList.contains("double"));
        Assert.assertTrue(mapNamesList.contains("float"));
        Assert.assertTrue(mapNamesList.contains("int"));
        Assert.assertTrue(mapNamesList.contains("long"));
        Assert.assertTrue(mapNamesList.contains("object"));
        Assert.assertTrue(mapNamesList.contains("short"));
        Assert.assertTrue(mapNamesList.contains("string"));
    }

    @Test
    public void testItemExists() throws JMSException {
        MapMessageImpl mapMessage = new MapMessageImpl();

        mapMessage.setString("exists", "test");

        mapMessage = (MapMessageImpl)mapMessage.copy();

        Assert.assertTrue(mapMessage.itemExists("exists"));
        Assert.assertFalse(mapMessage.itemExists("doesntExist"));
    }

    @Test
    public void testClearBody() throws JMSException {
        MapMessageImpl mapMessage = new MapMessageImpl();
        mapMessage.setString("String", "String");
        mapMessage.clearBody();
        Assert.assertFalse(mapMessage.isReadOnly());

        mapMessage.setContent(mapMessage.getContent());
        Assert.assertNull(mapMessage.getString("String"));
        mapMessage.clearBody();
        mapMessage.setString("String", "String");

        mapMessage = (MapMessageImpl)mapMessage.copy();

        mapMessage.getString("String");
    }

    @Test
    public void testReadOnlyBody() throws JMSException {
        MapMessageImpl msg = new MapMessageImpl();
        msg.setBoolean("boolean", true);
        msg.setByte("byte", (byte)1);
        msg.setBytes("bytes", new byte[1]);
        msg.setBytes("bytes2", new byte[3], 0, 2);
        msg.setChar("char", 'a');
        msg.setDouble("double", 1.5);
        msg.setFloat("float", 1.5f);
        msg.setInt("int", 1);
        msg.setLong("long", 1);
        msg.setObject("object", "stringObj");
        msg.setShort("short", (short)1);
        msg.setString("string", "string");

        msg.setReadOnly(true);

        try {
            msg.getBoolean("boolean");
            msg.getByte("byte");
            msg.getBytes("bytes");
            msg.getChar("char");
            msg.getDouble("double");
            msg.getFloat("float");
            msg.getInt("int");
            msg.getLong("long");
            msg.getObject("object");
            msg.getShort("short");
            msg.getString("string");
        } catch (MessageNotReadableException mnre) {
            Assert.fail("should be readable");
        }
        try {
            msg.setBoolean("boolean", true);
            Assert.fail("should throw exception");
        } catch (MessageNotWriteableException mnwe) {
        }
        try {
            msg.setByte("byte", (byte)1);
            Assert.fail("should throw exception");
        } catch (MessageNotWriteableException mnwe) {
        }
        try {
            msg.setBytes("bytes", new byte[1]);
            Assert.fail("should throw exception");
        } catch (MessageNotWriteableException mnwe) {
        }
        try {
            msg.setBytes("bytes2", new byte[3], 0, 2);
            Assert.fail("should throw exception");
        } catch (MessageNotWriteableException mnwe) {
        }
        try {
            msg.setChar("char", 'a');
            Assert.fail("should throw exception");
        } catch (MessageNotWriteableException mnwe) {
        }
        try {
            msg.setDouble("double", 1.5);
            Assert.fail("should throw exception");
        } catch (MessageNotWriteableException mnwe) {
        }
        try {
            msg.setFloat("float", 1.5f);
            Assert.fail("should throw exception");
        } catch (MessageNotWriteableException mnwe) {
        }
        try {
            msg.setInt("int", 1);
            Assert.fail("should throw exception");
        } catch (MessageNotWriteableException mnwe) {
        }
        try {
            msg.setLong("long", 1);
            Assert.fail("should throw exception");
        } catch (MessageNotWriteableException mnwe) {
        }
        try {
            msg.setObject("object", "stringObj");
            Assert.fail("should throw exception");
        } catch (MessageNotWriteableException mnwe) {
        }
        try {
            msg.setShort("short", (short)1);
            Assert.fail("should throw exception");
        } catch (MessageNotWriteableException mnwe) {
        }
        try {
            msg.setString("string", "string");
            Assert.fail("should throw exception");
        } catch (MessageNotWriteableException mnwe) {
        }
    }

    @Test
    public void testWriteOnlyBody() throws JMSException {
        MapMessageImpl msg = new MapMessageImpl();
        msg.setReadOnly(false);

        msg.setBoolean("boolean", true);
        msg.setByte("byte", (byte)1);
        msg.setBytes("bytes", new byte[1]);
        msg.setBytes("bytes2", new byte[3], 0, 2);
        msg.setChar("char", 'a');
        msg.setDouble("double", 1.5);
        msg.setFloat("float", 1.5f);
        msg.setInt("int", 1);
        msg.setLong("long", 1);
        msg.setObject("object", "stringObj");
        msg.setShort("short", (short)1);
        msg.setString("string", "string");

        msg.setReadOnly(true);

        msg.getBoolean("boolean");
        msg.getByte("byte");
        msg.getBytes("bytes");
        msg.getChar("char");
        msg.getDouble("double");
        msg.getFloat("float");
        msg.getInt("int");
        msg.getLong("long");
        msg.getObject("object");
        msg.getShort("short");
        msg.getString("string");
    }
}

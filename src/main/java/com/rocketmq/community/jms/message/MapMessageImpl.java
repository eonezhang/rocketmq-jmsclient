package com.rocketmq.community.jms.message;

import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.protocol.RemotingSerializable;
import com.rocketmq.community.jms.util.RemotingSerializableEx;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageFormatException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class MapMessageImpl extends MessageBase implements MapMessage{
    protected Map<String, Object> map = new HashMap<String, Object>();

    public MapMessageImpl() {

    }

    public MapMessageImpl(Map<String, Object> map, boolean readOnly) {
        this.readOnly = readOnly;
        this.map = map;
    }

    @Override
    public boolean getBoolean(String name) throws JMSException {
        Object value = map.get(name);
        if (value == null) {
            return false;
        }
        if (value instanceof Boolean) {
            return ((Boolean)value).booleanValue();
        }
        if (value instanceof String) {
            return Boolean.valueOf(value.toString()).booleanValue();
        } else {
            throw new MessageFormatException(" cannot read a boolean from " + value.getClass().getName());
        }
    }

    @Override
    public byte getByte(String name) throws JMSException {
        Object value = map.get(name);
        if (value == null) {
            return 0;
        }
        if (value instanceof Byte) {
            return ((Byte)value).byteValue();
        }
        if (value instanceof String) {
            return Byte.valueOf(value.toString()).byteValue();
        } else {
            throw new MessageFormatException(" cannot read a byte from " + value.getClass().getName());
        }
    }

    @Override
    public short getShort(String name) throws JMSException {
        Object value = map.get(name);
        if (value == null) {
            return 0;
        }
        if (value instanceof Short) {
            return ((Short)value).shortValue();
        }
        if (value instanceof Byte) {
            return ((Byte)value).shortValue();
        }
        if (value instanceof String) {
            return Short.valueOf(value.toString()).shortValue();
        } else {
            throw new MessageFormatException(" cannot read a short from " + value.getClass().getName());
        }
    }

    @Override
    public char getChar(String name) throws JMSException {
        Object value = map.get(name);
        if (value == null) {
            throw new NullPointerException();
        }
        if (value instanceof Character) {
            return ((Character)value).charValue();
        } else {
            throw new MessageFormatException(" cannot read a short from " + value.getClass().getName());
        }
    }

    @Override
    public int getInt(String name) throws JMSException {
        Object value = map.get(name);
        if (value == null) {
            return 0;
        }
        if (value instanceof Integer) {
            return ((Integer)value).intValue();
        }
        if (value instanceof Short) {
            return ((Short)value).intValue();
        }
        if (value instanceof Byte) {
            return ((Byte)value).intValue();
        }
        if (value instanceof String) {
            return Integer.valueOf(value.toString()).intValue();
        } else {
            throw new MessageFormatException(" cannot read an int from " + value.getClass().getName());
        }
    }

    @Override
    public long getLong(String name) throws JMSException {
        Object value = map.get(name);
        if (value == null) {
            return 0;
        }
        if (value instanceof Long) {
            return ((Long)value).longValue();
        }
        if (value instanceof Integer) {
            return ((Integer)value).longValue();
        }
        if (value instanceof Short) {
            return ((Short)value).longValue();
        }
        if (value instanceof Byte) {
            return ((Byte)value).longValue();
        }
        if (value instanceof String) {
            return Long.valueOf(value.toString()).longValue();
        } else {
            throw new MessageFormatException(" cannot read a long from " + value.getClass().getName());
        }
    }

    @Override
    public float getFloat(String name) throws JMSException {
        Object value = map.get(name);
        if (value == null) {
            return 0;
        }
        if (value instanceof Float) {
            return ((Float)value).floatValue();
        }
        if (value instanceof String) {
            return Float.valueOf(value.toString()).floatValue();
        } else {
            throw new MessageFormatException(" cannot read a float from " + value.getClass().getName());
        }
    }

    @Override
    public double getDouble(String name) throws JMSException {
        Object value = map.get(name);
        if (value == null) {
            return 0;
        }
        if (value instanceof Double) {
            return ((Double)value).doubleValue();
        }
        if (value instanceof Float) {
            return ((Float)value).doubleValue();
        }
        if (value instanceof String) {
            return Double.valueOf(value.toString()).doubleValue();
        } else {
            throw new MessageFormatException(" cannot read a double from " + value.getClass().getName());
        }
    }

    @Override
    public String getString(String name) throws JMSException {
        Object value = map.get(name);
        if (value == null) {
            return null;
        }
        if (value instanceof byte[]) {
            throw new MessageFormatException("Use getBytes to read a byte array");
        } else {
            return value.toString();
        }
    }

    @Override
    public byte[] getBytes(String name) throws JMSException {
        Object value = map.get(name);
        if (value instanceof byte[]) {
            return (byte[])value;
        } else {
            throw new MessageFormatException(" cannot read a byte[] from " + value.getClass().getName());
        }
    }

    @Override
    public Object getObject(String name) throws JMSException {
        Object result = map.get(name);
        return result;
    }

    @Override
    public Enumeration getMapNames() throws JMSException {
        return Collections.enumeration(map.keySet());
    }

    @Override
    public void setBoolean(String name, boolean value) throws JMSException {
        put(name, value ? Boolean.TRUE : Boolean.FALSE);
    }

    @Override
    public void setByte(String name, byte value) throws JMSException {
        put(name, Byte.valueOf(value));
    }

    @Override
    public void setShort(String name, short value) throws JMSException {
        put(name, Short.valueOf(value));
    }

    @Override
    public void setChar(String name, char value) throws JMSException {
        put(name, Character.valueOf(value));
    }

    @Override
    public void setInt(String name, int value) throws JMSException {
        put(name, Integer.valueOf(value));
    }

    @Override
    public void setLong(String name, long value) throws JMSException {
        put(name, Long.valueOf(value));
    }

    @Override
    public void setFloat(String name, float value) throws JMSException {
        put(name, new Float(value));
    }

    @Override
    public void setDouble(String name, double value) throws JMSException {
        put(name, new Double(value));
    }

    @Override
    public void setString(String name, String value) throws JMSException {
        put(name, value);
    }

    @Override
    public void setBytes(String name, byte[] value) throws JMSException {
        if (value != null) {
            put(name, value);
        } else {
            map.remove(name);
        }
    }

    @Override
    public void setBytes(String name, byte[] value, int offset, int length) throws JMSException {
        byte[] data = new byte[length];
        System.arraycopy(value, offset, data, 0, length);
        put(name, data);
    }

    @Override
    public void setObject(String name, Object value) throws JMSException {
        if (value != null) {
            // byte[] not allowed on properties
            if (!(value instanceof byte[])) {
                checkValidObject(value);
            }
            put(name, value);
        } else {
            put(name, null);
        }
    }

    @Override
    public boolean itemExists(String name) throws JMSException {
        return map.containsKey(name);
    }

    @Override
    public void clearBody() throws JMSException {
        super.clearBody();
        map = null;
    }

    @Override
    public Message convert() throws JMSException {
        Message message = new Message(this.getJMSDestination().toString(), // topic
                JMS_SOURCE,  // tag
                RemotingSerializableEx.encodeWithClass(map));  // body
        message.putProperty(MSG_TYPE_NAME, MessageTypeEnum.MapMessage.toString());
        return message;
    }

    protected void put(String name, Object value) throws JMSException {
        if (name == null) {
            throw new IllegalArgumentException("The name of the property cannot be null.");
        }
        if (name.length() == 0) {
            throw new IllegalArgumentException("The name of the property cannot be an emprty string.");
        }
        map.put(name, value);
    }

    protected void checkValidObject(Object value) throws MessageFormatException {

        boolean valid = value instanceof Boolean || value instanceof Byte || value instanceof Short || value instanceof Integer || value instanceof Long;
        valid = valid || value instanceof Float || value instanceof Double || value instanceof Character || value instanceof String || value == null;

        if (!valid) {
            throw new MessageFormatException("Only objectified primitive objects and String types are allowed but was: " + value + " type: " + value.getClass());
        }
    }
}

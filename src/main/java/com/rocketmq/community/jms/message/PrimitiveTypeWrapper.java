package com.rocketmq.community.jms.message;


import java.util.List;
import java.util.Map;

public class PrimitiveTypeWrapper {
    byte objectType;

    private Object primitiveObj;

    public PrimitiveTypeWrapper() {
    }

    public PrimitiveTypeWrapper(Object primitiveObj) {
        objectType = getTypeNumFromObject(primitiveObj);
        this.primitiveObj = primitiveObj;
    }

    public byte getObjectType() {
        return objectType;
    }

    public void setObjectType(byte objectType) {
        this.objectType = objectType;
    }

    public Object getObject() {
        return primitiveObj;
    }

    public void setObject(Object obj) {
        primitiveObj = obj;
        objectType = getTypeNumFromObject(obj);
    }

    private byte getTypeNumFromObject(Object obj) {
        if (obj instanceof Boolean) {
            return MessageBase.BOOLEAN_TYPE;
        } else if (obj instanceof Byte) {
            return MessageBase.BYTE_TYPE;
        } else if (obj instanceof Character) {
            return MessageBase.CHAR_TYPE;
        } else if (obj instanceof  Short) {
            return MessageBase.SHORT_TYPE;
        } else if (obj instanceof Integer) {
            return MessageBase.INTEGER_TYPE;
        } else if (obj instanceof Long) {
            return MessageBase.LONG_TYPE;
        } else if (obj instanceof Double) {
            return MessageBase.DOUBLE_TYPE;
        } else if (obj instanceof Float) {
            return MessageBase.FLOAT_TYPE;
        } else if (obj instanceof String) {
            return MessageBase.STRING_TYPE;
        } else if (obj instanceof byte[]) {
            return MessageBase.BYTE_ARRAY_TYPE;
        } else if (obj instanceof Map) {
            return MessageBase.MAP_TYPE;
        } else if (obj instanceof List) {
            return MessageBase.LIST_TYPE;
        }

        return 0;
    }
}

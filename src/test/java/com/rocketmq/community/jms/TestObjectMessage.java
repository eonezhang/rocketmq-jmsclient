package com.rocketmq.community.jms;

import com.rocketmq.community.jms.helper.TestObject;
import com.rocketmq.community.jms.message.ObjectMessageImpl;
import org.junit.Assert;
import org.junit.Test;

import javax.jms.JMSException;
import javax.jms.MessageNotReadableException;
import javax.jms.MessageNotWriteableException;
import java.io.Serializable;

public class TestObjectMessage {
    @Test
    public void testSetObject() throws JMSException {
        ObjectMessageImpl msg = new ObjectMessageImpl();
        TestObject testObject = new TestObject();
        msg.setObject(testObject);
        TestObject objectReturn = (TestObject)msg.getObject();
        Assert.assertEquals(testObject.character, objectReturn.character);
        Assert.assertEquals(testObject.byteArray[0], objectReturn.byteArray[0]);
    }

    @Test
    public void testClearBody() throws JMSException {
        ObjectMessageImpl objectMessage = new ObjectMessageImpl();
        try {
            objectMessage.setObject("String");
            objectMessage.clearBody();
            Assert.assertFalse(objectMessage.isReadOnly());
            Assert.assertNull(objectMessage.getObject());
            objectMessage.setObject("String");
            objectMessage.getObject();
        } catch (MessageNotWriteableException mnwe) {
            Assert.fail("should be writeable");
        }
    }

    @Test
    public void testReadOnlyBody() throws JMSException {
        ObjectMessageImpl msg = new ObjectMessageImpl();
        msg.setObject("test");
        msg.setReadOnly(true);
        try {
            msg.getObject();
        } catch (MessageNotReadableException e) {
            Assert.fail("should be readable");
        }
        try {
            msg.setObject("test");
            Assert.fail("should throw exception");
        } catch (MessageNotWriteableException e) {
        }
    }

    @Test
    public void testWriteOnlyBody() throws JMSException { // should always be readable
        ObjectMessageImpl msg = new ObjectMessageImpl();
        msg.setReadOnly(false);
        try {
            msg.setObject("test");
            msg.getObject();
        } catch (MessageNotReadableException e) {
            Assert.fail("should be readable");
        }
        msg.setReadOnly(true);
        try {
            msg.getObject();
            msg.setObject("test");
            Assert.fail("should throw exception");
        } catch (MessageNotReadableException e) {
            Assert.fail("should be readable");
        } catch (MessageNotWriteableException mnwe) {
        }
    }
}

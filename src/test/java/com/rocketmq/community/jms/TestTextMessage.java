package com.rocketmq.community.jms;

import com.rocketmq.community.jms.message.TextMessageImpl;
import org.junit.Assert;
import org.junit.Test;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageNotReadableException;
import javax.jms.MessageNotWriteableException;
import java.io.IOException;

public class TestTextMessage {
    @Test
    public void testDeepCopy() throws JMSException {
        TextMessageImpl msg = new TextMessageImpl();
        String string = "str";
        msg.setText(string);
        Message copy = msg.copy();
        Assert.assertTrue(msg.getText().equals(((TextMessageImpl) copy).getText()));
    }

    @Test
    public void testSetText() {
        TextMessageImpl msg = new TextMessageImpl();
        String str = "testText";
        try {
            msg.setText(str);
            Assert.assertEquals(msg.getText(), str);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testClearBody() throws JMSException, IOException {
        TextMessageImpl textMessage = new TextMessageImpl();
        textMessage.setText("string");
        textMessage.clearBody();
        Assert.assertFalse(textMessage.isReadOnly());
        Assert.assertNull(textMessage.getText());
        try {
            textMessage.setText("String");
            textMessage.getText();
        } catch (MessageNotWriteableException mnwe) {
            Assert.fail("should be writeable");
        } catch (MessageNotReadableException mnre) {
            Assert.fail("should be readable");
        }
    }

    @Test
    public void testReadOnlyBody() throws JMSException {
        TextMessageImpl textMessage = new TextMessageImpl();
        textMessage.setText("test");
        textMessage.setReadOnly(true);
        try {
            textMessage.getText();
        } catch (MessageNotReadableException e) {
            Assert.fail("should be readable");
        }
        try {
            textMessage.setText("test");
            Assert.fail("should throw exception");
        } catch (MessageNotWriteableException mnwe) {
        }
    }

    @Test
    public void testWriteOnlyBody() throws JMSException { // should always be readable
        TextMessageImpl textMessage = new TextMessageImpl();
        textMessage.setReadOnly(false);
        try {
            textMessage.setText("test");
            textMessage.getText();
        } catch (MessageNotReadableException e) {
            Assert.fail("should be readable");
        }
        textMessage.setReadOnly(true);
        try {
            textMessage.getText();
            textMessage.setText("test");
            Assert.fail("should throw exception");
        } catch (MessageNotReadableException e) {
            Assert.fail("should be readable");
        } catch (MessageNotWriteableException mnwe) {
        }
    }
}

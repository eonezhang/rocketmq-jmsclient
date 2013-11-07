package com.rocketmq.community.jms.helper;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.*;
import java.util.Random;

public class JmsProducer {

    public final String textMessage = "Test Text Message";

    public final String mapAcctId = "acctId";
    public final Long mapAcctIdValue = 12345l;
    public final String mapSide = "side";
    public final String mapSideValue = "SELL";
    public final String mapShares = "shares";
    public final Double mapSharesValue = 250.0;
    public final String mapDeptId = "deptId";
    public final Short mapDeptIdValue = 111;
    public final String mapByteArray = "byteArray";
    public final byte[] mapByteArrayValue = new byte[1];
    public final String mapSex = "sex";
    public final Character mapSexValue = 'M';

    public final Double double1 = 11.0;
    public final Double double2 = 12.33;
    public final char character = 'C';
    public final String utf = "This is A test &&||";

    public final TestObject testObject = new TestObject();

    public void sendTextMessage() {
        MessageCreator msg = new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                Random random = new Random();
                TextMessage msg = session.createTextMessage(textMessage);
                return msg;
            }
        };

        jmsTemplate.send(queueName, msg);
    }

    public void sendMapMessage() {
        MessageCreator msg = new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                MapMessage msg = session.createMapMessage();
                msg.setLong(mapAcctId, mapAcctIdValue);
                msg.setString(mapSide, mapSideValue);
                msg.setDouble(mapShares, mapSharesValue);
                msg.setShort(mapDeptId, mapDeptIdValue);
                msg.setBytes(mapByteArray, mapByteArrayValue);
                msg.setChar(mapSex, mapSexValue);
                return msg;
            }
        };

        jmsTemplate.send(queueName, msg);
    }

    public void sendBytesMessage() {
        MessageCreator msg = new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                BytesMessage msg = session.createBytesMessage();
                msg.writeDouble(double1);
                msg.writeDouble(double2);
                msg.writeChar(character);
                msg.writeUTF(utf);
                return msg;
            }
        };

        jmsTemplate.send(queueName, msg);
    }

    public void sendStreamMessage() {
        MessageCreator msg = new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                StreamMessage msg = session.createStreamMessage();
                msg.writeDouble(double1);
                msg.writeDouble(double2);
                return msg;
            }
        };

        jmsTemplate.send(queueName, msg);
    }

    public void sendObjectMessage() {
        MessageCreator msg = new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                ObjectMessage msg = session.createObjectMessage();
                msg.setObject(testObject);
                return msg;
            }
        };

        jmsTemplate.send(queueName, msg);
    }

    public JmsTemplate jmsTemplate = null;
    public String queueName = null;

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }
}


package com.rocketmq.community.jms.helper;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.*;
import java.util.Random;

public class JmsProducer {

    public final String textMessage = "Test Text Message";

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
                msg.setLong("acctId", 12345);
                msg.setString("side", "SELL");
                msg.setDouble("shares", 250.0);
                return msg;
            }
        };

        jmsTemplate.send(queueName, msg);
    }

    public void sendBytesMessage() {
        MessageCreator msg = new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                BytesMessage msg = session.createBytesMessage();
                msg.writeDouble(11.33);
                msg.writeDouble(22.44);
                return msg;
            }
        };

        jmsTemplate.send(queueName, msg);
    }

    public void sendStreamMessage() {
        MessageCreator msg = new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                StreamMessage msg = session.createStreamMessage();
                msg.writeDouble(11.33);
                msg.writeDouble(22.44);
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


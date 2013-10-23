package com.rocketmq.community.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import java.util.Properties;

public class MQConnectionFactory implements ConnectionFactory {// , QueueConnectionFactory, TopicConnectionFactory {
    private Properties properties;

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Connection createConnection() throws JMSException {
        MQConnection connection = new MQConnection();
        return connection;
    }

    public Connection createConnection(String userName, String password) throws JMSException {
        throw new JMSException("Not supported");
    }
}

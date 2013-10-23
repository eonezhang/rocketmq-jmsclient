package com.rocketmq.community.jms.jndi;

import com.rocketmq.community.jms.MQConnectionFactory;
import com.rocketmq.community.jms.MQQueue;
import com.rocketmq.community.jms.MQTopic;

import javax.jms.Queue;
import javax.jms.Topic;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class MQInitialContextFactory implements InitialContextFactory {
    private String connectionPrefix = "connection.";
    private String queuePrefix = "queue.";
    private String topicPrefix = "topic.";

    public Context getInitialContext(Hashtable environment) throws NamingException {
        Map<String, Object> contextDict = new ConcurrentHashMap<String, Object>();

        MQConnectionFactory factory;
        String factoryName = (String)environment.get("connectionFactoryName");
        factory = createConnectionFactory(environment);

        contextDict.put(factoryName, factory);

        createQueues(contextDict, environment);
        createTopics(contextDict, environment);

        return createContext(environment, contextDict);
    }

    protected MQConnectionFactory createConnectionFactory(Hashtable environment) {
        MQConnectionFactory factory = new MQConnectionFactory();
        Properties properties = new Properties();
        properties.putAll(environment);
        factory.setProperties(properties);
        return factory;
    }

    protected MQInitialContext createContext(Hashtable environment, Map<String, Object> data) {
        return new MQInitialContext(environment, data);
    }

    protected void createQueues(Map<String, Object> data, Hashtable environment) {
        for (Iterator iter = environment.entrySet().iterator(); iter.hasNext();) {
            Map.Entry entry = (Map.Entry)iter.next();
            String key = entry.getKey().toString();
            if (key.startsWith(queuePrefix)) {
                String jndiName = key.substring(queuePrefix.length());
                data.put(jndiName, createQueue(entry.getValue().toString()));
            }
        }
    }

    protected void createTopics(Map<String, Object> data, Hashtable environment) {
        for (Iterator iter = environment.entrySet().iterator(); iter.hasNext();) {
            Map.Entry entry = (Map.Entry)iter.next();
            String key = entry.getKey().toString();
            if (key.startsWith(topicPrefix)) {
                String jndiName = key.substring(topicPrefix.length());
                data.put(jndiName, createTopic(entry.getValue().toString()));
            }
        }
    }

    /**
     * Factory method to create new Queue instances
     */
    protected Queue createQueue(String name) {
        return new MQQueue(name);
    }

    /**
     * Factory method to create new Topic instances
     */
    protected Topic createTopic(String name) {
        return new MQTopic(name);
    }
}

package com.rocketmq.community.jms;

import javax.jms.JMSException;
import javax.jms.Queue;

public class MQQueue extends MQDestination implements Queue {
    public MQQueue(String name) {
        super(name);
    }

    public String getQueueName() throws JMSException {
        return name;
    }

    public String toString() {
        return name;
    }
}

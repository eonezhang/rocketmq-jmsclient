package com.rocketmq.community.jms.helper;

import javax.jms.*;

public class JmsConsumerAsync implements MessageListener {
    private Message message;
    final private Object lock = new Object();

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        synchronized (lock) {
            this.message = message;
        }
    }
    public Object getLock() {
        return lock;
    }

    public void onMessage(Message message) {
        synchronized (lock) {
            this.message = message;
            lock.notifyAll();
        }
    }
}

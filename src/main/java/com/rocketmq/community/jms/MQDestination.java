package com.rocketmq.community.jms;

public abstract class MQDestination {
    protected String name;

    public MQDestination(String name) {
        this.name = name;
    }
}

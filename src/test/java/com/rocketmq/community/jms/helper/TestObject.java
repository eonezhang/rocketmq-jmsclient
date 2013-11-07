package com.rocketmq.community.jms.helper;

import java.io.Serializable;

public class TestObject implements Serializable {
    public byte[] byteArray = {10, 20};
    public String name = "JMS";
    public Double value = 123.21;
    public Character character = 'M';
}

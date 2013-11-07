package com.rocketmq.community.jms.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.rocketmq.remoting.protocol.RemotingSerializable;


public class RemotingSerializableEx extends RemotingSerializable {
    public static byte[] encodeWithClass(final Object obj) {
        final String json = JSON.toJSONString(obj, SerializerFeature.WriteClassName);
        if (json != null) {
            return json.getBytes();
        }
        return null;
    }

    public static String toJsonWithClass(final Object obj) {
        return JSON.toJSONString(obj, SerializerFeature.WriteClassName);
    }
}

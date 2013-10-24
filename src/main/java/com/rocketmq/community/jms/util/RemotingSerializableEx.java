package com.rocketmq.community.jms.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.rocketmq.remoting.protocol.RemotingSerializable;

/**
 * Created with IntelliJ IDEA.
 * User: CalvinZhan
 * Date: 10/24/13
 * Time: 11:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class RemotingSerializableEx extends RemotingSerializable {
    public static byte[] encodeWithClass(final Object obj) {
        final String json = JSON.toJSONString(obj, SerializerFeature.WriteClassName);
        if (json != null) {
            return json.getBytes();
        }
        return null;
    }
}

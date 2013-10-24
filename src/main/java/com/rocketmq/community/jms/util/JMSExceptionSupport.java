package com.rocketmq.community.jms.util;

import javax.jms.JMSException;

public final class JMSExceptionSupport {
    private JMSExceptionSupport() {

    }

    public static JMSException create(Exception cause) {
        if (cause instanceof JMSException) {
            return (JMSException)cause;
        }
        String msg = cause.getMessage();
        if (msg == null || msg.length() == 0) {
            msg = cause.toString();
        }
        JMSException exception = new JMSException(msg);
        exception.setLinkedException(cause);
        exception.initCause(cause);
        return exception;
    }
}

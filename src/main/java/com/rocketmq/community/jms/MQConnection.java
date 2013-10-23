package com.rocketmq.community.jms;

import javax.jms.*;

public class MQConnection implements Connection, TopicConnection, QueueConnection {
    // Implement Connection

    @Override
    public Session createSession(boolean transacted, int acknowledgeMode) throws JMSException {
        if(!transacted) {
            if (acknowledgeMode==Session.SESSION_TRANSACTED) {
                throw new JMSException("acknowledgeMode SESSION_TRANSACTED cannot be used for an non-transacted Session");
            } else if (acknowledgeMode < Session.SESSION_TRANSACTED || acknowledgeMode > Session.DUPS_OK_ACKNOWLEDGE) {
                throw new JMSException("invalid acknowledgeMode: " + acknowledgeMode + ". Valid values are Session.AUTO_ACKNOWLEDGE (1), " +
                        "Session.CLIENT_ACKNOWLEDGE (2), Session.DUPS_OK_ACKNOWLEDGE (3), ActiveMQSession.INDIVIDUAL_ACKNOWLEDGE (4) or for transacted sessions Session.SESSION_TRANSACTED (0)");
            }
        }
        return new MQSession(this, transacted ? Session.SESSION_TRANSACTED : (acknowledgeMode == Session.SESSION_TRANSACTED
                ? Session.AUTO_ACKNOWLEDGE : acknowledgeMode));
    }

    @Override
    public String getClientID() throws JMSException {
        throw new JMSException("Not supported");
    }

    @Override
    public void setClientID(String clientID) throws JMSException {
        throw new JMSException("Not supported");
    }

    @Override
    public ConnectionMetaData getMetaData() throws JMSException {
        throw new JMSException("Not supported");
    }

    @Override
    public ExceptionListener getExceptionListener() throws JMSException {
        throw new JMSException("Not supported");
    }

    @Override
    public void setExceptionListener(ExceptionListener listener) throws JMSException {
        exceptionListener = listener;
    }

    @Override
    public void start() throws JMSException {
        throw new JMSException("Not supported");
    }

    @Override
    public void stop() throws JMSException {
        throw new JMSException("Not supported");
    }

    @Override
    public void close() throws JMSException {
        throw new JMSException("Not supported");
    }

    @Override
    public ConnectionConsumer createConnectionConsumer(
            Destination destination,
            String messageSelector,
            ServerSessionPool sessionPool,
            int maxMessages)
            throws JMSException {
        throw new JMSException("Not supported");
    }

    @Override
    public ConnectionConsumer createDurableConnectionConsumer(
            Topic topic,
            String subscriptionName,
            String messageSelector,
            ServerSessionPool sessionPool,
            int maxMessages)
            throws JMSException {
        throw new JMSException("Not supported");
    }

    // Implement TopicConnection

    @Override
    public TopicSession createTopicSession(boolean transacted, int acknowledgeMode) throws JMSException {
        throw new JMSException("Not supported");
    }

    @Override
    public ConnectionConsumer createConnectionConsumer(
            Topic topic,
            String messageSelector,
            ServerSessionPool sessionPool,
            int maxMessages)
            throws JMSException {
        throw new JMSException("Not supported");
    }

    // Implement QueueConnection

    @Override
    public QueueSession createQueueSession(boolean transacted, int acknowledgeMode) throws JMSException {
        throw new JMSException("Not supported");
    }

    @Override
    public ConnectionConsumer createConnectionConsumer(
            Queue queue,
            String messageSelector,
            ServerSessionPool sessionPool,
            int maxMessages)
            throws JMSException {
        throw new JMSException("Not supported");
    }
}

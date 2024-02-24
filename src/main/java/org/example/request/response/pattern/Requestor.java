package org.example.request.response.pattern;

import javax.jms.*;

public class Requestor {
    private final Session session;
    private final MessageProducer producer;
    private final MessageConsumer consumer;
    private final TemporaryQueue tempQueue;

    public Requestor(Connection connection) throws JMSException {
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination requestDestination = session.createQueue("RequestQueue");
        producer = session.createProducer(requestDestination);

        tempQueue = session.createTemporaryQueue();
        consumer = session.createConsumer(tempQueue);
    }

    public void sendRequest(String requestMessage) throws JMSException {
        System.out.println("Inside the sendRequest method");
        TextMessage request = session.createTextMessage(requestMessage);
        request.setJMSReplyTo(tempQueue);
        producer.send(request);
    }

    public String receiveReply() throws JMSException {
        System.out.println("Inside the receiveReply method");
        Message reply = consumer.receive();
        if (reply instanceof TextMessage) {
            return ((TextMessage) reply).getText();
        }
        return null;
    }

    public void close() throws JMSException {
        if (consumer != null) consumer.close();
        if (tempQueue != null) tempQueue.delete();
        if (producer != null) producer.close();
        if (session != null) session.close();
    }
}

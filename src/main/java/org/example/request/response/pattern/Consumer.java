package org.example.request.response.pattern;

import javax.jms.*;

public class Consumer {
    private Connection connection;
    private Session session;
    private MessageConsumer consumer;

    public Consumer(Connection connection) throws JMSException {
        this.connection = connection;
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination requestDestination = session.createQueue("RequestQueue");
        consumer = session.createConsumer(requestDestination);
    }

    public void start() throws JMSException {
        connection.start();
        consumer.setMessageListener(message -> {
            try {
                TextMessage request = (TextMessage) message;
                Destination replyTo = request.getJMSReplyTo();

                MessageProducer producer = session.createProducer(replyTo);
                TextMessage reply = session.createTextMessage("Reply to: " + request.getText());
                producer.send(reply);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });
    }

    public void close() throws JMSException {
        if (consumer != null) consumer.close();
        if (session != null) session.close();
    }
}

package org.example.message.filtering;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

public class OrderProducer {

    private Connection connection;
    private Queue queue;

    public OrderProducer(Connection connection, Queue queue) throws JMSException {
        this.connection = JmsProvider.getConnection();
        this.queue = queue;
    }

    public void sendOrder(String orderData, String tag) {

        try(Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(queue)) {

            TextMessage message = session.createTextMessage(orderData);
            message.setStringProperty("tag",tag);
            producer.send(message);
            System.out.println("Order sent with tag: " + tag);

        } catch (JMSException e) {
            System.out.println("Exception occurred while sending the order");
            e.printStackTrace();
        }

    }

}

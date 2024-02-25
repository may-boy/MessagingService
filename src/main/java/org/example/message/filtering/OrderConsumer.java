package org.example.message.filtering;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

public class OrderConsumer {

    private Connection connection;
    private Queue queue;

    public OrderConsumer(Connection connection, Queue queue) throws JMSException {
        this.connection = JmsProvider.getConnection();
        this.queue = queue;
    }

    public void processOrder() {

        try (Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
             MessageConsumer consumer = session.createConsumer(queue);) {

            connection.start();

            while (true) {
                Message message = consumer.receive();
                if (message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    String orderData = textMessage.getText();

                    //Get the tag from JMS header.
                    String tag = textMessage.getStringProperty("tag");

                    if ("important".equalsIgnoreCase(tag))
                        System.out.println("Processing important order: " + orderData);
                    else if ("emergency".equalsIgnoreCase(tag))
                        System.out.println("Processing emergency order: " + orderData);
                    else
                        System.out.println("Unknown tag: Skipping order: " + orderData);
                }
            }

        } catch (JMSException e) {
            System.out.println("Exception occurred while processing the order.");
            e.printStackTrace();
        }

    }

}

package org.example.message.transactions;

import org.example.message.filtering.JmsProvider;

import javax.jms.*;
import java.io.Serializable;

public class OrderService {

    public void sendOrder(Order order) {

        Connection connection = null;
        Session session = null;

        try {
            connection = JmsProvider.getConnection();
            connection.start();

            session = connection.createSession(true, Session.SESSION_TRANSACTED);
            Destination destination = session.createQueue("ORDER_QUEUE");

            MessageProducer messageProducer = session.createProducer(destination);
            ObjectMessage objectMessage = session.createObjectMessage(order);

            messageProducer.send(objectMessage);
            System.out.println("Order sent: " + order);

            //Committing the transaction.
            session.commit();
        } catch (JMSException e) {
            e.printStackTrace();
            if (session != null) {
                try {
                    session.rollback();
                } catch (JMSException t) {
                    System.out.println("Exception occurred while performing the rollback operation");
                    t.printStackTrace();
                }
            }
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    System.out.println("Exception occurred while closing the connection object.");
                    throw new RuntimeException(e);
                }
            }
        }

    }

}

package org.example.message.transactions;

import org.example.message.filtering.JmsProvider;

import javax.jms.*;

public class InventoryService {

    public void processOrders() {
        Connection connection = null;
        Session session = null;
        try {
            connection = JmsProvider.getConnection();
            connection.start();

            session = connection.createSession(true, Session.SESSION_TRANSACTED);
            Destination destination = session.createQueue("ORDER_QUEUE");

            MessageConsumer consumer = session.createConsumer(destination);

            Message message;

            while ((message = consumer.receive(10000)) != null) {
                //Pattern Variable.
                if(message instanceof ObjectMessage objectMessage) {
                    Order order = (Order) objectMessage.getObject();

                    //Process and update the orders.
                    updateInventory(order);

                    System.out.println("Order processed: " + order);
                }
            }

            session.commit();

        } catch (JMSException e) {
            if(session != null) {
                try {
                    session.rollback();
                } catch (JMSException ex) {
                    throw new RuntimeException(ex);
                }
            }
            System.out.println("Exception occurred while processing the orders.");
            throw new RuntimeException(e);
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private static void updateInventory(Order order) {

        //TODO: add the DB table updation logic here.
        System.out.println("Updated the DB for order: " + order);
    }

}

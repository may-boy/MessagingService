package org.example.message.filtering;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.Session;

public class Main {

    //Day 3: Implement Message Filtering

    //Explore message filtering techniques in JMS, such as message selectors or JMS headers.
    //Implement message filtering in your JMS consumer to selectively process messages based on criteria like message properties or content.
    //Experiment with different filtering strategies to optimize message processing efficiency.

    public static void main(String[] args) {

        try {
            Connection connection = JmsProvider.getConnection();

            //Create a queue object.
            Queue queue = createQueue(connection,"MessageFilteringTest");

            OrderProducer orderProducer = new OrderProducer(connection,queue);
            orderProducer.sendOrder("Order Data 1", "important");
            orderProducer.sendOrder("Order Data 2", "emergency");

            OrderConsumer consumer = new OrderConsumer(connection,queue);
            consumer.processOrder();

            connection.close();

        } catch (JMSException e) {
            System.out.println("Exception occurred while performing the operation.");
            e.printStackTrace();
        }

    }

    private static Queue createQueue(Connection connection, String messageFilteringTest) throws JMSException {

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        return session.createQueue(messageFilteringTest);

    }

}

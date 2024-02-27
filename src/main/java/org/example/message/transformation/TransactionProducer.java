package org.example.message.transformation;

import javax.jms.*;
import org.example.message.filtering.JmsProvider;

public class TransactionProducer {
    private Connection connection;
    private Queue queue;

    public TransactionProducer() throws JMSException {
        this.connection = JmsProvider.getConnection();
        this.queue = createQueue("TestTransactionQueue");
    }

    public void sendTransactionMessage(Transaction transaction) {
        try (Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
             MessageProducer producer = session.createProducer(queue)) {
            System.out.println("Inside the send Transaction message method.");
            // Construct transaction message
            ObjectMessage message = session.createObjectMessage(transaction);
            producer.send(message);
            System.out.println("Transaction message sent: " + transaction.toString());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private Queue createQueue(String queueName) {
        try {
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            return session.createQueue(queueName);
        } catch (JMSException e) {
            e.printStackTrace();
            return null;
        }
    }
}

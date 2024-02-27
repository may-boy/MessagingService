package org.example.message.transformation;

import javax.jms.*;
import org.example.message.filtering.JmsProvider;

public class EnrichmentService {
    private Connection connection;
    private Queue queue;

    public EnrichmentService() throws JMSException {
        this.connection = JmsProvider.getConnection();
        this.queue = JmsProvider.createQueue("TestTransactionQueue");
    }

    public void processTransactionMessages() {
        try (Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
             MessageConsumer consumer = session.createConsumer(queue)) {

            connection.start();

            while (true) {
                Message message = consumer.receive();
                if (message instanceof ObjectMessage) {
                    Transaction transaction = (Transaction) ((ObjectMessage) message).getObject();

                    // Enrich transaction with customer profile information
                    CustomerProfile profile = retrieveCustomerProfile(transaction.getCustomerId());
                    //transaction.setCustomerProfile(profile);

                    // Further processing of enriched transaction...
                }
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private CustomerProfile retrieveCustomerProfile(String customerId) {
        // Logic to retrieve customer profile from database or external service
        // This could involve querying a database or calling an external API
        // For simplicity, we'll just return a dummy profile here
        return new CustomerProfile(customerId, "John Doe", "john.doe@example.com");
    }
}

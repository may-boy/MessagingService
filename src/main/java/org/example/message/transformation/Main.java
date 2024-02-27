package org.example.message.transformation;

import javax.jms.JMSException;

public class Main {

    //Day 4: Implement Message Transformation
    public static void main(String[] args) throws JMSException {
        // Create a TransactionProducer instance
        TransactionProducer transactionProducer = new TransactionProducer();

        // Create a new transaction
        Transaction transaction = new Transaction("123456", 100.0, "transfer");

        // Send the transaction message
        transactionProducer.sendTransactionMessage(transaction);

        // Create an EnrichmentService instance
        EnrichmentService enrichmentService = new EnrichmentService();

        // Start processing transaction messages
        enrichmentService.processTransactionMessages();
    }
}

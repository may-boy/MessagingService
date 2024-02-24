package org.example.request.response.pattern;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Main {
    public static void main(String[] args) {
        try {

            Connection connection = JmsProvider.getConnection();

            // Create Requestor
            Requestor requestor = new Requestor(connection);

            // Create Consumer
            Consumer consumer = new Consumer(connection);
            consumer.start();

            // Send Request
            requestor.sendRequest("Hello, this is a request message");

            // Receive Reply
            String reply = requestor.receiveReply();
            System.out.println("Received reply: " + reply);

            // Close Resources
            requestor.close();
            consumer.close();
            connection.close();
        } catch (JMSException e) {
            System.out.println("Exception occurred while performing the request-response jms operation.");
            e.printStackTrace();
        }
    }
}

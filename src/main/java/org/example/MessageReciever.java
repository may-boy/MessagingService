package org.example;

import javax.jms.*;

public class MessageReciever
{
	
	public void recieveMessage(String queueName) throws JMSException
	{
		
		Connection connection = null;
		
		try{
			connection = JmsProvider.getConnection();
			connection.start();
			
			Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue(queueName);
			
			MessageConsumer consumer = session.createConsumer(destination);
			Message message = consumer.receive();
			
			if(message instanceof TextMessage) {
				TextMessage textMessage = (TextMessage) message;
				System.out.printf("Received: " + textMessage.getText());
			} else {
				System.out.printf("Could not receive a text message.");
			}
		} finally
		{
			if(connection != null)
				connection.close();
		}
		
	}
	
}

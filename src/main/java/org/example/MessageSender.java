package org.example;

import javax.jms.*;

public class MessageSender
{
	
	public void sendMessage(String queueName, String messageText) throws JMSException
	{
		System.out.println("Connection");
		Connection connection = null;
		try {
			connection = JmsProvider.getConnection();
			connection.start();
			
			Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue(queueName);
			
			MessageProducer producer = session.createProducer(destination);
			TextMessage message = session.createTextMessage(messageText);
			
			producer.send(message);
		} finally
		{
			if(connection != null) {
				connection.close();
			}
		}
	}
	
}

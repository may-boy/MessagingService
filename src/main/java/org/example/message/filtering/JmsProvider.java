package org.example.message.filtering;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Arrays;

public class JmsProvider
{
	private static final String USERNAME = "admin";
	private static final String PASSWORD = "admin";
	private static final String BROKER_URL = "tcp://localhost:61616";
	
	public static Connection getConnection() throws JMSException
	{
		System.out.println("Creating a connection");
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKER_URL);
		activeMQConnectionFactory.setTrustAllPackages(false);
		activeMQConnectionFactory.setTrustedPackages(Arrays.asList("org.example.message"));
		return activeMQConnectionFactory.createConnection();
	}

	public static Queue createQueue(String queueName) {
		try {
			Connection connection = getConnection();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			return session.createQueue(queueName);
		} catch (JMSException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}

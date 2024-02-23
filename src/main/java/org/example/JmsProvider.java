package org.example;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

public class JmsProvider
{
	private static final String USERNAME = "admin";
	private static final String PASSWORD = "admin";
	private static final String BROKER_URL = "tcp://localhost:61616";
	
	public static Connection getConnection() throws JMSException
	{
		System.out.println("Creating a connection");
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKER_URL);
		return connectionFactory.createConnection();
	}
	
}

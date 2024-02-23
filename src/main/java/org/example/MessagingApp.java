package org.example;

import javax.jms.JMSException;

public class MessagingApp
{
	
	public static void main(String[] args)
	{
		
		try {
			MessageSender sender = new MessageSender();
			sender.sendMessage("Test","Hello Mayank here..");
			
			MessageReciever reciever = new MessageReciever();
			reciever.recieveMessage("Test");
			
			System.out.println("Operation Successful");
		} catch (JMSException e) {
			System.out.printf("Exception occurred while performing the operation.");
			e.printStackTrace();
		}
		
	}
	
}

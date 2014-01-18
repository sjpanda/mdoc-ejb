package messageSenders;

import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Sender {

//	@Resource(name = "rigolo", mappedName = "topic_rigolo")
//	private static Topic topic;
//	@Resource(name = "factory", mappedName = "JTCF")
//	private static TopicConnectionFactory factory;
//	private static TopicSession session;
//	private static TopicPublisher sender;
//	
//	public static void publish(String value){
//		TopicConnection tc;
//		try {
//			tc = factory.createTopicConnection();
//			session = tc.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
//			sender = session.createPublisher(topic);
//			TextMessage msg = session.createTextMessage();
//			msg.setText("MDB : " + value);
//			sender.publish(msg);
//		} catch (JMSException e) {
//			e.printStackTrace();
//		}
//	}
	
	
	public static void publish(String value){
		try {
			InitialContext context = new InitialContext();
	        QueueConnectionFactory factory=(QueueConnectionFactory)context.lookup("ConnectionFactory");  
	          
	        QueueConnection queueConnection= factory.createQueueConnection();  
	          
	        QueueSession queueSession =queueConnection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);  
	          
	          
	        Queue queue = (Queue)context.lookup("queue/myqueue");  
	        TextMessage textMessage = queueSession.createTextMessage("See you server");  
	          
	        QueueSender sender = queueSession.createSender(queue);  
	          
	        sender.send(textMessage);  
	          
	        queueSession.close();  
	        queueConnection.close();  
	          
	        System.out.println("Message sent"); 
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}
	
	
}

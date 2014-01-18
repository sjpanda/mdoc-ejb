package messageSenders;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;

public class Sender {

	@Resource(name = "rigolo", mappedName = "topic_rigolo")
	private static Topic topic;
	@Resource(name = "factory", mappedName = "JTCF")
	private static TopicConnectionFactory factory;
	private static TopicSession session;
	private static TopicPublisher sender;
	
	public static void publish(String value){
		TopicConnection tc;
		try {
			tc = factory.createTopicConnection();
			session = tc.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			sender = session.createPublisher(topic);
			TextMessage msg = session.createTextMessage();
			msg.setText("MDB : " + value);
			sender.publish(msg);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}

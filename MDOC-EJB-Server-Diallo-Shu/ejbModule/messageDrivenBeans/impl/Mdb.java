package messageDrivenBeans.impl;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(
			propertyName = "destination",
			propertyValue = "topic_rigolo"),
		@ActivationConfigProperty(
			propertyName = "destinationType",
			propertyValue = "javax.jms.Topic")})
public class Mdb implements MessageListener {

	@Override
	public void onMessage(Message inMessage) {
		try {
			System.out.println(((TextMessage)inMessage).getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}

package messageDrivenBeans.impl;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(
		activationConfig = {  
				@ActivationConfigProperty(propertyName="destinationType",propertyValue="javax.jms.Queue"),  
				@ActivationConfigProperty(propertyName="destination",propertyValue="queue/myqueue")  
		}  
		)
public class Mdb implements MessageListener {
	public Mdb (){}

	@Override
	public void onMessage(Message inMessage) {
		try {
			System.out.println(((TextMessage)inMessage).getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}

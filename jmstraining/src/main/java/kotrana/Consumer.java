package kotrana;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Consumer {

	public static void main(String[] args) {
		//		Scanner scn = new Scanner(System.in);
		//		System.out.println("Code");
		//		String code = scn.nextLine();

		try {
			ConnectionFactory cnxFactory  = new ActiveMQConnectionFactory("tcp://localhost:61616");
			Connection cnx = cnxFactory.createConnection();
			cnx.start();
			Session session = cnx.createSession(false, Session.AUTO_ACKNOWLEDGE);//avec accuse de reception
			//Destination dest  = session.createQueue("kotrana.queue");
			Destination dest = session.createTopic("kotrana.topics");
			//MessageConsumer msg = session.createConsumer(dest , "code='"+code+"'");
			MessageConsumer msg = session.createConsumer(dest);
			msg.setMessageListener((message) -> {
				if (message instanceof TextMessage) {
					try {
						TextMessage txtMsg = (TextMessage) message ;
						System.out.println("Reception du message : "+txtMsg.getText());
					} catch (JMSException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
					);

		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

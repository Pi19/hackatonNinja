package kotrana;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Producer {

	public static void main(String[] args) {
		//		Scanner scn = new Scanner(System.in);
		//		System.out.println("Vers : ");
		//		String code  = scn.nextLine();
		try {

			ConnectionFactory cnxFactory  = new ActiveMQConnectionFactory("tcp://localhost:61616");
			Connection cnx = cnxFactory.createConnection();
			cnx.start();
			Session session = cnx.createSession(false, Session.AUTO_ACKNOWLEDGE);//avec accuse de reception
			//	Destination dest  = session.createQueue("kotrana.queue");
			Destination dest = session.createTopic("kotrana.topics");

			MessageProducer msgProduce = session.createProducer(dest);
			msgProduce.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			TextMessage textMessage = session.createTextMessage();
			textMessage.setText("Bienvenu au broker");
			//textMessage.setStringProperty("code", code);
			msgProduce.send(textMessage);
			System.out.println("Envoi du message");
			session.close();
			cnx.close();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

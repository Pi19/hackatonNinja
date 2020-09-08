package kotrana;

import java.net.URI;

import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.TransportConnector;



public class ActiveMqBroker {

	public static void main(String[] args) {


		try {
			BrokerService brokerService = new BrokerService() ;
			brokerService.setPersistent(false);
			TransportConnector connector = new TransportConnector();
			connector.setUri(new URI("tcp://localhost:61616"));
			brokerService.addConnector(connector);
			brokerService.start();
		} catch (Exception e) {

			e.printStackTrace();
		}




	}

}

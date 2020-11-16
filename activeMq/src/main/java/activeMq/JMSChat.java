package activeMq;


import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.StreamMessage;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class JMSChat extends Application {

	public static void main(String[] args) {
		Application.launch(JMSChat.class);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setTitle("JMS Chat");
		BorderPane pane = new BorderPane();
		HBox hBox = new HBox();
		hBox.setPadding(new Insets(10));
		hBox.setSpacing(10);
		hBox.setBackground(new Background(new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY)));

		Label lblCode = new Label("Code:");
		TextField txtFielCode = new TextField("C1");
		txtFielCode.setPromptText("Code");

		Label lblHost = new Label("Host:");
		TextField txtFielHost = new TextField("localhost");
		txtFielHost.setPromptText("Host");

		Label lblPort = new Label("Port:");
		TextField txtFielPort = new TextField("61616");
		txtFielPort.setPromptText("Port");

		Button buttonConnecter = new Button("Connecter");
		hBox.getChildren().add(lblCode);
		hBox.getChildren().add(txtFielCode);
		hBox.getChildren().add(lblHost);
		hBox.getChildren().add(txtFielHost);
		hBox.getChildren().add(lblPort);
		hBox.getChildren().add(txtFielPort);
		hBox.getChildren().add(buttonConnecter);

		pane.setTop(hBox);

		VBox vBox = new VBox();
		GridPane gridePane = new GridPane();
		HBox hBox2 = new HBox();
		vBox.getChildren().add(gridePane);
		vBox.getChildren().add(hBox2);
		pane.setCenter(vBox);

		Label lblTo = new Label("To:");
		TextField txtFieldTo = new TextField("C1");txtFieldTo.setPrefWidth(200);
		Label lblMessage = new Label("Message : ");
		TextArea txtAreaMessage = new TextArea();
		txtAreaMessage.setPrefRowCount(2);txtAreaMessage.setPrefWidth(200);
		Button btnEnvoyer = new Button("Envoyer");
		Label labelImage = new Label("Image:");
		ComboBox<String> cbxImages = new ComboBox<String>();
		Button btnEnvoyerImage = new Button("Envoyer Image");

		gridePane.setPadding(new Insets(10));
		gridePane.setVgap(15);
		gridePane.setHgap(10);
		gridePane.add(lblTo, 0, 0);	gridePane.add(txtFieldTo, 1, 0);

		gridePane.add(lblMessage, 0, 1);gridePane.add(txtAreaMessage, 1, 1);
		gridePane.add(btnEnvoyer, 2, 1);
		gridePane.add(labelImage, 0, 2);
		gridePane.add(cbxImages, 1, 2);
		gridePane.add(btnEnvoyerImage, 2, 2);


		Scene scene = new Scene(pane,800,600);
		primaryStage.setScene(scene);
		primaryStage.show();

		buttonConnecter.setOnAction((event) ->{
			try {
				String codeUser = txtFielCode.getText();
				String  host = txtFielHost.getText();
				int port = Integer.parseInt(txtFielPort.getText());
				ConnectionFactory cnxFactory  = new ActiveMQConnectionFactory("tcp://"+host+":"+port);
				Connection cnx = cnxFactory.createConnection();
				cnx.start();
				Session session = cnx.createSession(false, Session.AUTO_ACKNOWLEDGE);
				Destination dest = session.createTopic("chat.topics");
				MessageConsumer msg = session.createConsumer(dest);
				msg.setMessageListener((message) -> {

					try {
						if (message instanceof TextMessage) {

							TextMessage txtMsg = (TextMessage) message ;
							System.out.println("Reception du message : "+txtMsg.getText());
						}else if (message instanceof StreamMessage) {

						}
					} catch (JMSException e) {

						e.printStackTrace();
					}

				}
						);


			}  catch (Exception e) {
				e.printStackTrace();
			}
		});

	}

}

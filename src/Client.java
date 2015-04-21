import Client.DisplayFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.websocket.ClientEndpoint;
import javax.websocket.ClientEndpointConfig;
import javax.websocket.CloseReason;
import javax.websocket.DeploymentException;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import org.glassfish.tyrus.client.ClientManager;

@ClientEndpoint
public class Client implements ActionListener{
	private static final String SENT_MESSAGE = "Hello World";
	private static CountDownLatch messageLatch;
	private Logger logger = Logger.getLogger(this.getClass().getName());
	private DisplayFrame clientFrame;

	public Client(){
		clientFrame = new DisplayFrame("Client", this);
		System.out.println("Initialized");
	}
	
	public void actionPerformed(ActionEvent event){
		if(event.getSource() == clientFrame.loginButton){
		}
	}

	public void setupClient(){
		try {
			messageLatch = new CountDownLatch(1);

			final ClientEndpointConfig cec = ClientEndpointConfig.Builder.create().build();

			ClientManager client = ClientManager.createClient();
			client.connectToServer(new Client(), cec, new URI("ws://localhost:8025/websockets/board"));
		} catch (Exception e) {
			System.out.println("Failed to contact server.");
			e.printStackTrace();
		}

		System.out.println("connected");
	}

	@OnOpen
	public void onOpen(Session session) {
		logger.info("Connected ... " + session.getId());
		try {
			session.getBasicRemote().sendText("start");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@OnMessage
	public String onMessage(String message, Session session) {
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		try {
			logger.info("Received ...." + message);
			String userInput = bufferRead.readLine();
			return userInput;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		logger.info(String.format("Session %s close because of %s", session.getId(), closeReason));
	}

	public static void main(String[] args) {
		Client me = new Client();
	}
}

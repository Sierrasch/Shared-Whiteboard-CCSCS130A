import Client.DisplayFrame;
import Operations.insertOperation;
import Shared.ClientLogin;
import Shared.Element;
import Shared.ElementContainer;
import Shared.TypeIdentifier;
import Shared.util;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
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
import javax.websocket.CloseReason.CloseCodes;

import org.glassfish.tyrus.client.ClientManager;
import org.omg.CORBA.FREE_MEM;

import Client.clientProcessor;

import com.google.gson.Gson;

@ClientEndpoint
public class Client implements MouseListener, MouseMotionListener, ActionListener{
	private Logger logger = Logger.getLogger(this.getClass().getName());
	private DisplayFrame clientFrame;
	Session activeSession = null;
	Gson gson = util.getGSON();
	private long tempCounter = -1;
	clientProcessor processor = new clientProcessor();
	int localFreeNode = 0;
	String id = "no_id";
	String choosenDrawType = "rect";
	boolean scalingDrawing = false;
	int startingx = 0;
	int startingy = 0;

	public Client(){
		
		clientFrame = new DisplayFrame("Client", this);
		System.out.println("InitializedClient");
	}

	public void actionPerformed(ActionEvent event){
		if(event.getSource() == clientFrame.loginButton){
			setupClient(clientFrame.serverURIInput.getText(), clientFrame.userNameInput.getText());
		}
		else if(event.getSource() == clientFrame.chatEntry){
			if(clientFrame.chatEntry.getText().equals("")) return;
			/*clientFrame.appendToPane("User: ", Color.RED);
			clientFrame.appendToPane(clientFrame.chatEntry.getText() + '\n', Color.BLACK);*/
			
			// Adding text to the chat panel should actually be handlex exclusively on receiving a message
			// in order to ensure that the message order is the same on every client.
			//Communicator.sendChat(clientFrame.chatEntry.getText());
			clientFrame.chatEntry.setText("");
			System.out.println(event.getActionCommand());
			System.out.println(event.getActionCommand().charAt(event.getActionCommand().length()-1));
		}
	}
	
	// TODO: Drawing functionality not fully implemented yet.
	public void mousePressed(MouseEvent event){
		if(scalingDrawing == true)
			return;
		scalingDrawing =  true;
		startingx = event.getX();
		startingy = event.getY();
		
		
	}
	public void mouseDragged(MouseEvent e){
		if(scalingDrawing == false)
			return;
	}
	
	public void mouseReleased(MouseEvent event){
		if(scalingDrawing == false)
			return;
		System.out.println("Something Pressed"+ event.getX()+","+event.getY());
		Element e = Element.rectElement(startingx, startingy,  event.getX()-startingx, event.getY()-startingy,id + this.localFreeNode,id , this.localFreeNode);
		clientFrame.elements.put(e);
		insertOperation io = new insertOperation(id + this.localFreeNode, null, tempCounter, "rect", e.attributes);
		processor.sendInsert(io, activeSession);
		clientFrame.repaint();
		localFreeNode++;
		scalingDrawing =  false;
	}
	
	public void mouseExited(MouseEvent event){
		
	}
	
	public void mouseEntered(MouseEvent event){}
	public void mouseClicked(MouseEvent event){}
	public void mouseMoved(MouseEvent event){}

	public void setupClient(String uri, String userName){
		try {
			final ClientEndpointConfig cec = ClientEndpointConfig.Builder.create().build();

			ClientManager client = ClientManager.createClient();
			Session newSession = client.connectToServer(this, cec, new URI(uri));
			ClientLogin login = new ClientLogin(userName);
			if(activeSession != null){
				activeSession.close();
			}
			activeSession = newSession;
			id = activeSession.getId();
			activeSession.getBasicRemote().sendText(gson.toJson(login));

		} catch (Exception e) {
			System.out.println("Failed to contact server.");
			e.printStackTrace();
		}
		
		try {
			activeSession.getBasicRemote().sendText("Hello!");
			activeSession.getBasicRemote().sendText("quit");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@OnOpen
	public void onOpen(Session session) {
		logger.info("Connected ... " + session.getId());
	}

	@OnMessage
	public void onMessage(String message, Session session) {
		logger.info("Received ...." + message);
		TypeIdentifier typeID = gson.fromJson(message, TypeIdentifier.class);

		switch (typeID.type) {
		case "init":
			break;
		case "failed":
			break;
		case "insert":
			break;
		case "delete":
			break;
		case "modify":
			break;
		case "user_connect":
			break;
		case "user_disconnect":
			try {
				session.close(new CloseReason(CloseCodes.NORMAL_CLOSURE, "Game ended"));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			break;
		}

		System.out.println("Recieved Message with type: " + typeID.type);
	}

	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		logger.info(String.format("Session %s close because of %s", session.getId(), closeReason));
	}

	public static void main(String[] args) {
		Client me = new Client();
	}
}

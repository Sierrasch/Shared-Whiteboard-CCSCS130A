import Client.DisplayFrame;
import Client.Communicator;
import Shared.ClientLogin;
import Shared.Element;
import Shared.ElementContainer;
import Shared.TypeIdentifier;
import Shared.util;
import Client.Communicator;

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

import com.google.gson.Gson;

@ClientEndpoint
public class Client implements MouseListener, MouseMotionListener, ActionListener{
	private Logger logger = Logger.getLogger(this.getClass().getName());
	private DisplayFrame clientFrame;
	Session mySession;
	Session activeSession = null;
	Gson gson = util.getGSON();
	boolean drawing = false;
	private int tempCounter = -1;
	ElementContainer elements;
	int startX;
	int startY;
	final String tempUser = "@tEmP@";
	String currentShape = "";
	boolean fill = true;

	public Client(){
		elements = new ElementContainer();
		clientFrame = new DisplayFrame("Client", elements, this);
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

			// Adding text to the chat panel should actually be handled exclusively on receiving a message
			// in order to ensure that the message order is the same on every client.
			Communicator.sendChat(clientFrame.chatEntry.getText());
			clientFrame.chatEntry.setText("");
			System.out.println(event.getActionCommand());
			System.out.println(event.getActionCommand().charAt(event.getActionCommand().length()-1));
		}
		else if(event.getSource() == clientFrame.rectButton){
			currentShape = "rect";
		}
		else if(event.getSource() == clientFrame.ellipseButton){
			currentShape = "ellipse";
		}
		else if(event.getSource() == clientFrame.fillButton){
			fill = !fill;
			if(fill){
				clientFrame.fillButton.setText("Fill: ON");
			}
			else{
				clientFrame.fillButton.setText("Fill: OFF");
			}
		}
	}

	// TODO: Drawing functionality not fully implemented yet.
	public void mousePressed(MouseEvent event){
		drawing = true;
	}

	public void mouseDragged(MouseEvent e){
		elements.remove(tempUser + tempCounter);
		tempCounter--;
		
		if(drawing){
			if(currentShape.equals("rect")){
				String[] keys = {"x", "y", "width", "height", "fill"};
				String[] vals = {(startX < e.getX() ? startX : e.getX()) + "",
						(startY < e.getY() ? startY : e.getY()) + "",
						Math.abs(startX - e.getX()) + "",
						Math.abs(startY - e.getY()) + "",
						(fill ? "BLACK" : null)};	
				elements.put(new Element("rect", keys, vals, tempUser, tempCounter));
			}
		}
	}

	public void mouseReleased(MouseEvent event){
		drawing = false;
		elements.remove(tempUser + tempCounter);
		Communicator.addElement(new Element(null));	// This needs to be implemented in communicator.
	}

	public void mouseExited(MouseEvent event){
		elements.remove(tempUser + tempCounter);
		drawing = false;
	}

	public void mouseEntered(MouseEvent event){}
	public void mouseClicked(MouseEvent event){}
	public void mouseMoved(MouseEvent event){}

	public void setupClient(String uri, String userName){
		try {
			final ClientEndpointConfig cec = ClientEndpointConfig.Builder.create().build();

			ClientManager client = ClientManager.createClient();
			mySession = client.connectToServer(this, cec, new URI(uri));
			Session newSession = client.connectToServer(this, cec, new URI(uri));
			ClientLogin login = new ClientLogin(userName);
			if(activeSession != null){
				activeSession.close();
			}
			activeSession = newSession;
			activeSession.getBasicRemote().sendText(gson.toJson(login));

		} catch (Exception e) {
			System.out.println("Failed to contact server.");
			e.printStackTrace();
		}

		try {
			mySession.getBasicRemote().sendText("Hello!");
			mySession.getBasicRemote().sendText("quit");
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

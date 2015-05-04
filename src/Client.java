import Client.DisplayFrame;
import Operations.insertOperation;
import Shared.ClientIntialization;
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
import java.util.Arrays;
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
	clientProcessor processor = new clientProcessor();

	Gson gson = util.getGSON();


	int localFreeNode = 0;
	String id = "no_id";
	boolean scalingDrawing = false;
	int startingx = 0;
	int startingy = 0;
	final String tempUser = "@tEmP@";
	String currentShape = "rect";
	private int tempCounter = -1;
	private int lastCounter = -1;
	String fill = "green";


	public Client(){
		clientFrame = new DisplayFrame("Client", this);
		clientFrame.fillButton.setText("Fill: " + fill);
		System.out.println("InitializedClient");
	}

	public void actionPerformed(ActionEvent event){
		if(event.getSource() == clientFrame.loginButton){
			setupClient(clientFrame.serverURIInput.getText(), clientFrame.userNameInput.getText());
			return;
		}
		else if(event.getSource() == clientFrame.chatEntry){
			if(clientFrame.chatEntry.getText().equals("")) return;
			clientFrame.appendToPane("User: ", Color.RED);
			clientFrame.appendToPane(clientFrame.chatEntry.getText() + '\n', Color.BLACK);

			// Adding text to the chat panel should actually be handled exclusively on receiving a message
			// in order to ensure that the message order is the same on every client.
			//Communicator.sendChat(clientFrame.chatEntry.getText());
			clientFrame.chatEntry.setText("");
			return;
		}
		else if(event.getSource() == clientFrame.fillButton){
			if(fill.equals("red")){
				fill = "green";
			}
			else if(fill.equals("green")){
				fill = "red";
			}
			clientFrame.fillButton.setText("Fill: " + fill);
		}

		clientFrame.rectButton.setBackground(Color.WHITE);
		clientFrame.ellipseButton.setBackground(Color.WHITE);
		clientFrame.pathButton.setBackground(Color.WHITE);
		if(event.getSource() == clientFrame.rectButton){
			clientFrame.rectButton.setBackground(Color.RED);
			currentShape = "rect";
		}
		else if(event.getSource() == clientFrame.ellipseButton){
			clientFrame.ellipseButton.setBackground(Color.RED);
			currentShape = "ellipse";
		}
		else if(event.getSource() == clientFrame.pathButton){
			clientFrame.pathButton.setBackground(Color.RED);
			currentShape = "path";
		}
	}

	public void mousePressed(MouseEvent event){
		if(scalingDrawing == true)
			return;
		scalingDrawing =  true;
		startingx = event.getX();
		startingy = event.getY();
	}

	public void mouseDragged(MouseEvent event){
		clientFrame.elements.remove(tempUser + lastCounter);
		if(scalingDrawing){
			if(currentShape.equals("rect")){
				String[] keys = {"cx", "cy", "rx", "ry", "fill"};
				String[] vals = {(Math.round((startingx < event.getX() ? startingx : event.getX()) + 
						Math.abs(event.getX()-startingx) / 2.0)) + "",
						Math.round((startingy < event.getY() ? startingy : event.getY()) + 
						Math.abs(event.getY()-startingy) / 2.0) + "", 
						Math.round(Math.abs(event.getX()-startingx) / 2.0) + "",
						Math.round(Math.abs(event.getY()-startingy) / 2.0) + "",
						fill};
				clientFrame.elements.put(new Element("ellipse", keys, vals, tempUser, tempCounter));
			}
			lastCounter = tempCounter;
			tempCounter--;
		}
		clientFrame.repaint();
	}

	public void mouseReleased(MouseEvent event){
		scalingDrawing = false;
		clientFrame.elements.remove(tempUser + lastCounter);
		if(!scalingDrawing)
			return;
		if(currentShape.equals("rect")){
			String[] keys = {"x", "y", "width", "height", "fill"};
			String[] vals = {(startingx < event.getX() ? startingx : event.getX()) + "",
					(startingy < event.getY() ? startingy : event.getY()) + "", 
					Math.abs(event.getX()-startingx) + "",
					Math.abs(event.getY()-startingy) + "",
					fill};
			insertOperation io = new insertOperation("rect", keys, vals, id, this.localFreeNode, null);
			processor.sendInsert(io, activeSession);
			localFreeNode++;
		}
		else if(currentShape.equals("ellipse")){
			String[] keys = {"cx", "cy", "rx", "ry", "fill"};
			String[] vals = {(Math.round((startingx < event.getX() ? startingx : event.getX()) + 
					Math.abs(event.getX()-startingx) / 2.0)) + "",
					Math.round((startingy < event.getY() ? startingy : event.getY()) + 
					Math.abs(event.getX()-startingx) / 2.0) + "", 
					Math.round(Math.abs(event.getX()-startingx) / 2.0) + "",
					Math.round(Math.abs(event.getY()-startingy) / 2.0) + "",
					fill};
			insertOperation io = new insertOperation("ellipse", keys, vals, id, this.localFreeNode, null);
			processor.sendInsert(io, activeSession);
			localFreeNode++;
		}
		clientFrame.repaint();
		scalingDrawing =  false;
	}

	public void mouseExited(MouseEvent event){
		clientFrame.elements.remove(tempUser + lastCounter);
		tempCounter--;
		scalingDrawing = false;
	}

	public void mouseEntered(MouseEvent event){}
	public void mouseClicked(MouseEvent event){
		clientFrame.repaint();
	}
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
			processor.sendJoin(login, activeSession, clientFrame.elements);

		} catch (Exception e) {
			System.out.println("Failed to contact server.");
			e.printStackTrace();
		}
		/*
		try {
			//activeSession.getBasicRemote().sendText("Hello!");
			//activeSession.getBasicRemote().sendText("quit");
		} catch (IOException e) {
			e.printStackTrace();
		}
		 */
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
			ClientIntialization ci = gson.fromJson(message, ClientIntialization.class);
			processor.recieveJoin(ci, session, clientFrame.elements);
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

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;

import org.glassfish.tyrus.server.Server;

import com.google.gson.Gson;

import Operations.deleteOperation;
import Operations.insertOperation;
import Operations.modifyOperation;
import Server.serverProcessor;
import Shared.ClientLogin;
import Shared.ElementContainer;
import Shared.SourceObject;
import Shared.TypeIdentifier;
import Shared.operationProcessor;
import Shared.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.websocket.CloseReason;
import javax.websocket.CloseReason.CloseCodes;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
 
@ServerEndpoint(value = "/board")
public class WhiteboardServer {
	private serverProcessor processor;
	private Gson gson = util.getGSON();
    private ElementContainer ec;
    public WhiteboardServer () {
    	processor = new serverProcessor();
    	ec =  new ElementContainer();
    }
    public void runServer() {
    	Server server = new Server("ws://localhost", 8025, "/websockets", WhiteboardServer.class);
    	
        try {
            server.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Please press a key to stop the server.");
            reader.readLine();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            server.stop();
        }
    }
 
    private Logger logger = Logger.getLogger(this.getClass().getName());
 
    @OnOpen
    public void onOpen(Session session) {
        logger.info("Connected ... " + session.getId());
        System.out.println("Client connected");
    }
 
    @OnMessage
    public String onMessage(String message, Session session) {
    	String type = util.getType(message,gson);
    	
        switch (type) {
        case "join":
        	ClientLogin loginInfo = gson.fromJson(message, ClientLogin.class);
        	processor.join(loginInfo,session,ec);
        	break;
        case "insert":
        	insertOperation insert = gson.fromJson(message, insertOperation.class);
        	processor.recieveInsert(insert,session,ec);
        	break;
        case "delete":
        	deleteOperation delete = gson.fromJson(message, deleteOperation.class);
        	processor.recieveDelete(delete,session,ec);
        	break;
        case "modify":
        	modifyOperation modify = gson.fromJson(message, modifyOperation.class);
        	processor.recieveModify(modify,session,ec);
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
        
        System.out.println("Recieved Message with type: " + type);
        return null;
    }
 
    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        logger.info(String.format("Session %s closed because of %s", session.getId(), closeReason));
    }
    
    public static void main(String[] args) {
    	WhiteboardServer whiteboard = new WhiteboardServer();
        whiteboard.runServer();
    }
}
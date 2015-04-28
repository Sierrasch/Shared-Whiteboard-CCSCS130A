package Client;
import java.io.IOException;

import javax.websocket.Session;

import com.google.gson.Gson;

import Operations.deleteOperation;
import Operations.insertOperation;
import Operations.modifyOperation;
import Shared.ClientIntialization;
import Shared.ClientLogin;
import Shared.Element;
import Shared.ElementContainer;
import Shared.operationProcessor;
import Shared.util;


public class clientProcessor implements operationProcessor {

	private Gson gson = util.getGSON();

	
	public void recieveJoin(ClientIntialization loginInfo, Session session, ElementContainer ec) {
		for (Element element : loginInfo.doc) {
			ec.put(element);
		}
		
	}
	public void sendJoin(ClientLogin loginInfo, Session session, ElementContainer ec) {
		try {
			session.getBasicRemote().sendText(gson.toJson(loginInfo));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void recieveInsert(insertOperation operation, Session session, ElementContainer ec) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void recieveDelete(deleteOperation operation, Session session, ElementContainer ec) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void recieveModify(modifyOperation operation, Session session, ElementContainer ec) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendInsert(insertOperation operation, Session session) {
		/*
		try {
			session.getBasicRemote().sendText(util.getGSON().toJson(operation));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}

	@Override
	public void sendDelete(deleteOperation operation, Session session) {
		try {
			session.getBasicRemote().sendText(util.getGSON().toJson(operation));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void sendModify(modifyOperation operation, Session session) {
		try {
			session.getBasicRemote().sendText(util.getGSON().toJson(operation));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}

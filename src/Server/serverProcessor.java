package Server;

import java.io.IOException;
import java.util.HashMap;

import javax.websocket.Session;

import com.google.gson.Gson;

import Operations.deleteOperation;
import Operations.insertOperation;
import Operations.modifyOperation;
import Shared.ClientIntialization;
import Shared.ClientLogin;
import Shared.ElementContainer;
import Shared.operationProcessor;
import Shared.util;

public class serverProcessor implements operationProcessor {
	HashMap<String,String> users = new HashMap<String, String>();
	Gson g = util.getGSON();
	
	public void join(ClientLogin loginInfo, Session session, ElementContainer ec) {
		users.put(loginInfo.name, session.getId());
		String[] userArray = new String[users.size()];
		users.values().toArray(userArray);
		ClientIntialization ci = new ClientIntialization(session.getId(), ec.toArray(), userArray);
		
		try {
			session.getBasicRemote().sendText(g.toJson(ci));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void recieveInsert(insertOperation operation, Session session, ElementContainer ec) {
		ec.put(operation.)
		
	}

	@Override
	public void recieveDelete(deleteOperation operation, Session session, ElementContainer ec) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void recieveModify(modifyOperation operation, Session session, ElementContainer ec) {
		// TODO Auto-generated method stub
		
	}

	public void sendInsert(insertOperation operation, Session session) {
		try {
			session.getBasicRemote().sendText(util.getGSON().toJson(operation));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

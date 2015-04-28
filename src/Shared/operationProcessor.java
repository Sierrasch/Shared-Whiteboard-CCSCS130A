package Shared;

import javax.websocket.Session;

import Operations.deleteOperation;
import Operations.insertOperation;
import Operations.modifyOperation;

public interface operationProcessor {
	//public void join(ClientLogin loginInfo, Session session, ElementContainer ec);
	public void recieveInsert(insertOperation operation, Session session, ElementContainer ec);
	public void recieveDelete(deleteOperation operation, Session session, ElementContainer ec);
	public void recieveModify(modifyOperation operation, Session session, ElementContainer ec);
	public void sendInsert(insertOperation operation, Session session);
	public void sendDelete(deleteOperation operation, Session session);
	public void sendModify(modifyOperation operation, Session session);
}

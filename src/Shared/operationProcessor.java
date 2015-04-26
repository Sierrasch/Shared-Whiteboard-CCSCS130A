package Shared;

import javax.websocket.Session;

import Operations.deleteOperation;
import Operations.insertOperation;
import Operations.modifyOperation;

public interface operationProcessor {
	public void join(ClientLogin loginInfo, Session session);
	public void insert(insertOperation operation, Session session);
	public void delete(deleteOperation operation, Session session);
	public void modify(modifyOperation operation, Session session);
}

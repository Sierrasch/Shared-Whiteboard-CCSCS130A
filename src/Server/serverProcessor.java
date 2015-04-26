package Server;

import javax.websocket.Session;

import Operations.deleteOperation;
import Operations.insertOperation;
import Operations.modifyOperation;
import Shared.ClientLogin;
import Shared.operationProcessor;

public class serverProcessor implements operationProcessor {

	@Override
	public void join(ClientLogin loginInfo, Session session) {
		

	}

	@Override
	public void insert(insertOperation operation, Session session) {
		

	}

	@Override
	public void delete(deleteOperation operation, Session session) {
		

	}

	@Override
	public void modify(modifyOperation operation, Session session) {
		

	}

}

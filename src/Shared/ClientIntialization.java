package Shared;


public class ClientIntialization {
	public String type = "init";
	public String client_id; 
	public Element[] doc;
	public String[] users;
	public ClientIntialization (String client_id, Element[] doc, String[] users){
		this.client_id = client_id;
		this.doc = doc;
		this.users = users;
	}
}

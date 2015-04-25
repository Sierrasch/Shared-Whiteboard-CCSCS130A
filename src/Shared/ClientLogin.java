package Shared;

public class ClientLogin {
	public String type;
	public String name;
	public ClientLogin(String name)
	{
		this.name = name;
		this.type = "user_connect";
	}
}

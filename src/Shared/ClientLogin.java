package Shared;

public class ClientLogin implements Sanitizable {
	
	public String name;
	private boolean isClean;
	private String type;
	public ClientLogin(String name)
	{
		this.name = name;
		this.type = "user_connect";
		this.isClean = false;
	}
	@Override
	public void clean() {
		this.isClean = true;
		
	}
	@Override
	public boolean isClean() {
		return this.isClean;
	}
}

package Shared;

public class ClientLogin implements Sanitizable {
	
	public String name;
	public boolean isClean;
	public String type;
	public ClientLogin(String name)
	{
		this.name = name;
		this.type = "join";
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

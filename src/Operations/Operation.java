package Operations;

import Shared.SourceObject;

public abstract class Operation {
	public static final String INSERT = "insert";
	public static final String MODIFY = "MODIFY";
	public static final String DELETE = "DELETE";
	//public String type;
	public int tracker;
	public SourceObject src;
	
	
	
}

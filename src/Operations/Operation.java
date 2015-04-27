package Operations;

import java.util.HashMap;

import Shared.AttributeObject;
import Shared.SourceObject;

public abstract class Operation {
	public static final String INSERT = "insert";
	public static final String MODIFY = "MODIFY";
	public static final String DELETE = "DELETE";
	public String type;
	public int node_id;
	public int tracker;
	public SourceObject src;
	
	
	
}

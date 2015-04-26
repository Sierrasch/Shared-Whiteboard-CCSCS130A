package Operations;

import java.util.HashMap;

import Shared.AttributeObject;
import Shared.SourceObject;

public class insertOperation extends Operation{
	public static final String type = INSERT;
	public long node_id;
	public String data;
	public long before_node_id;
	public String element_type;
	public int tracker; // Only used when sending from client
	public SourceObject src; // Only used when sending from server
	public HashMap<String,String> attributes;
}

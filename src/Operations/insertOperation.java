package Operations;

import java.util.HashMap;

import Shared.AttributeObject;
import Shared.SourceObject;

public class insertOperation extends Operation{
	public static final String type = INSERT;
	public int node_id;
	public int before_node_id;
	public String element_type;
	public int tracker;
	public SourceObject src;
	public HashMap<String,String> attributes;
}

package Operations;

import java.util.HashMap;

import Shared.AttributeObject;
import Shared.SourceObject;

public class insertOperation extends Operation{
	public String type = INSERT;
	public int node_id;
	public String data;
	public long before_node_id;
	public String element_type;
	//public int tracker; // Only used when sending from client
	//public SourceObject src; // Only used when sending from server
	public HashMap<String,String> attributes;
	
	public insertOperation(int node_id, String data, long before_node_id, String element_type, HashMap<String,String> attributes){
		this.node_id = node_id;
		this.data =  data;
		this.before_node_id = before_node_id;
		this.element_type = element_type;
		this.attributes = attributes;
	}
	
	public insertOperation(String eType, String[] aNames, String[] aVals, String user, int id, String d){
		element_type = eType;
		this.data = d;
		this.node_id = id;
		src = new SourceObject(user, id);
		attributes = new HashMap<String,String>();
		for(int i = 0; i < aNames.length && i < aVals.length; i++){
			attributes.put(aNames[i], aVals[i]);
		}
		this.before_node_id = -1;
	}
}

package Shared;

import java.util.HashMap;

import Operations.insertOperation;

public class Element {
	public String element_type;
	public long node_id;
	public String data;
	public HashMap<String,String> attributes;
	public Element[] children;
	public SourceObject src;
	
	public Element(insertOperation iO){
		if(iO == null) return;
		element_type = iO.element_type;
		data = iO.data;
		node_id = iO.node_id;
		src.tracker = iO.tracker;
		if(iO.src != null){
			src = new SourceObject(iO.src.user, iO.src.tracker);
		}
		attributes = iO.attributes;
	}
}
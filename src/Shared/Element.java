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
	
	public Element(String eType, String[] aNames, String[] aVals, String tempUser, int tempTracker){
		element_type = eType;
		src.user = tempUser;
		src.tracker = tempTracker;
		attributes = new HashMap<String,String>();
		for(int i = 0; i < aNames.length && i < aVals.length; i++){
			attributes.put(aNames[i], aVals[i]);
		}
	}
}
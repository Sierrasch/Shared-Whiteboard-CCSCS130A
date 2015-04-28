package Shared;

import java.util.HashMap;

import Operations.insertOperation;

public class Element {
	public String element_type;
	public String node_id;
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
		src = new SourceObject(tempUser, tempTracker);
		attributes = new HashMap<String,String>();
		for(int i = 0; i < aNames.length && i < aVals.length; i++){
			attributes.put(aNames[i], aVals[i]);
		}
	}
	public Element() {
		// TODO Auto-generated constructor stub
	}
	public static Element rectElement(int x, int y, int width, int height, String node_id,String user,int tracker){
		Element e =  new Element();
		//if(iO == null) return;
		e.element_type = "rect";
		//data = iO.data;
		e.node_id = node_id;
		//src.tracker = iO.tracker;
		//if(iO.src != null){
		e.src = new SourceObject(user, tracker);
		//}
		HashMap<String,String> temp = new HashMap<String, String>();
		temp.put("x", Integer.toString(x));
		temp.put("y", Integer.toString(y));
		temp.put("width", Integer.toString(width));
		temp.put("height", Integer.toString(height));
		e.attributes = temp;
		return e;
	}
	public static Element textElement(int x, int y, String text, String node_id, String user,int tracker){
		Element e =  new Element();
		e.element_type = "text";
		
		e.data = text;
		e.node_id = node_id;
		//src.tracker = iO.tracker;
		//if(iO.src != null){
		e.src = new SourceObject(user, tracker);
		//}
		HashMap<String,String> temp = new HashMap<String, String>();
		temp.put("x", Integer.toString(x));
		temp.put("y", Integer.toString(y));
		e.attributes = temp;
		return e;
	}
	public static Element ellipseElement(int cx, int cy, int rx, int ry,String node_id, String user,int tracker){
		Element e =  new Element();
		e.element_type = "ellipse";
		
		//data = iO.data;
		e.node_id = node_id;
		//src.tracker = iO.tracker;
		//if(iO.src != null){
		e.src = new SourceObject(user, tracker);
		//}
		HashMap<String,String> temp = new HashMap<String, String>();
		temp.put("cx", Integer.toString(cx));
		temp.put("cy", Integer.toString(cy));
		temp.put("rx", Integer.toString(rx));
		temp.put("ry", Integer.toString(ry));
		e.attributes = temp;
		return e;
	}
	public static Element pathElement(String d, String node_id, String user,int tracker){
		Element e =  new Element();
		e.element_type = "path";
		//data = iO.data;
		e.node_id = node_id;
		//src.tracker = iO.tracker;
		//if(iO.src != null){
		e.src = new SourceObject(user, tracker);
		//}
		HashMap<String,String> temp = new HashMap<String, String>();
		temp.put("d", d);
		
		e.attributes = temp;
		return e;
	}
	
}
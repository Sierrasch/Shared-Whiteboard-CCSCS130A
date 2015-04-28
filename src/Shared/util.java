package Shared;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Iterator;

import com.google.gson.Gson;

public class util {
	public static Gson getGSON(){
		return new Gson();
	}
	public static String getType(String jsonText, Gson gson){

		TypeIdentifier thisObject = gson.fromJson(jsonText, TypeIdentifier.class);
		return thisObject.type;
	}


	public static void drawObjects(Iterator<Element> iterator, Graphics g){
		g.setColor(Color.BLACK);
		while(iterator.hasNext()){
			Element e = iterator.next();

			int x = -1;
			int y = -1;
			int width = -1;
			int height = -1;
			int cx = -1;
			int cy = -1;
			int rx = -1;
			int ry = -1;

			for(HashMap.Entry<String, String> entry : e.attributes.entrySet()){
				switch(entry.getKey()){
				case "x":
					x = Integer.parseInt(entry.getValue());
				case "y":
					y = Integer.parseInt(entry.getValue());
				case "width":
					width = Integer.parseInt(entry.getValue());
				case "height":
					height = Integer.parseInt(entry.getValue());
				case "cx":
					cx = Integer.parseInt(entry.getValue());
				case "cy":
					cy = Integer.parseInt(entry.getValue());
				case "rx":
					rx = Integer.parseInt(entry.getValue());
				case "ry":
					ry = Integer.parseInt(entry.getValue());
				}
			}

			switch(e.element_type){
			case "rect":
				if(x != -1 && y != -1 && width != -1 && height != -1){	
					g.drawRect(x, y, width, height);
				}
				break;
			case "ellipse":
				if(cx != -1 && cy != -1 && rx != -1 && ry != -1){
					g.drawOval(cx-rx, cy-ry, rx *2, ry*2);
				}
				break;
			case "text":
				if(x!= 0 && y!= 0){
					g.drawString(e.data, x, y);
				}
				break;
			case "path":
				break;
			}
		}
	}
}


/* testy thingy
	public static void main(String args[]){
		String object = "{\"type\" : \"init\", \"client_id\" : 5}";
		System.out.println(getType(object, getGSON()));
	}
}*/


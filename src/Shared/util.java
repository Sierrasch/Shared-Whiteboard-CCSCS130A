package Shared;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
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


	public static void drawObjects(Iterator<Element> iterator, Graphics gr){
		Graphics2D g = (Graphics2D)gr;

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
			String fill = null;
			String d = "";

			for(HashMap.Entry<String, String> entry : e.attributes.entrySet()){
				switch(entry.getKey()){
				case "x":
					x = Integer.parseInt(entry.getValue());
					break;
				case "y":
					y = Integer.parseInt(entry.getValue());
					break;
				case "width":
					width = Integer.parseInt(entry.getValue());
					break;
				case "height":
					height = Integer.parseInt(entry.getValue());
					break;
				case "cx":
					cx = Integer.parseInt(entry.getValue());
					break;
				case "cy":
					cy = Integer.parseInt(entry.getValue());
					break;
				case "rx":
					rx = Integer.parseInt(entry.getValue());
					break;
				case "ry":
					ry = Integer.parseInt(entry.getValue());
					break;
				case "fill":
					fill = entry.getValue();
					break;
				}
			}
			
			if(fill != null){
				Color c = parseColor(fill);
				g.setColor(c);
				g.setPaint(c);
			}
			else{
				g.setColor(Color.BLACK);
				g.setPaint(Color.BLACK);
			}

			switch(e.element_type){
			case "rect":
				if(x != -1 && y != -1 && width != -1 && height != -1){
					Rectangle s = new Rectangle(x, y, width, height);
					g.draw(s);
				}
				break;
			case "ellipse":
				if(cx != -1 && cy != -1 && rx != -1 && ry != -1){
					Ellipse2D s = new Ellipse2D.Double(cx-rx, cy-ry, rx *2, ry*2);
					g.draw(s);
				}
				break;
			case "text":
				if(x!= 0 && y!= 0){
					g.drawString(e.data, x, y);
				}
				break;
			case "path":
				drawPath(d, g);
				break;
			}
		}
	}

	public static void drawPath(String path, Graphics2D g2){
		if(path != ""){
			g2.setStroke(new BasicStroke(4.0f));
			GeneralPath polyline = new GeneralPath(GeneralPath.WIND_NON_ZERO);
			String[] pathTerms = path.split(" ");
			for(int i = 0; i < pathTerms.length; i++){
				switch(pathTerms[i]){
				case "M":
					polyline.moveTo(Double.parseDouble(pathTerms[i+1]), Double.parseDouble(pathTerms[i+2]));
					i+=2;
					break;
				case "L":
					polyline.lineTo(Double.parseDouble(pathTerms[i+1]), Double.parseDouble(pathTerms[i+2]));
					i+=2;
					break;
				case "H":
					break;
				case "V":
					break;
				case "C":
					break;
				case "S":
					break;
				case "Q":
					break;
				case "T":
					break;
				case "A":
					break;
				case "Z":
					polyline.closePath();
					break;

				}
				g2.draw(polyline);
			}
		}
	}

	public static Color parseColor(String c){
		switch(c.toLowerCase()){
		case "black":
			return Color.BLACK;
		case "blue":
			return Color.BLUE;
		case "cyan":
			return Color.CYAN;
		case "gray":
			return Color.GRAY;
		case "green":
			return Color.GREEN;
		case "magenta":
			return Color.MAGENTA;
		case "orange":
			return Color.ORANGE;
		case "pink":
			return Color.PINK;
		case "red":
			return Color.RED;
		case "white":
			return Color.WHITE;
		case "yellow":
			return Color.YELLOW;
		default:
			return Color.BLACK;
		}
	}
}

package Client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.google.gson.Gson;

import Shared.Element;
import Shared.ElementContainer;
import Shared.TypeIdentifier;
import Shared.util;

public class DrawPanel extends JPanel {
	ArrayList<Element> elements;
	ElementContainer eLeMeNtS = new ElementContainer();
	
	public DrawPanel(ElementContainer e) {
		super(true);
		elements = new ArrayList<Element>();
		eLeMeNtS = e;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Gson gson = util.getGSON();
		String jsonRectangle = "{\"element_type\":\"rect\","
				+"\"attributes\": {"
					+"\"x\" : 100,\"y\":300,\"width\":50,\"height\":150"
				+"},"
				+"\"data\" : \"foo\","
				+"\"children\" : ["
				+"]}";
		String jsonEllipse = "{\"element_type\":\"ellipse\","
				+"\"attributes\": {"
				+"\"cx\" : 300,\"cy\":200,\"rx\":50,\"ry\":150"
			+"},"
			+"\"data\" : \"foo\","
			+"\"children\" : ["
			+"]}";
		String jsonText = "{\"element_type\":\"text\","
				+"\"attributes\": {"
				+"\"x\" : 200,\"y\":100"
			+"},"
			+"\"data\" : \"yayyayayyayaya\","
			+"\"children\" : ["
			+"]}";
		Element element1 = gson.fromJson(jsonRectangle, Element.class);
		Element element2 = gson.fromJson(jsonEllipse, Element.class);
		Element element3 = gson.fromJson(jsonText, Element.class);
		Element[] myElements = {element1, element2, element3};
		
		util.drawObjects(myElements, g);
		/*
		for(int i = -100; i< 800; i +=100){
			for(int j=-100;j<800;j+=100){
				g.setColor(new Color((int)(256 * Math.random()),(int)(256 * Math.random()),(int)(256 * Math.random())));
				g.drawOval(45+i, 65+j, 60, 60);
				g.setColor(new Color((int)(256 * Math.random()),(int)(256 * Math.random()),(int)(256 * Math.random())));
				g.fillRect(74+i,25+j,2,100);
				int[] xs = {75+i,25+i,125+i};
				int[] ys = {25+j,125+j,125+j};
				g.setColor(new Color((int)(256 * Math.random()),(int)(256 * Math.random()),(int)(256 * Math.random())));
				g.drawPolygon(xs,ys,3);
			}
		}
		*/
	}

}

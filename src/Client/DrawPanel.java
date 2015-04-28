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
	ElementContainer elementContainer = new ElementContainer();
	
	public DrawPanel(ElementContainer e) {
		super(true);
		elements = new ArrayList<Element>();
	}
	public void setElements(ElementContainer e){
		elementContainer = e;
			
	}
	public void paint(Graphics g){
		super.paint(g);
		util.drawObjects(elementContainer.getValues(), g);
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		util.drawObjects(elementContainer.getValues(), g);
		
		/*
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
		*/
	}
	
}

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
	ElementContainer elementContainer = new ElementContainer();
	
	public DrawPanel(ElementContainer e) {
		super(true);
	}
	public void setElements(ElementContainer e){
		elementContainer = e;	
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		util.drawObjects(elementContainer.getValues(), g);
	}
}

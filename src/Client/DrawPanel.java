package Client;

import java.awt.Graphics;

import javax.swing.JPanel;

import Shared.ElementContainer;
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
		
		util.drawObjects(elementContainer.getValues(), g);
		util.drawObjects(elementContainer.getValues(), g);
	}
}

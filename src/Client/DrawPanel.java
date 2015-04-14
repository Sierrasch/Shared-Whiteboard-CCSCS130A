package Client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;

import javax.swing.JPanel;

public class DrawPanel extends JPanel {
	public DrawPanel() {
		super(true);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.BLUE);
		g.drawOval(45, 65, 60, 60);
		g.setColor(new Color((int)(256 * Math.random()),(int)(256 * Math.random()),(int)(256 * Math.random())));
		g.fillRect(74,25,2,100);
		int[] xs = {75,25,125};
		int[] ys = {25,125,125};
		g.drawPolygon(xs,ys,3);
	}
	
}

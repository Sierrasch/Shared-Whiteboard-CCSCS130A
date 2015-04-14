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
	}

}

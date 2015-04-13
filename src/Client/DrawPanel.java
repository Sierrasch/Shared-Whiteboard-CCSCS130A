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
		g.drawOval(50, 50, 100, 100);
	}
}

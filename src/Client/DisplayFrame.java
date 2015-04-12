package Client;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;

public class DisplayFrame extends JFrame {
	public DisplayFrame(String title) throws HeadlessException {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 600);
		setVisible(true);
	}
}
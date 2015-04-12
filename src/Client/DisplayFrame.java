package Client;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;

public class DisplayFrame extends JFrame {
	public DisplayPane(String title) throws HeadlessException {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 1000);
		setVisible(true);
		Thread.sleep(10000);
	}
}
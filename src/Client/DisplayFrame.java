package Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsConfiguration;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.Document;

import com.sun.xml.internal.ws.api.server.Container;

public class DisplayFrame extends JFrame {
	DrawPanel drawPanel;
	JTextField userNameInput;
	Document svgDocument;

	public DisplayFrame(String title) throws HeadlessException {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setupFrame();
		setSize(1000, 600);
		setVisible(true);
		while(true){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			drawPanel.repaint();
		}
	}

	private void setupFrame(){
		this.setLayout(new BorderLayout());

		drawPanel = new DrawPanel();
		drawPanel.setBackground(Color.BLACK);
		this.add(drawPanel, BorderLayout.CENTER);

		drawPanel.repaint();

		JPanel buttonsPanel = new JPanel();
		JPanel loginPanel = new JPanel();
		buttonsPanel.setLayout(new BorderLayout());
		buttonsPanel.setBackground(Color.WHITE);

		JLabel userNameLabel = new JLabel("Username: ");
		userNameInput = new JTextField();
		userNameInput.setColumns(15);
		loginPanel.setLayout(new FlowLayout());
		loginPanel.add(userNameLabel);
		loginPanel.add(userNameInput);

		buttonsPanel.add(loginPanel, BorderLayout.NORTH);
		this.add(buttonsPanel, BorderLayout.EAST);
	}
}

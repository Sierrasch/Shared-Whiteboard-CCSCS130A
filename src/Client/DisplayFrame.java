package Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsConfiguration;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.Document;

import Shared.Element;

import com.sun.xml.internal.ws.api.server.Container;


public class DisplayFrame extends JFrame {
	DrawPanel drawPanel;
	JTextField userNameInput;
	JTextField serverURIInput;
	Document svgDocument;
	Element[] myElements;
	
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
		JPanel loginPanel1 = new JPanel();
		JPanel loginPanel2 = new JPanel();
		JPanel loginPanel3 = new JPanel();
		JPanel loginPanelMain = new JPanel();
		buttonsPanel.setLayout(new BorderLayout());
		loginPanelMain.setLayout(new BorderLayout());
		buttonsPanel.setBackground(Color.WHITE);

		JLabel userNameLabel = new JLabel("Username: ");
		JLabel serverURILabel = new JLabel("Server URI: ");
		JButton loginButton = new JButton("Login");
		userNameInput = new JTextField();
		userNameInput.setColumns(15);
		serverURIInput = new JTextField();
		serverURIInput.setColumns(15);


		loginPanel1.setLayout(new FlowLayout());
		loginPanel2.setLayout(new FlowLayout());
		loginPanel3.setLayout(new FlowLayout());
		loginPanel1.add(userNameLabel,BorderLayout.NORTH);
		loginPanel1.add(userNameInput,BorderLayout.NORTH);
		loginPanel2.add(serverURILabel,BorderLayout.NORTH);
		loginPanel2.add(serverURIInput,BorderLayout.NORTH);
		loginPanel3.add(loginButton, BorderLayout.NORTH);

		loginPanelMain.add(loginPanel1, BorderLayout.NORTH);
		loginPanelMain.add(loginPanel2, BorderLayout.CENTER);
		buttonsPanel.add(loginPanelMain, BorderLayout.NORTH);
		buttonsPanel.add(loginPanel3, BorderLayout.CENTER);
		this.add(buttonsPanel, BorderLayout.EAST);
	}
}

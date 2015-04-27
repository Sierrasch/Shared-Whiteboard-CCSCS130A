package Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsConfiguration;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import Shared.Element;
import Shared.ElementContainer;


public class DisplayFrame extends JFrame {
	DrawPanel drawPanel;
	public JTextField userNameInput;
	public JTextField serverURIInput;
	public JButton loginButton;
	public JButton rectButton;
	public JButton ellipseButton;
	public JButton fillButton;
	Element[] myElements;
	JTextPane chatArea;
	public JTextField chatEntry;
	ElementContainer elements;

	public DisplayFrame(String title, ElementContainer els, ActionListener parent) throws HeadlessException {
		super(title);
		elements = els;

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setupFrame();
		loginButton.addActionListener(parent);
		chatEntry.addActionListener(parent);
		drawPanel.addMouseListener((MouseListener) parent);
		drawPanel.addMouseMotionListener((MouseMotionListener) parent);
		rectButton.addActionListener(parent);
		ellipseButton.addActionListener(parent);
		fillButton.addActionListener(parent);
		setSize(1000, 600);
		setVisible(true);
		repaint();
	}

	private void setupFrame(){
		this.setLayout(new BorderLayout());

		drawPanel = new DrawPanel(elements);
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
		loginButton = new JButton("Login");
		userNameInput = new JTextField();
		userNameInput.setColumns(25);
		serverURIInput = new JTextField();
		serverURIInput.setColumns(25);
		serverURIInput.setText("ws://0.0.0.0:8025/websockets/board");

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
		loginPanelMain.add(loginPanel3, BorderLayout.SOUTH);
		
		JPanel bPannel = new JPanel();
		rectButton = new JButton("Rectangle");
		ellipseButton = new JButton("Ellipse");
		fillButton = new JButton("Fill: OFF");
		bPannel.add(rectButton);
		bPannel.add(ellipseButton);
		bPannel.add(fillButton);

		JPanel chatPanel = new JPanel();
		chatPanel.setLayout(new BorderLayout());
		chatArea = new JTextPane();
		chatArea.setEditable(false);
		appendToPane("Hello User!\n", Color.BLUE);
		JScrollPane chatScrollPane = new JScrollPane(chatArea,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		chatPanel.add(chatScrollPane, BorderLayout.CENTER);
		chatEntry = new JTextField();
		chatPanel.add(chatEntry, BorderLayout.SOUTH);
		
		buttonsPanel.add(loginPanelMain, BorderLayout.NORTH);
		//buttonsPanel.add(loginPanel3, BorderLayout.CENTER);
		buttonsPanel.add(chatPanel, BorderLayout.CENTER);
		buttonsPanel.add(bPannel, BorderLayout.SOUTH);
		this.add(buttonsPanel, BorderLayout.EAST);
	}

	public void appendToPane(String msg, Color c)
	{
		chatArea.setEditable(true);
		StyleContext sc = StyleContext.getDefaultStyleContext();
		AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

		aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
		aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

		int len = chatArea.getDocument().getLength();
		chatArea.setCaretPosition(len);
		chatArea.setCharacterAttributes(aset, false);
		chatArea.replaceSelection(msg);
		chatArea.setEditable(false);
	}
}

package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class LoginPanel extends JPanel {
	
	private ShinyPancakeGUI myGUI;
	public JTextField myUsernameText;
	public JTextField myPasswordText;
	public JButton mySubmitBtn;
	public JButton myForgotPassBtn;
	public JButton myRegisterBtn;
	
	
	
	public LoginPanel(ShinyPancakeGUI theGUI) {
		super();
		myGUI = theGUI;
		myUsernameText = new JTextField(15);
		myPasswordText = new JTextField(15);
		mySubmitBtn = createSubmitButton("Submit", myGUI.myHomePanel);
		myForgotPassBtn = createForgotPassButton("Forgot Password", myGUI.myHomePanel);
		myRegisterBtn = createRegisterButton("Register", myGUI.myRegistrationPanel);
		
		buildPanel();
	}
	
	
	private void buildPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		
		JLabel title = new JLabel("Log In");
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 20));
		
		JPanel userPanel = new JPanel();
		userPanel.add(new JLabel("Username"));
		userPanel.add(myUsernameText);
		
		JPanel passPanel = new JPanel();
		passPanel.add(new JLabel("Password"));
		passPanel.add(myPasswordText);
		
		JPanel submitBtnPanel = new JPanel();
		submitBtnPanel.add(mySubmitBtn);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(myForgotPassBtn);
		buttonPanel.add(myRegisterBtn);
		
		this.add(title);
		this.add(userPanel);
		this.add(Box.createVerticalStrut(5));
		this.add(passPanel);
		this.add(submitBtnPanel);
		this.add(buttonPanel);
	}
	
	
	
	private JButton createSubmitButton(final String theStr, final JPanel thePanel) {
		final JButton button = new JButton(theStr);
		
		class MyActionListener implements ActionListener {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				
				// LOGIC HERE FOR SUBMITTING USER INFO TO DATABASE
				// - Justin
				
				myGUI.switchPanel(thePanel);
			}
		}
		button.addActionListener(new MyActionListener());
		return button;
	}
	
	
	
	private JButton createForgotPassButton(final String theStr, final JPanel thePanel) {
		final JButton button = new JButton(theStr);
		
		class MyActionListener implements ActionListener {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				
				// CURRENTLY JUST NAVIGATES BACK TO HOME PAGE. DOES NOTHING SPECIAL
				// - Justin
				
				myGUI.switchPanel(thePanel);
			}
		}
		button.addActionListener(new MyActionListener());
		return button;
	}
	
	
	
	private JButton createRegisterButton(final String theStr, final JPanel thePanel) {
		final JButton button = new JButton(theStr);
		
		class MyActionListener implements ActionListener {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				myGUI.switchPanel(thePanel);
			}
		}
		button.addActionListener(new MyActionListener());
		return button;
	}
	
	
}

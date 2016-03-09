package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Registration;

/**
 * The page that takes an input of log in credentials and checks them with
 * the database. If a match is found, will log the user in. If a match isn't
 * found, a warning message will display.
 * 
 * @author Justin Arnett
 * @version 4 March 2016
 */
@SuppressWarnings("serial")
public class LoginPanel extends JPanel implements PanelDisplay {
	
	private ShinyPancakeGUI myGUI;
	public JTextField myUsernameText;
	public JTextField myPasswordText;
	public JButton mySubmitBtn;
	public JButton myForgotPassBtn;
	public JButton myRegisterBtn;
	public JLabel myErrorLabel;
	
	
	/**
	 * Constructs the panel for displaying the login page.
	 * 
	 * @param theGUI The GUI frame.
	 */
	public LoginPanel(ShinyPancakeGUI theGUI) {
		super();
		myGUI = theGUI;
		myUsernameText = new JTextField(15);
		myPasswordText = new JTextField(15);
		mySubmitBtn = createSubmitButton("Log In", myGUI.myHomePanel);
		myForgotPassBtn = createForgotPassButton("Forgot Password", myGUI.myHomePanel);
		myRegisterBtn = createRegisterButton("Register", myGUI.myRegistrationPanel);
		myErrorLabel = new JLabel("");
		buildPanel();
	}
	
	/**
	 * Helper for building the panel display
	 */
	private void buildPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// The display title of the page
		JPanel titlePanel = new JPanel();
		JLabel title = new JLabel("Log In   ");
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 30));
		titlePanel.add(title);
		
		// Error popup panel
		JPanel errorPanel = new JPanel();
		myErrorLabel.setForeground(new Color(153, 0, 0));
		errorPanel.add(myErrorLabel);
		
		// Username label and textfield
		JPanel userPanel = new JPanel();
		userPanel.add(new JLabel("Username: "));
		userPanel.add(myUsernameText);
		
		// Password label and textfield
		JPanel passPanel = new JPanel();
		passPanel.add(new JLabel("Password: "));
		passPanel.add(myPasswordText);
		
		// Just the submit button
		JPanel submitBtnPanel = new JPanel();
		submitBtnPanel.add(mySubmitBtn);
		
		// misc buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(myForgotPassBtn);
		buttonPanel.add(myRegisterBtn);
		
		this.add(Box.createVerticalStrut(50));
		this.add(titlePanel);
		this.add(Box.createVerticalStrut(30));
		this.add(errorPanel);
		this.add(userPanel);
		this.add(passPanel);
		this.add(submitBtnPanel);
		this.add(Box.createVerticalStrut(30));
		this.add(buttonPanel);
	}
	
	/**
	 * Resets fields on the page to their default blank values.
	 */
	@Override
	public void resetFields() {
		myUsernameText.setText("");
		myPasswordText.setText("");
		myErrorLabel.setText("");
	}
	
	
	
	/**
	 * The button for submitting credentials for verification with the database, then
	 * logs the user in.
	 * 
	 * @param theStr Name of the button
	 * @param thePanel Name of the panel the button will redirect the page to.
	 * @return The created button.
	 */
	private JButton createSubmitButton(final String theStr, final PanelDisplay thePanel) {
		final JButton button = new JButton(theStr);
		
		class MyActionListener implements ActionListener {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				boolean isLoggedIn = false;
				
				// LOGIC HERE FOR CHECKING DATABASE TO VERIFY USER.
				// - Justin
				
				// myUsernameText.getText() is what you want to use
				// myPasswordText.getText()
				
				/*
				 * ***********************************************************
				 * 	Bill Sylvia
				 * ***********************************************************
				 */
				try {
					// Checks if username and password used are correct.
					isLoggedIn = Registration.userExists(myUsernameText.getText(), myPasswordText.getText());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				if (isLoggedIn) {
					myGUI.logIn(myUsernameText.getText());
					myGUI.switchPanel(thePanel);
				} else {
					myErrorLabel.setText("The username and/or password was incorrect.");
				}
			}
		}
		button.addActionListener(new MyActionListener());
		return button;
	}
	
	
	/**
	 * Creates the button for the forgot password button.
	 * 
	 * @param theStr Name of the button.
	 * @param thePanel The panel the button will redirect the page to.
	 * @return The newly created button.
	 */
	private JButton createForgotPassButton(final String theStr, final PanelDisplay thePanel) {
		final JButton button = new JButton(theStr);
		
		class MyActionListener implements ActionListener {
			/*
			 * ***********************************************************
			 * 	Bill Sylvia
			 * ***********************************************************
			 */
			String email;
			public void actionPerformed(final ActionEvent theEvent) {
				if(myUsernameText.getText().equals("")) {
		        	JOptionPane.showMessageDialog(
		        		    null, "Please enter your username.",
		        		    "Error",
		        		    JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						email = Registration.findEmail(myUsernameText.getText());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					if (email.equals("0")) {
			        	JOptionPane.showMessageDialog(
			        		    null, myUsernameText.getText() + " is not a registered user.",
			        		    "Error",
			        		    JOptionPane.ERROR_MESSAGE);
					} else if (email.equals("-1")) {
			        	JOptionPane.showMessageDialog(
			        		    null, myUsernameText.getText() + " does not have an associated email address.",
			        		    "Error",
			        		    JOptionPane.ERROR_MESSAGE);
					} else {
			        	JOptionPane.showMessageDialog(
			        		    null, "An email will be sent to " + email + " shortly.\n(Not really but we are pretending)");
						myGUI.switchPanel(thePanel);
					}
				}
				/*
				 * ***********************************************************
				 * 	Bill Sylvia
				 * ***********************************************************
				 */
			}
		}
		button.addActionListener(new MyActionListener());
		return button;
	}
	
	
	/**
	 * Creates the button for the Register button.
	 * 
	 * @param theStr Name of the button.
	 * @param thePanel The panel the button will redirect the page to.
	 * @return The newly created button.
	 */
	private JButton createRegisterButton(final String theStr, final PanelDisplay thePanel) {
		final JButton button = new JButton(theStr);
		
		class MyActionListener implements ActionListener {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				myGUI.switchPanel(thePanel);
			}
		}
		button.addActionListener(new MyActionListener(){

		});
		return button;
	}
	

	
	
}

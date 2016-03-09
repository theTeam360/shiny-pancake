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
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.Registration;
/**
 * The panel for displaying the registration page contents.
 * 
 * @author Justin Arnett
 * @version 4 March 2016
 */
@SuppressWarnings("serial")
public class RegistrationPanel extends JPanel implements PanelDisplay {
	
	
	private ShinyPancakeGUI myGUI;
	public JTextField myFirstNameText;
	public JTextField myLastNameText;
	public JTextField myPhoneText;
	public JTextField myEmailText;
	public JTextField myUsernameText;
	public JTextField myPasswordText;
	public JTextField myPasswordVerifyText;
	public JTextField myAgeText;
	public JLabel myErrorLabel;
	public JLabel myErrorLabel2;
	public JButton mySubmitBtn;
	
	
	/**
	 * Constructs the panel for displaying registration.
	 * 
	 * @param theGUI
	 */
	public RegistrationPanel(ShinyPancakeGUI theGUI) {
		super();
		myGUI = theGUI;
		myFirstNameText = new JTextField(15);
		myLastNameText = new JTextField(15);
		myPhoneText = new JTextField(15);
		myEmailText = new JTextField(15);
		myUsernameText = new JTextField(15);
		myPasswordText = new JTextField(15);
		myPasswordVerifyText = new JTextField(15);
		myAgeText = new JTextField(3);
		mySubmitBtn = createSubmitButton("Submit", myGUI.myHomePanel);
		myErrorLabel = new JLabel("");
		myErrorLabel2 = new JLabel("");
		buildPanel();
	}
	
	
	/**
	 * Helper method for constructing the registration panel.
	 */
	private void buildPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// The display title of the page
		JPanel titlePanel = new JPanel();
		JLabel title = new JLabel("Registration");
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 30));
		titlePanel.add(title);
		
		
		// First/Last name panel
		JPanel namePanel = new JPanel();
		JLabel firstName = new JLabel("First Name: ");
		JLabel lastName = new JLabel("Last Name: ");
		namePanel.add(firstName);
		namePanel.add(myFirstNameText);
		namePanel.add(Box.createHorizontalStrut(20));
		namePanel.add(lastName);
		namePanel.add(myLastNameText);
		
		
		// Panel for phone number and email.
		JPanel infoPanel = new JPanel();
		JLabel age = new JLabel("Age: ");
		JLabel phone = new JLabel("Phone #: ");
		JLabel email = new JLabel("Email: ");
		infoPanel.add(age);
		infoPanel.add(myAgeText);
		infoPanel.add(Box.createHorizontalStrut(16));
		infoPanel.add(phone);
		infoPanel.add(myPhoneText);
		infoPanel.add(Box.createHorizontalStrut(20));
		infoPanel.add(email);
		infoPanel.add(myEmailText);
		
		// Error popup panel
		JPanel errorPanel2 = new JPanel();
		myErrorLabel2.setForeground(new Color(153, 0, 0));
		errorPanel2.add(myErrorLabel2);
		
		// Username label and textfield
		JPanel userPanel = new JPanel();
		userPanel.add(new JLabel("Username: "));
		userPanel.add(myUsernameText);
		
		// Error popup panel
		JPanel errorPanel = new JPanel();
		myErrorLabel.setForeground(new Color(153, 0, 0));
		errorPanel.add(myErrorLabel);
		
		// Password label and textfield
		JPanel passPanel = new JPanel();
		passPanel.add(new JLabel("Password: "));
		passPanel.add(myPasswordText);
		
		// Password label and textfield
		JPanel passVerPanel = new JPanel();
		passVerPanel.add(new JLabel("Verify Password: "));
		passVerPanel.add(myPasswordVerifyText);
		passVerPanel.add(Box.createHorizontalStrut(30));
		
		// Just the submit button
		JPanel submitBtnPanel = new JPanel();
		submitBtnPanel.add(mySubmitBtn);
		
		this.add(Box.createVerticalStrut(50));
		this.add(titlePanel);
		this.add(Box.createVerticalStrut(30));
		this.add(namePanel);
		this.add(infoPanel);
		this.add(Box.createVerticalStrut(30));
		this.add(errorPanel2);
		this.add(userPanel);
		this.add(errorPanel);
		this.add(passPanel);
		this.add(passVerPanel);
		this.add(Box.createVerticalStrut(30));
		this.add(submitBtnPanel);
	}
	
	
	/**
	 * Resets fields to their default values.
	 */
	@Override
	public void resetFields() {
		myFirstNameText.setText("");
		myLastNameText.setText("");
		myPhoneText.setText("");
		myEmailText.setText("");
		myUsernameText.setText("");
		myPasswordText.setText("");
		myPasswordVerifyText.setText("");
		myErrorLabel.setText("");
		myErrorLabel2.setText("");
		myAgeText.setText("");
	}
	
	
	
	/**
	 * The button for submitting credentials to the database for user
	 * registration. 
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
				
				boolean isUsernameAvailable = false;
				
				/*
				 * ***********************************************************
				 * 	Bill Sylvia
				 * ***********************************************************
				 */
				
				try {
					isUsernameAvailable = !Registration.userExists(myUsernameText.getText(),null);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if(myUsernameText.getText().length() < 4) {
					myErrorLabel2.setText("Username needs to be at least 4 characters long");
				} else if(!isUsernameAvailable) {
					myErrorLabel2.setText("Username is already taken");
				} else if(myPasswordText.getText().length() < 4) {
					myErrorLabel2.setText("");
					myErrorLabel.setText("Password needs to be at least 4 characters long");
				} else if(!myPasswordText.getText().equals(myPasswordVerifyText.getText())) {
					myErrorLabel2.setText("");
					myErrorLabel.setText("Passwords do not match");
				} else {
					myGUI.myLoginPanel.resetFields();
					myGUI.switchPanel(myGUI.myLoginPanel);
					try {
						Registration.add(myUsernameText.getText(), myPasswordText.getText(), myFirstNameText.getText(), 
										myLastNameText.getText(), myAgeText.getText(), myEmailText.getText(), myPhoneText.getText(), "");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
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


	
	
	
	
}

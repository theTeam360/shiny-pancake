package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * 
 * @author Justin Arnett
 * @version 4 March 2016
 *
 */
@SuppressWarnings("serial")
public class ShinyPancakeGUI extends JFrame {

	private final JPanel myNavigationBar;
	
	private JPanel myMainViewPanel;
	
	public final PanelDisplay myHomePanel;
	
	public final PanelDisplay myLoginPanel;
	
	public final PanelDisplay mySubmitEntryPanel;
	
	public final PanelDisplay myRegistrationPanel;
	
	public final PanelDisplay myDownloadPanel;
	
	public String myCurrentUser;
	
	public boolean isLoggedIn;
	
	private JButton myRegisterButton;
	
	private JButton myLoginButton;
	
	private JButton mySubmitButton;
	
	
	/**
	 * Constructor for ShinyPancakeGUI to initialize its fields
	 */
	public ShinyPancakeGUI() {
		super();
		myCurrentUser = "";
		isLoggedIn = true;//false;
		myNavigationBar = new JPanel(new BorderLayout());
		myHomePanel = new HomePanel();
		mySubmitEntryPanel = new SubmitEntryPanel(this);
		myRegistrationPanel = new RegistrationPanel(this);
		myLoginPanel = new LoginPanel(this);
		myDownloadPanel = new DownloadPanel(this);
		myMainViewPanel = new JPanel(new BorderLayout());
		myRegisterButton = createRegisterLogoutButton();
		myLoginButton = createNavButton("Login", myLoginPanel);
		mySubmitButton = createSubmitButton("Submit Entry", mySubmitEntryPanel);
	}
	
	
	/**
	 * Assembles the GUI.
	 */
	public void start() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("WHAT SHOULD WE CALL THIS?");
		
		// myNavigationBar
		JPanel leftSection = new JPanel();
		leftSection.setBackground(new Color(102, 166, 255));
		leftSection.add(createNavButton("Home", myHomePanel));
		leftSection.add(mySubmitButton);
		leftSection.add(createNavButton("Download", myDownloadPanel));
		
		JPanel rightSection = new JPanel();
		rightSection.setBackground(new Color(102, 166, 255));
		rightSection.add(myLoginButton);
		rightSection.add(myRegisterButton);
		
		myNavigationBar.add(leftSection, BorderLayout.WEST);
		myNavigationBar.add(rightSection, BorderLayout.EAST);
		// soft blue background
		myNavigationBar.setBackground(new Color(102, 166, 255));
		
		myMainViewPanel.add((JPanel) myHomePanel, BorderLayout.NORTH);
		
		add(myMainViewPanel, BorderLayout.CENTER);
		add(myNavigationBar, BorderLayout.NORTH);
		
		resize();
		setVisible(true);
	}
	
	/**
	 * Sets the sizes of the window frame.
	 */
	private void resize() {
		pack();
		setPreferredSize(new Dimension(1000, 800));
		//setMinimumSize(getPreferredSize());
		pack();
		setLocationRelativeTo(null);
	}
	
	
	/**
	 * Creates button that will navigate to the page provided as a parameter.
	 * 
	 * @param theStr The name of the button.
	 * @param the view panel.
	 * @return The created button.
	 */
	private JButton createNavButton(final String theStr, final PanelDisplay thePanel) {
		final JButton button = new JButton(theStr);
		
		class MyActionListener implements ActionListener {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				thePanel.resetFields();
				switchPanel(thePanel);
			}
		}
		
		button.addActionListener(new MyActionListener());
		return button;
	}
	
	
	
	private JButton createSubmitButton(final String theStr, final PanelDisplay thePanel) {
		final JButton button = new JButton(theStr);
		
		class MyActionListener implements ActionListener {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				
				if (isLoggedIn) {
					thePanel.resetFields();
					switchPanel(thePanel);
				} else {
					myLoginPanel.resetFields();
					switchPanel(myLoginPanel);
				}
				
			}
		}
		
		button.addActionListener(new MyActionListener());
		return button;
	}
	
	
	
	/**
	 * Button that has two functionalities depending on if a user is logged in. Will
	 * redirect to registration page if no user is logged in. Will log user out and
	 * redirect to homepage if a user is logged in.
	 * 
	 * @return
	 */
	private JButton createRegisterLogoutButton() {
		final JButton button = new JButton("Register");
		
		class MyActionListener implements ActionListener {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				if (isLoggedIn) {
					logOut();
					switchPanel(myHomePanel);
				} else {
					myRegistrationPanel.resetFields();
					switchPanel(myRegistrationPanel);
				}
			}
		}
		
		button.addActionListener(new MyActionListener());
		return button;
	}
	
	
	/**
	 * Switches the active panel in the display area of the GUI.
	 * 
	 * @param thePanel The panel to switch to.
	 */
	public void switchPanel(final PanelDisplay thePanel) {
		myMainViewPanel.removeAll();
		myMainViewPanel.add((JPanel) thePanel, BorderLayout.NORTH);
		myMainViewPanel.revalidate();
		myMainViewPanel.repaint();
	}
	
	
	/**
	 * Changes properties on the website when a user is logged in.
	 * 
	 * @param name The name of the user that just logged in.
	 */
	public void logIn(final String name) {
		myCurrentUser = name;
		isLoggedIn = true;
		myLoginButton.setText(myCurrentUser + " Account");
		myRegisterButton.setText("Logout");
	}
	
	
	/**
	 * Logs a user out and reverts button text back to Register/Login.
	 */
	public void logOut() {
		myCurrentUser = "";
		isLoggedIn = false;
		myRegisterButton.setText("Register");
		myLoginButton.setText("Login");
	}
	
}

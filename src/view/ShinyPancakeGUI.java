package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
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

	/**
	 * The main navigation bar that persists through all webpages.
	 */
	private final JPanel myNavigationBar;
	
	/**
	 * The current "webpage" of the GUI
	 */
	private JPanel myMainViewPanel;
	
	private final HomePanel myHomePanel;
	
	private final LoginPanel myLoginPanel;
	
	private final SubmitEntryPanel mySubmitEntryPanel;
	
	private final RegistrationPanel myRegistrationPanel;
	
	private final DownloadPanel myDownloadPanel;
	
	
	/**
	 * Constructor for ShinyPancakeGUI to initialize its fields
	 */
	public ShinyPancakeGUI() {
		super();
		myNavigationBar = new JPanel(new BorderLayout());
		myHomePanel = new HomePanel();
		myLoginPanel = new LoginPanel();
		mySubmitEntryPanel = new SubmitEntryPanel();
		myRegistrationPanel = new RegistrationPanel();
		myDownloadPanel = new DownloadPanel();
		myMainViewPanel = new JPanel();
	}
	
	
	/**
	 * Assembles the GUI.
	 */
	public void start() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("WHAT SHOULD WE CALL THIS?");
		
		// myNavigationBar
		JPanel leftSection = new JPanel();
		JPanel rightSection = new JPanel();
		leftSection.add(createNavButton("Home", myHomePanel));
		leftSection.add(createNavButton("Submit Entry", mySubmitEntryPanel));
		leftSection.add(createNavButton("Download", myDownloadPanel));
		rightSection.add(createNavButton("Login", myLoginPanel));
		rightSection.add(createNavButton("Register", myRegistrationPanel));
		
		myNavigationBar.add(leftSection, BorderLayout.WEST);
		myNavigationBar.add(rightSection, BorderLayout.EAST);
		
		myMainViewPanel.add(myHomePanel);
		
		add(myMainViewPanel, BorderLayout.CENTER);
		add(myNavigationBar, BorderLayout.NORTH);
		
		resize();
		setVisible(true);
	}
	
	
	private void resize() {
		pack();
		setMinimumSize(getPreferredSize());
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
	private JButton createNavButton(final String theStr, final JPanel thePanel) {
		final JButton button = new JButton(theStr);
		
		class MyActionListener implements ActionListener {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				myMainViewPanel.removeAll();
				myMainViewPanel.add(thePanel);
				myMainViewPanel.revalidate();
				myMainViewPanel.repaint();
			}
		}
		
		
		button.addActionListener(new MyActionListener());
		return button;
	}
	
}

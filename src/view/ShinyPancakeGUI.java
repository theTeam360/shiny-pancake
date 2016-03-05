package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
	
	public final HomePanel myHomePanel;
	
	public final LoginPanel myLoginPanel;
	
	public final SubmitEntryPanel mySubmitEntryPanel;
	
	public final RegistrationPanel myRegistrationPanel;
	
	public final DownloadPanel myDownloadPanel;
	
	
	/**
	 * Constructor for ShinyPancakeGUI to initialize its fields
	 */
	public ShinyPancakeGUI() {
		super();
		myNavigationBar = new JPanel(new BorderLayout());
		myHomePanel = new HomePanel();
		mySubmitEntryPanel = new SubmitEntryPanel();
		myRegistrationPanel = new RegistrationPanel();
		myLoginPanel = new LoginPanel(this);
		myDownloadPanel = new DownloadPanel();
		myMainViewPanel = new JPanel(new BorderLayout());
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
		
		myMainViewPanel.add(myHomePanel, BorderLayout.NORTH);
		
		add(myMainViewPanel, BorderLayout.CENTER);
		add(myNavigationBar, BorderLayout.NORTH);
		
		resize();
		setVisible(true);
	}
	
	
	private void resize() {
		pack();
		setPreferredSize(new Dimension(800, 600));
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
	private JButton createNavButton(final String theStr, final JPanel thePanel) {
		final JButton button = new JButton(theStr);
		
		class MyActionListener implements ActionListener {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				switchPanel(thePanel);
			}
		}
		
		button.addActionListener(new MyActionListener());
		return button;
	}
	
	
	public void switchPanel(final JPanel thePanel) {
		myMainViewPanel.removeAll();
		myMainViewPanel.add(thePanel, BorderLayout.NORTH);
		myMainViewPanel.revalidate();
		myMainViewPanel.repaint();
	}
	
}

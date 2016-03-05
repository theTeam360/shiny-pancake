package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 * The panel that displays the content for entering new submissions.
 * 
 * @author Justin Arnett
 * @version 4 March 2016
 *
 */
@SuppressWarnings("serial")
public class SubmitEntryPanel extends JPanel implements PanelDisplay {

	private ShinyPancakeGUI myGUI;
	public JButton myUploadImageBtn;
	public JButton mySubmitBtn;
	public JCheckBox hasConsent;
    private final JLabel myIcon;
    private File myFile;
    private String myDirectory;
    private BufferedImage myImage;
    private boolean	isImageLoaded;
    private JLabel myErrorLabel;
	
	
    /**
     * Constructor for the submission panel.
     * 
     * @param theGUI
     */
	public SubmitEntryPanel(ShinyPancakeGUI theGUI) {
		super();
		myGUI = theGUI;
		isImageLoaded = false;
		myUploadImageBtn = createUploadButton("Browse...");
		mySubmitBtn = createSubmitButton("Submit Entry", myGUI.myHomePanel);
		myIcon = new JLabel();
		myDirectory = (".");
		myImage = null;
		hasConsent = new JCheckBox();
		myErrorLabel = new JLabel("");
		buildPanel();
	}
	
	
	/**
	 * Helper method for constructing the submission panel. 
	 */
	private void buildPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// The display title of the page
		JPanel titlePanel = new JPanel();
		JLabel title = new JLabel("Submit A Colored Picture!");
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 30));
		titlePanel.add(title);
		
		// Displays the image that will be uploaded
		final JPanel imagePanel = new JPanel(new GridBagLayout());
		//imagePanel.setBackground(Color.WHITE);
        imagePanel.add(myIcon);
        
        // The upload button panel
        final JPanel uploadPanel = new JPanel();
        uploadPanel.add(myUploadImageBtn);
        
        // Release of personal info check box
        final JPanel personalPanel = new JPanel();
        final JLabel personalLabel = new JLabel("May we use your Name and Age with your submission? ");
        personalPanel.add(personalLabel);
        personalPanel.add(hasConsent);
		
        // Error label panel
        JPanel errorPanel = new JPanel();
        myErrorLabel.setForeground(new Color(153, 0, 0));
        errorPanel.add(myErrorLabel);
        
		// Just the submit button
		JPanel submitBtnPanel = new JPanel();
		submitBtnPanel.add(mySubmitBtn);
		
		this.add(Box.createVerticalStrut(50));
		this.add(titlePanel);
		this.add(Box.createVerticalStrut(30));
		this.add(imagePanel);
		this.add(Box.createVerticalStrut(10));
		this.add(uploadPanel);
		this.add(personalPanel);
		this.add(Box.createVerticalStrut(15));
		this.add(errorPanel);
		this.add(submitBtnPanel);
	}
	
	
	/**
	 * Resets the fields to their default value.
	 */
	@Override
	public void resetFields() {
		myIcon.setIcon(null);
		myErrorLabel.setText("");
		isImageLoaded = false;
		hasConsent.setSelected(false);
	}
	
	
	
	/**
	 * Creates a button that will handle creating a new submission from
	 * the provided fields.
	 * 
	 * @param theStr Name of the button
	 * @param thePanel The panel the button will redirect the page to.
	 * @return The created button.
	 */
	private JButton createSubmitButton(final String theStr, final PanelDisplay thePanel) {
		final JButton button = new JButton(theStr);
		
		class MyActionListener implements ActionListener {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				if (isImageLoaded) {

					// LOGIC HERE FOR SAVING SUBMISSIONS TO DATABASE
					// - Justin

					// myFile is the file that was uploaded.
					// myImage is the loaded image if needed.
					// myGUI.myCurrentUser is the username string.
					// hasConsent.isSelected() will check if they checked the box.

				} else {
					myErrorLabel.setText("Image file was not loaded");
				}
			}
		}
		button.addActionListener(new MyActionListener());
		return button;
	}
	
	
	
	/**
	 * Opens a file browser dialog box that will let the user choose an image
	 * that they would like to upload.
	 * 
	 * @param theStr Name of the button
	 * @return The created button.
	 */
	private JButton createUploadButton(final String theStr) {
		final JButton button = new JButton(theStr);
		
		class MyActionListener implements ActionListener {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				final JFileChooser fileChooser = new JFileChooser(myDirectory);
				
				fileChooser.setSelectedFile(myFile);
				final int result = fileChooser.showOpenDialog(((JPanel) myGUI.mySubmitEntryPanel).getParent());
				if (result == JFileChooser.APPROVE_OPTION) {
					myFile = fileChooser.getSelectedFile();
					myDirectory = fileChooser.getSelectedFile().getAbsolutePath().toString();
					try {
						myImage = ImageIO.read(myFile);
						myIcon.setIcon(new ImageIcon(myImage));
						isImageLoaded = true;
						myErrorLabel.setText("");
					} catch (final IOException e1) {
						JOptionPane.showMessageDialog(((JPanel) myGUI.mySubmitEntryPanel).getParent(),
                                "The selected file did not contain an image!",
                                "Error!",
                                JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
		button.addActionListener(new MyActionListener());
		return button;
	}

}

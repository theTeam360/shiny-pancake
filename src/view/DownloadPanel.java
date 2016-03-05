package view;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

/**
 * Basic foundation for download page.
 * 
 * @author Justin Arnett
 * @version 4 March 2016
 */
@SuppressWarnings("serial")
public class DownloadPanel extends JPanel implements PanelDisplay {

	private ShinyPancakeGUI myGUI;
	private String myDirectory;
	private File myFile;
	
	
	public DownloadPanel(ShinyPancakeGUI theGUI) {
		super();
		myGUI = theGUI;
		myDirectory = (".");
		buildPanel();
	}
	
	
	private void buildPanel() {
		
		// An example of what we should do for download page, but many populated
		// from a database. All it would need is an object with a file path to the image
		// in the img_download folder. Then just a for each loop that generates all
		// the buttons with the method I provided. If database isn't created, then
		// manually populate a few image buttons; at least 5 would be nice. The code is
		// already here to copy so it would be easy.
		// - Justin
		
		Image img = new ImageIcon(this.getClass().getResource("/yoshi.png")).getImage();
		
		JButton yoshiButton = createButton("/yoshi.png", img);
		
		img = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		yoshiButton.setIcon(new ImageIcon(img));
		yoshiButton.setMaximumSize(new Dimension(100, 100));
		
		this.add(yoshiButton);
		
	}

	
	
	@Override
	public void resetFields() {
		// Should not need this for this page, but do not delete
		// - Justin

	}
	
	
	/**
	 * Creates a button that will handle creating a new submission from
	 * the provided fields.
	 * 
	 * @param theStr Name of the button
	 * @param thePanel The panel the button will redirect the page to.
	 * @return The created button.
	 */
	private JButton createButton(final String thePath, final Image theImage) {
		final JButton button = new JButton();
		
		class MyActionListener implements ActionListener {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				final JFileChooser fileChooser = new JFileChooser(myDirectory);
				File fileDes = new File(thePath);
				
				fileChooser.setSelectedFile(fileDes);
				final int result = fileChooser.showSaveDialog((((JPanel) myGUI.mySubmitEntryPanel).getParent()));
				if (result == JFileChooser.APPROVE_OPTION) {
					fileDes = fileChooser.getSelectedFile();
					BufferedImage newImage = new BufferedImage(
							theImage.getWidth(null), theImage.getHeight(null),
							BufferedImage.TYPE_INT_ARGB);
					Graphics2D g = newImage.createGraphics();
					g.drawImage(theImage, 0, 0, null);
					
					try {
						ImageIO.write(newImage, "png", fileDes);
					} catch (IOException e) {
						e.printStackTrace();
					}
					g.dispose();
				}
			}
		}
		button.addActionListener(new MyActionListener());
		return button;
	}
	
}

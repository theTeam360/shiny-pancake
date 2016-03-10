package view;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

/**
 * Basic foundation for download page.
 * 
 * @author Justin Arnett & Bill Sylvia
 * @version 4 March 2016
 */
@SuppressWarnings("serial")
public class DownloadPanel extends JPanel implements PanelDisplay {

	private ShinyPancakeGUI myGUI;
	private String myDirectory;
	
	
	public DownloadPanel(ShinyPancakeGUI theGUI) {
		super();
		myGUI = theGUI;
		myDirectory = ("C:\\Users\\" + System.getProperty("user.name") + "\\Downloads"); // Bill Sylvia
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
		
		
		
		/*
		 * ***********************************************************
		 * 	Bill Sylvia
		 * ***********************************************************
		 */
		File folder = new File("./img_download");
		File[] imgNames = folder.listFiles();
		ArrayList<Image> imgs = new ArrayList<Image>();
		ArrayList<JButton> myButtons = new ArrayList<JButton>();
		
		for (int i = 0; i < imgNames.length; i++) {
			imgs.add(new ImageIcon(imgNames[i].getAbsolutePath()).getImage());
		}
		
		for(int i = 0; i < imgNames.length; i++) {
			myButtons.add(createButton(imgNames[i].getAbsolutePath(), imgs.get(i)));
		}
		
		for(int i = 0; i < imgNames.length; i++) {
			imgs.set(i,imgs.get(i).getScaledInstance(100, 100, Image.SCALE_SMOOTH));
			myButtons.get(i).setIcon(new ImageIcon(imgs.get(i)));
			myButtons.get(i).setMaximumSize(new Dimension(100, 100));
		}
		for(int i = 0; i < imgNames.length; i++) {
			this.add(myButtons.get(i));
		}
		/*
		 * ***********************************************************
		 * 	Bill Sylvia
		 * ***********************************************************
		 */
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
				final JFileChooser fileChooser = new JFileChooser(new File(myDirectory));
				File fileDes = new File(thePath);
				
				fileChooser.setSelectedFile(fileDes);
				fileChooser.setCurrentDirectory(new File(myDirectory)); // Bill Sylvia
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

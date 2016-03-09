package view;

import java.awt.EventQueue;

/**
 * Runs ShinyPancake by instantiating and starting the ShinyPancakeGUI
 * 
 * @author Justin Arnett
 * @version March 4, 2016
 *
 */
public class ShinyPancakeMain {
	
	
	/**
	 * Private constructor, to prevent instantiation of this class.
	 */
	private ShinyPancakeMain() {
		throw new IllegalStateException();
	}

	
	/**
	 * The main method that invokes the GUI. Command line arguments
	 * are ignored.
	 * 
	 * @param theArgs Command line arguments
	 */
	public static void main(String[] theArgs) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				final ShinyPancakeGUI gui = new ShinyPancakeGUI();
				gui.start();
			}
		});

	}

}

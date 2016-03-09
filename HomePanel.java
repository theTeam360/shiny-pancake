package view;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class HomePanel extends JPanel implements PanelDisplay {

	
	public HomePanel() {
		//this.setLayout(new BorderLayout());
		this.add(new JLabel("this is home page"));
	}

	@Override
	public void resetFields() {
		// Do not worry about this for home page.
	}

}

package vue;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class CaseGraphique extends JPanel {
	
	private Border lineBorder = BorderFactory.createLineBorder(Color.black, 4);

	/**
	 * Create the panel.
	 */
	public CaseGraphique() {
		
		
		setPreferredSize(new Dimension(100, 100));
		setBackground(new Color(145, 137, 135));
		setBorder(lineBorder); //pas utile pour la suite

	}

}

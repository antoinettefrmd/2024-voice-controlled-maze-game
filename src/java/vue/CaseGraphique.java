package vue;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import modele.Labyrinth;


public class CaseGraphique extends JPanel {
	
	private Border lineBorder = BorderFactory.createLineBorder(Color.black, 4);

	/**
	 * Create the panel.
	 */
	public CaseGraphique(Labyrinth.Case c) {
		
		setPreferredSize(new Dimension(50, 50));
		if (c.getMur()) {
			setBackground(new Color(97, 93, 92));
		} else {
			setBackground(new Color(200,173,127));
		}

	}

}

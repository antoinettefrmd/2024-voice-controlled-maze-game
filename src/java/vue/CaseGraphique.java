package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import modele.Labyrinth;
import modele.Labyrinth.Case;


public class CaseGraphique extends JPanel {
	
	private Border lineBorder = BorderFactory.createLineBorder(Color.black, 4);
	private Case c;

	/**
	 * Create the panel.
	 */
	public CaseGraphique(Labyrinth.Case c) {
		
		this.c = c;
		setPreferredSize(new Dimension(50, 50));
		if (c.getMur()) {
			setBackground(new Color(97, 93, 92));
		} else {
			setBackground(new Color(200,173,127));
		}

	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (!c.estVide()) {
			g.setColor(Color.RED);
			g.fillOval(0, 0, 24, 24);
		}
	}

}

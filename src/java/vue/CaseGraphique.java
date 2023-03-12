package vue;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import modele.Labyrinth;
import modele.Labyrinth.Case;


public class CaseGraphique extends JPanel {
	
	private BufferedImage image;
	private Case c;

	/**
	 * Create the panel.
	 */
	public CaseGraphique(Labyrinth.Case c, BufferedImage imageMur, BufferedImage imageSol) {
		
		this.c = c;
		setPreferredSize(new Dimension(50, 50));
		if (c.getMur()) {
			image = imageMur;
		}
		else {
			image = imageSol;
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
		for (int i = 0; i < c.getJoueurs().size(); i++) {
			g.setColor(c.getJoueurs().get(i).getCouleur());
			g.fillOval(i * 40 / c.getJoueurs().size(), 0, 40/c.getJoueurs().size(), 40/c.getJoueurs().size());
		}
	}

}

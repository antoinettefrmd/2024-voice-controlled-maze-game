package vue;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import modele.Labyrinth;
import modele.Labyrinth.Case;


public class CaseGraphique extends JPanel {
	
	private BufferedImage image;
	private BufferedImage imageClef;
	private Case c;
	private boolean estclef;
	private boolean clefprise;

	/**
	 * Create the panel.
	 */
	public CaseGraphique(Labyrinth.Case c, BufferedImage imageMur, BufferedImage imageSol, BufferedImage clef) {
		
		this.c = c;
		imageClef = clef;
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
		//super.paintComponent(g);
		Graphics2D surface = (Graphics2D) g;
		g.drawImage(image, 0, 0, null);
		
		if(estclef && !clefprise) {
			surface.scale(0.5,0.5);
			surface.drawImage(imageClef, 0, 0, this);
		}
		
		for (int i = 0; i < c.getJoueurs().size(); i++) {
			g.setColor(c.getJoueurs().get(i).getCouleur());
			g.fillOval(i * 40 / c.getJoueurs().size(), 0, 40/c.getJoueurs().size(), 40/c.getJoueurs().size());
		}
	}

	public void setEstCle(boolean b) {
		estclef = b;
	}

}

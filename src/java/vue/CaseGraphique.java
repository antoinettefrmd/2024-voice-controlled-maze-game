package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import modele.Labyrinth;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import modele.Labyrinth.Case;


public class CaseGraphique extends JPanel {
	
	private Border lineBorder = BorderFactory.createLineBorder(Color.black, 4);
	private BufferedImage image;
	private Case c;
	/**
	 * Create the panel.
	 */
	public CaseGraphique(Labyrinth.Case c) {
		
		this.c = c;
		setPreferredSize(new Dimension(50, 50));
		if (c.getMur()) {
//			setBackground(new Color(97, 93, 92));
			try {
				image = ImageIO.read(new File("./src/ressources/images/mur.jpeg"));
//				image = ImageIO.read(new File("./src/ressources/images/mur2.jpg"));
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				image = ImageIO.read(new File("./src/ressources/images/cailloux.jpeg"));
			}
			catch (IOException e) {
				e.printStackTrace();
			}
//			setBackground(new Color(200,173,127));
		}

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < c.getPions().size(); i++) {
			g.setColor(c.getPions().get(i).getCouleur());
			g.fillOval(i * 40 / c.getPions().size(), 0, 40/c.getPions().size(), 40/c.getPions().size());
		}
	}

}

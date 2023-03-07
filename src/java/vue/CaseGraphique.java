package vue;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import modele.Labyrinth;



public class CaseGraphique extends JPanel {
	
	private BufferedImage image;

	/**
	 * Create the panel.
	 */
	public CaseGraphique(Labyrinth.Case c) {
		
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
	
	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, null);
	}

}

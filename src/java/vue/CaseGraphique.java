package vue;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import modele.Labyrinth;



public class CaseGraphique extends JPanel {
	
	private BufferedImage image;
	
	/**
	 * Create the panel.
	 */
	public CaseGraphique(Labyrinth.Case c, BufferedImage imageMur, BufferedImage imageSol) {
		
		setPreferredSize(new Dimension(50, 50));
		if (c.getMur()) {
			image = imageMur;
		}
		else {
			image = imageSol;
		}
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, null);
	}

}

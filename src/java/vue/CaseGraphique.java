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



public class CaseGraphique extends JPanel {
	
	private Border lineBorder = BorderFactory.createLineBorder(Color.black, 4);
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

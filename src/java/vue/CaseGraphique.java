package vue;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import modele.Labyrinth;
import modele.Labyrinth.Case;
import modele.Labyrinth.Cle;


public class CaseGraphique extends JPanel {
	
	private BufferedImage image;
	private BufferedImage imageClef;
	private Case c;
	private boolean estclef;
	private boolean clefprise;
	private boolean sortie;
	private BufferedImage escalier;
	private Cle clej;
	private BufferedImage imageclef;

	/**
	 * Create the panel.
	 */
	public CaseGraphique(Labyrinth.Case c, BufferedImage imageMur, BufferedImage imageSol, BufferedImage clef) {
		
		this.c = c;
		imageClef = clef;
		estclef = false;
		clefprise = false;
		
		
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
		//g.drawImage(image, 0, 0, null);
		
		surface.drawImage(
		        image, 
		        0, 
		        0, 
		        this.getWidth(), 
		        this.getHeight(), 
		        null
		);
		
		if(estclef && !clefprise) {
			surface.scale(0.5,0.5);
			surface.drawImage(imageClef, this.getWidth(), this.getHeight(), this);
		}
		
		if(sortie) {
			surface.drawImage(escalier, 0, 0, this.getWidth(), this.getHeight(), null);
		}
		
		for (int i = 0; i < c.getJoueurs().size(); i++) {
			g.setColor(c.getJoueurs().get(i).getCouleur());
			g.fillOval(i * 40 / c.getJoueurs().size(), 0, 40/c.getJoueurs().size(), 40/c.getJoueurs().size());
		}
	}

	public void setEstCle(boolean b) {
		estclef = b;
	}
	
	public boolean getEstCle() {
		return estclef;
	}
	
	public void setClej(Cle clej) {
		this.clej = clej;
	}
	
	public Cle getClej() {
		return clej;
	}
	
	public void setImageClef(BufferedImage imageClef) {
		this.imageClef = imageClef;
	}
	
	public void setSortie(boolean sortie) {
		this.sortie = sortie;
	}
	
	public void setEscalier(BufferedImage escalier) {
		this.escalier = escalier;
	}

}

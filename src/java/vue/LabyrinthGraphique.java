package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import modele.Labyrinth;
import modele.ListeDeJoueurs;

public class LabyrinthGraphique extends JPanel{
	
	private JPanel[][] labyrinthG;
	private Labyrinth labyrinthD;
	
	private BufferedImage imageMur;
	private BufferedImage imageSol;
	private BufferedImage clef;
	
	//private Border Jborder = BorderFactory.createLineBorder(Color.black, 2); //bordure pour entourer le nom d'un joueur
	//private Border JActuBorder = BorderFactory.createLineBorder(Color.gray, 2); //bordure pour entourer le nom du joueur qui joue
	
	//private LinkedList<JLabel> listj; //permet d'avoir la liste des JLabel représentant les joueurs

	/**
	 * n represente la taille n*n du labyrinth
	 */
	public LabyrinthGraphique(int n, ListeDeJoueurs p) {
		
		this.labyrinthD = new Labyrinth(n, p);
		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "right");
		this.getActionMap().put("right", right);
		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "left");
		this.getActionMap().put("left", left);
		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "up");
		this.getActionMap().put("up", up);
		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "down");
		this.getActionMap().put("down", down);
		setLayout(new GridLayout(2*n+1, 2*n+1));
		labyrinthG = new JPanel[2*n +1][2*n +1];
		
		setBackground(Color.GRAY);

		try {
			//permet de récupérer le fichier de l'image
			imageMur = ImageIO.read(new File("./src/ressources/images/mur.png"));
			imageSol = ImageIO.read(new File("./src/ressources/images/sol4.png"));
			clef = ImageIO.read(new File("./src/ressources/images/key.png"));
		} catch (IOException e){
			e.printStackTrace();
		}
		
		//cette double boucle for permet d'accéder à toutes les cases du labyrinth pour le créer graphiquement
		for(int i = 0; i < 2*n+1; i++) {
			for(int j = 0; j < 2*n+1; j++) {
				
				//On récupère la case et on crée un mur ou un chemin en fonction de la valeur de la case
				CaseGraphique tmp = new CaseGraphique(labyrinthD.getLabyrinth()[i][j], imageMur, imageSol, clef);
				labyrinthG[i][j] = tmp;
				this.add(tmp);
			}
		}
		
		setFocusable(true);
		requestFocus();
		
	}
	
	public void placerJoueurs(ListeDeJoueurs joueurs) {
		
	}
	private Action right = new AbstractAction() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			LabyrinthGraphique.this.labyrinthD.droite(LabyrinthGraphique.this.labyrinthD.getCurrent().getJoueur());
			repaint();
		}
	};

	private Action left = new AbstractAction() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			LabyrinthGraphique.this.labyrinthD.gauche(LabyrinthGraphique.this.labyrinthD.getCurrent().getJoueur());
			repaint();
		}
	};
	
	private Action up = new AbstractAction() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			LabyrinthGraphique.this.labyrinthD.haut(LabyrinthGraphique.this.labyrinthD.getCurrent().getJoueur());
			repaint();
		}
	};
	
	private Action down = new AbstractAction() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			LabyrinthGraphique.this.labyrinthD.bas(LabyrinthGraphique.this.labyrinthD.getCurrent().getJoueur());
			repaint();
		}
	};
	public Labyrinth getLabyrinthD() {
		return labyrinthD;
	}

}

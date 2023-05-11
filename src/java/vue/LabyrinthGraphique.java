package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import modele.Labyrinth;
import modele.ListeDeJoueurs;

public class LabyrinthGraphique extends JPanel{
	
	private static final long serialVersionUID = -2207872168451622130L;
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
		
		setLayout(new GridLayout(2*n+1, 2*n+1));
		labyrinthG = new JPanel[2*n +1][2*n +1];
		
		setBackground(Color.GRAY);

		try {
			//permet de récupérer les fichiers images
			imageMur = ImageIO.read(new File("./src/ressources/images/mur.png"));
			imageSol = ImageIO.read(new File("./src/ressources/images/stone.png"));
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

	public Labyrinth getLabyrinthD() {
		return labyrinthD;
	}

	public CaseGraphique getCase(int x, int y) {
		return (CaseGraphique)labyrinthG[x][y];
	}

}

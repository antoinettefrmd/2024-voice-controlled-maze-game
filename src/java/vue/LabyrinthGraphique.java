package vue;

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
	
	//private Border Jborder = BorderFactory.createLineBorder(Color.black, 2); //bordure pour entourer le nom d'un joueur
	//private Border JActuBorder = BorderFactory.createLineBorder(Color.gray, 2); //bordure pour entourer le nom du joueur qui joue
	
	//private LinkedList<JLabel> listj; //permet d'avoir la liste des JLabel représentant les joueurs

	/**
	 * n represente la taille n*n du labyrinth
	 */
	public LabyrinthGraphique(int n) {
		
		this.labyrinthD = new Labyrinth(n);
		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "right");
		this.getActionMap().put("right", right);
		setLayout(new GridLayout(2*n+1, 2*n+1));
		labyrinthG = new JPanel[2*n +1][2*n +1];

//		JMenuBar jmb = m.getJMenuBar();
//		
//		listj = new LinkedList<>();
//		
//		JLabel jactu = new JLabel("Joueur(s) / Joueuse(s) : ");
//		jactu.setBorder(new EmptyBorder(0, 10, 0, 0));
//		
//		jmb.add(jactu);
//		
//		JPanel jbox = new JPanel(new GridLayout(0, 5, 10, 0));
//		
//		if(georges) {
//			JLabel g = new JLabel("Georges");
//			g.setHorizontalAlignment(SwingConstants.CENTER);
//			g.setBorder(Jborder);
//			jbox.add(g);
//			listj.add(g);
//		}
//		
//		if(ronen) {
//			JLabel r = new JLabel("Ronen");
//			r.setHorizontalAlignment(SwingConstants.CENTER);
//			r.setBorder(Jborder);
//			jbox.add(r);
//			listj.add(r);
//		}
//		
//		if(antoinette) {
//			JLabel a1 = new JLabel("Antoinette");
//			a1.setHorizontalAlignment(SwingConstants.CENTER);
//			a1.setBorder(Jborder);
//			jbox.add(a1);
//			listj.add(a1);
//		}
//		
//		if(alec) {
//			JLabel a2 = new JLabel("Alec");
//			a2.setHorizontalAlignment(SwingConstants.CENTER);
//			a2.setBorder(Jborder);
//			jbox.add(a2);
//			listj.add(a2);
//		}
//		
//		if(lea) {
//			JLabel l = new JLabel("Lea");
//			l.setHorizontalAlignment(SwingConstants.CENTER);
//			l.setBorder(Jborder);
//			jbox.add(l);
//			listj.add(l);
//		}
//		
//		jmb.add(jbox);
		
		try {
			//permet de récupérer le fichier de l'image
			imageMur = ImageIO.read(new File("./src/ressources/images/mur.jpeg"));
			imageSol = ImageIO.read(new File("./src/ressources/images/cailloux.jpeg"));
		} catch (IOException e){
			e.printStackTrace();
		}
		
		//cette double boucle for permet d'accéder à toutes les cases du labyrinth pour le créer graphiquement
		for(int i = 0; i < 2*n+1; i++) {
			for(int j = 0; j < 2*n+1; j++) {
				
				//On récupère la case et on crée un mur ou un chemin en fonction de la valeur de la case
				CaseGraphique tmp = new CaseGraphique(labyrinthD.getLabyrinth()[i][j], imageMur, imageSol);
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
			
			
		}
	};

	
	public Labyrinth getLabyrinthD() {
		return labyrinthD;
	}

}

package modele;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import controlleur.ExecuteBash;
import vue.LabyrinthGraphique;
import vue.Menu;

public class Jeu {
	
	public static LabyrinthGraphique labyrinth;
	public static ListeDeJoueurs joueurs;
	public static ListeDeJoueurs joueursfinito;
	public static CellJoueur courant;
	public int etage;
	public int taille = 5;
	
	
	private String temps = "0";
	private Menu m;
	
	//permet de savoir qui est le joueur courant dans la liste de JLabel
	private int JLabelCourantJPos = 0; 
	
	//JLabel qui represente le joueur courant
	private JLabel JLabelCourant;
	
	//nombre de joueur max pour la partie
	private int nbrJ;
	
	private Color gold = new Color(255, 215, 0); //quand le joueur a une clef on met son pseudo en couleur gold
	
	private Border Jborder = BorderFactory.createLineBorder(Color.black, 2); //bordure pour entourer le nom d'un joueur
	private Border JActuBorder = BorderFactory.createLineBorder(Color.WHITE, 2); //bordure pour entourer le nom du joueur qui joue
	private Border JWinBorder = BorderFactory.createLineBorder(gold); //bordure pour les joueurs qui ont terminé
	
	
	private LinkedList<JLabel> listj; //permet d'avoir la liste des JLabel représentant les joueurs
	private JPanel jbox; //permet de contenir tous les JLabel des joueurs et de les organiser dans la topbar

	
	public Jeu(Menu m, ListeDeJoueurs j) {
		
		this.m = m;
		
		labyrinth = new LabyrinthGraphique(taille);
		m.MAJlabyrinthG(labyrinth); //met à jour l'interface graphique et donc le labyrinth
		joueurs = j;
		joueursfinito = new ListeDeJoueurs();
		etage = 0;
		courant = joueurs.getCourant();
		
		JMenuBar jmb = m.getJMenuBar();
		
		listj = new LinkedList<JLabel>();
		
		jbox = new JPanel(new GridLayout(0, 5, 10, 0));
		jbox.setOpaque(false);
		
		int n = j.getTaille();
		nbrJ = n;
		for(int i = 0; i < n; i++) {
			
			//recupère la couleur du joueur
			Color c = j.getCourant().getJoueur().getCouleur();
			
			String nom = "";
			
			//on verifie la couleur du joueur pour lui donner son nom
			if(c.equals(Color.PINK)) nom = "Georges";
			if(c.equals(Color.GREEN)) nom = "Ronen";
			if(c.equals(Color.BLUE)) nom = "Antoinette";
			if(c.equals(Color.MAGENTA)) nom = "Alec";
			if(c.equals(Color.ORANGE)) nom = "Léa";

			JLabel g = new JLabel(nom);
			g.setHorizontalAlignment(SwingConstants.CENTER);
			g.setBorder(Jborder);
			jbox.add(g);
			listj.add(g);
			j.suivant();
		} //possible probleme a la fin de la boucle qui est le joueur courant?
		
		System.out.println(j.getCourant().getJoueur().getCouleur());
		
		JLabelCourant = ((JLabel)jbox.getComponent(0));
		JLabelCourant.setBorder(JActuBorder);
		
		
		jmb.add(jbox);
		
		
	}
	
	public void jouer() {
		while(etage != 5) {
			etage();
		}
	}
	
	public void etage() { // set l'endroit des clé à chaque manche
		etage++;
		//changer l'affichage de l'étage (ALEC)
		m.changeEtage(etage);
		taille+=2;
		labyrinth = new LabyrinthGraphique(taille);
		if(joueurs.getTaille()==1) {
			labyrinth.getLabyrinthD().getLabyrinth()[taille][taille].addJoueur(courant.getJoueur());
		}
		CellJoueur tmp = courant;
		while(tmp.getSuivant()!=courant) {
			labyrinth.getLabyrinthD().getLabyrinth()[taille][taille].addJoueur(courant.getJoueur());
		}
		m.MAJlabyrinthG(labyrinth); //afficher le nouveau labyrinth avec les joueurs (ALEC)
		// while(!joueurs.estVide()) {
		// 	courant = courant.getSuivant();
		// 	actualisationLabelJCourant(); //mettre en surbrillance le joueur courant (ALEC) ça fonctionne normalement
	}
	
	//pour l'utilisation de script bash il faut utiliser la class ExecuteBash et donner en argument du constructeur le
	//chemin vers le script depuis src ex : "/src/java/controlleur/record.sh"

	public void tour(String deplacement) {

		Labyrinth lab = labyrinth.getLabyrinthD();
		Joueur current = courant.getJoueur();
		deplacement = deplacement.toLowerCase();
		if (deplacement.equals("haut") || deplacement.equals("eau") || deplacement.equals("au") || deplacement.equals("o") || deplacement.equals("oh")) {
			lab.haut(current);
		} else if (deplacement.equals("droite") || deplacement.equals("droit") || deplacement.equals("droit.")) {
			lab.droite(current);
		} else if (deplacement.equals("gauche") || deplacement.equals("gâche") || deplacement.equals("gouche") || deplacement.equals("douche")) {
			lab.gauche(current);
		} else if (deplacement.equals("bas") || deplacement.equals("bah")) { 
			lab.bas(current);
		} else {
			 //Alec message d'erreur
		}	
		m.MAJlabyrinthG(labyrinth);

		// if (!current.getCle().getAttrape()) {
		// 	if (current.getCle().getxCle() == current.getX() && current.getCle().getyCle() == current.getY()) { // pour moi c'est foncdamental qu'un joueur ait sa clé // vérifier si le joueur attérit sur sa cléf 
		// 		current.getCle().setAttrape(true);
		// 		// si oui, afficher une clef à coté de son pseudo (ALEC)	
		// 	}
		// }
		// else if (current.getX() == lab.getL() && current.getY()== lab.getL()) { 
		// 	// le mettre d'une couleur spéciale (ALEC)
		// 	joueursfinito.add(current);
		// 	joueurs.supprimer(current);	
		// }

		// if (joueurs.getTaille() == 0) {
		// 	etage();
		// }

	}
	
	public JPanel getJbox() {
		return jbox;
	}
	
	public String getTemps() {
		return temps;
	}
	
	//permet de mettre le joueur actuel avec la borduer spécial
	//normalement ça suit le joueur courant du jeu mais pas encore tester donc pas sur
	public void actualisationLabelJCourant() {
		if(JLabelCourantJPos == nbrJ-1) JLabelCourantJPos = 0;
		else JLabelCourantJPos++;
		
		JLabelCourant = (JLabel) jbox.getComponent(JLabelCourantJPos); 
		JLabelCourant.setBorder(JActuBorder);
	}
	
	
}
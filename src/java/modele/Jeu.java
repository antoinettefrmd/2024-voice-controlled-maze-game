package modele;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import vue.LabyrinthGraphique;
import vue.Menu;

public class Jeu {
	
	public static LabyrinthGraphique labyrinth;
	public static ListeDeJoueurs joueurs;
	public static ListeDeJoueurs joueursfinito;
	public static CellJoueur courant;
	public int etage;
	public int taille = 5;
	
	private Menu m;
	
	private int JLabelCourantJPos = 0;
	private JLabel JLabelCourant;
	
	private Border Jborder = BorderFactory.createLineBorder(Color.black, 2); //bordure pour entourer le nom d'un joueur
	private Border JActuBorder = BorderFactory.createLineBorder(Color.WHITE, 2); //bordure pour entourer le nom du joueur qui joue
	private Border JWinBorder = BorderFactory.createLineBorder(new Color(255, 215, 0));
	
	private LinkedList<JLabel> listj; //permet d'avoir la liste des JLabel représentant les joueurs
	private JPanel jbox;

	
	public Jeu(Menu m, ListeDeJoueurs j) {
		
		this.m = m;
		
		labyrinth = new LabyrinthGraphique(taille);
		m.MAJlabyrinthG(labyrinth); //met à jour l'interface graphique
		//afficher le labyrinth (ALEC)
		joueurs = j;
		joueursfinito = new ListeDeJoueurs();
		etage = 0;
		courant = joueurs.getCourant();
		
		JMenuBar jmb = m.getJMenuBar();
		
		listj = new LinkedList<JLabel>();
		
		jbox = new JPanel(new GridLayout(0, 5, 10, 0));
		jbox.setOpaque(false);
		
		int n = j.getTaille();
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
		
//		remettre plus tard -> le code ne fonctionne pas pour le moment
//		while(etage != 5) {
//			etage();
//		}
	}
	
	public void etage() {
		etage++;
		//changer l'affichage de l'étage (ALEC)
		m.changeEtage(etage);
		labyrinth = new LabyrinthGraphique(taille);
		taille+=2;
		if(joueurs.getTaille()==1) {
			labyrinth.getLabyrinthD().getLabyrinth()[taille][taille].addJoueur(courant.getJoueur());
		}
		CellJoueur tmp = courant;
		while(tmp.getSuivant()!=courant) {
			labyrinth.getLabyrinthD().getLabyrinth()[taille][taille].addJoueur(tmp.getJoueur());
		}
		m.MAJlabyrinthG(labyrinth); //afficher le nouveau labyrinth avec les joueurs (ALEC)
		while(!joueurs.estVide()) {
			courant = courant.getSuivant();
			actualisationLabelJCourant(); //mettre en surbrillance le joueur courant (ALEC) ça fonctionne normalement
			tour();
		}
	}
	
	public boolean tour() {
		return true;
		// faire jouer le joueur (lancer script bash+vérifier)
		// déplacer le pion
		// vérifier si le joueur attérit sur sa cléf
		// vérifier si il à sa clef et qu'il est au milieu
		// si il à finit, le mettre d'une couleur spéciale (ALEC)
	}
	
	public JPanel getJbox() {
		return jbox;
	}
	
	public void actualisationLabelJCourant() {
		if(JLabelCourantJPos == 5) JLabelCourantJPos = 0;
		else JLabelCourantJPos++;
		
		JLabelCourant = (JLabel) jbox.getComponent(JLabelCourantJPos);
		JLabelCourant.setBorder(JActuBorder);
	}
	
	
	
}
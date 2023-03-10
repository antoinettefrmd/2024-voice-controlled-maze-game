package modele;

import vue.LabyrinthGraphique;
import vue.Menu;

public class Jeu {
	
	public static LabyrinthGraphique labyrinth;
	public static ListeDeJoueurs joueurs;
	public static ListeDeJoueurs joueursfinito;
	public static CellJoueur courant;
	public int etage;
	public int taille = 5;
	
	public Jeu(Menu m, ListeDeJoueurs j) {
		labyrinth = new LabyrinthGraphique(taille);
		joueurs = j;
		joueursfinito = new ListeDeJoueurs();
		etage = 0;
		courant = joueurs.getCourant();
		
		while(etage != 5) {
			manche();
		}
	}
	
	public void manche() {
		etage++;
		//changer l'affichage de l'étage (ALEC)
		labyrinth = new LabyrinthGraphique(taille);
		taille+=2;
		CellJoueur tmp = courant;
		while(tmp.getSuivant()!=courant) {
			labyrinth.getLabyrinthD().getLabyrinth()[taille][taille].addJoueur(courant.getJoueur());
		}
		while(!joueurs.estVide()) {
			courant = courant.getSuivant();
			//mettre en surbrillance le joueur courant (ALEC)
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
}
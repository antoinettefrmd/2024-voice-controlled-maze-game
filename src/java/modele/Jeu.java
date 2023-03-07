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
		labyrinth = new LabyrinthGraphique(taille+2);
		CellJoueur tmp = courant;
		while(tmp.getSuivant()!=courant) {
			//placer le joueur au milieu du labyrinth
		}
	}
	
	public boolean tour() {
		return true;
	}
	
	
	public static void main(String[]args) {
		
	}
	
}
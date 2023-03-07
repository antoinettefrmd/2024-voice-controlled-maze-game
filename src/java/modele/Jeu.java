package modele;

import vue.LabyrinthGraphique;
import vue.Menu;

public class Jeu {
	
	public static LabyrinthGraphique labyrinth;
	public static ListeDeJoueurs joueurs;
	public static ListeDeJoueurs joueursfinito;
	public static CellJoueur courant;
	public int etage;
	
	public Jeu(ListeDeJoueurs j) {
		//labyrinth = new Labyrinth();
		joueurs = j;
		joueursfinito = new ListeDeJoueurs();
		etage = 0;
		courant = joueurs.getCourant();
	public Jeu(Menu m, ListeDeJoueurs ldj) {
		
	}
	
	public void jouer() {
		
	}
	
	
	public void manche() {
		
	}
	
	public boolean tour() {
		return true;
	}
	
	
	public static void main(String[]args) {
		
	}
	
}
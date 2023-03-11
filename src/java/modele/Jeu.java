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
		//afficher le labyrinth (ALEC)
		joueurs = j;
		joueursfinito = new ListeDeJoueurs();
		etage = 0;
		courant = joueurs.getCourant();
		
		while(etage != 5) {
			etage();
		}
	}
	
	public void etage() {
		etage++;
		//changer l'affichage de l'étage (ALEC)
		labyrinth = new LabyrinthGraphique(taille);
		taille+=2;
		if(joueurs.getTaille()==1) {
			labyrinth.getLabyrinthD().getLabyrinth()[taille][taille].addJoueur(courant.getJoueur());
		}
		CellJoueur tmp = courant;
		while(tmp.getSuivant()!=courant) {
<<<<<<< HEAD
			labyrinth.getLabyrinthD().getLabyrinth()[taille][taille].addJoueur(courant.getJoueur());
=======
			labyrinth.getLabyrinthD().getLabyrinth()[taille][taille].addJoueur(tmp.getJoueur());
>>>>>>> 671c26ef6793adaca41d9ddd4b936c9042f20310
		}
		//afficher le nouveau labyrinth avec les joueurs (ALEC)
		while(!joueurs.estVide()) {
			courant = courant.getSuivant();
			//mettre en surbrillance le joueur courant (ALEC)
			tour();
		}
	}
	
	public boolean tour() {
		return true;
		// faire jouer le joueur (lancer script bash+vérifier)
		// déplacer le pion si c'est bon
		// sinon le faire refaire la manip 5 fois max
		// si toujours pas bon, afficher un mesage d'erreur et finirle tour (ALEC)
		// vérifier si le joueur attérit sur sa cléf 
		// si oui, afficher une clef à coté de son pseudo (ALEC)
		// vérifier si il à sa clef et qu'il est au milieu
		// si il à finit, le mettre d'une couleur spéciale (ALEC)
	}
}
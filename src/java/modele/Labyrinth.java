package modele;

import java.util.Random;

public class Labyrinth {
	private Case[][] labyrinth;
	
	public Labyrinth(int n) {
		labyrinth = new Case[n][n];
		//Créer un labyrinthe de taille n*n, pour Léa
	}
	
	public Case[][] getLabyrinth(){ //On aura besoin d'avoir accès au labyrinthe
		return this.labyrinth;
	}
	
	//Je n'ai pas mis de setteur, je ne pense pas qu'on en aura besoin
	
	//Ajouter une classe interne coordonnées ? Ou autre part ?
	
	private class Case {
		private int mur; //Entier entre 0 et 3, pour connaître l'orientation du mur de la case
		//0 = Nord, 1 = Est, 2 = Sud, 3 = Ouest, ça vous va ?
		
		public Case() { //Crée une case avec un mur aléatoire
			Random rd = new Random();
			mur = rd.nextInt(4);
		}
		
		public Case(int i) { //Crée une case avec l'orientation du mur en argument 
			mur = i;
		}
	}
}
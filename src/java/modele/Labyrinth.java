package modele;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class Labyrinth {
	protected Case[][] labyrinth;
	private int[][]tab_val;//entier entre -1 et 4, -1 représentant la première case, 0 les cases non visitées 1-4 représentent d'ou vient l'ancienne case visitée
	//1 = gauche, 2 = bas, 3 = droite, 4 = haut
	protected int l; // taille du labyrinth
	private int x; // utile seulement pour la génération
	private int y; // idem
	
	public Labyrinth(int n) {
		l = n;
		labyrinth = new Case[2*l+1][2*l+1];
		tab_val = new int[n][n];
		for(int i=0;i<2*l+1;i++) {
			for(int j=0;j<2*l+1;j++) {
				labyrinth[i][j] = new Case();
				if(i%2==1 && j%2==1) labyrinth[i][j].setMur(false);;
			}
		}
		x = 0;
		y = 0;
		labyrinth[l][l].addPion(new Joueur(Color.BLUE));
		labyrinth[l][l].addPion(new Joueur(Color.RED));
		labyrinth[l][l].addPion(new Joueur(Color.PINK));
		tab_val[x][y] = -1;
		generate();
	}
	
	
	
	public Case[][] getLabyrinth(){ //On aura besoin d'avoir accès au labyrinthe
		return this.labyrinth;
	}
	
	//Ajouter une classe interne coordonnées ? Ou autre part ?
	
	public void generate() {
		boolean state = false;
		while(!state) {
			int direction[] = new int[4];
			int nbr_dir = 0;
			if(x<l-1) {
				if(tab_val[x+1][y] == 0 && labyrinth[2*x+2][2*y+1].mur) {
					direction[nbr_dir] = 1;
					nbr_dir++;
				}
			}
			if(y>0) {
				if(tab_val[x][y-1]==0 && labyrinth[2*x+1][2*y].mur) {
					direction[nbr_dir] = 2;
					nbr_dir++;
				}
			}
			if(x>0) {
				if(tab_val[x-1][y]==0 && labyrinth[2*x][2*y+1].mur) {
					direction[nbr_dir] = 3;
					nbr_dir++;
				}
			}
			if(y<l-1) {
				if(tab_val[x][y+1]==0 && labyrinth[2*x+1][2*y+2].mur) {
					direction[nbr_dir] = 4;
					nbr_dir++;
				}
			}
			
			
			if(nbr_dir>0) {
				Random r =  new Random();
				int i = r.nextInt(nbr_dir);
				switch(direction[i]) {
					case 1 :
						labyrinth[2*x+2][2*y+1].mur = false;
						x++;
						break;
					case 2 :
						labyrinth[2*x+1][2*y].mur = false;
						y--;
						break;
					case 3 :
						labyrinth[2*x][2*y+1].mur = false;
						x--;
						break;
					case 4 :
						labyrinth[2*x+1][2*y+2].mur = false;
						y++;
						break;
				}	
				//System.out.println(x);
				//System.out.println(y);
				tab_val[x][y] = direction[i];
			}
			else {
				if(tab_val[x][y]==1) x--;
				else if (tab_val[x][y]==2) y++;
				else if (tab_val[x][y]==3) x++;
				else if (tab_val[x][y]==4) y--;
				else state = true;
				/*switch(tab_val[x][y]) {
					case 1 :
						x--;
						break;
					case 2 :
						y++;
					case 3 :
						x++;
					case 4 :
						y--;
					default :
						state = true;
				}*/
			}
		}
	}
	
	public String toString() {
		String res = "";
		for(int i=0;i<2*l+1;i++) {
			for(int j=0;j<2*l+1;j++) {
				res += labyrinth[i][j].toString();
			}
			res+="\n";
		}
		return res;
	}
	
	public class Case {
		private boolean mur; // définie si une case est un mur ou non
		private ArrayList<Joueur> joueurs;
		public Case() { //Crée une case avec un mur par défaut
			mur = true;
			joueurs = new ArrayList<Joueur>();
		}

		// public Case getCase() {
		// 	return this;
		// }
		
		public Case(boolean i) { //Crée une case avec l'orientation du mur en argument 
			mur = i;
		}
		
		public boolean getMur() {
			return mur;
		}
		
		public void setMur(boolean b) {
			mur = b;
		}
		
		public void addPion(Joueur p) {
			joueurs.add(p);
		}
		public ArrayList<Joueur> getPions() {
			return this.joueurs;
		}
		public boolean delPion(Joueur p) {
			return joueurs.remove(p);
		}
		public boolean estVide() {
			return joueurs.isEmpty();
		}
		
		@Override
		public String toString() {
			if(mur) return "# ";
			else if (estVide()) return ". ";
			else return joueurs.get(0).toString();
		}
			
	}
	
	public static void main(String[]args) {
		Labyrinth test = new Labyrinth(21);
		System.out.println(test);
		System.out.println(test.labyrinth[20][20].mur);
	}

}

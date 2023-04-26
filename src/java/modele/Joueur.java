package modele;
import java.awt.Color;

public class Joueur {
 
    private Color couleur;
    private int x;
    private int y;
    private Labyrinth.Cle cle;

    public Joueur (Color couleur, int x, int y) {
    	this.x = x;
    	this.y = y;
        this.couleur = couleur;
        cle = null;
    }

    public Color getCouleur () {
        return this.couleur;
    }

    public String toString() {
        return "B";
    }
    
    public int getX() {
		return x;
	}
    public int getY() {
		return y;
	}
    public void setX(int x) {
		this.x = x;
	}
    public void setY(int y) {
		this.y = y;
	}

    public Labyrinth.Cle getCle() {
       return cle;
    }

    public void setCle(Labyrinth.Cle c) {
        cle = c;
    }
}

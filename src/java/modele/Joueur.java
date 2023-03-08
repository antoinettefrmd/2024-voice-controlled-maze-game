package modele;
import java.awt.Color;

public class Joueur {
 
    private Color couleur;
    private int x;
    private int y;
    

    public Joueur (Color couleur, int x, int y) {
    	this.x = x;
    	this.y = y;
        this.couleur = couleur;
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
    
}

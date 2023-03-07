package modele;

import java.awt.Color;

public class Joueur {
 
    private Color couleur;
    

    public Joueur (Color couleur) {
        this.couleur = couleur;
    }

    public Color getCouleur () {
        return this.couleur;
    }

    public String toString() {
        return "B";
    }
}
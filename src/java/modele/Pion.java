package modele;

public class Pion {
    private String couleur;

    public Pion (String couleur) {
        this.couleur = couleur;
    }

    public getCouleur () {
        return this.couleur;
    }

    public String toString() {
        return couleur[0].toUpperCase();
    }
}
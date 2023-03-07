package modele;

public class Pion {
    private String couleur;

    public Pion (String couleur) {
        this.couleur = couleur;
    }

    public String getCouleur () {
        return this.couleur;
    }

    public String toString() {
        return Character.toUpperCase(this.couleur.charAt(0)) + "";
    }
}
package modele;

public class Joueur {
    private String couleur;

    public Joueur (String couleur) {
        this.couleur = couleur;
    }

    public String getCouleur () {
        return this.couleur;
    }

    public String toString() {
        return Character.toUpperCase(this.couleur.charAt(0)) + "";
    }
}
package modele;

import javax.swing.JLabel;

public class CellJoueur {

	private Joueur joueur;
	private JLabel labelJoueur;
	private CellJoueur suivant;
	private CellJoueur precedent;
	
	public CellJoueur(Joueur p){
		joueur = p;
		suivant = this;
	}
	
	public CellJoueur(Joueur p, CellJoueur suiv,CellJoueur prec){
		joueur = p;
		suivant = suiv;
		precedent = prec;
	}
	
	public Joueur getJoueur() {
		return joueur;
	}
	
	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}
	
	public CellJoueur getSuivant() {
		return suivant;
	}
	
	public void setSuivant(CellJoueur suivant) {
		this.suivant = suivant;
	}
	
	public CellJoueur getPrecedent() {
		return precedent;
	}
	
	public void setPrecedent(CellJoueur precedent) {
		this.precedent = precedent;
	}
	
	public boolean supprimer(Joueur p,CellJoueur courant) {
		if(this!=courant) {
			if(joueur.equals(p)) {
				precedent.suivant=suivant;
				suivant.precedent=precedent;
				return true;
			}
			return suivant.supprimer(p, courant);
		}
		return false;
	}
	
	public JLabel getLabelJoueur() {
		return labelJoueur;
	}
	
	public void setLabelJoueur(JLabel labelJoueur) {
		this.labelJoueur = labelJoueur;
	}
}

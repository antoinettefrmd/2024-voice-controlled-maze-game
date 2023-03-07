package modele;


public class ListeDeJoueurs {

	private CellJoueur courant;
	private int taille;
	
	public ListeDeJoueurs(){
		taille = 0;
		courant =null;
	}
	
	public CellJoueur getCourant() {
		return courant;
	}
	
	public boolean estVide() {
		return taille == 0;
	}

	public void add(Joueur p){
		if(courant!=null) {
			CellJoueur res = new CellJoueur(p,courant,courant.getPrecedent());
			courant.getPrecedent().setSuivant(res);
			courant.setPrecedent(res);
		}
		else {
			courant = new CellJoueur(p);
			courant.setPrecedent(courant);
			courant.setSuivant(courant);
		}
		taille++;
	}
	
	public boolean supprimer(Joueur p) {
		if(courant==null) return false;
		if(courant.getSuivant().getJoueur().equals(courant.getJoueur()) && courant.getPrecedent().getJoueur().equals(courant.getJoueur())) {
			if(courant.getJoueur().equals(p)) {
				courant = null;
				taille--;
				return true;
			}
			else return false;
		}
		else if(courant.getJoueur().equals(p)){
			courant.getPrecedent().setSuivant(courant.getSuivant());
			courant.getSuivant().setPrecedent(courant.getPrecedent());
			courant = courant.getSuivant();
			taille--;
			return true;
		}
		else return courant.getSuivant().supprimer(p,courant);
	}
	
	public int getTaille() {
		return taille;
	}
}

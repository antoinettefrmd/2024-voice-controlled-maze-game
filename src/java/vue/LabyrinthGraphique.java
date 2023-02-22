package vue;

import java.awt.GridLayout;

import javax.swing.JPanel;

import modele.Labyrinth;

public class LabyrinthGraphique extends JPanel {
	
	private JPanel[][] labyrinth;
	
	private Labyrinth laby;

	/**
	 * n represente la taille n*n du labyrinth
	 */
	public LabyrinthGraphique(int n) {
		
		laby = new Labyrinth(n);
		
		setLayout(new GridLayout(n, n));
		labyrinth = new JPanel[n][n];
		
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				boolean estmur = (laby.getLabyrinth())[i][j].getMur();
				CaseGraphique tmp = new CaseGraphique(); //il faut ajouter un parametre a CaseGraphique pour lui donner estmur en argument
				labyrinth[i][j] = tmp;
				this.add(tmp);
			}
		}
		
	}

}

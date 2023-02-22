package vue;

import java.awt.GridLayout;

import javax.swing.JPanel;

import modele.Labyrinth;

public class LabyrinthGraphique extends JPanel {
	
	private JPanel[][] labyrinthG;
	private Labyrinth labyrinthD;

	/**
	 * n represente la taille n*n du labyrinth
	 */
	public LabyrinthGraphique(int n) {
		
		this.labyrinthD = new Labyrinth(n);
		setLayout(new GridLayout(2*n+1, 2*n+1));
		labyrinthG = new JPanel[2*n +1][2*n +1];
		
		
		for(int i = 0; i < 2*n+1; i++) {
			for(int j = 0; j < 2*n+1; j++) {
				CaseGraphique tmp = new CaseGraphique(labyrinthD.getLabyrinth()[i][j]);
				labyrinthG[i][j] = tmp;
				this.add(tmp);
			}
		}
		
	}

}

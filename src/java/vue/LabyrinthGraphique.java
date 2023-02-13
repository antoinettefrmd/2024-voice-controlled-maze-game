package vue;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class LabyrinthGraphique extends JPanel {
	
	private JPanel[][] labyrinth;

	/**
	 * n represente la taille n*n du labyrinth
	 */
	public LabyrinthGraphique(int n) {
		
		
		setLayout(new GridLayout(n, n));
		labyrinth = new JPanel[n][n];
		
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				CaseGraphique tmp = new CaseGraphique();
				labyrinth[i][j] = tmp;
				this.add(tmp);
			}
		}
		
	}

}

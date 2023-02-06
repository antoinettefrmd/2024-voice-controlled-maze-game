package vue;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class LabyrinthGraphique extends JPanel {

	/**
	 * n represente la taille n*n du labyrinth
	 */
	public LabyrinthGraphique(int n) {
		
		setLayout(new GridLayout(n, n));
		
		int maxcase = n*n;
		
		for(int i = 0; i < maxcase; i++) {
			add(new CaseGraphique());
		}
	}

}

package vue;

import java.awt.GridLayout;

import javax.swing.JPanel;

import modele.Labyrinth;

public class LabyrinthGraphique extends JPanel {
	
	private JPanel[][] labyrinthG;
	private Labyrinth labyrinthD;
	
//	private Border Jborder = BorderFactory.createLineBorder(Color.black, 2);
//	private Border JActuBorder = BorderFactory.createLineBorder(Color.gray, 2);
//	
//	private LinkedList<JLabel> listj;

	/**
	 * n represente la taille n*n du labyrinth
	 */
	public LabyrinthGraphique(Menu m, int n) {
		
		this.labyrinthD = new Labyrinth(n);
		setLayout(new GridLayout(2*n+1, 2*n+1));
		labyrinthG = new JPanel[2*n +1][2*n +1];
		
//		JMenuBar jmb = m.getJMenuBar();
//		
//		listj = new LinkedList<>();
//		
//		JLabel jactu = new JLabel("Joueur(s) / Joueuse(s) : ");
//		jactu.setBorder(new EmptyBorder(0, 10, 0, 0));
//		
//		jmb.add(jactu);
//		
//		JPanel jbox = new JPanel(new GridLayout(0, 5, 10, 0));
//		
//		if(georges) {
//			JLabel g = new JLabel("Georges");
//			g.setHorizontalAlignment(SwingConstants.CENTER);
//			g.setBorder(Jborder);
//			jbox.add(g);
//			listj.add(g);
//		}
//		
//		if(ronen) {
//			JLabel r = new JLabel("Ronen");
//			r.setHorizontalAlignment(SwingConstants.CENTER);
//			r.setBorder(Jborder);
//			jbox.add(r);
//			listj.add(r);
//		}
//		
//		if(antoinette) {
//			JLabel a1 = new JLabel("Antoinette");
//			a1.setHorizontalAlignment(SwingConstants.CENTER);
//			a1.setBorder(Jborder);
//			jbox.add(a1);
//			listj.add(a1);
//		}
//		
//		if(alec) {
//			JLabel a2 = new JLabel("Alec");
//			a2.setHorizontalAlignment(SwingConstants.CENTER);
//			a2.setBorder(Jborder);
//			jbox.add(a2);
//			listj.add(a2);
//		}
//		
//		if(lea) {
//			JLabel l = new JLabel("Lea");
//			l.setHorizontalAlignment(SwingConstants.CENTER);
//			l.setBorder(Jborder);
//			jbox.add(l);
//			listj.add(l);
//		}
//		
//		jmb.add(jbox);
		
		for(int i = 0; i < 2*n+1; i++) {
			for(int j = 0; j < 2*n+1; j++) {
				CaseGraphique tmp = new CaseGraphique(labyrinthD.getLabyrinth()[i][j]);
				labyrinthG[i][j] = tmp;
				this.add(tmp);
			}
		}
		
	}

}

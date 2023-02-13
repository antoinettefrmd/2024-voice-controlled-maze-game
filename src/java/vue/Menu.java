package vue;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modele.Labyrinth;

public class Menu extends JFrame {

	private JPanel contentPane;
	private JMenuBar jmb;
	
	private Labyrinth laby;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Menu() {
		
		//recupere la taille de l'écran
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		contentPane = new LabyrinthGraphique(5);
		
		jmb = new JMenuBar();

		ImageIcon logo = new ImageIcon(getClass().getResource("/images/logo.png"));
		
		JButton quitter = new JButton("Quitter");
		jmb.add(quitter);

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		setBounds(100, 100, 450, 300);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setJMenuBar(jmb);
		jmb.setVisible(false);
		
		setIconImage(logo.getImage());
		setTitle("Trouve La Voie | G.R.A.A.L");
		setContentPane(contentPane);
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
	}

}

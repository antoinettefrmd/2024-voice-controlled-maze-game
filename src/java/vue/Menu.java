package vue;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Menu extends JFrame {

	private JPanel contentPane;

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

		ImageIcon logo = new ImageIcon(getClass().getResource("/images/logo.png"));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(screenSize);
		
//		setBounds(100, 100, 450, 300);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setIconImage(logo.getImage());
		setTitle("Trouve La Voie | G.R.A.A.L");
		setContentPane(contentPane);
	}

}

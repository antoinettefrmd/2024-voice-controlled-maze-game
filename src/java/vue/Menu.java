package vue;

import java.lang.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Menu extends JFrame {

	private JPanel contentPane;
	private JPanel menuPanel;
	private JPanel creditPanel;
	private JMenuBar jmb; //permet de créer une barre en haut dans le jeu
	
	private Dimension dimMenu; //donne la dimension de notre interface graphique sur l'écran
	private Font DayDream; //police d'écriture pour le titre
	private Font minecraft; //police d'écriture pour le texte en général
	private BufferedImage image; //image pour le fond de l'interface graphique
	
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
	
	//Class qui permet de créer le menu
	private class MenuPanel extends JPanel {
		
		public MenuPanel() {
			
			//essaye de trouver l'image pour le fond de l'interface graphique, en cas d'échec cela renvoie une erreur
			try {
				image = ImageIO.read(new File("./src/ressources/images/gregory-ligman-brickwall.jpeg"));	
				// image = ImageIO.read(new File("./src/ressources/images/mur2.jpg"));	
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			
			
			setLayout(new BorderLayout()); //permet de set notre JPanel en BorderLayout
			setPreferredSize(dimMenu);
			
			JLabel titre = new JLabel("Trouve la voie");
			titre.setHorizontalAlignment(SwingConstants.CENTER);
			titre.setBorder(new EmptyBorder(150, 0, 0, 0));
			//titre.setForeground(new Color(220, 226, 195));
			titre.setForeground(new Color(34, 120, 15));
			titre.setForeground(new Color(250, 250, 175));
			titre.setFont(DayDream);
			
			//contient les boutons de navigation, cela permet de bien les organiser sur l'interface graphique
			JPanel buttonBox = new JPanel(new GridLayout(3, 0, 0, 60));
			buttonBox.setBorder(new EmptyBorder(100, 0, 0, 0)); //ajoute une bordure invisible qui nous permet de laisser un écart entre le titre et les boutons
			buttonBox.setOpaque(false);
			
			Dimension dimButton = new Dimension(150, 50);
			Font fontButton = new Font("Arial Black", ABORT, 20);
					
			//bouton qui permet d'accéder à ParametresPartie
			JButton jouer = new JButton("Jouer");
			jouer.setPreferredSize(dimButton); //permet d'appliquer la taille voulue au bouton
			jouer.setFont(minecraft); //permet d'appliquer une police d'écriture pour le texte du bouton
			jouer.setForeground(Color.white); //permet d'appliquer la couleur au texte du bouton
			jouer.setBorder(null); //fonction qui rend invisible les bordures du bouton
			jouer.setOpaque(false); //permet de rendre le background du bouton transparent
			//jouer.setBorderPainted(false);
			jouer.setFocusPainted(false); //permet de ne pas afficher le fait que le bouton soit selectionné
			jouer.setContentAreaFilled(false); //permet de ne pas rendre visible le fait de cliquer sur le bouton 
			jouer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); //change le curseur lorsque la souris est au-dessus du bouton
			
			//bouton qui permet d'accéder à Credit
			JButton credit = new JButton("Credit");

			credit.setPreferredSize(dimButton);
			credit.setFont(minecraft);
			credit.setForeground(Color.white);
			credit.setBorder(null);
			credit.setOpaque(false);
			credit.setFocusPainted(false);
			credit.setContentAreaFilled(false);
			credit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

			//bouton qui permet de quitter le jeu et d'arrêter le programme
			JButton quitter = new JButton("Quitter");
			quitter.setPreferredSize(dimButton);
			quitter.setFont(minecraft);
			quitter.setForeground(Color.white);
			quitter.setBorder(null);
			quitter.setOpaque(false);
			quitter.setFocusPainted(false);
			quitter.setContentAreaFilled(false);
			quitter.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

			buttonBox.add(jouer);
			buttonBox.add(credit);
			buttonBox.add(quitter);

			
			JPanel centerBox = new JPanel();
			centerBox.add(buttonBox);
			centerBox.setOpaque(false);
			
			add(titre, BorderLayout.NORTH);
			add(centerBox, BorderLayout.CENTER);
			setOpaque(false);
			
			//Controller
			//####################################################
			
			jouer.addActionListener((ActionEvent event) -> {
				ParametresPartie pp = new ParametresPartie(Menu.this); //On crée une instance de ParametresPartie
				getContentPane().remove(menuPanel); //On vide notre JFrame
				contentPane = pp;
				getContentPane().add(contentPane); //On remplit le JFrame avec notre instance de ParametresPartie
				contentPane.updateUI(); //On met à jour la vue pour que les changements soient visibles
			});
			
			credit.addActionListener((ActionEvent event) -> {
				getContentPane().remove(menuPanel);
				contentPane = creditPanel;
				getContentPane().add(contentPane);
				contentPane.updateUI();
			});
			
			quitter.addActionListener((ActionEvent event) -> {
				Menu.this.dispose(); //permet d'arrêter l'interface graphique
			});
			
			//####################################################

		}
		
		public void paintComponent(Graphics g) { //on redéfinit la méthode paintComponent de JPanel pour mettre une image en fond
			g.drawImage(image, 0, 0, null); //permet de dessiner une image sur le fond de notre JPanel
		}
		
	}

	/**
	 * Create the frame.
	 */
	public Menu() {
		
		//essaye de trouver les fichiers pour créer les polices d'écriture, si on ne les trouve cela renvoie une erreur
		try {
			InputStream is = getClass().getResourceAsStream("/fonts/Daydream.ttf"); //On va chercher le fichier contenant notre police d'écriture
			DayDream = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(40f); //On crée notre police avec une taille de 40
			is = getClass().getResourceAsStream("/fonts/Minecraft.ttf");
			minecraft = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(40f);
		}
		catch(IOException | FontFormatException e) {
			e.printStackTrace();
		}
		
		//recupere la taille de l'écran
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		dimMenu = new Dimension(1000,800);
		
		menuPanel = new MenuPanel();
		
		creditPanel = new Credit(this);
		
		//JMenuBar
		//####################################################
		
		jmb = new JMenuBar();
		
		//utile quand on est en partie, le bouton permet de quitter la partie et de retourner au Menu 
		JButton quitterjeu = new JButton("Quitter");
		
		jmb.add(quitterjeu);
		
		//####################################################

		
		ImageIcon logo = new ImageIcon(getClass().getResource("/images/logo.png")); //On va chercher l'image pour le logo du jeu
		//ImageIcon logo = new ImageIcon("../../images/logo.png");
		

//		setBounds(100, 100, 450, 300);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setJMenuBar(jmb);
		jmb.setVisible(false); //On set le JMenuBar a false car on en aura besoin seulement quand la partie a commencé
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //permet de stopper le programme quand on ferme la page
		setIconImage(logo.getImage()); //On applique le logo pour notre jeu
		setTitle("Trouve La Voie | G.R.A.A.L");
		getContentPane().add(menuPanel);
		setResizable(false);
		pack(); //permet de definir la taille du JFrame en la taille de son ContentPane donc ici de menuPanel
		setLocationRelativeTo(null); //permet de mettre la page du jeu au millieu de l'écran
		
		//Controller
		//####################################################

		quitterjeu.addActionListener((ActionEvent event) -> {
			jmb.setVisible(false);
			getContentPane().remove(contentPane);
			getContentPane().add(menuPanel);
		});
		
		//####################################################

	}
	
	//utilisé dans ParametresPartie et Credit pour revenir au Menu
	public void retour() {
		getContentPane().removeAll();
		getContentPane().add(menuPanel);
		menuPanel.updateUI();
	}
	
	//permet de lancer la partie depuis parametresPartie
	public void lancerjeu(boolean georges, boolean ronen, boolean antoinette, boolean alec, boolean lea) {
		//On crée une instance de LabyrinthGraphique à laquelle on donne l'instance de Menu, la taille du labyrinth et des boolean pour dire qui va jouer ou non
		LabyrinthGraphique lg = new LabyrinthGraphique(this, 11, georges, ronen, antoinette, alec, lea);
		jmb.setVisible(true);
		getContentPane().removeAll();
		contentPane = lg;
		getContentPane().add(contentPane);
		contentPane.updateUI();
	}
	
	public double getHauteur() {
		return dimMenu.getHeight();
	}
	
	public double getLargeur() {
		return dimMenu.getWidth();
	}
	
	public Font getMinecraft() {
		return minecraft;
	}
	
	public Font getDayDream() {
		return DayDream;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
}

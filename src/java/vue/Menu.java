package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import modele.Jeu;
import modele.ListeDeJoueurs;

public class Menu extends JFrame {
	
	private static class ProcessReader implements Callable {
		
		private InputStream inputStream;
		
		public ProcessReader(InputStream inputStream) {
			this.inputStream = inputStream;
		}

		@Override
		public Object call() throws Exception {
			return new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.toList());
		}
		
	}

	private JPanel contentPane;
	private JPanel menuPanel;
	private JPanel creditPanel;
	private JPanel creationJoueurPanel;
	private JMenuBar jmb; //permet de créer une barre en haut dans le jeu
	private JPanel jbox; //contient tous les labels pour les joueurs
	private JLabel etage;
	
	private Dimension dimMenu; //donne la dimension de notre interface graphique sur l'écran
	private Font DayDream; //police d'écriture pour le titre
	private Font minecraft; //police d'écriture pour le texte en général
	private Font minecraftButton;
	private BufferedImage image; //image pour le fond de l'interface graphique
	private Border Jborder = BorderFactory.createLineBorder(Color.black, 2);
	
	private ListeDeJoueurs joueurSupp; //permet d'ajouter des joueurs supplémentaire a la liste de joueur de base

	
	
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
			//titre.setForeground(new Color(34, 120, 15));
			titre.setForeground(new Color(250, 250, 175));
			titre.setFont(DayDream);
			
			//contient les boutons de navigation, cela permet de bien les organiser sur l'interface graphique
			JPanel buttonBox = new JPanel(new GridLayout(4, 0, 0, 60));
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
			
			//permet d'accéder au menu de création des joueurs
			JButton creationJoueur = new JButton("Creation");
			creationJoueur.setPreferredSize(new Dimension(200, 50));
			creationJoueur.setFont(minecraft);
			creationJoueur.setForeground(Color.white);
			creationJoueur.setBorder(null);
			creationJoueur.setOpaque(false);
			creationJoueur.setFocusPainted(false);
			creationJoueur.setContentAreaFilled(false);
			creationJoueur.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
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
			buttonBox.add(creationJoueur);
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
				ParametresPartie pp = new ParametresPartie(Menu.this, joueurSupp); //On crée une instance de ParametresPartie
				getContentPane().remove(menuPanel); //On vide notre JFrame
				contentPane = pp;
				getContentPane().add(contentPane); //On remplit le JFrame avec notre instance de ParametresPartie
				contentPane.updateUI(); //On met à jour la vue pour que les changements soient visibles
			});
			
			creationJoueur.addActionListener((ActionEvent event) -> {
				getContentPane().remove(menuPanel);
				contentPane = creationJoueurPanel;
				getContentPane().add(contentPane);
				contentPane.updateUI();

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
			is = getClass().getResourceAsStream("/fonts/Minecraft.ttf");
			minecraftButton = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(20f);

		}
		catch(IOException | FontFormatException e) {
			e.printStackTrace();
		}
		
		//recupere la taille de l'écran
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		joueurSupp = new ListeDeJoueurs();
		
		dimMenu = new Dimension(1000,800);
		
		menuPanel = new MenuPanel();
		
		creationJoueurPanel = new CreationJoueur(this);
		
		creditPanel = new Credit(this);
		
		
		//JMenuBar
		//####################################################
		
		jmb = new JMenuBar();
		
		jmb.setBackground(Color.GRAY);
		
		//utile quand on est en partie, le bouton permet de quitter la partie et de retourner au Menu 
		JButton quitterjeu = new JButton("Quitter");
		quitterjeu.setFocusPainted(false);
		quitterjeu.setBorderPainted(false);
		//quitterjeu.setFont(minecraftButton);
		quitterjeu.setBackground(new Color(176, 69, 25));

		etage = new JLabel("Etage : 0");
		//etage.setBorder(BorderFactory.createCompoundBorder(Jborder, new EmptyBorder(5,5,5,5)));
		etage.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JLabel jactu = new JLabel("Joueur(s) / Joueuse(s) : ");
		jactu.setBorder(new EmptyBorder(0, 10, 0, 0));
		
		//permet de lancer l'enregistrement de la voix
		JButton record = new JButton("Record");
		
		jmb.add(quitterjeu);
		jmb.add(etage);
		jmb.add(record);
		jmb.add(jactu);

		
		//####################################################

		
		ImageIcon logo = new ImageIcon(getClass().getResource("/images/logo.png")); //On va chercher l'image pour le logo du jeu
		//ImageIcon logo = new ImageIcon("../../images/logo.png");
		
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
			resetJMenuBar(jbox);
			jmb.setVisible(false);
			getContentPane().remove(contentPane);
			getContentPane().add(menuPanel);
		});
		
		record.addActionListener((ActionEvent event) -> {
			
			//on regarde si on est sur windows ou non
			boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
			
			ProcessBuilder builder = new ProcessBuilder();
			
			if(isWindows) {
				//il faut changer le fichier bat n'est pas bon
				builder.command(System.getProperty("user.dir") + "\\src\\java\\controlleur\\echo.bat");
			} else {
				builder.command("sh", "-c", System.getProperty("user.dir") + "/src/java/controlleur/record.sh");
			}
			
			ExecutorService pool = Executors.newSingleThreadExecutor();
			
			try {
				Process process = builder.start();
				
				ProcessReader task = new ProcessReader(process.getInputStream());
				
				Future<List<String>> future = pool.submit(task);
				
				List<String> results = future.get();
				for (String res : results) {
					System.out.println(res);
				}
				
				int exitCode = process.waitFor();
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			} finally {
				pool.shutdown();
			}
			
			
		});
		
		//####################################################

	}
	
	//utilisé dans ParametresPartie et Credit pour revenir au Menu
	public void retour() {
		getContentPane().removeAll();
		getContentPane().add(menuPanel);
		menuPanel.updateUI();
	}
	
	public void quitterJeu() {
		resetJMenuBar(jbox);
		jmb.setVisible(false);
		getContentPane().remove(contentPane);
		getContentPane().add(menuPanel);
	}
	
	//permet de lancer le jeu depuis parametresPartie
	public void lancerjeu(ListeDeJoueurs ldj) {
		
		Jeu j = new Jeu(this, ldj);
		
		jbox = j.getJbox();
		
		//on utilise normalement la classe jouer mais elle ne fonctionne pas pour le moment
		//j.jouer();
		
		//REMETTRE LE CODE QUAND LA FONCTION TOUR FONCTIONNE
//		MessageFin dialog;
//
//		try {
//			dialog = new MessageFin(this ,j.getTemps());
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//			//dialog.ferme();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
//		try {
//			//Thread.sleep(5000);
//			//dialog.dispose();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
	
	public void MAJlabyrinthG(LabyrinthGraphique lg) {
		jmb.setVisible(true);
		getContentPane().removeAll();
		contentPane = lg;
		getContentPane().add(contentPane);
		contentPane.updateUI();
	}
	
	public void MAJJMenuBAr(JPanel jbox) {
		jmb.remove(jbox);
		
	}
	
	public void resetJMenuBar(JPanel jbox) {
		jmb.remove(jbox);
		int n = jbox.getComponentCount();
		for(int i = 0; i < n; i++) {
			JLabel tmp = (JLabel) jbox.getComponent(i);
			tmp.setBorder(Jborder);
		}
	}
	
	public void changeEtage(int numEtage) {
		etage.setText("Etage : " + numEtage);
		etage.updateUI();
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
	
	public Font getMinecraftButton() {
		return minecraftButton;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public Dimension getDimMenu() {
		return dimMenu;
	}
	
	
}

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
	private JMenuBar jmb;
	
	private Dimension dimMenu;
	private Font DayDream;
	private Font minecraft;
	private BufferedImage image;
	
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
	
	private class MenuPanel extends JPanel {
		
		public MenuPanel() {
			
			try {
				image = ImageIO.read(new File("./src/ressources/images/gregory-ligman-brickwall.jpeg"));
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			
			setLayout(new BorderLayout());
			setPreferredSize(dimMenu);
			
			JLabel titre = new JLabel("Trouve la voie");
			titre.setHorizontalAlignment(SwingConstants.CENTER);
			titre.setBorder(new EmptyBorder(150, 0, 0, 0));
			//titre.setForeground(new Color(220, 226, 195));
			titre.setForeground(new Color(33, 54, 79));
			titre.setFont(DayDream);
			
			
			JPanel buttonBox = new JPanel(new GridLayout(3, 0, 0, 60));
			buttonBox.setBorder(new EmptyBorder(100, 0, 0, 0));
			buttonBox.setOpaque(false);
			
			Dimension dimButton = new Dimension(150, 50);
			Font fontButton = new Font("Arial Black", ABORT, 20);
					
			JButton jouer = new JButton("Jouer");
			jouer.setPreferredSize(dimButton);
			jouer.setFont(minecraft);
			jouer.setForeground(Color.white);
			jouer.setBackground(Color.black);
			jouer.setBorder(null);
			jouer.setOpaque(false);
			//jouer.setBorderPainted(false);
			jouer.setFocusPainted(false);
			jouer.setContentAreaFilled(false);
			jouer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			
			JButton credit = new JButton("Credit");
			credit.setPreferredSize(dimButton);
			credit.setFont(minecraft);
			credit.setForeground(Color.white);
			credit.setBackground(Color.black);
			credit.setBorder(null);
			credit.setOpaque(false);
			credit.setFocusPainted(false);
			credit.setContentAreaFilled(false);
			credit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

			
			JButton quitter = new JButton("Quitter");
			quitter.setPreferredSize(dimButton);
			quitter.setFont(minecraft);
			quitter.setForeground(Color.white);
			quitter.setBackground(Color.black);
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
				ParametresPartie pp = new ParametresPartie(Menu.this);
				getContentPane().remove(menuPanel);
				contentPane = pp;
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
				Menu.this.dispose();
			});
			
			//####################################################

		}
		
		public void paintComponent(Graphics g) {
			g.drawImage(image, 0, 0, null);
		}
		
	}

	/**
	 * Create the frame.
	 */
	public Menu() {
		
		try {
			InputStream is = getClass().getResourceAsStream("/fonts/Daydream.ttf");
			DayDream = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(40f);
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
		
		JButton quitterjeu = new JButton("Quitter");
		jmb.add(quitterjeu);
		
		//####################################################

		
		ImageIcon logo = new ImageIcon(getClass().getResource("/images/logo.png"));

//		setBounds(100, 100, 450, 300);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setJMenuBar(jmb);
		jmb.setVisible(false);
		
		JLabel l = new JLabel(new ImageIcon(getClass().getResource("/images/gregory-ligman-brickwall.jpeg")));
		l.setLocation(0, 0);
		l.setPreferredSize(new Dimension(1000, 800));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(logo.getImage());
		setTitle("Trouve La Voie | G.R.A.A.L");
		getContentPane().add(menuPanel);
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		
		//Controller
		//####################################################

		quitterjeu.addActionListener((ActionEvent event) -> {
			jmb.setVisible(false);
			getContentPane().remove(contentPane);
			getContentPane().add(menuPanel);
		});
		
		//####################################################

	}
	
	public void retour() {
		getContentPane().removeAll();
		getContentPane().add(menuPanel);
		menuPanel.updateUI();
	}
	
	public void lancerjeu() { //permet de lancer le jeu depuis parametresPartie
		LabyrinthGraphique lg = new LabyrinthGraphique(11);
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
	
	public BufferedImage getImage() {
		return image;
	}
	
}

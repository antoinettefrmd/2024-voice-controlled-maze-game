package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controlleur.ExecuteBash;
import modele.Joueur;
import modele.ListeDeJoueurs;

public class ParametresPartie extends JPanel {
	
	private static final long serialVersionUID = -2766301827457600207L;
	private BufferedImage image;
	private Font minecraft;
	private Font DayDream;
	private LinkedList<JCheckBox> jSuppJCheckBox;
	private Scanner sc;

	public ParametresPartie(Menu m, ListeDeJoueurs joueurSupp) {
		
		try {
			InputStream is = getClass().getResourceAsStream("/fonts/Daydream.ttf");
			DayDream = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(25f);
			is = getClass().getResourceAsStream("/fonts/Minecraft.ttf");
			minecraft = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(20f);
		}
		catch(IOException | FontFormatException e) {
			e.printStackTrace();
		}
		
		setSize(new Dimension(1000, 800));
		setLayout(new BorderLayout(0, 0));
		
		image = m.getImage();
		jSuppJCheckBox = new LinkedList<JCheckBox>();
		
		JPanel topBox = new JPanel(new BorderLayout());
		topBox.setOpaque(false);
		
		JButton retour = new JButton("retour");
		retour.setFont(minecraft);
		retour.setBackground(new Color(250, 250, 175));
		retour.setFocusPainted(false);
		retour.setHorizontalAlignment(SwingConstants.LEFT);

		
		JLabel titre = new JLabel("Pour jouer, cliquez sur votre nom puis parlez pendant 10 secondes pour vérifier votre identité");
		titre.setBorder(new EmptyBorder(50, 0, 0, 0)); //on crée un espace au-dessus du titre
		titre.setHorizontalAlignment(SwingConstants.CENTER); //permet de centrer le texte
		titre.setForeground(new Color(250, 250, 175));
		titre.setFont(DayDream.deriveFont(11f));
		
		topBox.add(retour, BorderLayout.WEST);
		
		JPanel centerBox = new JPanel();

		centerBox.setLayout(new BorderLayout());
		centerBox.setOpaque(false);
		
		JPanel centerTopBox = new JPanel();
		centerTopBox.setOpaque(false);
		centerTopBox.add(titre, BorderLayout.CENTER);
		centerTopBox.setOpaque(false);
		
		
		JPanel centerCenterBox = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
		centerCenterBox.setBorder(new EmptyBorder(200, 0, 0, 0));
		centerCenterBox.setOpaque(false);
		
		Color checkBoxColor = new Color(205,83,52); //new Color(250, 250, 175)
		
		JCheckBox j1 = new JCheckBox("Georges");
		j1.setFont(DayDream);
		j1.setForeground(checkBoxColor);
		j1.setOpaque(false);
		j1.setFocusPainted(false); //permet de ne pas afficher le fait que le bouton soit selectionné
		
		JCheckBox j2 = new JCheckBox("Ronen");
		j2.setFont(DayDream);
		j2.setForeground(checkBoxColor);
		j2.setOpaque(false);
		j2.setFocusPainted(false);

		JCheckBox j3 = new JCheckBox("Antoinette");
		j3.setFont(DayDream);
		j3.setForeground(checkBoxColor);
		j3.setOpaque(false);
		j3.setFocusPainted(false);

		JCheckBox j4 = new JCheckBox("Alec");
		j4.setFont(DayDream);
		j4.setForeground(checkBoxColor);
		j4.setOpaque(false);
		j4.setFocusPainted(false);

		JCheckBox j5 = new JCheckBox("Lea");
		j5.setFont(DayDream);
		j5.setForeground(checkBoxColor);
		j5.setOpaque(false);
		j5.setFocusPainted(false);
		
		centerCenterBox.add(j1);
		centerCenterBox.add(j2);
		centerCenterBox.add(j3);
		centerCenterBox.add(j4);
		centerCenterBox.add(j5);
		
		int n = joueurSupp.getTaille();
		
		ListeDeJoueurs courant = joueurSupp;
		
		//permet d'ajouter les JCheckBox pour cocher ou non les nouveaux joueurs
		for(int i = 0; i < n; i++) {
			JCheckBox tmpJCheckBox = new JCheckBox("Il faut creer un attribut nom pour les joueurs");
			tmpJCheckBox.setFont(DayDream);
			tmpJCheckBox.setForeground(new Color(205,83,52));
			tmpJCheckBox.setOpaque(false);
			tmpJCheckBox.setFocusPainted(false);
			centerCenterBox.add(tmpJCheckBox);
			jSuppJCheckBox.add(tmpJCheckBox);
			courant.suivant();
		}

		
		centerBox.add(centerTopBox, BorderLayout.NORTH);
		centerBox.add(centerCenterBox, BorderLayout.CENTER);
		
		
		JPanel southBox = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
		southBox.setOpaque(false);
		setBorder(new EmptyBorder(0, 0, 100, 0));
		
		JButton lancer = new GenericRoundedButton("Lancer");
		lancer.setFocusPainted(false);
		lancer.setFont(minecraft);
		lancer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lancer.setPreferredSize(new Dimension(200, 50));
		
		southBox.add(lancer);

		add(topBox, BorderLayout.NORTH);
		add(centerBox, BorderLayout.CENTER);
		add(southBox, BorderLayout.SOUTH);
		
		//Controller
		//####################################################
		
		retour.addActionListener((ActionEvent event) -> {
			m.retour();
		});
		
		j1.addActionListener((ActionEvent event) -> {
			appelVerif(j1,"G", 0.80);
		});
		
		j2.addActionListener((ActionEvent event) -> {
			appelVerif(j2,"R", 0.60);
		});
		
		j3.addActionListener((ActionEvent event) -> {
			appelVerif(j3,"AN",0.25);
		});
		
		j4.addActionListener((ActionEvent event) -> {
			appelVerif(j4,"AL",0.25);
		});
		
		j5.addActionListener((ActionEvent event) -> {
			appelVerif(j5,"L",0.10);
		});
		
		lancer.addActionListener((ActionEvent event) -> {
			//on verifie s'il y a au moins une personne qui participe au jeu, si c'est le cas on lance la partie
			 if(j1.isSelected() || j2.isSelected() || j3.isSelected() || j4.isSelected() || j5.isSelected()) {
	                
	                ListeDeJoueurs ldj = new ListeDeJoueurs();
	                
	                if(j1.isSelected()) {
	                    ldj.add(new Joueur(new Color(183, 82, 174), 0, 0));//georges, rose
	                }
	                
	                if(j2.isSelected()) {
	                    ldj.add(new Joueur(new Color(61, 163, 93), 0, 0));//ronen, vert
	                }
	                
	                if(j3.isSelected()) {
	                    ldj.add(new Joueur(new Color(25,130,196), 0, 0));//antoinette, bleu
	                }
	                
	                if(j4.isSelected()) {
	                    ldj.add(new Joueur(new Color(106,76,147), 0, 0));//alec, violet
	                }
	                
	                if(j5.isSelected()) {
	                    ldj.add(new Joueur(new Color(255,202,58), 0, 0));//lea, orange
	                }
	                
	                //permet d'ajouter les nouveaux joueurs a la liste de joueur
	                for(int i = 0; i < n; i++) {
	                	if(((JCheckBox)jSuppJCheckBox.get(i)).isSelected()) {
	                		ldj.add(joueurSupp.getCourant().getJoueur());
	                		joueurSupp.suivant();
	                	}
	                }

	                m.lancerjeu(ldj);
	            }
		});
		
		//####################################################
	}
	
	public void paintComponent(Graphics g) { //on redéfinit la méthode paintComponent de JPanel pour mettre une image en fond
		g.drawImage(image, 0, 0, null); //permet de dessiner une image sur le fond de notre JPanel
	}
	
	public void appelVerif(JCheckBox j,String n, double c) {
		if(j.isSelected()) {
			j.setEnabled(false);
			
			boolean verifier = verif(n,c);

			if(verifier) {
				j.setForeground(new Color(161,207,107));
			} else {
				j.setSelected(false);
			}
			j.setEnabled(true);
		} else {
			j.setForeground(new Color(205,83,52));
		}
	}
	
	public boolean verif(String n, double c) {
		ExecuteBash.cmd_system("./src/java/controlleur/recordVoix.sh");
		ExecuteBash.cmd_system("./src/java/controlleur/computeTest"+n+".sh");
		try {
			sc = new Scanner(new File("src/ressources/modele_voix/CFG/resultat.txt"));
		}
		catch(Exception e) {
			System.out.println("Erreur lors d’ouverture fichier:");
			e.printStackTrace();
			System.exit(1);
		}
		String res = "";
		while(sc.hasNext()) {
			res = sc.next();
		}
		System.out.println(res);
		sc.close();
		return (Double.parseDouble(res)>c);
	}

	@SuppressWarnings("unused")
	public class GenericRoundedButton extends JButton {
		
		private static final long serialVersionUID = -3826764148755708623L;
		private Color color;
		private Color colorOver;
		private Color colorClick;
		
		private Graphics2D g2d;
		
		 public GenericRoundedButton(String title) {
		   super(title);
		   init();
		 }

		 public GenericRoundedButton() {
		   super();
		   init();
		 }

		 private void init() {
		   //setBackground(Color.white);
		   setBorder(null);
		   setFocusable(false);
		   color = new Color(0x47,0xB0,0x19,0xFF);
		   colorOver = new Color(89, 217, 32);
		 }

		 public void paint(Graphics g) {
		   // Don't need to set these to get transparent button --
		   // we'll simply not draw it!
//		        setBackground(getParent().getBackground());
//		        setBorder(Styles.BORDER_NONE);

		   // Don't draw the button or border
		   this.setContentAreaFilled(false);
		   this.setBorderPainted(false);

		   g2d = (Graphics2D)g;
		      
		   // Anti-aliased lines and text
		   g2d.setRenderingHint(
		        RenderingHints.KEY_ANTIALIASING,
		        RenderingHints.VALUE_ANTIALIAS_ON);
		   g2d.setRenderingHint(
		        RenderingHints.KEY_TEXT_ANTIALIASING,
		        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		    
		   // This is needed on non-Mac so text
		   // is repainted correctly!
		   super.paint(g);

		   // Make it grey #DDDDDD, and make it round with 1px
		   // black border
		   //g2d.setColor(new Color(0x47,0xB0,0x19,0xFF));
		   g2d.setColor(new Color(250, 250, 175));
		   g2d.fillRoundRect(0,0,getWidth(),getHeight(),18,18);
		   g2d.setColor(Color.BLACK);
		   g2d.drawRoundRect(0,0,getWidth()-1,getHeight()-1,18,18);

		   // Determine the label size so can center it
		   FontRenderContext frc = new FontRenderContext(null, false, false);
		   Rectangle2D r = getFont().getStringBounds(getText(), frc);

		   float xMargin = (float)(getWidth()-r.getWidth())/2;
		   float yMargin = (float)(getHeight()-getFont().getSize())/2;

		   // Draw the text in the center
		   g2d.drawString(getText(),xMargin,
		     (float)getFont().getSize()+yMargin);
		 }

	}
}

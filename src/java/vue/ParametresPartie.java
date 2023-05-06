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
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import modele.Joueur;
import modele.ListeDeJoueurs;

public class ParametresPartie extends JPanel {
	
	private static final long serialVersionUID = -2766301827457600207L;
	private BufferedImage image;
	private Font minecraft;
	private Font mincecarftcheckbox;
	private Font DayDream;
	private LinkedList<JCheckBox> jSuppJCheckBox;

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
		mincecarftcheckbox = m.getMinecraft();
		jSuppJCheckBox = new LinkedList<JCheckBox>();
		
		JPanel topBox = new JPanel(new BorderLayout());
		topBox.setOpaque(false);
		
		JButton retour = new JButton("retour");
		retour.setFont(minecraft);
//		retour.setBackground(new Color(176, 69, 25));
		retour.setBackground(new Color(250, 250, 175));
		retour.setFocusPainted(false);
		retour.setHorizontalAlignment(SwingConstants.LEFT);

		
		JLabel titre = new JLabel("Choisir les participans :");
		titre.setBorder(new EmptyBorder(50, 0, 0, 0)); //on crée un espace au-dessus du titre
		titre.setHorizontalAlignment(SwingConstants.CENTER); //permet de centrer le texte
		titre.setForeground(new Color(250, 250, 175));
		titre.setFont(DayDream);
		
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
		
		Color checkBoxColor = Color.red; //new Color(250, 250, 175)
		Color checkBoxColorV = Color.green;

		
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
			tmpJCheckBox.setForeground(new Color(250, 250, 175));
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
		// lancer.setBorder(null);
		// lancer.setOpaque(false);
		// lancer.setContentAreaFilled(false);

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
			if(j1.isSelected()) {
				j1.setEnabled(false);
				
				//REMETTRE QUAND LES Instruction jsp sont ajouter
//				ExecuteBash recordVoix = new ExecuteBash("/src/java/controlleur/recordVoix.sh");
//				String fichierWav = recordVoix.getResultat();
				
				//Instruction jsp il faut verifier quoi a faire dans verificationJoueur
				//VerificationJoueur vj = new VerificationJoueur();
				//Il faut peut etres transmettre une information? pour verifier?
				
				
				//mettre le resultat de Instruction jsp
				boolean verifier = true;
				//il faut remplacer par :
				//boolean verifier = vj.getVerif();
				
				if(verifier) {
					j1.setForeground(checkBoxColorV);
				} else {
					j1.setSelected(false);
				}
				j1.setEnabled(true);
			} else {
				j1.setForeground(checkBoxColor);
			}
		});
		
		j2.addActionListener((ActionEvent event) -> {
			if(j2.isSelected()) {
				j2.setEnabled(false);
				
				//REMETTRE QUAND LES Instruction jsp sont ajouter
//				ExecuteBash recordVoix = new ExecuteBash("/src/java/controlleur/recordVoix.sh");
//				String fichierWav = recordVoix.getResultat();
				
				//Instruction jsp il faut verifier quoi
				
				boolean verifier = true; //mettre le resultat de Instruction jsp
				if(verifier) {
					j2.setForeground(checkBoxColorV);
				} else {
					j2.setSelected(false);
				}
				j2.setEnabled(true);
			} else {
				j2.setForeground(checkBoxColor);
			}
		});
		
		j3.addActionListener((ActionEvent event) -> {
			if(j3.isSelected()) {
				j3.setEnabled(false);
				
				//REMETTRE QUAND LES Instruction jsp sont ajouter
//				ExecuteBash recordVoix = new ExecuteBash("/src/java/controlleur/recordVoix.sh");
//				String fichierWav = recordVoix.getResultat();
				
				//Instruction jsp il faut verifier quoi
				
				boolean verifier = true; //mettre le resultat de Instruction jsp
				if(verifier) {
					j3.setForeground(checkBoxColorV);
				} else {
					j3.setSelected(false);
				}
				j3.setEnabled(true);
			} else {
				j3.setForeground(checkBoxColor);
			}
		});
		
		j4.addActionListener((ActionEvent event) -> {
			if(j4.isSelected()) {
				j4.setEnabled(false);
				
				//REMETTRE QUAND LES Instruction jsp sont ajouter
//				ExecuteBash recordVoix = new ExecuteBash("/src/java/controlleur/recordVoix.sh");
//				String fichierWav = recordVoix.getResultat();
				
				//Instruction jsp il faut verifier quoi
				
				boolean verifier = true; //mettre le resultat de Instruction jsp
				if(verifier) {
					j4.setForeground(checkBoxColorV);
				} else {
					j4.setSelected(false);
				}
				j4.setEnabled(true);
			} else {
				j4.setForeground(checkBoxColor);
			}
		});
		
		j5.addActionListener((ActionEvent event) -> {
			if(j5.isSelected()) {
				j5.setEnabled(false);
				
				//REMETTRE QUAND LES Instruction jsp sont ajouter
//				ExecuteBash recordVoix = new ExecuteBash("/src/java/controlleur/recordVoix.sh");
//				String fichierWav = recordVoix.getResultat();
				
				//Instruction jsp il faut verifier quoi
				
				boolean verifier = true; //mettre le resultat de Instruction jsp
				if(verifier) {
					j5.setForeground(checkBoxColorV);
				} else {
					j5.setSelected(false);
				}
				j5.setEnabled(true);
			} else {
				j5.setForeground(checkBoxColor);
			}
		});
		
		lancer.addActionListener((ActionEvent event) -> {
			//on verifie s'il y a au moins une personne qui participe au jeu, si c'est le cas on lance la partie
			 if(j1.isSelected() || j2.isSelected() || j3.isSelected() || j4.isSelected() || j5.isSelected()) {
	                
	                ListeDeJoueurs ldj = new ListeDeJoueurs();
	                
	                if(j1.isSelected()) {
	                    ldj.add(new Joueur(Color.PINK, 0, 0));//georges
	                }
	                
	                if(j2.isSelected()) {
	                    ldj.add(new Joueur(Color.GREEN, 0, 0));//ronen
	                }
	                
	                if(j3.isSelected()) {
	                    ldj.add(new Joueur(Color.BLUE, 0, 0));//antoinette
	                }
	                
	                if(j4.isSelected()) {
	                    ldj.add(new Joueur(Color.MAGENTA, 0, 0));//alec
	                }
	                
	                if(j5.isSelected()) {
	                    ldj.add(new Joueur(Color.ORANGE, 0, 0));//lea
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

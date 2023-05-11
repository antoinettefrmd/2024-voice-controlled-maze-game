package modele;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import modele.Labyrinth.Cle;
import vue.JoueurSuivant;
import vue.LabyrinthGraphique;
import vue.Menu;
import vue.MessageFin;
import vue.Scores;

public class Jeu {
	
	public static LabyrinthGraphique labyrinth;
	public static ListeDeJoueurs joueurs;
	public static ListeDeJoueurs joueursencours;
	public static CellJoueur courant;
	public int etage;
	private JLabel chrono;
	private long startTime;
	private long duration;
	private Timer time;
	public int taille = 5;
	public static LinkedList<Cle> clefs;
	private BufferedImage clef;
	private Icon clefvertical;
	private BufferedImage escalier;
	private Font font = new Font("Arial Black", Font.BOLD, 12);

	
	private Menu m;
	
	//nombre de joueur max pour la partie
	private int nbrJ;
	
	private Color gold = new Color(255, 215, 0); //quand le joueur a une clef on met son pseudo en couleur gold
	
	private Border Jborder1 = BorderFactory.createLineBorder(Color.black, 2); //bordure pour entourer le nom d'un joueur
	private Border Jborder = BorderFactory.createCompoundBorder(Jborder1, new EmptyBorder(0,9,0,6));
	
	private Border JActuBorder1 = BorderFactory.createLineBorder(Color.WHITE, 2); //bordure pour entourer le nom du joueur qui joue
	private Border JActuBorder = BorderFactory.createCompoundBorder(JActuBorder1, new EmptyBorder(0,9,0,6));

	private Border JWinBorder = BorderFactory.createLineBorder(gold, 2); //bordure pour les joueurs qui ont terminé
	private Font Jfontwin; //texte pour les joueurs qui ont terminé
	
	private JPanel jbox; //permet de contenir tous les JLabel des joueurs et de les organiser dans la topbar

	
	@SuppressWarnings("unchecked")
	public Jeu(Menu m, ListeDeJoueurs j) {
		
		this.m = m;
		clefs = new LinkedList<Cle>();

		joueurs = ListeDeJoueurs.copier(j);
		joueursencours = ListeDeJoueurs.copier(j);
		labyrinth = new LabyrinthGraphique(taille, joueursencours);
		labyrinth.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "right");
		labyrinth.getActionMap().put("right", right);
		labyrinth.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "left");
		labyrinth.getActionMap().put("left", left);
		labyrinth.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "up");
		labyrinth.getActionMap().put("up", up);
		labyrinth.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "down");
		labyrinth.getActionMap().put("down", down);

		etage = 0;
		
		int n = j.getTaille();
		nbrJ = n;		
		
		try {
			clef = ImageIO.read(new File("./src/ressources/images/key.png"));
			escalier = ImageIO.read(new File("./src/ressources/images/escalier.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JMenuBar jmb = m.getJMenuBar();
		
		jbox = new JPanel(new GridLayout(0, 5, 10, 0));
		jbox.setOpaque(false);
		
		@SuppressWarnings("rawtypes")
		Map  attributes = font.getAttributes();
		attributes.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
		Jfontwin = new Font(attributes); //on fait une police d'écriture barre pour les joueurs qui ont terminé
		
		BufferedImage rotatedclef = rotateImage(clef, -90);
		clefvertical = new Icon() {
			
			@Override
			public void paintIcon(Component c, Graphics g, int x, int y) {
				Graphics2D surface = (Graphics2D) g;
				surface.scale(0.4, 0.4);
				surface.drawImage(rotatedclef, 0, 7, null);
				surface.scale(2.5, 2.5);
			}
			
			@Override
			public int getIconWidth() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public int getIconHeight() {
				// TODO Auto-generated method stub
				return 0;
			}
		};
		
		for(int i = 0; i < n; i++) {
			//recupère la couleur du joueur
			Color c = j.getCourant().getJoueur().getCouleur();
			
			String nom = "";
			
			//on verifie la couleur du joueur pour lui donner son nom
			if(c.equals(new Color(183, 82, 174))) nom = "Georges";
			if(c.equals(new Color(61, 163, 93))) nom = "Ronen";
			if(c.equals(new Color(25,130,196))) nom = "Antoinette";
			if(c.equals(new Color(106,76,147))) nom = "Alec";
			if(c.equals(new Color(255,202,58))) nom = "Léa";

			JLabel g = new JLabel(nom);
			
			g.setHorizontalAlignment(SwingConstants.CENTER);
			g.setBorder(Jborder);
			jbox.add(g);
			j.getCourant().setLabelJoueur(g);
			j.suivant();
		} //possible probleme a la fin de la boucle qui est le joueur courant?
		
		((JLabel)jbox.getComponent(0)).setBorder(JActuBorder);
		
		joueurs = ListeDeJoueurs.copier(j);
		joueursencours = ListeDeJoueurs.copier(j);
		labyrinth = new LabyrinthGraphique(taille, joueursencours);
		
		courant = joueursencours.getCourant();
		
		//permet de faire en sorte que la case du millieu soit un escalier
		labyrinth.getCase(taille, taille).setSortie(true);
		labyrinth.getCase(taille, taille).setEscalier(escalier);
		
		CellJoueur current = joueurs.getCourant();
		CellJoueur tmp = current;
		do {
			int l = labyrinth.getLabyrinthD().getL();
			tmp.getJoueur().setX(l);
			tmp.getJoueur().setY(labyrinth.getLabyrinthD().getL());
			labyrinth.getLabyrinthD().getLabyrinth()[l][l].addJoueur(tmp.getJoueur());
			tmp = tmp.getSuivant();
		} while (tmp != current);

				
		m.MAJlabyrinthG(labyrinth); //met à jour l'interface graphique et donc le labyrinth
		
		genererClefs();
		
		chrono = new JLabel("temps écoulé : 0");
		chrono.setBackground(Color.CYAN);
		//chrono.setBorder(Jborder);
		startTime = System.currentTimeMillis();
		time = new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				duration = (System.currentTimeMillis() - startTime) / 1000;
				LocalTime lt = LocalTime.ofSecondOfDay(duration);
				chrono.setText("temps : " + lt+"  ");
			}
		});
		time.start();
		jmb.add(jbox);
		jmb.add(chrono);
		
		
	}
	
	@SuppressWarnings("serial")
	private Action right = new AbstractAction() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//LabyrinthGraphique.this.labyrinthD.droite(LabyrinthGraphique.this.labyrinthD.getCurrent().getJoueur());
			//repaint();
			tour("je vais à droite");
		}
	};

	@SuppressWarnings("serial")
	private Action left = new AbstractAction() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//LabyrinthGraphique.this.labyrinthD.gauche(LabyrinthGraphique.this.labyrinthD.getCurrent().getJoueur());
			//repaint();
			tour("je vais à gauche");
		}
	};
	
	@SuppressWarnings("serial")
	private Action up = new AbstractAction() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//LabyrinthGraphique.this.labyrinthD.haut(LabyrinthGraphique.this.labyrinthD.getCurrent().getJoueur());
			//repaint();
			tour("je vais en haut");
		}
	};
	
	@SuppressWarnings("serial")
	private Action down = new AbstractAction() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//LabyrinthGraphique.this.labyrinthD.bas(LabyrinthGraphique.this.labyrinthD.getCurrent().getJoueur());
			//repaint();
			tour("je vais en bas");
		}
	};

	public void setEtage(int etage) {
		this.etage = etage;
		m.changeEtage(etage);
	}
	
	public void etage() { // set l'endroit des clé à chaque manche
		etage++;
		m.changeEtage(etage);
		taille+=2;
		joueursencours = ListeDeJoueurs.copier(joueurs);
		courant = joueursencours.getCourant();
		for (int i = 0 ; i < joueursencours.getTaille() ; i++){
			joueursencours.getCourant().getJoueur().setX(taille);
			joueursencours.getCourant().getJoueur().setY(taille);
			courant = courant.getSuivant();
		}
		
		

		reinitialisationToutJLabel();
		
		labyrinth = new LabyrinthGraphique(taille, joueursencours);
		labyrinth.getCase(taille, taille).setSortie(true);
		labyrinth.getCase(taille, taille).setEscalier(escalier);
		if(joueursencours.getTaille()==1) {
			//labyrinth.getLabyrinthD().getLabyrinth()[taille][taille].addJoueur(courant.getJoueur());
			courant.getJoueur().setX(taille);
			courant.getJoueur().setY(taille);
		}
		CellJoueur tmp = courant;
		while((tmp=tmp.getSuivant())!=courant) {
			//labyrinth.getLabyrinthD().getLabyrinth()[taille][taille].addJoueur(courant.getJoueur());
			tmp.getJoueur().setX(taille);
			tmp.getJoueur().setY(taille);
		}
		m.MAJlabyrinthG(labyrinth); //afficher le nouveau labyrinth avec les joueurs (ALEC)
		genererClefs();

	}
	
	public void tour(String deplacement) {

		Labyrinth lab = labyrinth.getLabyrinthD();
		Joueur current = courant.getJoueur();
		deplacement = deplacement.toLowerCase();
		if (deplacement.equals("je vais en haut") || deplacement.equals("je vais en haut.")) {
			lab.haut(current);
		} else if (deplacement.equals("je vais à droite") || deplacement.equals("je vais à droite.")) {
			lab.droite(current);
		} else if (deplacement.equals("je vais à gauche") || deplacement.equals("je vais à gauche.")) {
			lab.gauche(current);
		} else if (deplacement.equals("je vais en bas.") || deplacement.equals("je vais en bas")) { 
			lab.bas(current);
		} else {
			@SuppressWarnings("unused")
			JoueurSuivant js = new JoueurSuivant();		
		}	
		m.MAJlabyrinthG(labyrinth);

		if (!current.getCle().getAttrape()) {
			if (current.getCle().getxCle() == current.getX() && current.getCle().getyCle() == current.getY()) {
				current.getCle().setAttrape(true);
				ajouteclefJLabel();
				labyrinth.getCase(current.getX(), current.getY()).setClefprise(true);;
			}
			courant = courant.getSuivant();
		}
		else if (current.getX() == lab.getL() && current.getY()== lab.getL()) { 
			barreJLabel();
			courant.getLabelJoueur().setBorder(Jborder);
			courant = courant.getSuivant();
			joueursencours.supprimer(courant.getPrecedent().getJoueur());
			if (joueursencours.getTaille() == 0) {
				if(etage<5){
					etage();
				}	
				else finir();
			}
		}
		else {
			courant = courant.getSuivant();
		}
		actualisationLabelJCourant();		
	}
	
	public JPanel getJbox() {
		return jbox;
	}
	
	//permet de mettre le joueur actuel avec la borduer spécial
	//normalement ça suit le joueur courant du jeu mais pas encore tester donc pas sur
	public void actualisationLabelJCourant() {
		courant.getPrecedent().getLabelJoueur().setBorder(Jborder);
		courant.getLabelJoueur().setBorder(JActuBorder);;
	}

	public void genererClefs(){
		int l = labyrinth.getLabyrinthD().getL();
		Random rand = new Random();
		CellJoueur tmp = courant;
		int n = 0;
		
		do {
			int xCle = 0;
			int yCle = 0; 
			while(!labyrinth.getLabyrinthD().surChemin(xCle,yCle) || !PasDejaDeClef(xCle,yCle) || (xCle == l && yCle == l)){
				xCle = rand.nextInt(2*l)+1;
				yCle = rand.nextInt(2*l)+1;
			}
			Cle c = labyrinth.getLabyrinthD().new Cle(tmp.getJoueur().getCouleur(),xCle,yCle);
			clefs.add(c);
			
			BufferedImage btmp = new BufferedImage(clef.getWidth(), clef.getHeight(), BufferedImage.TYPE_INT_ARGB);
			
			for(int i = 0; i < btmp.getWidth(); i++){
				for(int j = 0; j < btmp.getHeight(); j++) {
					if(clef.getRGB(i, j) == Color.BLACK.getRGB()) {
						btmp.setRGB(i,j, tmp.getJoueur().getCouleur().getRGB());
					}
				}
			}
			
			tmp.getJoueur().setCle(c);
			labyrinth.getCase(xCle, yCle).setImageClef(btmp);
			labyrinth.getCase(xCle,yCle).setEstCle(true);
			labyrinth.getCase(xCle, yCle).setClej(c);
			tmp = tmp.getSuivant();
			n++;
		} while(n < nbrJ);
	}
	
	public boolean PasDejaDeClef(int x, int y){
		for(int i=0;i<clefs.size();i++){
			if(clefs.get(i).getxCle() == x && clefs.get(i).getyCle() == y) return false;
		}
		return true;
	}
	
	public void ajouteclefJLabel() {
		courant.getLabelJoueur().setIcon(clefvertical);
	}
	
	public void barreJLabel() {
		courant.getLabelJoueur().setFont(Jfontwin);
	}
	
	public void bordureGoldJLabel() {
		courant.getLabelJoueur().setBorder(JWinBorder);
	}
	
	public void reinitialisationToutJLabel() {
		for(int i = 0; i < nbrJ; i++) {
			JLabel tmp = (JLabel) jbox.getComponent(i);
			tmp.setIcon(null);
			tmp.setFont(font);
			tmp.setBorder(Jborder);
		}
		courant.getLabelJoueur().setBorder(JActuBorder);
	}
	
	private static BufferedImage rotateImage(BufferedImage buffImage, double angle) {
	    double radian = Math.toRadians(angle);
	    double sin = Math.abs(Math.sin(radian));
	    double cos = Math.abs(Math.cos(radian));

	    int width = buffImage.getWidth();
	    int height = buffImage.getHeight();

	    int nWidth = (int) Math.floor((double) width * cos + (double) height * sin);
	    int nHeight = (int) Math.floor((double) height * cos + (double) width * sin);

	    BufferedImage rotatedImage = new BufferedImage(
	            nWidth, nHeight, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D graphics = rotatedImage.createGraphics();

	    graphics.setRenderingHint(
	            RenderingHints.KEY_INTERPOLATION,
	            RenderingHints.VALUE_INTERPOLATION_BICUBIC);

	    graphics.translate((nWidth - width) / 2, (nHeight - height) / 2);
	    // rotation around the center point
	    graphics.rotate(radian, (double) (width / 2), (double) (height / 2));
	    graphics.drawImage(buffImage, 0, 0, null);
	    graphics.dispose();

	    return rotatedImage;
	}
	
	public Timer getTime() {
		return time;
	}
	
	public JLabel getChrono() {
		return chrono;
	}
	
	public void finir() {
		m.getJMenuBar().remove(chrono);
		time.stop();
		etage = 0;
		m.changeEtage(etage);
		boolean majscores = ((Scores) m.getMeilleurScore()).sauvegardeScores(duration);
		if(majscores) {
			((Scores) m.getMeilleurScore()).majScores();
		}
		
		MessageFin dialog = new MessageFin(m , duration);
		dialog.setVisible(true);
	}
	
}
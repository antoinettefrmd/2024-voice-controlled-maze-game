package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class CreationJoueur extends JPanel {
	
	private BufferedImage image;
	private Font minecraft;
	private Font minecraftButton;
	private Dimension dimButton = new Dimension(150, 50);

	/**
	 * Create the panel.
	 */
	public CreationJoueur(Menu m) {
		
		image = m.getImage();
		minecraft = m.getMinecraft();
		minecraftButton = m.getMinecraftButton();
		
		setLayout(new BorderLayout());
		
		JPanel topBox = new JPanel(new BorderLayout());
		topBox.setOpaque(false);
		topBox.setBorder(new EmptyBorder(new Insets(0, 0, 20, 0)));

		
		JButton retour = new JButton("retour");
		retour.setFont(minecraftButton);
		retour.setBackground(new Color(176, 69, 25));
		retour.setFocusPainted(false);
		retour.setHorizontalAlignment(SwingConstants.LEFT);
		
		topBox.add(retour, BorderLayout.WEST);
		
		JPanel centerBox = new JPanel(new BorderLayout());
		centerBox.setOpaque(false);
		
		
		JLabel titre = new JLabel("Pour créer/supprimer un nouveau joueur choisissez :");
		titre.setHorizontalAlignment(SwingConstants.CENTER);
		titre.setOpaque(false);
		titre.setFont(new Font("Arial Black", ABORT, 20));
		
		JPanel buttonBox = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
		buttonBox.setBorder(new EmptyBorder(new Insets(300, 0, 0, 0)));
		buttonBox.setOpaque(false);
		
		JButton creermodele = new JButton("Créer");
		creermodele.setVerticalAlignment(SwingConstants.CENTER);
		creermodele.setPreferredSize(dimButton);
		
		JButton importermodele = new JButton("Importer");
		importermodele.setVerticalAlignment(SwingConstants.CENTER);
		importermodele.setPreferredSize(dimButton);


		JButton supprimermodele = new JButton("Supprimer");
		supprimermodele.setVerticalAlignment(SwingConstants.CENTER);
		supprimermodele.setPreferredSize(dimButton);


		buttonBox.add(creermodele);
		buttonBox.add(importermodele);
		buttonBox.add(supprimermodele);

		
		centerBox.add(titre, BorderLayout.NORTH);
		centerBox.add(buttonBox, BorderLayout.CENTER);
		
		add(topBox, BorderLayout.NORTH);
		add(centerBox);
		
		//Controller
		//####################################################
		
		retour.addActionListener((ActionEvent event) -> {
			m.retour();
		});
		
		creermodele.addActionListener((ActionEvent event) -> {
			//Il faut créer une classe creationJoueur
			System.out.println("La classe qui permet de réaliser cette action n'existe pas");
		});
		
		importermodele.addActionListener((ActionEvent event) -> {
			//Il faut créer une classe importationModele
			System.out.println("La classe qui permet de réaliser cette action n'existe pas");
		});
		
		supprimermodele.addActionListener((ActionEvent event) -> {
			//Il faut créer une classe supprimerModele
			System.out.println("La classe qui permet de réaliser cette action n'existe pas");
		});
		
		//####################################################

		
	}
	
	public void paintComponent(Graphics g) { //on redéfinit la méthode paintComponent de JPanel pour mettre une image en fond
		g.drawImage(image, 0, 0, null); //permet de dessiner une image sur le fond de notre JPanel
	}

}

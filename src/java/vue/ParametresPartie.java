package vue;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Color;

// import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;

public class ParametresPartie extends JPanel {

	/**
	 * Create the panel.
	 */
	private BufferedImage image;
	private Font minecraft;
	// private Color c;

	public ParametresPartie(Menu m) {
		
		image = m.getImage();
		minecraft = m.getMinecraft();
		
		setSize(new Dimension(1000, 800));
		setLayout(new BorderLayout(0, 0));
		
		JPanel topBox = new JPanel(new BorderLayout());
		topBox.setOpaque(false);
		
		JButton retour = new JButton("retour");
		retour.setHorizontalAlignment(SwingConstants.LEFT);

		
		JLabel titre = new JLabel("Choisir les joueurs participant a la quete :");
		titre.setOpaque(false);
		titre.setHorizontalAlignment(SwingConstants.CENTER);
		titre.setFont(minecraft);
		
		topBox.add(retour, BorderLayout.WEST);
		//topBox.add(titre, BorderLayout.CENTER);		
		
		
		JPanel centerBox = new JPanel();
		centerBox.setOpaque(false); // à enlever peut être
		// centerBox.setLayout(new BorderLayout());
		
		JPanel centerTopBox = new JPanel();
		centerTopBox.setOpaque(false);
		centerTopBox.add(titre, BorderLayout.CENTER);
		
		
		JPanel centerCenterBox = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
		centerCenterBox.setBorder(new EmptyBorder(200, 0, 0, 0));
		centerCenterBox.setOpaque(false);
		
		//JPanel g = new JPanel(new GridLayout(1, 2, 0, 0));
		JCheckBox j1 = new JCheckBox("Georges");
		j1.setFont(minecraft);
		j1.setOpaque(false);
		
		JCheckBox j2 = new JCheckBox("Ronen");
		j2.setFont(minecraft);
		j2.setOpaque(false);

		JCheckBox j3 = new JCheckBox("Antoinette");
		j3.setFont(minecraft);
		j3.setOpaque(false);

		JCheckBox j4 = new JCheckBox("Alec");
		j4.setFont(minecraft);
		j4.setOpaque(false);

		JCheckBox j5 = new JCheckBox("Lea");
		j5.setFont(minecraft);
		j5.setOpaque(false);

		centerCenterBox.add(j1);
		centerCenterBox.add(j2);
		centerCenterBox.add(j3);
		centerCenterBox.add(j4);
		centerCenterBox.add(j5);

		
		centerBox.add(centerTopBox, BorderLayout.NORTH);
		centerBox.add(centerCenterBox, BorderLayout.CENTER);
		
		
		JPanel southBox = new JPanel(new FlowLayout());
		southBox.setOpaque(false);
		setBorder(new EmptyBorder(0, 0, 100, 0));
		
		JButton lancer = new JButton("Lancer"); //mettre lancer dans un panel pour bien regler la taille
		lancer.setFont(minecraft);
		lancer.setForeground(Color.black);
		lancer.setBorder(null);
		lancer.setOpaque(false);
		lancer.setContentAreaFilled(false);

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
		
		lancer.addActionListener((ActionEvent event) -> {
			m.lancerjeu();
		});
		
		//####################################################
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, null);
	}

}

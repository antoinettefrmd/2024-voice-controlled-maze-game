package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Scores extends JPanel {
	
	private static final long serialVersionUID = -5453585265679531011L;
	private BufferedImage image;
	private Font minecraft;
	private Font minecraftButton;
	
	private File sauvegarde;
	
	
	private static JLabel num1 = new JLabel("1. ");
	private static JLabel num2 = new JLabel("2. ");
	private static JLabel num3 = new JLabel("3. ");
	private static JLabel num4 = new JLabel("4. ");
	private static JLabel num5 = new JLabel("5. ");
	private static JLabel num6 = new JLabel("6. ");
	private static JLabel num7 = new JLabel("7. ");
	private static JLabel num8 = new JLabel("8. ");
	private static JLabel num9 = new JLabel("9. ");
	private static JLabel num10 = new JLabel("10. ");
	 
	 
	private static LinkedList<JLabel> top10 = new LinkedList<JLabel>();
	private static LinkedList<Long> tempstop10 = new LinkedList<Long>();

	
	/**
	 * Create the panel.
	 */
	public Scores(Menu m) {
		
		
		
		image = m.getImage();
		minecraft = m.getMinecraft();
		minecraftButton = m.getMinecraftButton();
		
		
		sauvegarde = new File("./src/ressources/topscores/scores.txt");
		
		setLayout(new BorderLayout());
		
		JPanel topBox = new JPanel(new BorderLayout());
		topBox.setOpaque(false);
		topBox.setBorder(new EmptyBorder(new Insets(0, 0, 20, 0)));

		
		JButton retour = new JButton("retour");
		retour.setFont(minecraftButton);
		retour.setBackground(new Color(250, 250, 175));
		retour.setFocusPainted(false);
		retour.setHorizontalAlignment(SwingConstants.LEFT);
		
		topBox.add(retour, BorderLayout.WEST);
		
		
		JPanel centerBox = new JPanel(new BorderLayout());
		centerBox.setOpaque(false);
		centerBox.setBackground(Color.black);
		
		
		JLabel titre = new JLabel("Meilleurs Scores");
		titre.setHorizontalAlignment(SwingConstants.CENTER);
		titre.setOpaque(false);
		titre.setFont(minecraft);
		titre.setForeground(new Color(250, 250, 175));
		//titre.setFont(new Font("Arial Black", ABORT, 20));
		
		
		//JPanel scoreBox = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
		JPanel scoreBox = new JPanel(new GridLayout(10, 1, 0, 10));
		scoreBox.setBorder(new EmptyBorder(new Insets(50, 0, 50, 0)));
		scoreBox.setOpaque(false);
		
		top10.add(num1);
		num1.setHorizontalAlignment(SwingConstants.CENTER);
		num1.setFont(minecraft);
		num1.setForeground(new Color(250, 250, 175));
		
		top10.add(num2);
		num2.setHorizontalAlignment(SwingConstants.CENTER);
		num2.setFont(minecraft);
		num2.setForeground(new Color(250, 250, 175));

		top10.add(num3);
		num3.setHorizontalAlignment(SwingConstants.CENTER);
		num3.setFont(minecraft);
		num3.setForeground(new Color(250, 250, 175));
		
		top10.add(num4);
		num4.setHorizontalAlignment(SwingConstants.CENTER);
		num4.setFont(minecraft);
		num4.setForeground(new Color(250, 250, 175));
		
		top10.add(num5);
		num5.setHorizontalAlignment(SwingConstants.CENTER);
		num5.setFont(minecraft);
		num5.setForeground(new Color(250, 250, 175));
		
		top10.add(num6);
		num6.setHorizontalAlignment(SwingConstants.CENTER);
		num6.setFont(minecraft);
		num6.setForeground(new Color(250, 250, 175));
		
		top10.add(num7);
		num7.setHorizontalAlignment(SwingConstants.CENTER);
		num7.setFont(minecraft);
		num7.setForeground(new Color(250, 250, 175));
		
		top10.add(num8);
		num8.setHorizontalAlignment(SwingConstants.CENTER);
		num8.setFont(minecraft);
		num8.setForeground(new Color(250, 250, 175));
		
		top10.add(num9);
		num9.setHorizontalAlignment(SwingConstants.CENTER);
		num9.setFont(minecraft);
		num9.setForeground(new Color(250, 250, 175));
		
		top10.add(num10);
		num10.setHorizontalAlignment(SwingConstants.CENTER);
		num10.setFont(minecraft);
		num10.setForeground(new Color(250, 250, 175));
		
		scoreBox.add(num1);
		scoreBox.add(num2);
		scoreBox.add(num3);
		scoreBox.add(num4);
		scoreBox.add(num5);
		scoreBox.add(num6);
		scoreBox.add(num7);
		scoreBox.add(num8);
		scoreBox.add(num9);
		scoreBox.add(num10);

		tempstop10.add((long)-1);
		tempstop10.add((long)-1);
		tempstop10.add((long)-1);
		tempstop10.add((long)-1);
		tempstop10.add((long)-1);
		tempstop10.add((long)-1);
		tempstop10.add((long)-1);
		tempstop10.add((long)-1);
		tempstop10.add((long)-1);
		tempstop10.add((long)-1);

		
		centerBox.add(titre, BorderLayout.NORTH);
		centerBox.add(scoreBox, BorderLayout.CENTER);
		
		
		this.add(topBox, BorderLayout.NORTH);
		this.add(centerBox, BorderLayout.CENTER);
		
		//Controller
		//####################################################
		
		retour.addActionListener((ActionEvent event) -> {
			m.retour();
		});
		
		//####################################################

		majScores();
	}
	
	public boolean sauvegardeScores(long nouveauScore) {
		int i = 0;
		int n = tempstop10.size();
		while(i < n) {
			if(nouveauScore > tempstop10.get(i)) {
				tempstop10.add(i, nouveauScore);
				tempstop10.removeLast();
				try {
					FileWriter fw = new FileWriter(sauvegarde);
					for(int j = 0; j < n; j++) {
						fw.write(tempstop10.get(j)+"\n");
					}
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return true;
			}
			i++;
		}
		return false;
	}
	
	public void majScores() {
		try {
			FileReader fr = new FileReader(sauvegarde);
			BufferedReader br = new BufferedReader(fr);
			
			String ligne;
			int i = 0;
			while((ligne = br.readLine()) != null){
				if(Integer.parseInt(ligne) < 0) {
					top10.get(i).setText((i + 1) + ". ");
				} else {
					top10.get(i).setText((i + 1) + ". " + Integer.parseInt(ligne));
				}
				tempstop10.set(i,(long)Integer.parseInt(ligne));
				i++;
			}
			fr.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g) { //on redéfinit la méthode paintComponent de JPanel pour mettre une image en fond
		g.drawImage(image, 0, 0, null); //permet de dessiner une image sur le fond de notre JPanel
	}

}

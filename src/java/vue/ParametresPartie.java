package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import java.awt.GridLayout;
import java.io.File;
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
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class ParametresPartie extends JPanel {
	
	private BufferedImage image;
	private Font minecraft;
	private Font mincecarftcheckbox;
	private Font DayDream;

	public ParametresPartie(Menu m) {
		
// <<<<<<< HEAD
		// image = m.getImage();
		// minecraft = m.getMinecraft();
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
		
		JPanel topBox = new JPanel(new BorderLayout());
		topBox.setOpaque(false);
		
		JButton retour = new JButton("retour");
		retour.setFont(minecraft);
		retour.setBackground(new Color(176, 69, 25));
		retour.setFocusPainted(false);
		retour.setHorizontalAlignment(SwingConstants.LEFT);

		
		JLabel titre = new JLabel("Choisir les joueurs participant a la quete :");
		// titre.setOpaque(false);
		// titre.setFont(minecraft);
		titre.setBorder(new EmptyBorder(50, 0, 0, 0));
		titre.setHorizontalAlignment(SwingConstants.CENTER);
		titre.setFont(DayDream);
		
		//titre.setFont(new Font("Arial Black", ABORT, 20));
		
		topBox.add(retour, BorderLayout.WEST);
		//topBox.add(titre, BorderLayout.CENTER);		
		
		
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
		
		JCheckBox j1 = new JCheckBox("Georges");

		j1.setFont(mincecarftcheckbox);
		j1.setOpaque(false);
		j1.setFocusPainted(false);
		
		JCheckBox j2 = new JCheckBox("Ronen");
		j2.setFont(mincecarftcheckbox);
		j2.setOpaque(false);
		j2.setFocusPainted(false);

		JCheckBox j3 = new JCheckBox("Antoinette");
		j3.setFont(mincecarftcheckbox);
		j3.setOpaque(false);
		j3.setFocusPainted(false);

		JCheckBox j4 = new JCheckBox("Alec");
		j4.setFont(mincecarftcheckbox);
		j4.setOpaque(false);
		j4.setFocusPainted(false);

		JCheckBox j5 = new JCheckBox("Lea");
		j5.setFont(mincecarftcheckbox);
		j5.setOpaque(false);
		j5.setFocusPainted(false);
		
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
		

		//JButton lancer = new JButton("Lancer");
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
		
		lancer.addActionListener((ActionEvent event) -> {
			if(j1.isSelected() || j2.isSelected() || j3.isSelected() || j4.isSelected() || j5.isSelected()) {
				m.lancerjeu(j1.isSelected(), j2.isSelected(), j3.isSelected(), j4.isSelected(), j5.isSelected());
			}
		});
		
		//####################################################
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, null);
	}

	public class GenericRoundedButton extends JButton {
		
		private Color color;
		private Color colorOver;
		private Color colorClick;
		
		private Graphics2D g2d;
		
		private boolean over;

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
		   g2d.setColor(new Color(0x47,0xB0,0x19,0xFF));
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

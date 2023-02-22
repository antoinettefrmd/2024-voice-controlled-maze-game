package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Credit extends JPanel {
	
	private BufferedImage image;
	private Font minecraft;
	private Color c;

	/**
	 * Create the panel.
	 */
	public Credit(Menu m) {
		
		c = new Color(14, 189, 6);

		image = m.getImage();
		minecraft = m.getMinecraft();
		
		setLayout(new BorderLayout());
		
		JPanel topBox = new JPanel(new FlowLayout(FlowLayout.LEADING));
		topBox.setOpaque(false);

		
		JButton retour = new JButton("retour");
		
		topBox.add(retour);
		
		
		JPanel gridlayout = new JPanel(new GridLayout(5, 0, 0, 40));
		gridlayout.setAlignmentX(CENTER_ALIGNMENT);
		gridlayout.setOpaque(false);

		
		JLabel georges = new JLabel("Georges Lecomte");
		georges.setHorizontalAlignment(SwingConstants.CENTER);
		georges.setFont(minecraft);
		georges.setForeground(c);
		
		JLabel ronen = new JLabel("Ronen Shay");
		ronen.setHorizontalAlignment(SwingConstants.CENTER);
		ronen.setFont(minecraft);
		ronen.setForeground(c);

		JLabel lea = new JLabel("Lea Benoiton");
		lea.setHorizontalAlignment(SwingConstants.CENTER);
		lea.setFont(minecraft);
		lea.setForeground(c);
		
		JLabel alec = new JLabel("Alec Martinez");
		alec.setHorizontalAlignment(SwingConstants.CENTER);
		alec.setFont(minecraft);
		alec.setForeground(c);
		
		JLabel antoinette = new JLabel("Antoinette Fourmond");
		antoinette.setHorizontalAlignment(SwingConstants.CENTER);
		antoinette.setFont(minecraft);
		antoinette.setForeground(c);

		gridlayout.add(georges);
		gridlayout.add(ronen);
		gridlayout.add(lea);
		gridlayout.add(alec);
		gridlayout.add(antoinette);
		
		add(topBox, BorderLayout.NORTH);
		add(gridlayout, BorderLayout.CENTER);
		
		//Controller
		//####################################################
		
		retour.addActionListener((ActionEvent event) -> {
			m.retour();
		});
		
		//####################################################

	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, null);
	}
	

}

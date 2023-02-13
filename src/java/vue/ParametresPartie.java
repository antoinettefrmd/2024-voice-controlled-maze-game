package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ParametresPartie extends JPanel {

	/**
	 * Create the panel.
	 */
	public ParametresPartie() {
		setSize(new Dimension(1200, 800));
		setLayout(new BorderLayout(0, 0));
		
		JPanel southBox = new JPanel();
		add(southBox, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Lancer");
		southBox.add(btnNewButton);
		
		JPanel centerBox = new JPanel();
		add(centerBox, BorderLayout.CENTER);
		centerBox.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel gp = new JPanel(new GridLayout(1, 2, 5, 0));
		gp.setBorder(new EmptyBorder(100, 0, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Taille du Labyrinth");
		
		JComboBox<Integer> choixtailleplateau = new JComboBox<>();
		gp.add(lblNewLabel);
		gp.add(choixtailleplateau);
		
		centerBox.add(gp);
		
		
		
		
		
	}
}

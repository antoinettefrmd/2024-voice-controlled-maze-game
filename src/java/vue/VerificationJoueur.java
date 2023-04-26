package vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class VerificationJoueur extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	private JButton okButton;
	private JButton cancelButton;
	private boolean verif;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VerificationJoueur dialog = new VerificationJoueur();
			//dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VerificationJoueur() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		
		this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
		
			public void windowClosing(WindowEvent arg0) {
				//on fait les instruction pour remettre la couleurs verte
				System.out.println("Coucou");
				dispose();
				System.exit(0);
			}
			
		});
		
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel titre = new JLabel("Vérifier votre identité");
			titre.setHorizontalAlignment(SwingConstants.CENTER);
			getContentPane().add(titre, BorderLayout.CENTER);
			
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Vérifier");
				okButton.setActionCommand("Vérifier");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		//Controller
		//####################################################
		
		okButton.addActionListener((ActionEvent event) -> {
			//instruction de vérification (Léa)
		});
		
		cancelButton.addActionListener((ActionEvent event) -> {
			verif = false;
			this.dispose();
		});
		
		//####################################################

	}
	
	public boolean getVerif() {
		return verif;
	}
	
}

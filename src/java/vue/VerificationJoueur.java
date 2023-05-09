package vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controlleur.ExecuteBash;

public class VerificationJoueur extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	private JButton verifButton;
	private JButton cancelButton;
	private boolean verif;
	private Scanner sc;

	/**
	 * Launch the application.
	public static void main(String[] args) {
		try {
			VerificationJoueur dialog = new VerificationJoueur();
			//dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/

	/**
	 * Create the dialog.
	 */
	public VerificationJoueur(String n, double c) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		
		try {
			sc = new Scanner(new File("src/ressources/modele_voix/CFG/resultat.txt"));
		}
		catch(Exception e) {
			System.out.println("Erreur lors d’ouverture fichier:");
			e.printStackTrace();
			System.exit(1);
		}
		
		this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
		
			public void windowClosing(WindowEvent arg0) {
				//on fait les instruction pour remettre la couleurs verte
				verif = false;
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
				verifButton = new JButton("Vérifier");
				verifButton.setActionCommand("Vérifier");
				buttonPane.add(verifButton);
				getRootPane().setDefaultButton(verifButton);
			}
			{
				cancelButton = new JButton("Annuler");
				cancelButton.setActionCommand("Annuler");
				buttonPane.add(cancelButton);
			}
		}
		
		//Controller
		//####################################################
		
		verifButton.addActionListener((ActionEvent event) -> {
			//ExecuteBash recordVoix = new ExecuteBash("./src/java/controlleur/recordVoix.sh");
			ExecuteBash.cmd_system("./src/java/controlleur/recordVoix.sh");
			//ExecuteBash computeTest = new ExecuteBash("src/java.controlleur/computeTest"+n+".sh");
			ExecuteBash.cmd_system("./src/java/controlleur/computeTest"+n+".sh");
			String res = "";
			while(sc.hasNext()) {
				res = sc.next();
			}
			System.out.println(res);
			verif = (Double.parseDouble(res)>c);
		});
		
		cancelButton.addActionListener((ActionEvent event) -> {
			verif = false;
			this.dispose();
		});
		

		this.setVisible(true);

		//####################################################

	}
	
	public boolean getVerif() {
		return verif;
	}
	
}

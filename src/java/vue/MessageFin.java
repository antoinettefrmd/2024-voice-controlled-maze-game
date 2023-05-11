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
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class MessageFin extends JDialog {

	private static final long serialVersionUID = 136046461121052494L;
	private final JPanel contentPanel = new JPanel();
	private JButton okButton;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			MessageFin dialog = new MessageFin("17min / 2s");
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public MessageFin(Menu m, long temps) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel titreFin = new JLabel("Bravo ! vous avez terminer le jeu en " + temps);
			titreFin.setHorizontalAlignment(SwingConstants.CENTER);
			getContentPane().add(titreFin, BorderLayout.CENTER);
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
		
			public void windowClosing(WindowEvent arg0) {
				dispose();
				m.quitterJeu();
				System.exit(0);
			}
			
		});
		
		setLocationRelativeTo(null);
 		
		//Controller
		//####################################################
		
		okButton.addActionListener((ActionEvent event) -> {
			this.dispose();
			m.quitterJeu();
		});
		
		//####################################################

	}
	
}

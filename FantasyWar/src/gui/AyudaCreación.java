package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JEditorPane;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AyudaCreación extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public AyudaCreación() {
		setResizable(false);
		setModal(true);
		setTitle("Ayuda para la creaci\u00F3n de monstruos.");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JEditorPane dtrpnCreacinDeMonstruos = new JEditorPane();
			dtrpnCreacinDeMonstruos.setBackground(SystemColor.control);
			dtrpnCreacinDeMonstruos.setText("Creaci\u00F3n de monstruos.\r\n\r\nConsejos:\r\n-Debes de tomar buenas decisiones, ya que algunas razas beneficiar a algunas clases y/o hacerles el juego m\u00E1s dificil.\r\n\r\n-Para saber que ofrecen las razas y clases, visita la ayuda Clases y Razas.\r\n\r\nBuenas combinaciones:\r\n-NoMuerto - Mago\r\n-Orco -Guerrero");
			dtrpnCreacinDeMonstruos.setBounds(0, 0, 434, 229);
			contentPanel.add(dtrpnCreacinDeMonstruos);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}

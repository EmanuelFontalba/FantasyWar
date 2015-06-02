package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ComoEmpezar extends JDialog {

	private final JPanel contentPanel = new JPanel();


	/**
	 * Create the dialog.
	 */
	public ComoEmpezar() {
		setTitle("Empezando...");
		setBounds(100, 100, 450, 336);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JTextPane txtpnnoSabesComo = new JTextPane();
			txtpnnoSabesComo.setEditable(false);
			txtpnnoSabesComo.setOpaque(false);
			txtpnnoSabesComo.setContentType("text/html");
			txtpnnoSabesComo.setText("<html>\r\n<h2>\u00BFNo sabes como empezar a jugar?</h2>\r\n<ul>\r\n<li> Si no tienes ya un jugador creado, debes ir a Archivo/Nuevo Jugador. <br />\r\nJusto despu\u00E9s debes de crearte un monstruo en Colecci\u00F3n/A\u00F1adir monstruo. <br />\r\nY ahora por ultimo debes de buscar tu monstruo creado de cualquiera de las opciones de b\u00FAsqueda y haz click en Seleccionar. <br />\r\nY ya puedes luchar!!</li>\r\n<li>Si ya tienes un jugador creado y con tu partida guardada, click en Archivo/Cargar partida y a jugar!! </li></ul></html>");
			txtpnnoSabesComo.setBounds(0, 0, 434, 255);
			contentPanel.add(txtpnnoSabesComo);
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

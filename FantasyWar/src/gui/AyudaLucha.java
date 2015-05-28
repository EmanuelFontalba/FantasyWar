package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JEditorPane;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AyudaLucha extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public AyudaLucha() {
		setResizable(false);
		setModal(true);
		setTitle("Ayuda para la lucha");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JEditorPane dtrpnHolaHolahola = new JEditorPane();
		dtrpnHolaHolahola.setBackground(SystemColor.control);
		dtrpnHolaHolahola.setEditable(false);
		dtrpnHolaHolahola.setText("La lucha es simple.\r\nSelecciona el ataque  que vas a usar y tras aceptar usar\u00E1s tu turno, y a la vez  el monstruo enemigo usar\u00E1 un ataque contra t\u00ED.\r\n\r\nCada clase va a tener una especie de \"energia\", que se va acabando en funci\u00F3n del coste que tenga cada ataque. Cuando esta \"energia\", no podr\u00E1s realizar los ataques hasta que no se regenere al menos el coste.\r\n\r\nEl primero en morir pierde y se lleva un poco de experiencia.\r\nEl ganador se llevar\u00E1 mucha m\u00E1s experiencia.");
		dtrpnHolaHolahola.setBounds(0, 0, 434, 229);
		contentPanel.add(dtrpnHolaHolahola);
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

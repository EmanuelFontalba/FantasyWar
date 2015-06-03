package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import mazmorras.Mazmorras;
import comunicacionConGui.Comunicacion;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MazmorrasGUI extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JComboBox<Object> comboBoxMazmorras;

	/**
	 * Create the dialog.
	 */
	public MazmorrasGUI() {
		
		setTitle("Mazmorras disponibles.");
		setResizable(false);
		setBounds(100, 100, 298, 192);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblMazmorras = new JLabel("Mazmorras: ");
		lblMazmorras.setBounds(10, 11, 87, 14);
		contentPanel.add(lblMazmorras);
		
		comboBoxMazmorras = new JComboBox<Object>();
		comboBoxMazmorras.setBounds(116, 8, 143, 20);
		contentPanel.add(comboBoxMazmorras);
		comboBoxMazmorras.setModel(new DefaultComboBoxModel<Object>(Mazmorras.getArray(Comunicacion.getMonstruoSeleccionado().getNivel())));
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Comenzar");
				if(Comunicacion.getMonstruoSeleccionado().getNivel() <10)
					okButton.setEnabled(false);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						inicializaMazmorra();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	protected void inicializaMazmorra() {
		Comunicacion.setMazmorra((Mazmorras)comboBoxMazmorras.getSelectedItem());
		NavegandoPorMazmorra mazmorra = new NavegandoPorMazmorra();
		mazmorra.setVisible(true);
		setVisible(false);
	}
}

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

public class MazmorrasGUI extends JDialog {

	private final JPanel contentPanel = new JPanel();

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
		
		JComboBox comboBoxMazmorras = new JComboBox();
		comboBoxMazmorras.setBounds(116, 8, 143, 20);
		contentPanel.add(comboBoxMazmorras);
		comboBoxMazmorras.setModel(new DefaultComboBoxModel(Mazmorras.getArray(Comunicacion.getMonstruoSeleccionado().getNivel())));
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Comenzar");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}

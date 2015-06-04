package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import clasesPrincipales.Coleccion;
import clasesPrincipales.Razas;
import comunicacionConGui.Comunicacion;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BuscarPorRaza extends JDialog {
	private static final long serialVersionUID = -5832505224296810321L;
	private static final Component contentPane = null;
	private final JPanel contentPanel = new JPanel();
	JComboBox<Object> comboBox = new JComboBox<Object>();

	/**
	 * Create the dialog.
	 */
	public BuscarPorRaza() {
		setResizable(false);
		setModal(true);
		setTitle("Buscar por raza");
		setBounds(100, 100, 201, 135);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblRazas = new JLabel("Razas: ");
			lblRazas.setBounds(10, 28, 56, 14);
			contentPanel.add(lblRazas);
		}
		
		
		comboBox.setBounds(76, 25, 99, 20);
		contentPanel.add(comboBox);
		comboBox.setModel(new DefaultComboBoxModel<Object>(Razas.values()));
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						muestraMonstruosPorRaza();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	/**
	 * Crea una nueva colección para mostrar los monstruos de una raza específica.
	 */
	private void muestraMonstruosPorRaza() {
		Comunicacion.setMonstruosEncontrados(new Coleccion());
		Comunicacion.getMonstruosEncontrados().setColeccion(Comunicacion.getJugador().getColeccionMonstruos().getMostruosRaza(((Razas)comboBox.getSelectedItem())));
		if(Comunicacion.getMonstruosEncontrados().size() == 0)
			JOptionPane.showMessageDialog(contentPane, "No tienes monstruos en tu colección.", "ERROR", JOptionPane.ERROR_MESSAGE);
		else{
			Comunicacion.setMonstruoEncontrado(Comunicacion.getMonstruosEncontrados().get(0));
			MostrarPorRaza mostrar = new MostrarPorRaza();
			mostrar.setVisible(true);
			setVisible(false);
		}
	}
}

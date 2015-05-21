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
import comunicacionConGui.Comunicacion;
import enumeraciones.Razas;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BuscarPorRaza extends JDialog {

	private final JPanel contentPanel = new JPanel();
	JComboBox comboBox = new JComboBox();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BuscarPorRaza dialog = new BuscarPorRaza();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BuscarPorRaza() {
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
		comboBox.setModel(new DefaultComboBoxModel(Razas.values()));
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					private Component contentPane;

					public void actionPerformed(ActionEvent e) {
						Comunicacion.monstruosEncontrados = new Coleccion();
						Comunicacion.monstruosEncontrados.setColeccion(Comunicacion.jugador.getColeccionMonstruos().getMostruosRaza(((Razas)comboBox.getSelectedItem())));
						if(Comunicacion.monstruosEncontrados.size() == 0)
							JOptionPane.showMessageDialog(contentPane, "No tienes monstruos en tu colecci�n.", "ERROR", JOptionPane.ERROR_MESSAGE);
						else{
							Comunicacion.monstruoEncontrado = Comunicacion.monstruosEncontrados.get(0);
							MostrarPorRaza mostrar = new MostrarPorRaza();
							mostrar.setVisible(true);
							setVisible(false);
						}
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
}
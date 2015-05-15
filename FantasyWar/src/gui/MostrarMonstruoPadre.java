package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import comunicacionConGui.Comunicacion;
import excepciones.MonstruoNoExisteException;
import excepciones.NombreInvalidoException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MostrarMonstruoPadre extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldNombre;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MostrarMonstruoPadre dialog = new MostrarMonstruoPadre();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MostrarMonstruoPadre() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNombre = new JLabel("Nombre: ");
			lblNombre.setBounds(10, 11, 76, 14);
			contentPanel.add(lblNombre);
		}
		{
			textFieldNombre = new JTextField();
			textFieldNombre.setBounds(104, 8, 86, 20);
			contentPanel.add(textFieldNombre);
			textFieldNombre.setColumns(10);
		}
		{
			JLabel lblRaza = new JLabel("Raza:");
			lblRaza.setBounds(10, 61, 46, 14);
			contentPanel.add(lblRaza);
		}
		{
			textField_1 = new JTextField();
			textField_1.setEditable(false);
			textField_1.setBounds(104, 58, 86, 20);
			contentPanel.add(textField_1);
			textField_1.setColumns(10);
		}
		{
			JLabel lblClase = new JLabel("Clase:");
			lblClase.setBounds(10, 114, 46, 14);
			contentPanel.add(lblClase);
		}
		{
			textField_2 = new JTextField();
			textField_2.setEditable(false);
			textField_2.setBounds(104, 111, 86, 20);
			contentPanel.add(textField_2);
			textField_2.setColumns(10);
		}
		{
			JButton btnSeleccionar = new JButton("Seleccionar");
			btnSeleccionar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
						Comunicacion.monstruoSeleccionado = Comunicacion.jugador.getColeccionMonstruos().get(0);
						Principal.textFieldNombrePJ.setText(Comunicacion.monstruoSeleccionado.getNombre());
						setVisible(false);
				}
			});
			btnSeleccionar.setBounds(290, 79, 89, 23);
			contentPanel.add(btnSeleccionar);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
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

package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;

import clasesPrincipales.Jugador;
import comunicacionConGui.Comunicacion;
import excepciones.NombreInvalidoException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NuevoJugador extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NuevoJugador dialog = new NuevoJugador();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NuevoJugador() {
		setAlwaysOnTop(true);
		setTitle("Nuevo jugador");
		setBounds(100, 100, 216, 160);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 43, 174, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblIntroduceTuNombre = new JLabel("Introduce tu nombre: ");
		lblIntroduceTuNombre.setBounds(10, 18, 174, 14);
		contentPanel.add(lblIntroduceTuNombre);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							Comunicacion.jugador = new Jugador(textField.getText());
							Comunicacion.guardado=false;
							Comunicacion.modificado=false;
							Comunicacion.archivoElegido=null;
							Component parentComponent = null;
							JOptionPane.showMessageDialog(parentComponent, "Partida creada con �xito");
							setVisible(false);
						} catch (NombreInvalidoException e1) {
							//CAPTURAR EXCEPCION
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

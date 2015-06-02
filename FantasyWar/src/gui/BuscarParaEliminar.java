package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import clasesPrincipales.Monstruo;
import clasesPrincipales.MonstruoNoExisteException;
import clasesPrincipales.NombreInvalidoException;
import comunicacionConGui.Comunicacion;

public class BuscarParaEliminar extends JDialog {
	private static final long serialVersionUID = -6217286020518915175L;
	private static final Component contentPane = null;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

	/**
	 * Create the dialog.
	 */
	public BuscarParaEliminar() {
		setResizable(false);
		setModal(true);
		textField = new JTextField();
		textField.setBounds(87, 8, 86, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if(!Monstruo.nombreValido(textField.getText()))
					textField.setForeground(java.awt.Color.RED);
				else
					textField.setForeground(java.awt.Color.BLACK);
			}
		});
		setTitle("Buscar");
		setBounds(100, 100, 204, 109);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre: ");
		lblNombre.setBounds(10, 11, 75, 14);
		contentPanel.add(lblNombre);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
				}
			});
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					muestraParaEliminar();
				}
			});
		}
	}
	
	/**
	 * Muestra un monstruo para eliminarlo.
	 */
	private void muestraParaEliminar() {
		if( Comunicacion.getJugador().getColeccionMonstruos().size() == 0)
			JOptionPane.showMessageDialog( contentPane, "Aun no hay ningun monstruo en tu colección", "ERROR", JOptionPane.ERROR_MESSAGE);
		else{
			
			try {
				Comunicacion.setMonstruoEncontrado(Comunicacion.getJugador().getColeccionMonstruos().get(textField.getText()));
				EliminarMonstruo eliminar = new EliminarMonstruo();
				eliminar.setVisible(true);
				setVisible(false);
			} catch (MonstruoNoExisteException
					| NombreInvalidoException e) {
				JOptionPane.showMessageDialog(contentPane, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
		setVisible(false);
	}

}

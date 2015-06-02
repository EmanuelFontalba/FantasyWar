package gui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JOptionPane;

import clasesPrincipales.Clase;
import clasesPrincipales.Guerrero;
import clasesPrincipales.Mago;
import clasesPrincipales.Monstruo;
import clasesPrincipales.MonstruoYaExisteException;
import clasesPrincipales.NombreInvalidoException;
import clasesPrincipales.Razas;
import clasesPrincipales.Sacerdote;
import comunicacionConGui.Comunicacion;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AnnadirMonstruo extends VentanaPadreMonstruo {

	private static final long serialVersionUID = -8526593579645562050L;
	private Component contentPane;
	private Component parentComponent;
	/**
	 * Create the dialog.
	 */
	public AnnadirMonstruo() {
		setAlwaysOnTop(true);
		setTitle("A\u00F1adir monstruo");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				annadirMonstruo();
			}
		});
		textFieldNombre.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if (textFieldNombre.getText() == null)
					return;
				if (nombreValido(textFieldNombre.getText()))
					textFieldNombre.setForeground(Color.BLACK);
				else
					textFieldNombre.setForeground(Color.RED);
			}
		});
		buttonNext.setVisible(false);
		buttonPrevious.setVisible(false);
	}
	
	/**
	 * Añade un monstruo a la colección.
	 */
	private void annadirMonstruo() {
		Monstruo monstruo = null;
		try {
			if (textFieldNombre.getText() == null)
				throw new NombreInvalidoException(
						"Debes introducir el nombre del monstruo");
			
//			switch(comboBoxClase.getSelectedItem()){
//			case Clase.MAGO:
//				monstruo = new Mago(textFieldNombre.getText(),
//						(Razas) comboBoxRaza.getSelectedItem());
//				break;
//			}
			
			if (comboBoxClase.getSelectedItem() == Clase.MAGO)
				monstruo = new Mago(textFieldNombre.getText(),
						(Razas) comboBoxRaza.getSelectedItem());
				
			if (comboBoxClase.getSelectedItem() == Clase.SACERDOTE)
				monstruo = new Sacerdote(textFieldNombre.getText(),
								(Razas) comboBoxRaza.getSelectedItem());
			if (comboBoxClase.getSelectedItem() == Clase.GUERRERO)
				monstruo = new Guerrero(textFieldNombre.getText(),
								(Razas) comboBoxRaza.getSelectedItem());
			
			Comunicacion.getJugador().getColeccionMonstruos().add(monstruo);
			setVisible(false);
			JOptionPane.showMessageDialog(parentComponent,
					"Monstruo añadido con éxito");
		} catch (NombreInvalidoException | MonstruoYaExisteException e) {
			JOptionPane.showMessageDialog(contentPane, e.getMessage(),
					"ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

}

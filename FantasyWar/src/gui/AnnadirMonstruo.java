package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import comunicacionConGui.Comunicacion;
import enumeraciones.Clase;
import enumeraciones.Razas;
import excepciones.ClaseNoSelecionadaException;
import excepciones.MonstruoYaExisteException;
import excepciones.NombreInvalidoException;
import Monstruos.Guerrero;
import Monstruos.Mago;
import Monstruos.Monstruo;
import Monstruos.Sacerdote;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AnnadirMonstruo extends VentanaPadreMonstruo {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AnnadirMonstruo dialog = new AnnadirMonstruo();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
			private Component contentPane;
			private Component parentComponent;

			public void actionPerformed(ActionEvent arg0){
				try {
					if(textFieldNombre.getText() == null)
						throw new NombreInvalidoException("Debes introducir el nombre del monstruo");
					if(comboBoxClase.getSelectedItem() == Clase.MAGO)
						Comunicacion.jugador.getColeccionMonstruos().add
							(new Mago(textFieldNombre.getText(), (Razas) comboBoxRaza.getSelectedItem()));
					if(comboBoxClase.getSelectedItem() == Clase.SACERDOTE)
						Comunicacion.jugador.getColeccionMonstruos().add
							(new Sacerdote(textFieldNombre.getText(), (Razas) comboBoxRaza.getSelectedItem()));
					if(comboBoxClase.getSelectedItem() == Clase.GUERRERO)
						Comunicacion.jugador.getColeccionMonstruos().add
							(new Guerrero(textFieldNombre.getText(), (Razas) comboBoxRaza.getSelectedItem()));
					setVisible(false);
					JOptionPane.showMessageDialog(parentComponent, "Monstruo añadido con éxito");
				} catch (NombreInvalidoException | MonstruoYaExisteException e) {
					JOptionPane.showMessageDialog(contentPane, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		textFieldNombre.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if(textFieldNombre.getText() == null)
					return;
				if(nombreValido(textFieldNombre.getText()))
					textFieldNombre.setForeground(Color.BLACK);
				else
					textFieldNombre.setForeground(Color.RED);
			}
		});
		buttonNext.setVisible(false);
		buttonPrevious.setVisible(false);
	}

}

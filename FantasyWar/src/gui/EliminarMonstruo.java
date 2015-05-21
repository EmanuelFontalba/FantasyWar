package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class EliminarMonstruo extends MostrarMonstruoPadre {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EliminarMonstruo dialog = new EliminarMonstruo();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EliminarMonstruo() {
		setTitle("Eliminar monstruo");
		btnEliminar.setVisible(true);
		buttonNext.setVisible(false);
		buttonPrevious.setVisible(false);
		btnSeleccionar.setVisible(false);
		okButton.setText("Cancelar");
	}

}

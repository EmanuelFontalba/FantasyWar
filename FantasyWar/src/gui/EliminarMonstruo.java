package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class EliminarMonstruo extends MostrarMonstruoPadre {

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
		setModal(true);
	}

}

package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MostrarPorRaza extends MostrarMonstruoPadre {


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MostrarPorRaza dialog = new MostrarPorRaza();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MostrarPorRaza() {
		super();
		setTitle("Monstruo");
	}

}
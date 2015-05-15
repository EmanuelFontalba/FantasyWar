package gui;


import java.io.File;

import javax.swing.AbstractButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import comunicacionConGui.Comunicacion;


public class Abrir extends JDialog {


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Abrir dialog = new Abrir();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Abrir() {
		setTitle("Abrir");
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("OBJ", "obj");
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(filtro);
		
		int respuesta = fc.showOpenDialog(this);

		if (respuesta == JFileChooser.APPROVE_OPTION)
		{
			Comunicacion.archivoElegido = fc.getSelectedFile();
		}
		if (respuesta == JFileChooser.CANCEL_OPTION){
			setVisible(false);
		}
	}

}

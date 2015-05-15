package gui;


import java.io.File;

import javax.swing.AbstractButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import comunicacionConGui.Comunicacion;


public class GuardarComo extends JDialog {
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GuardarComo dialog = new GuardarComo();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GuardarComo() {
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("OBJ", "obj");
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(filtro);
		int respuesta = fc.showSaveDialog(this);

		if (respuesta == JFileChooser.APPROVE_OPTION)
		{
			Comunicacion.archivoElegido = fc.getSelectedFile();
		}
		if (respuesta == JFileChooser.CANCEL_OPTION){
			setVisible(false);
			fc.setVisible(false);
		}

	}

}

package gui;


import javax.swing.JDialog;

@SuppressWarnings("serial")
public class MostrarPorNombre extends MostrarMonstruoPadre {

	/**
	 * Create the dialog.
	 */
	public MostrarPorNombre() {
		super();
		setTitle("Monstruo");
		buttonNext.setVisible(false);
		buttonPrevious.setVisible(false);
		setModal(true);
	}

}

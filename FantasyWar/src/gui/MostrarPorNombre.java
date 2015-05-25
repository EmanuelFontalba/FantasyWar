package gui;


import javax.swing.JDialog;

@SuppressWarnings("serial")
public class MostrarPorNombre extends MostrarMonstruoPadre {

	
	public static void main(String[] args) {
		try {
			MostrarPorNombre dialog = new MostrarPorNombre();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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

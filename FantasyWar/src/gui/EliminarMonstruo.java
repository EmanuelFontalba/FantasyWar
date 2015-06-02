package gui;

public class EliminarMonstruo extends MostrarMonstruoPadre {
	private static final long serialVersionUID = -3473431151845447878L;

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

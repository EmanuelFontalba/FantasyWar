package gui;

public class MostrarPorNombre extends MostrarMonstruoPadre {
	private static final long serialVersionUID = 3437220708263435361L;

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

package clasesPrincipales;

/**
 * Cuando el nombre de un jugador o monstruo no es v�lido.
 * @author Emanuel Galv�n Fontalba
 *
 */
public class NombreInvalidoException extends Exception {
	private static final long serialVersionUID = 7735334833591147324L;

	public NombreInvalidoException(String string) {
		super(string);
	}

}

package clasesPrincipales;

/**
 * Cuando a�ades un monstruo a la colecci�n del jugador 
 * que ya existe.
 * @author Emanuel Galv�n Fontalba
 *
 */
public class MonstruoYaExisteException extends Exception {
	private static final long serialVersionUID = -6851567365248941293L;

	public MonstruoYaExisteException(String string) {
		super(string);
	}

}

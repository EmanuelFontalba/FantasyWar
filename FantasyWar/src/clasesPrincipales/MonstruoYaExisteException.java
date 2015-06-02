package clasesPrincipales;

/**
 * Cuando añades un monstruo a la colección del jugador 
 * que ya existe.
 * @author Emanuel Galván Fontalba
 *
 */
public class MonstruoYaExisteException extends Exception {
	private static final long serialVersionUID = -6851567365248941293L;

	public MonstruoYaExisteException(String string) {
		super(string);
	}

}

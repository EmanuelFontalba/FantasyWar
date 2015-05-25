package clasesPrincipales;

/**
 * Cuando añades un monstruo a la colección del jugador 
 * que ya existe.
 * @author Emanuel Galván Fontalba
 *
 */
public class MonstruoYaExisteException extends Exception {

	public MonstruoYaExisteException(String string) {
		super(string);
	}

}

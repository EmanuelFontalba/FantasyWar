package clasesPrincipales;

/**
 * Cuando un monstruo no existe en la colección del jugador.
 * @author Emanuel Galván Fontalba
 *
 */
public class MonstruoNoExisteException extends Exception {

	public MonstruoNoExisteException(String string) {
		super(string);
	}

}

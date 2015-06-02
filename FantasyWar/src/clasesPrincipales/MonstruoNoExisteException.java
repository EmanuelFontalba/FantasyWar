package clasesPrincipales;

/**
 * Cuando un monstruo no existe en la colección del jugador.
 * @author Emanuel Galván Fontalba
 *
 */
public class MonstruoNoExisteException extends Exception {
	private static final long serialVersionUID = 3020377109637898352L;

	public MonstruoNoExisteException(String string) {
		super(string);
	}

}

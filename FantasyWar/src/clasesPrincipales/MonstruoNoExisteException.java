package clasesPrincipales;

/**
 * Cuando un monstruo no existe en la colecci�n del jugador.
 * @author Emanuel Galv�n Fontalba
 *
 */
public class MonstruoNoExisteException extends Exception {
	private static final long serialVersionUID = 3020377109637898352L;

	public MonstruoNoExisteException(String string) {
		super(string);
	}

}

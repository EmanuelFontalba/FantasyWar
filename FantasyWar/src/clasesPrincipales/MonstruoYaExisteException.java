package clasesPrincipales;

/**
 * Cuando a�ades un monstruo a la colecci�n del jugador 
 * que ya existe.
 * @author Emanuel Galv�n Fontalba
 *
 */
public class MonstruoYaExisteException extends Exception {

	public MonstruoYaExisteException(String string) {
		super(string);
	}

}

package clasesPrincipales;

/**
 * Cuando el mago no niene man� suficiente para hacer un ataque.
 * @author Emanuel Galv�n Fontalba
 *
 */
public class ManaInsuficienteException extends Exception {
	private static final long serialVersionUID = 32967072995359954L;

	public ManaInsuficienteException(String string) {
		super(string);
	}

}

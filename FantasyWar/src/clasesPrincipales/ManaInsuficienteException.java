package clasesPrincipales;

/**
 * Cuando el mago no niene maná suficiente para hacer un ataque.
 * @author Emanuel Galván Fontalba
 *
 */
public class ManaInsuficienteException extends Exception {
	private static final long serialVersionUID = 32967072995359954L;

	public ManaInsuficienteException(String string) {
		super(string);
	}

}

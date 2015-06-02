package clasesPrincipales;

/**
 * Cuando el guerrero no tiene ira suficiente para hacer un ataque.
 * @author Emanuel Galván Fontalba
 *
 */
public class IraInsuficienteException extends Exception {
	private static final long serialVersionUID = -7575093473294046767L;

	public IraInsuficienteException(String string) {
		super(string);
	}

}

package clasesPrincipales;

/**
 * Cuando el sacerdote no tiene f� suficiente para hacer un ataque.
 * @author Emanuel Galv�n Fontalba
 *
 */
public class FeInsuficienteException extends Exception {
	private static final long serialVersionUID = 7082003679602798224L;

	public FeInsuficienteException(String string) {
		super(string);
	}

}

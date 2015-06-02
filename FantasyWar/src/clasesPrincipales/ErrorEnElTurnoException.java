package clasesPrincipales;

/**
 * Si ocurre algun error en el turno.
 * @author Emanuel Galván Fontalba
 *
 */
public class ErrorEnElTurnoException extends Exception {
	private static final long serialVersionUID = -224257629985850171L;

	public ErrorEnElTurnoException(String string) {
		super(string);
	}

}

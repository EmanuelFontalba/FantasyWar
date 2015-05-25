package clasesPrincipales;

/**
 * Ataques para sacerdotes.
 * @author Emanuel Galv�n Fontalba.
 *
 */
public interface Saneable {
	/**
	 * Ataque energia divina.
	 * @return Da�o verdadero.
	 * @throws FeInsuficienteException
	 * 			No hay suficiente f� para realizar el ataque.
	 */
	int energiaDivina() throws FeInsuficienteException;
	/**
	 * Ataque curaci�n ancestral. No tiene da�o.
	 * @throws FeInsuficienteException
	 * 			No hay suficiente f� para realizar el ataque.
	 */
	void curacionAncestral() throws FeInsuficienteException;
	/**
	 * Ataque escudo divino. No tiene da�o.
	 * @throws FeInsuficienteException
	 * 			No hay suficiente f� para realizar el ataque.
	 */
	void escudoDivino() throws FeInsuficienteException;
	/**
	 * Ataque curaci�n absoluta. No tiene da�o.
	 * @throws FeInsuficienteException
	 * 			No hay suficiente f� para realizar el ataque.
	 */
	void curacionAbsoluta() throws FeInsuficienteException;
	/**
	 * Regeneraci�n de fe por turno.
	 */
	void regeneracionFe();
}

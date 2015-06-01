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
	 * @return 
	 * @throws FeInsuficienteException
	 * 			No hay suficiente f� para realizar el ataque.
	 */
	int curacionAncestral() throws FeInsuficienteException;
	/**
	 * Ataque escudo divino. No tiene da�o.
	 * @return 
	 * @throws FeInsuficienteException
	 * 			No hay suficiente f� para realizar el ataque.
	 */
	int escudoDivino() throws FeInsuficienteException;
	/**
	 * Ataque curaci�n absoluta. No tiene da�o.
	 * @return 
	 * @throws FeInsuficienteException
	 * 			No hay suficiente f� para realizar el ataque.
	 */
	int curacionAbsoluta() throws FeInsuficienteException;
	/**
	 * Regeneraci�n de fe por turno.
	 */
	void regeneracionFe();
}

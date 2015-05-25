package clasesPrincipales;

/**
 * Ataques para sacerdotes.
 * @author Emanuel Galván Fontalba.
 *
 */
public interface Saneable {
	/**
	 * Ataque energia divina.
	 * @return Daño verdadero.
	 * @throws FeInsuficienteException
	 * 			No hay suficiente fé para realizar el ataque.
	 */
	int energiaDivina() throws FeInsuficienteException;
	/**
	 * Ataque curación ancestral. No tiene daño.
	 * @throws FeInsuficienteException
	 * 			No hay suficiente fé para realizar el ataque.
	 */
	void curacionAncestral() throws FeInsuficienteException;
	/**
	 * Ataque escudo divino. No tiene daño.
	 * @throws FeInsuficienteException
	 * 			No hay suficiente fé para realizar el ataque.
	 */
	void escudoDivino() throws FeInsuficienteException;
	/**
	 * Ataque curación absoluta. No tiene daño.
	 * @throws FeInsuficienteException
	 * 			No hay suficiente fé para realizar el ataque.
	 */
	void curacionAbsoluta() throws FeInsuficienteException;
	/**
	 * Regeneración de fe por turno.
	 */
	void regeneracionFe();
}

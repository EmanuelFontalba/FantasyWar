package clasesPrincipales;


/**
 * Interfaz que se le aplicará a los monstruos guerreros.
 * @author Emanuel Galván Fontalba.
 *
 */
public interface Luchable {
	/**
	 * Ataque rajar
	 * @return Daño verdadero.
	 * @throws IraInsuficienteException
	 * 			No hay ira suficiente para hacer el ataque.
	 */
	int rajar() throws IraInsuficienteException; 
	/**
	 * Ataque ejecutar
	 * @return Daño verdadero.
	 * @throws IraInsuficienteException
	 * 			No hay ira suficiente para hacer el ataque.
	 */
	int ejecutar() throws IraInsuficienteException;
	/**
	 * Ataque protección. No tiene daño
	 * @throws IraInsuficienteException
	 * 			No hay ira suficiente para hacer el ataque.
	 */
	void proteccion() throws IraInsuficienteException; 
	/**
	 * Ataque filotormenta.
	 * @return Daño verdadero.
	 * @throws IraInsuficienteException
	 * 			No hay ira suficiente para hacer el ataque.
	 */
	int filoTormenta() throws IraInsuficienteException;
	/**
	 * Regeneración de ira por turno.
	 */
	void regeneracionDeIra();
}

package clasesPrincipales;


/**
 * Interfaz que se le aplicar� a los monstruos guerreros.
 * @author Emanuel Galv�n Fontalba.
 *
 */
public interface Luchable {
	/**
	 * Ataque rajar
	 * @return Da�o verdadero.
	 * @throws IraInsuficienteException
	 * 			No hay ira suficiente para hacer el ataque.
	 */
	int rajar() throws IraInsuficienteException; 
	/**
	 * Ataque ejecutar
	 * @return Da�o verdadero.
	 * @throws IraInsuficienteException
	 * 			No hay ira suficiente para hacer el ataque.
	 */
	int ejecutar() throws IraInsuficienteException;
	/**
	 * Ataque protecci�n. No tiene da�o
	 * @throws IraInsuficienteException
	 * 			No hay ira suficiente para hacer el ataque.
	 */
	void proteccion() throws IraInsuficienteException; 
	/**
	 * Ataque filotormenta.
	 * @return Da�o verdadero.
	 * @throws IraInsuficienteException
	 * 			No hay ira suficiente para hacer el ataque.
	 */
	int filoTormenta() throws IraInsuficienteException;
	/**
	 * Regeneraci�n de ira por turno.
	 */
	void regeneracionDeIra();
}

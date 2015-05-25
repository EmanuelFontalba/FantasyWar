package clasesPrincipales;

/**
 * Interfaz para monstruos magos.
 * @author Emanuel Galv�n Fontalba
 *
 */
public interface Hechizable {
	/**
	 * Ataque bola de fuego
	 * @return Da�o verdadero.
	 * @throws ManaInsuficienteException
	 * 			No se tiene man� suficiente para realizar el ataque.
	 */
	int bolaDeFuego() throws ManaInsuficienteException;
	
	/**
	 * Ataque escudo de escarcha. No hace da�o.
	 * @throws ManaInsuficienteException
	 * 			No se tiene el man� suficiente para realizar el ataque.
	 */
	void escudoDeEscarcha() throws ManaInsuficienteException;
	
	/**
	 * Ataque meditaci�n. No hace da�o.
	 * @throws ManaInsuficienteException
	 * 			No se tiene el man� suficiente para realizar el ataque.
	 */
	void meditacion() throws ManaInsuficienteException;
	
	/**
	 * Ataque lluvia de meteoros.
	 * @return	Da�o verdadero.
	 * @throws ManaInsuficienteException
	 * 			No se tiene el man� suficiente para realizar el ataque.
	 */
	int lluviaDeMeteoros() throws ManaInsuficienteException;
	
	/**
	 * Regeneraci�n de man� por turno.
	 */
	void regeneracionMana();
}

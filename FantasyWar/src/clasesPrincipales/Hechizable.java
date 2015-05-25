package clasesPrincipales;

/**
 * Interfaz para monstruos magos.
 * @author Emanuel Galván Fontalba
 *
 */
public interface Hechizable {
	/**
	 * Ataque bola de fuego
	 * @return Daño verdadero.
	 * @throws ManaInsuficienteException
	 * 			No se tiene maná suficiente para realizar el ataque.
	 */
	int bolaDeFuego() throws ManaInsuficienteException;
	
	/**
	 * Ataque escudo de escarcha. No hace daño.
	 * @throws ManaInsuficienteException
	 * 			No se tiene el maná suficiente para realizar el ataque.
	 */
	void escudoDeEscarcha() throws ManaInsuficienteException;
	
	/**
	 * Ataque meditación. No hace daño.
	 * @throws ManaInsuficienteException
	 * 			No se tiene el maná suficiente para realizar el ataque.
	 */
	void meditacion() throws ManaInsuficienteException;
	
	/**
	 * Ataque lluvia de meteoros.
	 * @return	Daño verdadero.
	 * @throws ManaInsuficienteException
	 * 			No se tiene el maná suficiente para realizar el ataque.
	 */
	int lluviaDeMeteoros() throws ManaInsuficienteException;
	
	/**
	 * Regeneración de maná por turno.
	 */
	void regeneracionMana();
}

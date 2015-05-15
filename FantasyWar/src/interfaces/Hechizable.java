package interfaces;

import excepciones.ManaInsuficienteException;

/**
 * Interfaz para monstruos magos.
 * @author Emanuel Galván Fontalba
 *
 */
public interface Hechizable {
	int bolaDeFuego() throws ManaInsuficienteException;
	void escudoDeEscarcha() throws ManaInsuficienteException;
	void meditacion() throws ManaInsuficienteException;
	int lluviaDeMeteoros() throws ManaInsuficienteException;
	void regeneracionMana();
}

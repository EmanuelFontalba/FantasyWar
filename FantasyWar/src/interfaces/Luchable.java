package interfaces;

import excepciones.IraInsuficienteException;

/**
 * Interfaz que se le aplicará a los monstruos guerreros.
 * @author Emanuel Galván Fontalba.
 *
 */
public interface Luchable {
	int rajar() throws IraInsuficienteException; 
	int ejecutar() throws IraInsuficienteException;
	void proteccion() throws IraInsuficienteException; 
	int filoTormenta() throws IraInsuficienteException;
	void regeneracionDeIra();
}

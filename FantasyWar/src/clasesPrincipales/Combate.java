package clasesPrincipales;

import Monstruos.Monstruo;
import enumeraciones.TipoDeDanno;
import excepciones.ErrorEnElTurnoException;
/**
 * Clase est�tica para manejar el combate
 * @author Emanuel Galv�n Fontalba
 *
 */
public class Combate {
	
	
	/**
	 * Establece la experiencia correspondiente y aumenta la salud del monstruo a su salud m�xima.
	 * 
	 * @param monstruo
	 * 			Monstruo a aplicarle el aumento de experiencia y la salud.
	 * @param jugador
	 * 			Jugador al que aplicarle el aumento de experiencia.
	 */
	void finDelCombate(Monstruo monstruo, Jugador jugador){
		if(monstruo.isMuerto()){
			monstruo.aumentarExp(100);
			jugador.aumentarExp(100);
		}
		else{
			monstruo.aumentarExp(1000);
			jugador.aumentarExp(1000);
		}
		monstruo.aumentarSaludActual(monstruo.getSaludMaxima());
	}
}

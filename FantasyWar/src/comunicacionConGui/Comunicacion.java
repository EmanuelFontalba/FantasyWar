package comunicacionConGui;

import java.io.File;

import mazmorras.GulDan;
import mazmorras.Mazmorra;
import mazmorras.Mazmorras;
import mazmorras.MinasDeKobolt;
import mazmorras.PrisionDeElwin;
import mazmorras.ZulFarrack;
import clasesPrincipales.Coleccion;
import clasesPrincipales.Jugador;
import clasesPrincipales.Monstruo;

public class Comunicacion {
	private static Jugador jugador;
	private static File archivoElegido;	
	private static boolean guardado=false;
	private static boolean modificado = false;
	private static Monstruo monstruoSeleccionado;
	private static Monstruo monstruoEncontrado;
	private static Coleccion monstruosEncontrados;
	private static Mazmorra mazmorra;
	private static Monstruo monstruoMazmorra;
	
	public static Jugador getJugador() {
		return jugador;
	}
	public static void setJugador(Jugador jugador) {
		Comunicacion.jugador = jugador;
	}
	public static File getArchivoElegido() {
		return archivoElegido;
	}
	public static void setArchivoElegido(File archivoElegido) {
		Comunicacion.archivoElegido = archivoElegido;
	}
	public static boolean isGuardado() {
		return guardado;
	}
	public static void setGuardado(boolean guardado) {
		Comunicacion.guardado = guardado;
	}
	public static boolean isModificado() {
		return modificado;
	}
	public static void setModificado(boolean modificado) {
		Comunicacion.modificado = modificado;
	}
	public static Monstruo getMonstruoSeleccionado() {
		return monstruoSeleccionado;
	}
	public static void setMonstruoSeleccionado(Monstruo monstruoSeleccionado) {
		Comunicacion.monstruoSeleccionado = monstruoSeleccionado;
	}
	public static Monstruo getMonstruoEncontrado() {
		return monstruoEncontrado;
	}
	public static void setMonstruoEncontrado(Monstruo monstruoEncontrado) {
		Comunicacion.monstruoEncontrado = monstruoEncontrado;
	}
	public static Coleccion getMonstruosEncontrados() {
		return monstruosEncontrados;
	}
	public static void setMonstruosEncontrados(Coleccion monstruosEncontrados) {
		Comunicacion.monstruosEncontrados = monstruosEncontrados;
	}
	public static Mazmorra getMazmorra() {
		return mazmorra;
	}
	
	/**
	 * Establece la mazmorra correspondiente.
	 * @param mazmorraEn
	 * 			Enumeracion mazmorra.
	 */
	public static void setMazmorra(Mazmorras mazmorraEn) {
		switch(mazmorraEn){
		case GUL_DAN:
			mazmorra = new GulDan();
			break;
		case PRISION_DE_ELWIN:
			mazmorra = new PrisionDeElwin();
			break;
		case MINAS_DE_KOBOLT:
			mazmorra = new MinasDeKobolt();
			break;
		case ZUL_FARACK:
			mazmorra = new ZulFarrack();
			break;
		default:
			break;
		}
	}
	public static Monstruo getMonstruoMazmorra() {
		return monstruoMazmorra;
	}
	public static void setMonstruoMazmorra(Monstruo monstruoMazmorra) {
		Comunicacion.monstruoMazmorra = monstruoMazmorra;
	}
	
}

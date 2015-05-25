package comunicacionConGui;

import java.io.File;

import clasesPrincipales.Coleccion;
import clasesPrincipales.Jugador;
import clasesPrincipales.Monstruo;

public class Comunicacion {
	public static Jugador jugador;
	public static File archivoElegido;	
	public static boolean guardado=false;
	public static boolean modificado = false;
	public static Monstruo monstruoSeleccionado;
	public static Monstruo monstruoEncontrado;
	public static Coleccion monstruosEncontrados;
}

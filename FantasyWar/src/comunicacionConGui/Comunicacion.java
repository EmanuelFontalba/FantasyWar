package comunicacionConGui;

import java.io.File;
import Monstruos.Monstruo;
import clasesPrincipales.Coleccion;
import clasesPrincipales.Jugador;

public class Comunicacion {
	public static Jugador jugador;
	public static File archivoElegido;	
	public static boolean guardado=false;
	public static boolean modificado = false;
	public static Monstruo monstruoSeleccionado;
	public static Monstruo monstruoEncontrado;
	public static Coleccion monstruosEncontrados;
}

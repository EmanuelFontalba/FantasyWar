package comunicacionConGui;

import java.io.File;

import Monstruos.Guerrero;
import Monstruos.Mago;
import Monstruos.Monstruo;
import Monstruos.Sacerdote;
import clasesPrincipales.Jugador;

public class Comunicacion {
	public static Jugador jugador;
	public static File archivoElegido;	
	public static boolean guardado=false;
	public static boolean modificado = false;
	public static Monstruo monstruoSeleccionado;
	public static Mago mago;
	public static Guerrero guerrero;
	public static Sacerdote sacerdote;
}

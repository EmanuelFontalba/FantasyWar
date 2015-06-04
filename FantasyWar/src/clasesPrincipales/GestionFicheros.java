package clasesPrincipales;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Gestión de los ficheros
 * @author Emanuel Galván Fontalba
 *
 */
public class GestionFicheros {
	/**
	 * Guarda un objeto en un archivo .obj
	 * @param object
	 * 			Objeto a guardar.
	 * @param archivo
	 * 			Archivo donde guardar.
	 * @throws IOException
	 */
	public static void guardar(Object object, File archivo)throws IOException {
		try (ObjectOutputStream escrituraObjeto = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(extensionValida(archivo))));) {
			escrituraObjeto.writeObject(object);
		}
	}
	
	/**
	 * Abre un archivo .obj
	 * @param archivo
	 * 			Archivo a abrir.
	 * @return	Lectura del archivo.
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static Object abrir(File archivo) throws ClassNotFoundException, IOException {
		try (ObjectInputStream lecturaObjeto = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream(extensionValida(archivo))))) {
			return lecturaObjeto.readObject();
		}
	}
	
	/**
	 * Comprueba si un archivo acaba en .obj, si no, se le añade la extensión.
	 * @param archivo
	 * 			Archivo a comprobar.
	 * @return Archivo ya comparado y/o modificado.
	 */
	private static File extensionValida(File archivo){
		if(archivo.getPath().endsWith(".fw"))
			return archivo;
		else
			return new File(archivo+".fw");
	}
}

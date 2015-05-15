package clasesPrincipales;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GestionFicheros {
	public static void guardar(Object object, File archivo)throws IOException {
		try (ObjectOutputStream escrituraObjeto = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(extensionValida(archivo))));) {
			escrituraObjeto.writeObject(object);
		}
	}

	public static Object abrir(File archivo) throws ClassNotFoundException, IOException {
		try (ObjectInputStream lecturaObjeto = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream(extensionValida(archivo))))) {
			return lecturaObjeto.readObject();
		}
	}
	
	private static File extensionValida(File archivo){
		if(archivo.getPath().endsWith(".obj"))
			return archivo;
		else
			return new File(archivo+".obj");
	}
}

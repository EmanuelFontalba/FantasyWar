package clasesPrincipales;

import java.io.Serializable;
import java.util.ArrayList;

import Monstruos.Mago;
import Monstruos.Monstruo;
import enumeraciones.Razas;
import excepciones.MonstruoNoExisteException;
import excepciones.MonstruoYaExisteException;
import excepciones.NombreInvalidoException;
/**
 * Envoltorio de un ArrayList
 * Colección de monstruo donde se hace la gestión de la colección.
 * @author Emanuel Galván Fontalba
 *
 */
@SuppressWarnings("serial")
public class Coleccion implements Serializable {
	/**
	 * Colección de monstruos
	 */
	private ArrayList<Monstruo> coleccion = new ArrayList<Monstruo>();
	
	public ArrayList<Monstruo> getColeccion() {
		return coleccion;
	}

	public void setColeccion(ArrayList<Monstruo> coleccion) {
		this.coleccion = coleccion;
	}

	/**
	 * Añade un monstruo a nuestra colección
	 * @param monstruo
	 * 				Monstruo a añadir
	 * @throws NombreInvalidoException 
	 * 				El nombre del monstruo no empieza por mayusculas.
	 * @throws MonstruoNoExisteException 
	 * 
	 * @throws MonstruoYaExisteException 
	 */
	public void add(Monstruo monstruo) throws NombreInvalidoException, MonstruoYaExisteException{
		if(!monstruoExiste(monstruo.getNombre()))
			coleccion.add(monstruo);
		else
			throw new MonstruoYaExisteException("El nombre ya existe");
	}
	
	/**
	 * Elimina un monstruo de nuestra colección.
	 * @param nombre
	 * 				Nombre del monstruo a eliminar
	 * @throws NombreInvalidoException 
	 * 				El nombre no comieza por mayuscula.
	 * @throws MonstruoNoExisteException 
	 * 				El monstruo no está en la colección.
	 */
	public void remove(String nombre) throws NombreInvalidoException, MonstruoNoExisteException{
		Mago monstruo = new Mago(nombre);
		if(coleccion.remove(monstruo))
			return;
		throw new MonstruoNoExisteException("El monstruo no existe");
	}
	
	/**
	 * @return Cantidad de monstruos.
	 */
	public int size(){
		return coleccion.size();
	}
	
	/**
	 * Comprueba si el nombre de un monstruo ya está en la colección
	 * @param nombre
	 * 			Nombre a comprobar
	 * @return True si ya está en la coleccion, false en lo contrario.
	 * @throws NombreInvalidoException
	 * 				El nombre no empieza por mayuscula.
	 */
	boolean monstruoExiste(String nombre) throws NombreInvalidoException{
		Monstruo m1 = new Mago(nombre);
		for(Monstruo monstruo : coleccion)
			if(monstruo.getNombre().equals(m1.getNombre()))
				return true;
		return false;
	}
	/**
	 * Busca un monstruo en nuestra coleccion por el nombre
	 * @param nombre
	 * 			Nombre del monstruo a buscar
	 * @return Monstruo buscado.
	 * @throws MonstruoNoExisteException
	 * 				El monstruo no se encuentra en la colección.
	 * @throws NombreInvalidoException
	 * 				El nombre introducido no comienza por mayuscula.
	 */
	public Monstruo get(String nombre) throws MonstruoNoExisteException, NombreInvalidoException{
		Mago monstruo = new Mago(nombre);
		int index = coleccion.indexOf(monstruo);
		if (index != -1) {
			return coleccion.get(index);
		}
		throw new MonstruoNoExisteException("El monstruo no existe");
	}
	
	/**
	 * Busca un monstruo en nuestra colección por la posición en el ArrayList.
	 * @param index
	 * 			Posición a buscar.
	 * @return El monstruo encontrado.
	 */
	public Monstruo get(int index){
		return coleccion.get(index);
	}
	
	/**
	 * Busca todos los monstruos de una misma raza
	 * @param raza
	 * 			Raza a buscar
	 * @return Los monstruos encontrados.
	 */
	public ArrayList<Monstruo> getMostruosRaza(Razas raza) {
		ArrayList<Monstruo> monstruosDeRaza = new ArrayList<Monstruo>();
		for (Monstruo monstruo : coleccion) {
			if(monstruo.getRaza()== raza)
				monstruosDeRaza.add(monstruo);
		}
		return monstruosDeRaza;
	}
}

package enumeraciones;

import java.util.ArrayList;

import Monstruos.Guerrero;
import Monstruos.Mago;
import Monstruos.Sacerdote;

public enum Ataques {
	//Sacerdote
	/**
	 * Ataque energia divina. Clase sacerdote.
	 */
	ENERGIA_DIVINA (Sacerdote.class),
	/**
	 * Ataque curaci�n ancestral. Clase sacerdote.
	 */
	CURACION_ANCESTRAL(Sacerdote.class),
	/**
	 * Ataque escudo divino. Clase sacerdote.
	 */
	ESCUDO_DIVINO(Sacerdote.class),
	/**
	 * Ataque curaci�n absoluta. Clase sacerdote.
	 */
	CURACION_ABSOLUTA(Sacerdote.class),
	
	//Mago
	/**
	 * Ataque bola de fuego. Clase mago.
	 */
	BOLA_DE_FUEGO(Mago.class),
	/**
	 * Ataque escudo de escarcha. Clase mago.
	 */
	ESCUDO_DE_ESCARCHA(Mago.class),
	/**
	 * Ataque meditaci�n. Clase mago.
	 */
	MEDITACION(Mago.class),
	/**
	 * Ataque lluvia de meteoros. Clase mago.
	 */
	LLUVIA_DE_METEOROS(Mago.class),
	
	//Guerrero
	/**
	 * Ataque rajar. Clase guerrero.
	 */
	RAJAR(Guerrero.class),
	/**
	 * Ataque ejecutar. Clase guerrero.
	 */
	EJECUTAR(Guerrero.class),
	/**
	 * Ataque protecci�n. Clase guerrero.
	 */
	PROTECCION(Guerrero.class),
	/**
	 * Ataque filotormenta. Clase guerrero.
	 */
	FILOTORMENTA(Guerrero.class);
	
	private Class<?> clase;

	Ataques(Class<?> clase){
		this.clase=clase;
	}
	
	/**
	 * Devuelve un array de los ataques de una clase.
	 * 
	 * @param clase
	 * 			Clase para devolver los ataques
	 * @return Array de ataques.
	 */
	public static Object[] getArray(Class<?> clase){
		ArrayList<Ataques> arrayList = new ArrayList<Ataques>();
		for (Ataques ataque : values()) {
			if(ataque.clase==clase) arrayList.add(ataque);
		}
		return arrayList.toArray();
		
	}
}

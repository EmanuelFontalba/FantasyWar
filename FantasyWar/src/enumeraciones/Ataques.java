package enumeraciones;

import java.util.ArrayList;

import Monstruos.Guerrero;
import Monstruos.Mago;
import Monstruos.Sacerdote;

public enum Ataques {
	//Sacerdote
	ENERGIA_DIVINA (Sacerdote.class),
	CURACION_ANCESTRAL(Sacerdote.class),
	ESCUDO_DIVINO(Sacerdote.class),
	CURACION_ABSOLUTA(Sacerdote.class),
	//Mago
	BOLA_DE_FUEGO(Mago.class),
	ESCUDO_DE_ESCARCHA(Mago.class),
	MEDITACION(Mago.class),
	LLUVIA_DE_METEOROS(Mago.class),
	//Guerrero
	RAJAR(Guerrero.class),
	EJECUTAR(Guerrero.class),
	PROTECCION(Guerrero.class),
	FILOTORMENTA(Guerrero.class);
	
	private Class<?> clase;

	Ataques(Class<?> clase){
		this.clase=clase;
	}
	
	public static Object[] getArray(Class<?> clase){
		ArrayList<Ataques> arrayList = new ArrayList<Ataques>();
		for (Ataques ataque : values()) {
			if(ataque.clase==clase) arrayList.add(ataque);
		}
		return arrayList.toArray();
		
	}
}

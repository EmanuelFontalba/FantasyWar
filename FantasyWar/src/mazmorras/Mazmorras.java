package mazmorras;

import java.util.ArrayList;

import clasesPrincipales.Ataques;

public enum Mazmorras {
	GUL_DAN(10),
	PRISION_DE_ELWIN(10),
	MINAS_DE_KOBOLT(11),
	ZUL_FARACK(15);
	
	private int nivel;
	
	Mazmorras(int nivel){
		setNivel(nivel);
	}
	
	/**
	 * Devuelve un array de las mazmorras disponibles para un nivel.
	 * 
	 * @param nivel
	 * 			Nivel para devolver las mazmorras
	 * @return Array de mazmorras.
	 */
	public static Object[] getArray(int nivel){
		ArrayList<Mazmorras> arrayList = new ArrayList<Mazmorras>();
		for (Mazmorras mazmorra : values()) {
			if(mazmorra.nivel<=nivel) arrayList.add(mazmorra);
		}
		return arrayList.toArray();
		
	}

	public int getNivel() {
		return nivel;
	}

	private void setNivel(int nivel) {
		this.nivel = nivel;
	}
}


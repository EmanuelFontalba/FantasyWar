package mazmorras;

import java.util.ArrayList;
import clasesPrincipales.Monstruo;

public abstract class Mazmorra {
	private String nombre;
	private ArrayList<Monstruo> mazmorra;

	Mazmorra(){
		setMazmorra(new ArrayList<Monstruo>());
	}
	
	abstract void inicializarMazmorra();
	

	
	//-------------------------------SETTERS & GETTERS------------------------

	public ArrayList<Monstruo> getMazmorra() {
		return mazmorra;
	}

	private void setMazmorra(ArrayList<Monstruo> mazmorra) {
		this.mazmorra = mazmorra;
	}

	public String getNombre() {
		return nombre;
	}

	protected void setNombre(String nombre) {
		this.nombre = nombre;
	}
}

package mazmorras;

import java.util.ArrayList;

import clasesPrincipales.Guerrero;
import clasesPrincipales.Mago;
import clasesPrincipales.Monstruo;
import clasesPrincipales.NombreInvalidoException;
import clasesPrincipales.Razas;
import clasesPrincipales.Sacerdote;

public abstract class Mazmorra {
	private String nombre;
	private ArrayList<Monstruo> mazmorra;

	Mazmorra(){
		setMazmorra(new ArrayList<Monstruo>());
	}
	
	/**
	 * Inicializa la mazmorra con unos monstruos por defecto y niveles correspondientes.
	 * 
	 * @param nombre
	 * 			Nombre de la mazmorra.
	 * @param e1
	 * 			Primer esbirro.
	 * @param e2
	 * 			Segundo esbirro.
	 * @param e3
	 * 			Tercer esbirro.
	 * @param e4
	 * 			Cuarto esbirro.
	 * @param b
	 * 			Jefe de mazmorra.
	 * @param nvl1
	 * 			Nivel del primer esbirro.
	 * @param nvl2
	 * 			Nivel del segundo esbirro.
	 * @param nvl3
	 * 			Nivel del tercer esbirro.
	 * @param nvl4
	 * 			Nivel del cuarto esbirro.
	 * @param nvl5
	 * 			Nivel del jefe de mazmorra.
	 */
	protected void inicializarMazmorra(String nombre, Monstruo e1, Monstruo e2, Monstruo e3, Monstruo e4, 
			Monstruo b,int nvl1,int nvl2,int nvl3,int nvl4,int nvl5){
		//Establecemos el nombre
		setNombre(nombre);
		//Definimos los monstruos
		Monstruo esbirro1 = e1;
		Monstruo esbirro2 = e2;
		Monstruo esbirro3 = e3;
		Monstruo esbirro4 = e4;
		Monstruo boss = b;
		//Establecemos el nivel de los esbirros
		
		for(int i=0; i<nvl1; i++)
			esbirro1.aumentarNivel();
		for(int i=0; i<nvl2; i++)
			esbirro2.aumentarNivel();
		for(int i=0; i<nvl3; i++)
			esbirro3.aumentarNivel();
		for(int i=0; i<nvl4; i++)
			esbirro4.aumentarNivel();
		for(int i=0; i<nvl5; i++)
			boss.aumentarNivel();
		esbirro1.reestablecerse();
		esbirro2.reestablecerse();
		esbirro3.reestablecerse();
		esbirro4.reestablecerse();
		boss.reestablecerse();

		getMazmorra().add(esbirro1);
		getMazmorra().add(esbirro2);
		getMazmorra().add(esbirro3);
		getMazmorra().add(esbirro4);
		getMazmorra().add(boss);

	}
	

	
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

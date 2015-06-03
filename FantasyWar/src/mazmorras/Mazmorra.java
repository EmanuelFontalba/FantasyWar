package mazmorras;

import java.util.ArrayList;
import clasesPrincipales.Monstruo;

public abstract class Mazmorra {
	private String nombre;
	private ArrayList<Monstruo> mazmorra;
	private Monstruo esbirro1;
	private Monstruo esbirro2;
	private Monstruo esbirro3;
	private Monstruo esbirro4;
	private Monstruo boss;

	Mazmorra(){
		setMazmorra(new ArrayList<Monstruo>());
	}
	
	abstract void inicializarMazmorra();
	
	protected void annadeMonstruos(){
		mazmorra.add(esbirro1);
		mazmorra.add(esbirro2);
		mazmorra.add(esbirro3);
		mazmorra.add(esbirro4);
		mazmorra.add(boss);
	}
	
	//-------------------------------SETTERS & GETTERS------------------------
	protected Monstruo getEsbirro1() {
		return esbirro1;
	}

	protected void setEsbirro1(Monstruo esbirro1) {
		this.esbirro1 = esbirro1;
	}

	protected Monstruo getEsbirro2() {
		return esbirro2;
	}

	protected void setEsbirro2(Monstruo esbirro2) {
		this.esbirro2 = esbirro2;
	}

	protected Monstruo getEsbirro3() {
		return esbirro3;
	}

	protected void setEsbirro3(Monstruo esbirro3) {
		this.esbirro3 = esbirro3;
	}

	protected Monstruo getEsbirro4() {
		return esbirro4;
	}

	protected void setEsbirro4(Monstruo esbirro4) {
		this.esbirro4 = esbirro4;
	}

	protected Monstruo getBoss() {
		return boss;
	}

	protected void setBoss(Monstruo boss) {
		this.boss = boss;
	}

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

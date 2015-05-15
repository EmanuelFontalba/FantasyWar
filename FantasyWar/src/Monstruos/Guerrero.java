package Monstruos;

import enumeraciones.Clase;
import enumeraciones.Razas;
import enumeraciones.TipoDeDanno;
import excepciones.IraInsuficienteException;
import excepciones.NombreInvalidoException;
import interfaces.Luchable;

public class Guerrero extends Monstruo implements Luchable {
	private int ira;
	private static final int IRA_MAX = 100;
	

	public Guerrero(String nombre, Razas raza) throws NombreInvalidoException {
		super(nombre, raza);
		setIra(getIramax());
		incrementaAtaqueBasico();
		decrementaPoderHabilidad();
		setTipo(TipoDeDanno.FISICO);
		CLASE = Clase.GUERRERO;
	}
	
	/**
	 * Regenera ira en función del daño recibido.
	 */
	@Override
	public void regeneracionDeIra(int dañoRecibido){
		int aumentoIra = (int) Math.round(dañoRecibido * 0.1);
		if(getIra()+aumentoIra>=getIramax())
			setIra(getIramax());
		else
			setIra(getIra()+aumentoIra);
	}
	/**
	 * Inflije daño fisico igual a su ataque básico más su ira actual.
	 * Tiene un coste de 10 de ira.
	 * @return Daño verdadero.
	 * @throws IraInsuficienteException
	 * 				No hay ira suficiente para hacer el ataque 
	 */
	@Override
	public int rajar() throws IraInsuficienteException {
		if(getIra()<10)
			throw new IraInsuficienteException("No tienes suficiente ira. ");
		int danno =getAtaqueBasico()+getIra();
		setIra(getIra()-10);
		return danno;
	}
	
	/**
	 * Inflige un daño fisico igual a su ataque básico más cuatro veces su ira actual.
	 * Agota toda tu ira.
	 * @return Daño verdadero.
	 * @throws IraInsuficienteException 
	 * 				No hay ira suficiente para hacer el ataque
	 */
	@Override
	public int ejecutar() throws IraInsuficienteException {
		if(getIra()<10)
			throw new IraInsuficienteException("No tienes suficiente ira. ");
		int danno = getAtaqueBasico()+(getIra()*4);
		setIra(getIra()-getIra());
		return danno;
	}
	
	/**
	 * Incrementa su armadura en 30 y la resistencia mágica en 20.
	 * Tiene un coste de 30 puntos de ira.
	 * @throws IraInsuficienteException 
	 * 				No hay ira suficiente para hacer el ataque
	 */
	@Override
	public void proteccion() throws IraInsuficienteException {
		if(getIra()<10)
			throw new IraInsuficienteException("No tienes suficiente ira. ");
		setArmadura(getArmadura()+30);
		setResistenciaMagica(getResistenciaMagica()+20);
		setIra(getIra()-30);
	}
	
	/**
	 * Causa un gran daño por un coste de 30 puntos de ira.
	 * @return Daño verdadero.
	 * @throws IraInsuficienteException 
	 * 				No hay ira suficiente para hacer el ataque
	 */
	@Override
	public int filoTormenta() throws IraInsuficienteException {
		if(getIra()<10)
			throw new IraInsuficienteException("No tienes suficiente ira. ");
		int danno = (getAtaqueBasico()*2)+getPoderHabilidad()+getIra();
		setIra(getIra()-30);
		return danno;
	}
	
	/**
	 * Incrementa el ataque básico en 80 puntos
	 */
	private void incrementaAtaqueBasico() {
		setAtaqueBasico(getAtaqueBasico()+80);
	}

	/**
	 * Decrementa el poder de habilidad en 80 puntos.
	 */
	private void decrementaPoderHabilidad() {
		setPoderHabilidad(getPoderHabilidad()-80);
	}
	
	//-------------------------GETTERS & SETTERS -----------------------
	public int getIra() {
		return ira;
	}

	public void setIra(int ira) {
		this.ira = ira;
	}

	public static int getIramax() {
		return IRA_MAX;
	}
	
}

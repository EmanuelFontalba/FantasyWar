package Monstruos;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import clasesPrincipales.Leveable;
import enumeraciones.Ataques;
import enumeraciones.Razas;
import enumeraciones.TipoDeDanno;
import excepciones.IraInsuficienteException;
import excepciones.NombreInvalidoException;
import interfaces.Razable;
/**
 * Clase monstruo.
 * @author Emanuel Galván Fontalba
 *
 */

public abstract class Monstruo extends Leveable implements Razable{
	private String nombre;
	private Date fNacimiento;
	private Razas raza;
	private int saludActual;
	private int saludMaxima;
	private int ataqueBasico;
	private int poderHabilidad;
	private int armadura;
	private int resistenciaMagica;
	private double probabilidadCritico;
	private int probabilidadEsquivar;
	private boolean muerto;
	private TipoDeDanno tipo;
	
	private int armaduraProvisional=0;
	private int resistenciaMagicaProvisional=0;



	

	/**
	 * Patrón para nombres correctos. Deben de empezar por una mayúscula.
	 */
	private static final Pattern PATRON_NOMBRE = Pattern.compile("^[A-Z][a-z]*[a-z]$");

	/**
	 * Constructor completo.
	 * @param nombre
	 * 			Nombre del monstruo
	 * @param raza
	 * 			Raza del monstruo
	 * @throws NombreInvalidoException
	 * 			El nombre no empieza por mayuscula.
	 */
	public Monstruo(String nombre, Razas raza) throws NombreInvalidoException{
		setNombre(nombre);
		setRaza(raza);
		setExp(0);
		setNivel(1);
		setfNacimiento(new Date());
		estableceAtributosDeRaza(getRaza());
		setSaludActual(getSaludMaxima());
		setMuerto(false);
	}
	
	Monstruo(String nombre) throws NombreInvalidoException{
		setNombre(nombre);
	}
	
	abstract public void reestablecerse();
	
	/**
	 * Aumenta en nivel en 1.
	 */
	public void aumentarNivel(){
		setNivel(getNivel()+1);
		aumentarEstadisticas();
	}
	
	/**
	 * Aumenta las estadisticas.
	 */
	private void aumentarEstadisticas(){
		setSaludMaxima(getSaludMaxima()+100);
		setAtaqueBasico(getAtaqueBasico()+5);
		setPoderHabilidad(getPoderHabilidad()+5);
		setArmadura(getArmadura()+2);
		setResistenciaMagica(getResistenciaMagica()+2);
	}
	
	/**
	 * Establece atributos básicos según su raza.
	 */
	@Override
	public void estableceAtributosDeRaza(Razas raza) {
		switch (raza){
		case ORCO:
			setSaludMaxima(1200);
			setAtaqueBasico(150);
			setPoderHabilidad(80);
			setArmadura(20);
			setResistenciaMagica(20);
			setProbabilidadCritico(0.12);
			setProbabilidadEsquivar(5);
			break;
			
		case NOMUERTO:
			setSaludMaxima(900);
			setAtaqueBasico(100);
			setPoderHabilidad(140);
			setArmadura(10);
			setResistenciaMagica(40);
			setProbabilidadCritico(0.03);
			setProbabilidadEsquivar(1);
			break;
			
		case DEMONIO:
			setSaludMaxima(1100);
			setAtaqueBasico(130);
			setPoderHabilidad(90);
			setArmadura(40);
			setResistenciaMagica(30);
			setProbabilidadCritico(0.15);
			setProbabilidadEsquivar(15);
			break;
			
		case HUMANO:
			setSaludMaxima(1000);
			setAtaqueBasico(120);
			setPoderHabilidad(120);
			setArmadura(30);
			setResistenciaMagica(30);
			setProbabilidadCritico(0.12);
			setProbabilidadEsquivar(9);
			break;
			
		case ELFO:
			setSaludMaxima(900);
			setAtaqueBasico(120);
			setPoderHabilidad(120);
			setArmadura(20);
			setResistenciaMagica(30);
			setProbabilidadCritico(0.10);
			setProbabilidadEsquivar(17);
			break;
		
		case ENANO:
			setSaludMaxima(1300);
			setAtaqueBasico(140);
			setPoderHabilidad(60);
			setArmadura(40);
			setResistenciaMagica(10);
			setProbabilidadCritico(0.14);
			setProbabilidadEsquivar(3);
			break;
		}
		
	}
	
	/**
	 * Ataque básico
	 * @return Daño verdadero igual a su ataque básico.
	 */
	public int ataqueBasico(){
		return getAtaqueBasico();
	}
	
	/**
	 * Aumenta la salud actual.
	 * @param salud
	 * 			Salud a aumentar.
	 */
	public void aumentarSaludActual(int salud){
		if(getSaludActual()+salud>=getSaludMaxima())
			setSaludActual(getSaludMaxima());
		else
			setSaludActual(getSaludActual()+salud);
	}
	
	/**
	 * Disminuye la salud actual.
	 * @param salud
	 * 			Salud a disminuir
	 */
	public void disminuirSaludActual(int salud){
		if(getSaludActual()-salud<=0){
			setSaludActual(0);
			setMuerto(true);
		}
		else
			setSaludActual(getSaludActual()-salud);
	}
	
	/**
	 * Comprueba si un nombre es válido
	 * @param nombre
	 * 			Nombre a comprobar
	 * @return true si es valido si no false.
	 */
	public boolean nombreValido(String nombre){
		return PATRON_NOMBRE.matcher(nombre).matches();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Monstruo other = (Monstruo) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
	
	//--------------------------------GETTERS & SETTERS--------------------------------------
	
	

	
	
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Si el alias es valido, lo añade al campo.
	 * @param nombre
	 * 			Nombre a comprobar.
	 * @throws NombreInvalidoException 
	 * 			El nombre no comienza por mayuscula.
	 */
	private void setNombre(String nombre) throws NombreInvalidoException {
		if(nombreValido(nombre))
			this.nombre = nombre;
		else
			throw new NombreInvalidoException("El nombre introducido no es valido. Debe comenzar por mayuscula.");
	}

	public Date getfNacimiento() {
		return fNacimiento;
	}

	private void setfNacimiento(Date fNacimiento) {
		this.fNacimiento = fNacimiento;
	}

	public Razas getRaza() {
		return raza;
	}

	private void setRaza(Razas raza) {
		this.raza = raza;
	}

	public int getSaludActual() {
		return saludActual;
	}

	void setSaludActual(int saludActual) {
		this.saludActual = saludActual;
	}

	public int getSaludMaxima() {
		return saludMaxima;
	}

	private void setSaludMaxima(int saludMaxima) {
		this.saludMaxima = saludMaxima;
	}

	public int getAtaqueBasico() {
		return ataqueBasico;
	}

	protected void setAtaqueBasico(int ataqueBasico) {
		this.ataqueBasico = ataqueBasico;
	}

	public int getPoderHabilidad() {
		return poderHabilidad;
	}

	protected void setPoderHabilidad(int poderHabilidad) {
		this.poderHabilidad = poderHabilidad;
	}

	public int getArmadura() {
		return armadura;
	}

	protected void setArmadura(int armadura) {
		this.armadura = armadura;
	}

	public int getResistenciaMagica() {
		return resistenciaMagica;
	}

	protected void setResistenciaMagica(int resistenciaMagica) {
		this.resistenciaMagica = resistenciaMagica;
	}

	public double getProbabilidadCritico() {
		return probabilidadCritico;
	}

	private void setProbabilidadCritico(double d) {
		this.probabilidadCritico = d;
	}

	public int getProbabilidadEsquivar() {
		return probabilidadEsquivar;
	}

	private void setProbabilidadEsquivar(int probabilidadEsquivar) {
		this.probabilidadEsquivar = probabilidadEsquivar;
	}
	
	public boolean isMuerto() {
		return muerto;
	}

	public void setMuerto(boolean muerto) {
		this.muerto = muerto;
	}

	public TipoDeDanno getTipo() {
		return tipo;
	}

	protected void setTipo(TipoDeDanno tipo) {
		this.tipo = tipo;
	}
	
	public int getArmaduraProvisional() {
		return armaduraProvisional;
	}

	public void setArmaduraProvisional(int armaduraProvisional) {
		this.armaduraProvisional = armaduraProvisional;
	}

	public int getResistenciaMagicaProvisional() {
		return resistenciaMagicaProvisional;
	}

	public void setResistenciaMagicaProvisional(int resistenciaMagicaProvisional) {
		this.resistenciaMagicaProvisional = resistenciaMagicaProvisional;
	}

	public abstract int getPotenciador();
	
	public abstract void luchar(Ataques ataque, Monstruo defensor) throws Exception ;

	public void recibirDannoFisico(int dannoVerdadero) {
		int dadosDefensor = (int) Math.random()*(0-100)+100;
		int danno;

		if(dannoVerdadero==0)
			return;
		if(dadosDefensor<=getProbabilidadEsquivar())
			return;
		danno=dannoVerdadero - (getArmadura()+getArmaduraProvisional());
		if(danno<=0)
			return;
		if(getSaludActual()<=0)
			setMuerto(true);
		disminuirSaludActual(danno) ;
	}

	public void recibirDannoMagico(int dannoVerdadero) {
		int dadosDefensor = (int) Math.random()*(0-100)+100;
		int danno;

		if(dannoVerdadero==0)
			return;
		if(dadosDefensor<=getProbabilidadEsquivar())
			return;
		danno=dannoVerdadero - (getResistenciaMagica()+getResistenciaMagicaProvisional());
		if(getSaludActual()<=0)
			setMuerto(true);
		if(danno<=0)
			return;
		disminuirSaludActual(danno) ;
	}
		
}

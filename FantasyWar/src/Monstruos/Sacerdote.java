package Monstruos;

import enumeraciones.Ataques;
import enumeraciones.Clase;
import enumeraciones.Razas;
import enumeraciones.TipoDeDanno;
import excepciones.FeInsuficienteException;
import excepciones.IraInsuficienteException;
import excepciones.NombreInvalidoException;
import interfaces.Saneable;

public class Sacerdote extends Monstruo implements Saneable {
	
	private int fe;
	private static final int FE_MAX = 150;


	public Sacerdote(String nombre, Razas raza) throws NombreInvalidoException {
		super(nombre, raza);
		incrementaPoderHabilidad();
		decrementaAtaqueBasico();
		setTipo(TipoDeDanno.MAGICO);
		energia = getFe();
	}
	
	/**
	 * Si la fé no llega a la fé máxima aumenta 15 puntos.
	 */
	@Override
	public void regeneracionFe() {
		if (getFe()+15>=getFemax())
			setFe(getFemax());
		else
			setFe(getFe()+15);
		energia = getFe();
	}
	/**
	 * Hace daño magico igual a su poder de habilidad mas su fé duplicada.
	 * Tiene un coste de fe de 20 puntos.
	 * @return Daño verdadero.
	 * @throws FeInsuficienteException 
	 * 				No hay suficiente fé para lanzar el hechizo.
	 */
	@Override
	public int energiaDivina() throws FeInsuficienteException {
		if(getFe()<20)
			throw new FeInsuficienteException("No tienes suficiente fé");	
		int danno = getPoderHabilidad() + getFe()*2;
		setFe(getFe()-20);
		energia = getFe();
		return danno;
	}
	
	/**
	 * Se cura según la fé que tenga e incrementa su poder de habilidad.
	 * @throws FeInsuficienteException 
	 * 				No hay suficiente fé para lanzar el hechizo.
	 */
	@Override
	public void curacionAncestral() throws FeInsuficienteException {
		if(getFe()<40)
			throw new FeInsuficienteException("No tienes suficiente fé");
		aumentarSaludActual(getFe());
		incrementaPoderHabilidad();
		setFe(getFe()-40);	
		energia = getFe();
	}
	/**
	 * Aumenta su armadura y resistencia mágica en 5 puntos.
	 * Tiene un coste de fe de 10 puntos
	 * @throws FeInsuficienteException 
	 * 				No hay suficiente fé para lanzar el hechizo.
	 */
	@Override
	public void escudoDivino() throws FeInsuficienteException {
		if(getFe()<10)
			throw new FeInsuficienteException("No tienes suficiente fé");
		setArmadura(getArmadura()+5);
		setResistenciaMagica(getResistenciaMagica()+5);
		setFe(getFe()-10);
		energia = getFe();
	}
	
	/**
	 * Se cura una gran cantidad de vida.
	 * Tiene un coste de 60 puntos de fe.
	 * @throws FeInsuficienteException 
	 * 				No hay suficiente fé para lanzar el hechizo.
	 */
	@Override
	public void curacionAbsoluta() throws FeInsuficienteException {
		if(getFe()<60)
			throw new FeInsuficienteException("No tienes suficiente fé");
		aumentarSaludActual((int) Math.round(getSaludMaxima()*0.5)+getFe());
		setFe(getFe()-60);
		energia = getFe();
	}
	
	/**
	 * Incrementa el poder de habilidad en 30 puntos
	 */
	private void incrementaPoderHabilidad() {
		setPoderHabilidad(getPoderHabilidad()+30);
	}

	/**
	 * Decrementa el ataque básico en 30 puntos.
	 */
	private void decrementaAtaqueBasico() {
		setAtaqueBasico(getAtaqueBasico()-30);
	}
	
	//---------------------------------GETTES & SETTERS---------------------------------
	public int getFe() {
		return fe;
	}

	private void setFe(int fe) {
		this.fe = fe;
	}

	private static int getFemax() {
		return FE_MAX;
	}

	@Override
	public
	void luchar(Ataques ataque, Monstruo defensor)
			throws Exception {
		int dadosAtacante = (int) ((Math.random()*(0-100)+100));
		int danno=0;
	
		switch (ataque) {
		case CURACION_ABSOLUTA :
			danno = 0;
			curacionAbsoluta();
			break;
		case CURACION_ANCESTRAL:
			danno = 0;
			curacionAncestral();
			break;
		case ENERGIA_DIVINA:
			danno = energiaDivina();
			break;
		case ESCUDO_DIVINO:
			danno=0;
			escudoDivino();
			break;
		default:
			break;
		}
		
		if(dadosAtacante<=getProbabilidadCritico())
			danno=danno*2;
			
		defensor.recibirDannoMagico(danno);
		
		
		if(defensor.isMuerto()){
			setSaludActual(getSaludMaxima());
			setFe(getFemax());
			return;
		}
		
		regeneracionFe();
	}

}

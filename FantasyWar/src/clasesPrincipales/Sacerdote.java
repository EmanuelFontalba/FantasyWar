package clasesPrincipales;


public class Sacerdote extends Monstruo implements Saneable {
	private static final long serialVersionUID = 6783024868276173942L;
	private int fe;
	private static final int FE_MAX = 150;


	public Sacerdote(String nombre, Razas raza) throws NombreInvalidoException {
		super(nombre, raza);
		incrementaPoderHabilidad();
		decrementaAtaqueBasico();
		setTipo(TipoDeDanno.MAGICO);
		setFe(getFemax());
		estableceImg();
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
		return danno;
	}
	
	/**
	 * Se cura según la fé que tenga e incrementa su poder de habilidad.
	 * @throws FeInsuficienteException 
	 * 				No hay suficiente fé para lanzar el hechizo.
	 */
	@Override
	public int curacionAncestral() throws FeInsuficienteException {
		if(getFe()<40)
			throw new FeInsuficienteException("No tienes suficiente fé");
		aumentarSaludActual(getFe());
		incrementaPoderHabilidad();
		setFe(getFe()-40);
		return 0;
	}
	/**
	 * Aumenta su armadura y resistencia mágica en 5 puntos.
	 * Tiene un coste de fe de 10 puntos
	 * @throws FeInsuficienteException 
	 * 				No hay suficiente fé para lanzar el hechizo.
	 */
	@Override
	public int escudoDivino() throws FeInsuficienteException {
		if(getFe()<10)
			throw new FeInsuficienteException("No tienes suficiente fé");
		setArmaduraProvisional(getArmadura()+5);
		setResistenciaMagicaProvisional(getResistenciaMagica()+5);
		setFe(getFe()-10);
		return 0;
	}
	
	/**
	 * Se cura una gran cantidad de vida.
	 * Tiene un coste de 60 puntos de fe.
	 * @return 
	 * @throws FeInsuficienteException 
	 * 				No hay suficiente fé para lanzar el hechizo.
	 */
	@Override
	public int curacionAbsoluta() throws FeInsuficienteException {
		if(getFe()<60)
			throw new FeInsuficienteException("No tienes suficiente fé");
		aumentarSaludActual((int) Math.round(getSaludMaxima()*0.5)+getFe());
		setFe(getFe()-60);
		return 0;
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
	
	/**
	 * Calcula el daño verdadero y se lo aplica al monstruo defensor.
	 */
	@Override
	public
	void luchar(Ataques ataque, Monstruo defensor)
			throws Exception {
		int dadosAtacante = (int) ((Math.random()*(0-100)+100));
		int danno=0;
		
		regeneracionFe();
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
		
		
	}
	
	/**
	 * Reestablece su salud, fe y atributos provisionales modificados en el turno.
	 */
	@Override
	public void reestablecerse() {
		setSaludActual(getSaludMaxima());
		setFe(getFemax());
		setArmaduraProvisional(0);
		setResistenciaMagicaProvisional(0);
		setMuerto(false);
	}
	
	/**
	 * Establece la ruta de la imagen en función de su raza.
	 */
	@Override
	void estableceImg() {
		switch (getRaza()) {
		case DEMONIO:
			setRutaImg("src\\imagenes\\demonioSacerdote.jpg");
			break;
		case ELFO:
			setRutaImg("src\\imagenes\\elfoSacerdote.jpg");
			break;
		case ENANO:
			setRutaImg("src\\imagenes\\enanoSacerdote.jpg");
			break;
		case HUMANO:
			setRutaImg("src\\imagenes\\humanoSacerdote.jpg");
			break;
		case NOMUERTO:
			setRutaImg("src\\imagenes\\noMuertoSacerdote.jpg");
			break;
		case ORCO:
			setRutaImg("src\\imagenes\\orcoSacerdote.jpg");
			break;
		}
		
	}
	
	/**
	 * Realiza un ataque inteligente para la cpu.
	 * @return Daño realizado. 
	 */
	@Override
	public Ataques ataqueInteligente(Monstruo defensor){
		if(getFe() > 20){
			if(getSaludActual()== getSaludMaxima())
				return Ataques.ENERGIA_DIVINA;
			if(defensor.getSaludMaxima()/2 >= defensor.getSaludActual())
				if(getSaludMaxima()/2 >= getSaludActual())
					if(getSaludActual() > defensor.getSaludActual())
						if(defensor.getSaludMaxima()/4 >= defensor.getSaludActual())
							return Ataques.CURACION_ABSOLUTA;
						else
							return Ataques.CURACION_ANCESTRAL;
					else
						return Ataques.ESCUDO_DIVINO;
				else
					return Ataques.ENERGIA_DIVINA;
			else
				if(getSaludMaxima()/2 >= getSaludActual())
						return Ataques.CURACION_ABSOLUTA;
				else
					if(getSaludActual() > defensor.getSaludActual())
						if(defensor.getSaludMaxima()/4 >= defensor.getSaludActual())
							return Ataques.ENERGIA_DIVINA;
						else
							return Ataques.CURACION_ANCESTRAL;
					else
						return Ataques.CURACION_ABSOLUTA;	
		}else
			return Ataques.ENERGIA_DIVINA;
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
	public int getPotenciador() {
		return getFe();
	}
	
	

}

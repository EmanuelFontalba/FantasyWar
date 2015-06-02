package clasesPrincipales;


public class Guerrero extends Monstruo implements Luchable {
	private static final long serialVersionUID = 4251532845695003870L;
	private int ira;
	private static final int IRA_MAX = 100;

	public Guerrero(String nombre, Razas raza) throws NombreInvalidoException {
		super(nombre, raza);
		setIra(getIramax());
		incrementaAtaqueBasico();
		decrementaPoderHabilidad();
		setTipo(TipoDeDanno.FISICO);
		estableceImg();
	}

	/**
	 * Regenera ira en función del daño recibido.
	 */
	@Override
	public void regeneracionDeIra() {
		int aumentoIra = 10;
		if (getIra() + aumentoIra >= getIramax())
			setIra(getIramax());
		else
			setIra(getIra() + aumentoIra);
	}

	/**
	 * Inflije daño fisico igual a su ataque básico más su ira actual. Tiene un
	 * coste de 10 de ira.
	 * 
	 * @return Daño verdadero.
	 * @throws IraInsuficienteException
	 *             No hay ira suficiente para hacer el ataque
	 */
	@Override
	public int rajar() throws IraInsuficienteException {
		if (getIra() < 10)
			throw new IraInsuficienteException("No tienes suficiente ira. ");
		int danno = getAtaqueBasico() + getIra();
		setIra(getIra() - 10);
		return danno;
	}

	/**
	 * Inflige un daño fisico igual a su ataque básico más cuatro veces su ira
	 * actual. Agota toda tu ira.
	 * 
	 * @return Daño verdadero.
	 * @throws IraInsuficienteException
	 *             No hay ira suficiente para hacer el ataque
	 */
	@Override
	public int ejecutar() throws IraInsuficienteException {
		if (getIra() < 10)
			throw new IraInsuficienteException("No tienes suficiente ira. ");
		int danno = getAtaqueBasico() + (getIra() * 4);
		setIra(getIra() - getIra());
		return danno;
	}

	/**
	 * Incrementa su armadura en 30 y la resistencia mágica en 20. Tiene un
	 * coste de 30 puntos de ira.
	 * 
	 * @throws IraInsuficienteException
	 *             No hay ira suficiente para hacer el ataque
	 */
	@Override
	public int proteccion() throws IraInsuficienteException {
		if (getIra() < 10)
			throw new IraInsuficienteException("No tienes suficiente ira. ");
		setArmaduraProvisional(getArmadura() + 30);
		setResistenciaMagicaProvisional(getResistenciaMagica() + 20);
		setIra(getIra() - 30);
		return 0;
	}

	/**
	 * Causa un gran daño por un coste de 30 puntos de ira.
	 * 
	 * @return Daño verdadero.
	 * @throws IraInsuficienteException
	 *             No hay ira suficiente para hacer el ataque
	 */
	@Override
	public int filoTormenta() throws IraInsuficienteException {
		if (getIra() < 30)
			throw new IraInsuficienteException("No tienes suficiente ira. ");
		int danno = (getAtaqueBasico() * 2) + getPoderHabilidad() + getIra();
		setIra(getIra() - 30);
		return danno;
	}

	/**
	 * Incrementa el ataque básico en 80 puntos
	 */
	private void incrementaAtaqueBasico() {
		setAtaqueBasico(getAtaqueBasico() + 80);
	}

	/**
	 * Decrementa el poder de habilidad en 80 puntos.
	 */
	private void decrementaPoderHabilidad() {
		setPoderHabilidad(getPoderHabilidad() - 80);
	}
	
	/**
	 * Calcula el daño verdadero se lo realiza al monstruo defensor.
	 */
	@Override
	public void luchar(Ataques ataque, Monstruo defensor)
			throws IraInsuficienteException {

		int dadosAtacante = (int) ((Math.random()*(0-100)+100));
		int danno=0;
		
		regeneracionDeIra();
		switch (ataque) {
		case RAJAR:
			danno = rajar();
			break;
		case EJECUTAR:
			danno = ejecutar();
			break;
		case PROTECCION:
			proteccion();
			break;
		case FILOTORMENTA:
			danno = filoTormenta();
			break;
		default:
			break;
		}
		
		if(dadosAtacante<=getProbabilidadCritico())
			danno=danno*2;
			
		defensor.recibirDannoFisico(danno);
		
		
		if(defensor.isMuerto()){
			setSaludActual(getSaludMaxima());
			setIra(getIramax());
			return;
		}
		
		
	}
	
	/**
	 * Se reestablece la salud, ira y las estadisticas aumentadas en el turno.
	 */
	@Override
	public void reestablecerse() {
		setSaludActual(getSaludMaxima());
		setIra(getIramax());
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
			setRutaImg("src\\imagenes\\demonioGuerrero.jpg");
			break;
		case ELFO:
			setRutaImg("src\\imagenes\\elfoGuerrero.jpg");
			break;
		case ENANO:
			setRutaImg("src\\imagenes\\enanoGuerrero.jpg");
			break;
		case HUMANO:
			setRutaImg("src\\imagenes\\humanoGuerrero.jpg");
			break;
		case NOMUERTO:
			setRutaImg("src\\imagenes\\noMuertoGuerrero.jpg");
			break;
		case ORCO:
			setRutaImg("src\\imagenes\\orcoGuerrero.jpg");
			break;
		}
		
	}
	
	/**
	 * Realiza un ataque inteligente para la cpu.
	 * @return Daño realizado.
	 */
	@Override
	public Ataques ataqueInteligente(Monstruo defensor){
		if(getIra() > 20)
			if(defensor.getSaludMaxima()/2 >= defensor.getSaludActual())
				if(getSaludMaxima()/2 >= getSaludActual())
					if(getSaludActual() > defensor.getSaludActual())
						if(defensor.getSaludMaxima()/4 >= defensor.getSaludActual())
							return Ataques.EJECUTAR;
						else
							return Ataques.RAJAR;
					else
						return Ataques.PROTECCION;
				else
					return Ataques.FILOTORMENTA;
			else
				if(getSaludMaxima()/2 >= getSaludActual())
						return Ataques.FILOTORMENTA;
				else
					if(getSaludActual() > defensor.getSaludActual())
						if(defensor.getSaludMaxima()/4 >= defensor.getSaludActual())
							return Ataques.EJECUTAR;
						else
							return Ataques.RAJAR;
					else
						return Ataques.PROTECCION;	
		else
			return Ataques.RAJAR;
	}

	// -------------------------GETTERS & SETTERS -----------------------
	public int getIra() {
		return ira;
	}

	public void setIra(int ira) {
		this.ira = ira;
	}

	public static int getIramax() {
		return IRA_MAX;
	}
	

	@Override
	public int getPotenciador() {
		return getIra();
	}


}

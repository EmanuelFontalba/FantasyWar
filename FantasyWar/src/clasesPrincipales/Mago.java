package clasesPrincipales;


/**
 * Por ser clase mago cambia treinta puntos de ataque basico por otros treinta de poder de habilidad.
 * 
 * @author Emanuel Galv�n FOntalba
 *
 */
public class Mago extends Monstruo implements Hechizable{
	private static final long serialVersionUID = -7456235836948543809L;
	private int mana;
	private static final int MANA_MAX = 200;
	 

	public Mago(String nombre,Razas razas) throws NombreInvalidoException {
		super(nombre, razas);
		setMana(getManamax());
		incrementaPoderHabilidad();
		decrementaAtaqueBasico();
		setTipo(TipoDeDanno.MAGICO);
		estableceImg();
	}
	
	/**
	 * Constructor para buscar MOSTRUOS por nombre, ya que la clase Monstruo es abstracta
	 * y no podemos usar los constructores fuera de sus hijos.
	 * 
	 * @param nombre
	 * 			Nombre a buscar.
	 * @throws NombreInvalidoException
	 * 			El nombre no empieza por mayuscula.
	 */
	public Mago(String nombre) throws NombreInvalidoException{
		super(nombre);
	}
	
	/**
	 * Si el man� no llega a el man� m�ximo lo incrementa en 10 puntos.
	 */
	@Override
	public void regeneracionMana() {
		if (getMana()+10>=getManamax())
			setMana(getManamax());
		else
			setMana(getMana()+10);
	}
	
	/**
	 * Hace da�o m�gico igual a su poder de habilidad mas un 80% de este mismo.
	 * Tiene un coste de 20 de man�
	 * @return Da�o verdadero.
	 * @throws ManaInsuficienteException 
	 * 				No hay man� suficiente para lanzar el hechizo.
	 */
	@Override
	public int bolaDeFuego() throws ManaInsuficienteException {
		if(getMana()<40)
			throw new ManaInsuficienteException("Tienes el man� agotado");
		setMana(getMana()-20);
		return (int) Math.round(getPoderHabilidad()*1.8);
	}
	
	/**
	 * Incrementa su armadura y su resistencia m�gica en 5 puntos.
	 * Tiene un coste de 40 de man�
	 * @return 
	 * @throws ManaInsuficienteException 
	 * 				No hay man� suficiente para lanzar el hechizo.
	 */
	@Override
	public int escudoDeEscarcha() throws ManaInsuficienteException {
		if(getMana()<40)
			throw new ManaInsuficienteException("Tienes el man� agotado");
		setArmadura(getArmadura()+5);
		setResistenciaMagicaProvisional(getResistenciaMagicaProvisional()+5);
		setMana(getMana()-40);
		return 0;
	}
	
	/**
	 * Se cura a el mismo un 12% de su vida m�xima.
	 * Tiene un coste de 20 de man�.
	 * @return 
	 * @throws ManaInsuficienteException 
	 * 				No hay man� suficiente para lanzar el hechizo.
	 */
	@Override
	public int meditacion() throws ManaInsuficienteException {
		if(getMana()<20)
			throw new ManaInsuficienteException("Tienes el man� agotado");
		aumentarSaludActual((int) Math.round(getSaludMaxima()*0.12));
		setMana(getMana()-20);
		return 0;
	}
	
	/**
	 * Hace da�o m�gico igual al doble de su poder de habilidad.
	 * Tiene un coste de 40 de man�.
	 * @return Da�o verdadero
	 * @throws ManaInsuficienteException 
	 * 				No hay suficiente man� para lanzar el hechizo.
	 */
	@Override
	public int lluviaDeMeteoros() throws ManaInsuficienteException {
		if(getMana()<40)
			throw new ManaInsuficienteException("Tienes el man� agotado");
		setMana(getMana()-40);
		return getPoderHabilidad()*2;
	}
	
	/**
	 * Incrementa el poder de habilidad en 30 puntos
	 */
	private void incrementaPoderHabilidad() {
		setPoderHabilidad(getPoderHabilidad()+30);
	}

	/**
	 * Decrementa el ataque b�sico en 30 puntos.
	 */
	private void decrementaAtaqueBasico() {
		setAtaqueBasico(getAtaqueBasico()-30);
	}
	
	/**
	 * Calcula el da�o verdadero a realizar, y se lo aplica al monstruo defensor.
	 */
	@Override
	public
	void luchar(Ataques ataque, Monstruo defensor)
			throws ManaInsuficienteException {
		int dadosAtacante = (int) ((Math.random()*(0-100)+100));
		int danno=0;
		
		regeneracionMana();
		switch (ataque) {
		case BOLA_DE_FUEGO:
			danno = bolaDeFuego();
			break;
		case ESCUDO_DE_ESCARCHA:
			danno = 0;
			escudoDeEscarcha();
			break;
		case LLUVIA_DE_METEOROS:
			danno = lluviaDeMeteoros();
			break;
		case MEDITACION:
			danno=0;
			meditacion();
			break;
		default:
			break;
		}
		
		if(dadosAtacante<=getProbabilidadCritico())
			danno=danno*2;
			
		defensor.recibirDannoMagico(danno);
		
		
	}
	
	/**
	 * Reestablece la salud, el man� y los atributos provisionales del turno.
	 */
	@Override
	public void reestablecerse() {
		setSaludActual(getSaludMaxima());
		setMana(getManamax());
		setArmaduraProvisional(0);
		setResistenciaMagicaProvisional(0);
		setMuerto(false);
		
	}
	
	/**
	 * Establece la ruta de la imagen en funci�n de su raza.
	 */
	@Override
	void estableceImg() {
		switch (getRaza()) {
		case DEMONIO:
			setRutaImg("src\\imagenes\\demonioMago.jpg");
			break;
		case ELFO:
			setRutaImg("src\\imagenes\\elfoMago.jpg");
			break;
		case ENANO:
			setRutaImg("src\\imagenes\\enanoMago.jpg");
			break;
		case HUMANO:
			setRutaImg("src\\imagenes\\humanoMago.jpg");
			break;
		case NOMUERTO:
			setRutaImg("src\\imagenes\\noMuertoMago.jpg");
			break;
		case ORCO:
			setRutaImg("src\\imagenes\\orcoMago.jpg");
			break;
		}
		
	}
	
	/**
	 * Realiza un ataque inteligente para la cpu.
	 * @return Da�o realizado.
	 */
	@Override
	public Ataques ataqueInteligente(Monstruo defensor){
		if(getMana() > 20)
			if(defensor.getSaludMaxima()/2 >= defensor.getSaludActual())
				if(getSaludMaxima()/2 >= getSaludActual())
					if(getSaludActual() > defensor.getSaludActual())
						if(defensor.getSaludMaxima()/4 >= defensor.getSaludActual())
							return Ataques.LLUVIA_DE_METEOROS;
						else
							return Ataques.BOLA_DE_FUEGO;
					else
						return Ataques.BOLA_DE_FUEGO;
				else
					return Ataques.MEDITACION;
			else
				if(getSaludMaxima()/2 >= getSaludActual())
						return Ataques.BOLA_DE_FUEGO;
				else
					if(getSaludActual() > defensor.getSaludActual())
						if(defensor.getSaludMaxima()/4 >= defensor.getSaludActual())
							return Ataques.LLUVIA_DE_METEOROS;
						else
							return Ataques.BOLA_DE_FUEGO;
					else
						return Ataques.ESCUDO_DE_ESCARCHA;	
		else
			return Ataques.BOLA_DE_FUEGO;
	}
	
	//-------------------------------GETTERS & SETTERS---------------------
	public int getMana() {
		return mana;
	}

	private void setMana(int mana) {
		this.mana = mana;
	}

	private static int getManamax() {
		return MANA_MAX;
	}


	@Override
	public int getPotenciador() {
		return getMana();
	}

	

}

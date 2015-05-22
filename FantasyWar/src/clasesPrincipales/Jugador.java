package clasesPrincipales;

import java.io.Serializable;
import java.util.Date;
import java.util.regex.Pattern;

import excepciones.NombreInvalidoException;
/**
 * Se guardan los datos del jugador y sus estadisticas, junto con su colección de monstruos.
 * 
 * @author Emanuel Galván Fontalba
 */
public class Jugador extends Leveable implements Serializable{
	//datos del jugador
	/**
	 * Alias del jugador
	 */
	private String alias;
	/**
	 * Fecha de creación del jugador
	 */
	private Date fNacimiento;
	/**
	 * Colección del jugador
	 */
	private Coleccion coleccionMonstruos;
	
	private int exp;
	private int nivel;
	
	//estadisticas del jugador
	
	
	
	/**
	 * Patrón para nombres correctos. Deben de empezar por una mayúscula.
	 */
	private static final Pattern patronNombre = Pattern.compile("^[A-Z][a-z]*[a-z]$");
	
	/**
	 * Constructor
	 * @param alias
	 * @throws NombreInvalidoException 
	 * 				El nombre no comienza por mayuscula.
	 */
	public Jugador(String alias) throws NombreInvalidoException{
		super();
		setAlias(alias);
		setfNacimiento(new Date());
		setColeccionMonstruos(new Coleccion());
		setExp(0);
		setNivel(1);
	}

	/**
	 * Comprueba si un nombre es válido
	 * @param nombre
	 * 			Nombre a comprobar
	 * @return true si es valido si no false.
	 */
	private boolean nombreValido(String nombre){
		return patronNombre.matcher(nombre).matches();
	}

	
	//------------------------------GETTERS & SETTERS-----------------------------------
	/**
	 * Si el alias es valido, lo añade al campo.
	 * @param alias
	 * 			Nombre a comprobar.
	 * @throws NombreInvalidoException 
	 * 			El nombre no comienza por mayuscula.	
	 */
	public void setAlias(String alias) throws NombreInvalidoException {
		if(nombreValido(alias))
			this.alias = alias;
		else
			throw new NombreInvalidoException("El nombre introducido no es valido. Debe comenzar por mayuscula.");
	}

	public  String getAlias() {
		return alias;
	}
	

	public Date getfNacimiento() {
		return fNacimiento;
	}

	private void setfNacimiento(Date fNacimiento) {
		this.fNacimiento = fNacimiento;
	}

	public Coleccion getColeccionMonstruos() {
		return coleccionMonstruos;
	}

	private void setColeccionMonstruos(Coleccion coleccionMonstruos) {
		this.coleccionMonstruos = coleccionMonstruos;
	}

	@Override
	public void aumentarNivel() {
		setNivel(getNivel()+1);
	}

	@Override
	/**
	 * Aumenta la experiencia.
	 * @param exp
	 * 			Experiencia a aumentar.
	 */
	public void aumentarExp(int exp){
		if(getNivel()<getNivelMaximo()){
			setExp(getExp()+exp);
			if (getExp()>getExpMaxima()){
				aumentarNivel();
				setExp(-(getExpMaxima()-getExp()));
			}
		}
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

}

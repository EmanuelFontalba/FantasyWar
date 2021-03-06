package clasesPrincipales;

import java.io.Serializable;
import java.util.Date;
import java.util.regex.Pattern;
/**
 * Se guardan los datos del jugador y sus estadisticas, junto con su colecci�n de monstruos.
 * 
 * @author Emanuel Galv�n Fontalba
 */
public class Jugador extends Leveable implements Serializable{
	private static final long serialVersionUID = 3816894630401055572L;
	//datos del jugador
	/**
	 * Alias del jugador
	 */
	private String alias;
	/**
	 * Fecha de creaci�n del jugador
	 */
	private Date fNacimiento;
	/**
	 * Colecci�n del jugador
	 */
	private Coleccion coleccionMonstruos;
	/**
	 * Experiencia del jugador.
	 */
	private int exp;
	/**
	 * Nivel del jugador.
	 */
	private int nivel;
	/**
	 * Patr�n para nombres correctos. Deben de empezar por una may�scula.
	 */
	private static final Pattern patronNombre = Pattern.compile("^[A-Z][a-z]*[a-z]$");

	public Jugador(String alias) throws NombreInvalidoException{
		super();
		setAlias(alias);
		setfNacimiento(new Date());
		setColeccionMonstruos(new Coleccion());
		setExp(0);
		setNivel(1);
	}

	/**
	 * Comprueba si un nombre es v�lido
	 * @param nombre
	 * 			Nombre a comprobar
	 * @return true si es valido si no false.
	 */
	private boolean nombreValido(String nombre){
		return patronNombre.matcher(nombre).matches();
	}
	
	/**
	 * Incrementa el nivel en 1.
	 */
	@Override
	public void aumentarNivel() {
		setNivel(getNivel()+1);
	}

	@Override
	/**
	 * Aumenta la experiencia sin que sobrepase el valor m�ximo.
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

	
	//------------------------------GETTERS & SETTERS-----------------------------------
	/**
	 * Si el alias es valido, lo a�ade al campo.
	 * @param alias
	 * 			Nombre a comprobar.
	 * @throws NombreInvalidoException 
	 * 			El nombre no comienza por mayuscula.	
	 */
	public void setAlias(String alias) throws NombreInvalidoException {
		if(nombreValido(alias))
			this.alias = alias;
		else
			throw new NombreInvalidoException("El nombre introducido no es valido.\n"
					+ "Posibles errores: \n"
					+ "-El nombre no comienza por mayuscula. \n"
					+ "-Hay alguna mayuscula adem�s de la primera.\n"
					+ "-Hay alg�n digito en el nombre.");
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

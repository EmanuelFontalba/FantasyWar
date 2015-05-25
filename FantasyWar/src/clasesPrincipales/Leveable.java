package clasesPrincipales;

/**
 * Interfaz que se aplicar� a toda clase que tenga experiencia y nivel.
 * @author Emanuel Galv�n Fontalba
 *
 */
public abstract class Leveable {

	
	/**
	 * Valor m�ximo que puede tomar la experiencia.
	 */
	private static final int EXP_MAXIMA = 2000;

	/**
	 * Valor m�ximo que puede tomar el nivel.
	 */
	
	private static final int NIVEL_MAXIMO = 100;

	/**
	 * Aumenta el nivel.
	 */
	public abstract void aumentarNivel();
	
	/**
	 * Si el nivel est� por debajo del m�ximo aumenta su expericiencia.
	 * Si la experiencia llega al tope m�ximo, aumenta de nivel.
	 * 
	 * @param exp
	 * 			experiencia a aumentar
	 * @return true si sube de nivel sino false
	 */
	public abstract void aumentarExp(int exp);
	

	public static int getExpMaxima() {
		return EXP_MAXIMA;
	}
	public static int getNivelMaximo() {
		return NIVEL_MAXIMO;
	}
}

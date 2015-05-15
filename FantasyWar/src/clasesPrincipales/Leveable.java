package clasesPrincipales;

/**
 * Interfaz que se aplicar� a toda clase que tenga experiencia y nivel.
 * @author Emanuel Galv�n Fontalba
 *
 */
public abstract class Leveable {
	/**
	 * Experiencia.
	 */
	private int exp;
	
	/**
	 * Valor m�ximo que puede tomar la experiencia.
	 */
	private static final int EXP_MAXIMA = 2000;
	/**
	 * Nivel.
	 */
	private int nivel;
	/**
	 * Valor m�ximo que puede tomar el nivel.
	 */
	
	private static final int NIVEL_MAXIMO = 100;

	
	public abstract void aumentarNivel();
	
	/**
	 * Si el nivel est� por debajo del m�ximo aumenta su expericiencia.
	 * Si la experiencia llega al tope m�ximo, aumenta de nivel.
	 * 
	 * @param exp
	 * 			experiencia a aumentar
	 * @return true si sube de nivel sino false
	 */
	public boolean aumentarExp(int exp){
		if(getNivel()<getNivelMaximo()){
			setExp(getExp()+exp);
			if (getExp()>getExpMaxima()){
				aumentarNivel();
				setExp(-(getExpMaxima()-getExp()));
				return true;
			}
		}
		return false;
	}
	
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	public static int getExpMaxima() {
		return EXP_MAXIMA;
	}
	public static int getNivelMaximo() {
		return NIVEL_MAXIMO;
	}
}

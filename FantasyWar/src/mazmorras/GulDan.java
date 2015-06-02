package mazmorras;

import clasesPrincipales.Guerrero;
import clasesPrincipales.Mago;
import clasesPrincipales.NombreInvalidoException;
import clasesPrincipales.Razas;
import clasesPrincipales.Sacerdote;

public class GulDan {
	private static Guerrero esbirro1;
	private static Guerrero esbirro2;
	private static Mago esbirro3;
	private static Sacerdote esbirro4;
	private static Guerrero boss;
	
	public void inicializarMazmorra(){
		try {
			setEsbirro1(new Guerrero("Esbirro", Razas.DEMONIO));
			setEsbirro2(new Guerrero("Mongolin", Razas.DEMONIO));
			setEsbirro3(new Mago("Aprendiz", Razas.ORCO));
			setEsbirro4(new Sacerdote("Padre", Razas.HUMANO));
			setBoss(new Guerrero("Guldan", Razas.ORCO));
			
			while(getEsbirro1().getNivel()<5){
				getEsbirro1().aumentarNivel();
				getEsbirro2().aumentarNivel();
				getEsbirro3().aumentarNivel();
				getEsbirro4().aumentarNivel();
			}
			
			while(getBoss().getNivel()<15){
				boss.aumentarNivel();
			}
		} catch (NombreInvalidoException e) {
			System.out.println(e.getMessage());
		}
	}

	public static Guerrero getEsbirro1() {
		return esbirro1;
	}

	private static void setEsbirro1(Guerrero esbirro1) {
		GulDan.esbirro1 = esbirro1;
	}

	public static Guerrero getEsbirro2() {
		return esbirro2;
	}

	private static void setEsbirro2(Guerrero esbirro2) {
		GulDan.esbirro2 = esbirro2;
	}

	public static Mago getEsbirro3() {
		return esbirro3;
	}

	private static void setEsbirro3(Mago esbirro3) {
		GulDan.esbirro3 = esbirro3;
	}

	public static Sacerdote getEsbirro4() {
		return esbirro4;
	}

	private static void setEsbirro4(Sacerdote esbirro4) {
		GulDan.esbirro4 = esbirro4;
	}

	public static Guerrero getBoss() {
		return boss;
	}

	private static void setBoss(Guerrero boss) {
		GulDan.boss = boss;
	}
}

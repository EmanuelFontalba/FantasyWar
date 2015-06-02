package mazmorras;

import clasesPrincipales.Guerrero;
import clasesPrincipales.Mago;
import clasesPrincipales.NombreInvalidoException;
import clasesPrincipales.Razas;
import clasesPrincipales.Sacerdote;

public class GulDan extends Mazmorra{	
	GulDan(){
		inicializarMazmorra();
	}
	
	/**
	 * Inicializa la mazmorra.
	 */
	@Override
	void inicializarMazmorra(){
		try {
			setNombre("Gul´Dan");
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
				getBoss().aumentarNivel();
			}
		} catch (NombreInvalidoException e) {
			System.out.println(e.getMessage());
		}
	}
}

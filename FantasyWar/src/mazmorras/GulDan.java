package mazmorras;

import clasesPrincipales.Guerrero;
import clasesPrincipales.Mago;
import clasesPrincipales.NombreInvalidoException;
import clasesPrincipales.Razas;
import clasesPrincipales.Sacerdote;

public class GulDan extends Mazmorra{	
	public GulDan(){
		super();
		inicializarMazmorra();
	}
	
	/**
	 * Inicializa la mazmorra.
	 */
	@Override
	void inicializarMazmorra(){
		try {
			//Establecemos el nombre
			setNombre("Gul´Dan");
			//Definimos los monstruos
			setEsbirro1(new Guerrero("Esbirro", Razas.DEMONIO));
			setEsbirro2(new Guerrero("Mongolin", Razas.DEMONIO));
			setEsbirro3(new Mago("Aprendiz", Razas.ORCO));
			setEsbirro4(new Sacerdote("Padre", Razas.HUMANO));
			setBoss(new Guerrero("Guldan", Razas.ORCO));
			//Establecemos el nivel de los esbirros
			while(getEsbirro1().getNivel()<5){
				getEsbirro1().aumentarNivel();
				getEsbirro2().aumentarNivel();
				getEsbirro3().aumentarNivel();
				getEsbirro4().aumentarNivel();
			}
			//Establecemos el nivel del boss
			while(getBoss().getNivel()<15){
				getBoss().aumentarNivel();
			}
			//Añadimos los monstruos creados a la colection.
			annadeMonstruos();
			
		} catch (NombreInvalidoException e) {
			System.out.println(e.getMessage());
		}
	}
}

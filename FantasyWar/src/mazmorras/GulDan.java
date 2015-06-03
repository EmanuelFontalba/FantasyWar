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
			Guerrero esbirro1 = new Guerrero("Esbirro", Razas.DEMONIO);
			Guerrero esbirro2 = new Guerrero("Mongolin", Razas.DEMONIO);
			Mago esbirro3 = new Mago("Aprendiz", Razas.ORCO);
			Sacerdote esbirro4 = new Sacerdote("Padre", Razas.HUMANO);
			Guerrero boss = new Guerrero("Guldan", Razas.ORCO);
			//Establecemos el nivel de los esbirros
			
			for(int i=0; i<5; i++)
				esbirro1.aumentarNivel();
			for(int i=0; i<10; i++)
				esbirro2.aumentarNivel();
			for(int i=0; i<15; i++)
				esbirro3.aumentarNivel();
			for(int i=0; i<20; i++)
				esbirro4.aumentarNivel();
			for(int i=0; i<30; i++)
				boss.aumentarNivel();
			esbirro1.reestablecerse();
			esbirro2.reestablecerse();
			esbirro3.reestablecerse();
			esbirro4.reestablecerse();
			boss.reestablecerse();

			getMazmorra().add(esbirro1);
			getMazmorra().add(esbirro2);
			getMazmorra().add(esbirro3);
			getMazmorra().add(esbirro4);
			getMazmorra().add(boss);

			System.out.println(esbirro1.getSaludActual());
			System.out.println(esbirro1.getNivel());			
		} catch (NombreInvalidoException e) {
			System.out.println(e.getMessage());
		}
	}
}

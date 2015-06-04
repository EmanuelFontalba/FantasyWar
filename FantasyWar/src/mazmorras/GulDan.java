package mazmorras;

import clasesPrincipales.Guerrero;
import clasesPrincipales.Mago;
import clasesPrincipales.NombreInvalidoException;
import clasesPrincipales.Razas;
import clasesPrincipales.Sacerdote;
/**
 * Mazmorra Gul´Dan.
 * @author Emanuel Galván Fontalba
 *
 */
public class GulDan extends Mazmorra{	
	public GulDan(){
		super();
		try {
			inicializarMazmorra("Gul´Dan",new Guerrero("Esbirro", Razas.DEMONIO),new Guerrero("Mongolin", Razas.DEMONIO),
					new Mago("Aprendiz", Razas.ORCO),new Sacerdote("Padre", Razas.HUMANO),new Guerrero("Guldan", Razas.ORCO),
					4,9,14,19,29);
		} catch (NombreInvalidoException e) {
			e.printStackTrace();
		}
	}
}

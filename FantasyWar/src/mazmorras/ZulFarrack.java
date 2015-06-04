package mazmorras;

import clasesPrincipales.Guerrero;
import clasesPrincipales.Mago;
import clasesPrincipales.NombreInvalidoException;
import clasesPrincipales.Razas;
import clasesPrincipales.Sacerdote;

public class ZulFarrack extends Mazmorra {
	public ZulFarrack() {
		super();
		try {
			inicializarMazmorra("Zul´Farrack", new Mago("Xima", Razas.ORCO),new Guerrero("Cuix", Razas.ORCO),
					new Mago("Miic", Razas.ORCO),new Sacerdote("Huitlte", Razas.ORCO),new Guerrero("Farrack", Razas.ORCO),
					20,25,30,35,45);
		} catch (NombreInvalidoException e) {
			e.printStackTrace();
		}
	}
}

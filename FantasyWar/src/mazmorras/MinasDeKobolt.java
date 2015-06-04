package mazmorras;

import clasesPrincipales.Guerrero;
import clasesPrincipales.Mago;
import clasesPrincipales.NombreInvalidoException;
import clasesPrincipales.Razas;
import clasesPrincipales.Sacerdote;

public class MinasDeKobolt extends Mazmorra {
	public MinasDeKobolt() {
		super();
		try {
			inicializarMazmorra("Minas de Kobolt",new Guerrero("Minero", Razas.ENANO),new Mago("Grargel", Razas.NOMUERTO),
					new Mago("Garan", Razas.ORCO), new Sacerdote("Irarg", Razas.DEMONIO), new Guerrero("Kobolt", Razas.ELFO),
					14,19,24,29,39);
		} catch (NombreInvalidoException e) {
			e.printStackTrace();
		}
	}
}

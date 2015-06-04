package mazmorras;

import clasesPrincipales.Guerrero;
import clasesPrincipales.Mago;
import clasesPrincipales.NombreInvalidoException;
import clasesPrincipales.Razas;
import clasesPrincipales.Sacerdote;

public class PrisionDeElwin extends Mazmorra {
	public PrisionDeElwin() {
		super();
		try {
			inicializarMazmorra("Prisión de Elwin", new Guerrero("Carcelero", Razas.HUMANO),new Sacerdote("Corrupto", Razas.NOMUERTO),
					new Mago("Cabum", Razas.ELFO),new Guerrero("Baldur", Razas.DEMONIO),new Sacerdote("Draguil", Razas.DEMONIO),
					9,14,19,24,34);
		} catch (NombreInvalidoException e) {
			e.printStackTrace();
		}
	}
}

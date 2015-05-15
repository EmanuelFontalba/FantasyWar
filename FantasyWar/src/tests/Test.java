package tests;

import Monstruos.Guerrero;
import enumeraciones.Razas;
import excepciones.NombreInvalidoException;

public class Test {
	public static void main(String [] args){
		float saludFloat = (float) (5*0.5);
		int salud = (int) Math.round(5*0.5);
		System.out.println(saludFloat);
		System.out.println(salud);
		try {
			Guerrero m1 = new Guerrero("Lollalalalalalalallalalalala",Razas.DEMONIO);
			
		} catch (NombreInvalidoException e) {
			System.out.println(e.getMessage());
		}
		
	}
}

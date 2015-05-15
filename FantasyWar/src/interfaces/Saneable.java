package interfaces;

import excepciones.FeInsuficienteException;

public interface Saneable {
	int energiaDivina() throws FeInsuficienteException;
	void curacionAncestral() throws FeInsuficienteException;
	void escudoDivino() throws FeInsuficienteException;
	void curacionAbsoluta() throws FeInsuficienteException;
	void regeneracionFe();
}

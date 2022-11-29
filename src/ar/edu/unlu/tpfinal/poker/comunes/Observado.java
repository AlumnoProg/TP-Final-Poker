package ar.edu.unlu.tpfinal.poker.comunes;

import ar.edu.unlu.tpfinal.poker.modelo.Informe;

public interface Observado {

	void agregarObservador(Observer o);
    void notificarObservers(Informe informe);
	
}

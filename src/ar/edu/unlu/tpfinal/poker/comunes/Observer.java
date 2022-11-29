package ar.edu.unlu.tpfinal.poker.comunes;

import ar.edu.unlu.tpfinal.poker.modelo.Informe;

public interface Observer {

	void update(Observado observado, Informe informe);
	
}

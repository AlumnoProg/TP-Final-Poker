package ar.edu.unlu.tpfinal.poker.controlador;

import java.util.LinkedList;

import ar.edu.unlu.tpfinal.poker.comunes.Observado;
import ar.edu.unlu.tpfinal.poker.comunes.Observer;
import ar.edu.unlu.tpfinal.poker.modelo.Informe;
import ar.edu.unlu.tpfinal.poker.modelo.Jugador;
import ar.edu.unlu.tpfinal.poker.modelo.Mesa;
import ar.edu.unlu.tpfinal.poker.vista.VistaConsola;

public class Controlador implements Observer{
	
	private VistaConsola vista;
	private Mesa mesa;
	
	public Controlador(VistaConsola vista, Mesa mesa) {
		this.vista = vista;
		this.mesa = mesa;
	}

	@Override
	public void update(Observado observado, Informe informe) {
		switch(informe) {
			case JUGADOR_MANO:
				vista.mostrarJugadorMano(((Mesa)observado).obtenerJugadorMano());
			break;
			case CARTAS_REPARTIDAS:
				vista.mostrarCartasJugador(((Mesa)observado).getJugadoresMesa());
				vista.mostrarMenuApuestas();
			break;
			case CANT_JUGADORES_INSUFICIENTES:
				vista.informarJugadoresInsuficientes();
			break;
			case CANT_JUGADORES_EXCEDIDOS:
				vista.informarCantJugadoresExcedidos();
			break;
		}
	}

	public LinkedList<Jugador> getJugadoresMesa(){
		return mesa.getJugadoresMesa();
	}
	
	public LinkedList<Jugador> getListaOrdenadaJugadorMano() {
		return mesa.devolverJugadorEntregaCarta();
	}

	public void enviarApuestas(int apuesta, Jugador j) {
		mesa.apuestaJugador(apuesta, j);
	}
	
}

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
			case APUESTA_MAYOR:
				vista.igualarDemasJugadoresApuestaMayor(((Mesa)observado).getJugadoresMesa());
			break;
			case APUESTA_IGUAL:
				vista.apuestaIgualada(((Mesa)observado).getJugadoresMesa());
			break;
			case APUESTA_MENOR:
				vista.eliminarJugador(((Mesa)observado).getJugadoresMesa());
			break;
			case DEVOLVER_GANADOR:
				vista.mostrarGanador(((Mesa)observado).devolverGanador());
			break;
		}
	}

	public void agregarJugador(Jugador j) {
		mesa.agregarJugador(j);
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
	
	public void setearFondoApuestas(int fondo) {
		mesa.setFondoApuesta(fondo);
	}
	
	public LinkedList<Jugador> obtenerJugadores(){
		return mesa.getJugadoresMesa();
	}
	
	public void iniciarGame() {
		mesa.iniciarJuego();
	}
	
	public void igualarDemasJugadoresApuestaMayor() {
		
	}
	
}

package ar.edu.unlu.tpfinal.poker.modelo;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

import ar.edu.unlu.tpfinal.poker.comunes.Observado;
import ar.edu.unlu.tpfinal.poker.comunes.Observer;

public class Mesa implements Observado{
	
	private LinkedList<Jugador> jugadoresMesa = new LinkedList<Jugador>();
	private Dealer dealer = new Dealer();
	private LinkedList<Observer> listaObservadores = new LinkedList<Observer>();
	private LinkedList<Jugador> jugadoresApuesta = new LinkedList<Jugador>();
	private int posJugadorMano;
	private int fondoApuesta;
	private int apuestaAnterior = 0;
	
	@Override
	public void agregarObservador(Observer o) {
		this.listaObservadores.add(o);
	}
	@Override
	public void notificarObservers(Informe informe) {
		this.listaObservadores.forEach(t -> t.update(this, informe));
	}
	
	public void agregarJugador(Jugador jugador) {
		if (this.jugadoresMesa.size() < 8) {
			this.jugadoresMesa.add(jugador);
		} else {
			this.notificarObservers(Informe.CANT_JUGADORES_EXCEDIDOS);
		}
	}
	
	public Jugador obtenerJugadorMano() {
		return this.jugadoresMesa.get(this.posJugadorMano);
	}
	
	public void iniciarJuego() {
		if (this.jugadoresMesa.size() > 1) {
			this.dealer.setearCartasRonda();
			this.posJugadorMano = this.seleccionarJugadorRandom();
			this.notificarObservers(Informe.JUGADOR_MANO);
			this.setearFondoApuestas();
			this.dealer.repartirCartasRonda(this.jugadoresMesa, this.posJugadorMano);
			this.notificarObservers(Informe.CARTAS_REPARTIDAS);
		} else {
			this.notificarObservers(Informe.CANT_JUGADORES_INSUFICIENTES);
		}
	}
	
	
	public LinkedList<Jugador> getJugadoresMesa() {
		return jugadoresMesa;
	}
	private void setearFondoApuestas() {
		for (Jugador j : this.jugadoresMesa) {
			j.setApuestaDisponible(this.fondoApuesta);
		}
	}
	
	private int seleccionarJugadorRandom() {
		return (int) (Math.random() * this.jugadoresMesa.size());
	}
	
	
	public int getPosJugadorMano() {
		return posJugadorMano;
	}
	public int getFondoApuesta() {
		return fondoApuesta;
	}
	public void setFondoApuesta(int fondoApuesta) {
		this.fondoApuesta = fondoApuesta;
	}
	
	public LinkedList<Jugador> devolverJugadorEntregaCarta() {
		int i = 0;
		Jugador jugadorActual;
		int jugManoAux = this.posJugadorMano;
		LinkedList<Jugador> listaOrdenada = new LinkedList<Jugador>();
		while (i < this.jugadoresMesa.size()) {
			jugadorActual = this.jugadoresMesa.get(jugManoAux);
			jugManoAux++;
			jugManoAux = jugManoAux %this.jugadoresMesa.size(); 
			listaOrdenada.add(jugadorActual);
			i++;
		}
		return listaOrdenada;
	}
	
	private Informe revisarApuesta(int apuesta) {
		if (apuesta > this.apuestaAnterior) {
			this.apuestaAnterior = apuesta;
			return Informe.APUESTA_MAYOR;
		}
		if (apuesta == this.apuestaAnterior) {
			this.apuestaAnterior = apuesta;
			return Informe.APUESTA_IGUAL;
		}
		return Informe.APUESTA_MENOR;
	}
	
	private int buscarApuestaMayor() {
		this.jugadoresApuesta.sort(Comparator.comparing(Jugador :: getApuesta));
		return this.jugadoresApuesta.getLast().getApuesta();
	}
	
	private boolean verificarApuestasIguales() {
		int apuestaMayor = buscarApuestaMayor();
		for (Jugador j : this.jugadoresApuesta) {
			if (j.getApuesta() != apuestaMayor) {
				return false;
			}
		}
		return true;
	}
	
	public void apuestaJugador(int apuesta, Jugador j) {
		this.notificarObservers(revisarApuesta(apuesta));
		
		this.jugadoresApuesta.add(j);
	}

}

package ar.edu.unlu.tpfinal.poker.modelo;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

import ar.edu.unlu.tpfinal.poker.comunes.Observado;
import ar.edu.unlu.tpfinal.poker.comunes.Observer;

public class Mesa implements Observado{
	
	private LinkedList<Jugador> jugadoresMesa = new LinkedList<Jugador>();
	private LinkedList<Observer> listaObservadores = new LinkedList<Observer>();
	//private LinkedList<Jugador> jugadoresApuesta = new LinkedList<Jugador>();
	private static HashMap<String, Integer> valorCarta= new HashMap<String, Integer>();
	private int posJugadorMano;
	//private int fondoApuesta;
	//private int apuestaAnterior = 0;
	//private int posoDeApuestas = 0;
	
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

			Dealer dealer = new Dealer();
			dealer.setearCartasRonda();
			this.posJugadorMano = this.seleccionarJugadorRandom();
			this.notificarObservers(Informe.JUGADOR_MANO);
			//this.setearFondoApuestas();
			dealer.repartirCartasRonda(this.jugadoresMesa, this.posJugadorMano);
			this.notificarObservers(Informe.CARTAS_REPARTIDAS);
			this.notificarObservers(Informe.DEVOLVER_GANADOR);
			for (Jugador j : this.jugadoresMesa) {
				j.resetearCartas();
			}
		} else {
			this.notificarObservers(Informe.CANT_JUGADORES_INSUFICIENTES);
		}
	}
	
	
	public LinkedList<Jugador> getJugadoresMesa() {
		return jugadoresMesa;
	}
	
	/**public void setFondoApuesta(int fondo) {
		this.fondoApuesta = fondo;
	}
	
	private void setearFondoApuestas() {
		for (Jugador j : this.jugadoresMesa) {
			j.setApuestaDisponible(this.fondoApuesta);
		}
	}*/
	
	private int seleccionarJugadorRandom() {
		return (int) (Math.random() * this.jugadoresMesa.size());
	}
	
	
	public int getPosJugadorMano() {
		return posJugadorMano;
	}
	/**
	public int getFondoApuesta() {
		return fondoApuesta;
	}*/
	
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
	
	/**private Informe revisarApuesta(int apuesta) {
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
	
	public void apuestaJugador(int apuesta, Jugador j) {
		j.realizarApuesta(apuesta);
		this.fondoApuesta += apuesta;
		this.jugadoresApuesta.add(j);
		//if (apuesta == 0) {
			//eliminarJugadoresQueNoApostaron(j);
		//}
		this.notificarObservers(revisarApuesta(apuesta));
	}
	
	private void eliminarJugadoresQueNoApostaron(Jugador j) {
		for (Jugador ja : this.jugadoresApuesta) {
			if (ja.equals(j)) {
				this.jugadoresApuesta.remove(ja);
			}
		}
	}*/
	
	private void calcularResultadoJugadores() {
		for (Jugador j : this.jugadoresMesa) {
			j.calcularValorCartas();
		}
	}
	
	public LinkedList<Jugador> devolverGanador(){
		calcularResultadoJugadores();
		LinkedList<Jugador> ganador = new LinkedList<Jugador>();
		ganador.add(this.jugadoresMesa.getFirst());
		Jugador jugadorActual = null;
		Jugador jugadorAux = null;
		for (int i = 1; i < this.jugadoresMesa.size(); i++) {
			jugadorActual = this.jugadoresMesa.get(i);
			if (jugadorActual.getResultadoValoresCartas().ordinal() > ganador.getFirst().getResultadoValoresCartas().ordinal()) {
				ganador.clear();
				ganador.add(jugadorActual);
			} else if (jugadorActual.getResultadoValoresCartas().ordinal() == ganador.getFirst().getResultadoValoresCartas().ordinal()) {
				jugadorAux = buscarCartaMayor(ganador.getFirst(), jugadorActual);
				if (jugadorAux.equals(ganador.getFirst())) {
					//Gana el anterior
				} else if (jugadorAux.equals(jugadorActual)) {
					ganador.clear();
					ganador.add(jugadorActual);
				} else {
					ganador.add(jugadorActual);
				}
			}
		}
		return ganador;
	}
	
	private Jugador buscarCartaMayor(Jugador jugador1, Jugador jugador2) {
		Carta carta1 = jugador1.getCartasOrdenadas().getLast();
		Carta carta2 = jugador2.getCartasOrdenadas().getLast();
		LinkedList <Carta> cartasAlta = new LinkedList<Carta>();
		cartasAlta.add(carta1);
		cartasAlta.add(carta2);
		ResultadoJugadaJugador r = new ResultadoJugadaJugador();
		Carta cartaMasAlta = r.cartaMasAlta(cartasAlta);
		if (cartaMasAlta.equals(carta1)) {
			return jugador1;
		} else if (cartaMasAlta.equals(carta2)){
			return jugador2;
		} 
		return null;
	}
	
	

}

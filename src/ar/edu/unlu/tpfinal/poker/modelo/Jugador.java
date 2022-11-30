package ar.edu.unlu.tpfinal.poker.modelo;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

public class Jugador {

	private LinkedList<Carta> cartas = new LinkedList<Carta>();
	private LinkedList<Carta> cartas2 = new LinkedList<Carta>();
	//private static HashMap<String, Integer> valorCarta= new HashMap<String, Integer>();
	private int apuesta;
	private int apuestaDisponible;
	private String nombre;
	//private int cantCartas = 0;
	private Resultado resultadoValoresCartas;
	
	public void setApuestaDisponible(int apuestaDisponible) {
		this.apuestaDisponible = apuestaDisponible;
	}

	private Resultado resultadoMano;
	
	public Jugador(String nombre) {
		this.nombre = nombre;
		this.apuesta = 0;
		//this.cantCartas = 0;
	}
	
	public Jugador(String nombre, int apuestaDisponible) {
		this.nombre = nombre;
		this.apuestaDisponible = apuestaDisponible;
		this.apuesta = 0;
		//this.cantCartas = 0;
	}

	public LinkedList<Carta> getCartas() {
		return cartas;
	}
	
	
	public boolean descartarCarta(Carta c) {
		if (buscarCarta(c)) {
			this.cartas.remove(c);
			return true;
		}
		return false;
	}
	
	private boolean buscarCarta(Carta c) {
		for (Carta car : this.cartas) {
			if ((c.getValor().equals(car.getValor())) && (c.getPalo().equals(car.getPalo()))) {
				return true;
			}
		}
		return false;
	}

	public int getApuesta() {
		return apuesta;
	}

	public String getNombre() {
		return nombre;
	}
	
	public int getApuestaDisponible() {
		return apuestaDisponible;
	}
	
	public void realizarApuesta(int cant) {
		if (this.apuestaDisponible >= cant) {
			this.apuesta += cant;
			this.apuestaDisponible -= cant;
		}
	}

	public void recibirCarta(Carta carta) {
		this.cartas.add(carta);
	}
	
	public void calcularValorCartas() {
		ResultadoJugadaJugador jugada = new ResultadoJugadaJugador();
		LinkedList<Carta> cartasAux = this.cartas;
		this.resultadoValoresCartas = jugada.devolverValor(cartasAux);
	}
	
	public LinkedList<Carta> getCartasOrdenadas(){
		ResultadoJugadaJugador r = new ResultadoJugadaJugador();
		return r.ordenarCartas(this.cartas);
	}
	

	public Resultado getResultadoValoresCartas() {
		return resultadoValoresCartas;
	}

}

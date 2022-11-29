package ar.edu.unlu.tpfinal.poker.modelo;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

public class Jugador {

	private LinkedList<Carta> cartas = new LinkedList<Carta>();
	private LinkedList<Carta> cartas2 = new LinkedList<Carta>();
	private static HashMap<String, Integer> valorCarta= new HashMap<String, Integer>();
	private int apuesta;
	private int apuestaDisponible;
	private String nombre;
	private int cantCartas = 0;
	public void setApuestaDisponible(int apuestaDisponible) {
		this.apuestaDisponible = apuestaDisponible;
	}

	private Resultado resultadoMano;
	
	public Jugador(String nombre) {
		this.nombre = nombre;
		this.apuesta = 0;
		this.cantCartas = 0;
	}
	
	public Jugador(String nombre, int apuestaDisponible) {
		this.nombre = nombre;
		this.apuestaDisponible = apuestaDisponible;
		this.apuesta = 0;
		this.cantCartas = 0;
	}

	public LinkedList<Carta> getCartas() {
		return cartas;
	}
	
	public void mostrarCartas() {
		System.out.println("Cartas: ");
		System.out.println("");
		for (Carta c : this.getCartas()) {
			System.out.println(c.getValor() + " de " + c.getPalo());
			System.out.println("");
		}
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
		if (this.cantCartas < 6) {
			this.cartas2.add(carta);
			this.cantCartas ++;
		} else {
			this.cantCartas = 0;
			this.cartas = ordenarCartas(this.cartas2);
		}
	}
	
	public Resultado calcularValorCartas() {
		ResultadoJugadaJugador jugada = new ResultadoJugadaJugador();;
		return jugada.devolverValor(this.cartas);
	}
	
	
	public int calcularResultado() {
		ResultadoJugadaJugador jugada = new ResultadoJugadaJugador();
		Resultado resultadoJugada;
		resultadoJugada = jugada.devolverValor(this.cartas);
		this.resultadoMano = resultadoJugada;
		if (resultadoJugada == Resultado.CARTA_MAYOR) {
			return 0;
		} else if(resultadoJugada == Resultado.PAR) {
			return 1;
		} else if (resultadoJugada == Resultado.DOBLE_PAR) {
			return 2;
		} else if (resultadoJugada == Resultado.TRIO) {
			return 3;
		} else if (resultadoJugada == Resultado.ESCALERA) {
			return 4;
		} else if (resultadoJugada == Resultado.COLOR) {
			return 5;
		} else if (resultadoJugada == Resultado.FULL) {
			return 6;
		} else if(resultadoJugada == Resultado.POKER) {
			return 7;
		} else {
			return 8;
		}
	}
	
	private LinkedList<Carta> ordenarCartas(LinkedList<Carta> cartas){
		cartas.sort(Comparator.comparing(carta -> valorCarta.get(carta.getValor())));
		return cartas;
	}

	public Resultado getResultadoMano() {
		return resultadoMano;
	}

}

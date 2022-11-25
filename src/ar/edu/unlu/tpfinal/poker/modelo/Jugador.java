package ar.edu.unlu.tpfinal.poker.modelo;

import java.util.LinkedList;

public class Jugador {

	private LinkedList<Carta> cartas = new LinkedList<Carta>();
	private LinkedList<Carta> cartas2 = new LinkedList<Carta>();
	private int envite;
	private int enviteDisponible;
	private String nombre;
	private int cantCartas;
	private Carta cart;
	
	public Jugador(String nombre, int enviteDisponible) {
		this.nombre = nombre;
		this.enviteDisponible = enviteDisponible;
		this.envite = 0;
		this.cantCartas = 0;
	}

	public LinkedList<Carta> getCartas() {
		return cartas;
	}

	public int getEnvite() {
		return envite;
	}

	public String getNombre() {
		return nombre;
	}
	
	public int getEnviteDisponible() {
		return enviteDisponible;
	}
	
	public void realizarEnvite(int cant) {
		if (this.enviteDisponible >= cant) {
			this.envite += cant;
		}
	}

	public void recibirCarta(Carta carta) {
		if (this.cantCartas < 5) {
			this.cartas2.add(carta);
		} else {
			this.cartas2.add(carta);
			this.cantCartas = 0;
			this.cartas = ordenarCartas();
		}
	}
	
	
	public Resultado calcularValorCartas() {
		ResultadoJugadaJugador jugada = new ResultadoJugadaJugador();
		return jugada.devolverValor(this.cartas);
	}
	
	private LinkedList<Carta> ordenarCartas() {
		Carta[] ingresoCartas = inicializarVector();
		for (Carta c : this.cartas2) {
			ingresoCartas[this.obtenerPosicion(c)] = c;
		}
		
		for(int i = 0; i <= 12; i++) {
			if (ingresoCartas[i].getValor() != "0") {
				this.cartas.add(ingresoCartas[i]);
			}
		}
		
		return this.cartas;
	}
	
	private Carta[] inicializarVector() {
		Carta[] resultado = new Carta[12];
		Carta c = new Carta("0", "0");
		for (int i = 0; i <= 12; i++) {
			resultado[i] = c;
		}
		return resultado;
	}
	
	private int obtenerPosicion(Carta carta) {
		String[] orden = cart.getOrdenCartas();
		for (int i = 0; i <= 12; i++) {
			if (orden[i] == carta.getValor()) {
				return i;
			}
		}
		return -1;
	}

}

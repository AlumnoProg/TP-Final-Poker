package ar.edu.unlu.tpfinal.poker.modelo;

import java.util.LinkedList;

public class Dealer {

	private LinkedList<Carta> cartasTotales = new LinkedList<Carta>();
	private LinkedList<Carta> cartas;
	
	public Dealer() {
		this.setearCartasTotales();
		this.setearCartasRonda();
	}
	
	private Carta repartirCarta() {
		int indice = (int) (Math.random()* ((this.cartas.size() - 1)));
		Carta cartaResultado;
		cartaResultado = this.cartas.get(indice);
		this.cartas.remove(indice);
		return cartaResultado;
	}
	
	public void repartirCartasRonda(LinkedList<Jugador> jugadores, int posJugadorMano) {
		Jugador jugadorActual;
		for (int j = 0; j < 5; j++) {
			int i = 0;
			while (i < jugadores.size()) {
				jugadorActual = jugadores.get(posJugadorMano);
				if (jugadorActual.getCartas().size() < 5) {
					jugadorActual.recibirCarta(this.repartirCarta());
				}
				posJugadorMano++;
				posJugadorMano = posJugadorMano %jugadores.size(); 
				i++;
			}
		}
	}
	
	public LinkedList<Jugador> primerJugadorRepartir(LinkedList<Jugador> jugadoresAux) {
		int indice = (int) (Math.random()* (jugadoresAux.size() - 1));
		LinkedList<Jugador> jugadoresSeleccionados = jugadoresAux;
		Jugador primerJugador = jugadoresSeleccionados.get(indice);
		jugadoresSeleccionados.remove(indice);
		jugadoresSeleccionados.add(0, primerJugador);
		return jugadoresSeleccionados;
	}
	
	public void setearCartasRonda() {
		int i = 0;
		this.cartas  = new LinkedList<Carta>();
		for (Carta c : this.cartasTotales) {
			this.cartas.add(c);
		}
	}

	private void setearCartasTotales() {
		Carta asPica = new Carta("AS", "PICA");
		Carta asCorazon = new Carta("AS", "CORAZON");
		Carta asDiamante = new Carta("AS", "DIAMANTE");
		Carta asTrebol = new Carta("AS", "TREBOL");
		Carta dosPica = new Carta("2", "PICA");
		Carta dosCorazon = new Carta("2", "CORAZON");
		Carta dosDiamante = new Carta("2", "DIAMANTE");
		Carta dosTrebol = new Carta("2", "TREBOL");
		Carta tresPica = new Carta("3", "PICA");
		Carta tresCorazon = new Carta("3", "CORAZON");
		Carta tresDiamante = new Carta("3", "DIAMANTE");
		Carta tresTrebol = new Carta("3", "TREBOL");
		Carta cuatroPica = new Carta("4", "PICA");
		Carta cuatroCorazon = new Carta("4", "CORAZON");
		Carta cuatroDiamante = new Carta("4", "DIAMANTE");
		Carta cuatroTrebol = new Carta("4", "TREBOL");
		Carta cincoPica = new Carta("5", "PICA");
		Carta cincoCorazon = new Carta("5", "CORAZON");
		Carta cincoDiamante = new Carta("5", "DIAMANTE");
		Carta cincoTrebol = new Carta("5", "TREBOL");
		Carta seisPica = new Carta("6", "PICA");
		Carta seisCorazon = new Carta("6", "CORAZON");
		Carta seisDiamante = new Carta("6", "DIAMANTE");
		Carta seisTrebol = new Carta("6", "TREBOL");
		Carta sietePica = new Carta("7", "PICA");
		Carta sieteCorazon = new Carta("7", "CORAZON");
		Carta sieteDiamante = new Carta("7", "DIAMANTE");
		Carta sieteTrebol = new Carta("7", "TREBOL");
		Carta ochoPica = new Carta("8", "PICA");
		Carta ochoCorazon = new Carta("8", "CORAZON");
		Carta ochoDiamante = new Carta("8", "DIAMANTE");
		Carta ochoTrebol = new Carta("8", "TREBOL");
		Carta nuevePica = new Carta("9", "PICA");
		Carta nueveCorazon = new Carta("9", "CORAZON");
		Carta nueveDiamante = new Carta("9", "DIAMANTE");
		Carta nueveTrebol = new Carta("9", "TREBOL");
		Carta diezPica = new Carta("10", "PICA");
		Carta diezCorazon = new Carta("10", "CORAZON");
		Carta diezDiamante = new Carta("10", "DIAMANTE");
		Carta diezTrebol = new Carta("10", "TREBOL");
		Carta jotaPica = new Carta("J", "PICA");
		Carta jotaCorazon = new Carta("J", "CORAZON");
		Carta jotaDiamante = new Carta("J", "DIAMANTE");
		Carta jotaTrebol = new Carta("J", "TREBOL");
		Carta quPica = new Carta("Q", "PICA");
		Carta quCorazon = new Carta("Q", "CORAZON");
		Carta quDiamante = new Carta("Q", "DIAMANTE");
		Carta quTrebol = new Carta("Q", "TREBOL");
		Carta kaPica = new Carta("K", "PICA");
		Carta kaCorazon = new Carta("K", "CORAZON");
		Carta kaDiamante = new Carta("K", "DIAMANTE");
		Carta kaTrebol = new Carta("K", "TREBOL");
		this.cartasTotales.add(asPica);
		this.cartasTotales.add(asCorazon);
		this.cartasTotales.add(asDiamante);
		this.cartasTotales.add(asTrebol);
		this.cartasTotales.add(dosPica);
		this.cartasTotales.add(dosCorazon);
		this.cartasTotales.add(dosDiamante);
		this.cartasTotales.add(dosTrebol);
		this.cartasTotales.add(tresPica);
		this.cartasTotales.add(tresCorazon);
		this.cartasTotales.add(tresDiamante);
		this.cartasTotales.add(tresTrebol);
		this.cartasTotales.add(cuatroPica);
		this.cartasTotales.add(cuatroCorazon);
		this.cartasTotales.add(cuatroDiamante);
		this.cartasTotales.add(cuatroTrebol);
		this.cartasTotales.add(cincoPica);
		this.cartasTotales.add(cincoCorazon);
		this.cartasTotales.add(cincoDiamante);
		this.cartasTotales.add(cincoTrebol);
		this.cartasTotales.add(seisPica);
		this.cartasTotales.add(seisCorazon);
		this.cartasTotales.add(seisDiamante);
		this.cartasTotales.add(seisTrebol);
		this.cartasTotales.add(sietePica);
		this.cartasTotales.add(sieteCorazon);
		this.cartasTotales.add(sieteDiamante);
		this.cartasTotales.add(sieteTrebol);
		this.cartasTotales.add(ochoPica);
		this.cartasTotales.add(ochoCorazon);
		this.cartasTotales.add(ochoDiamante);
		this.cartasTotales.add(ochoTrebol);
		this.cartasTotales.add(nuevePica);
		this.cartasTotales.add(nueveCorazon);
		this.cartasTotales.add(nueveDiamante);
		this.cartasTotales.add(nueveTrebol);
		this.cartasTotales.add(diezPica);
		this.cartasTotales.add(diezCorazon);
		this.cartasTotales.add(diezDiamante);
		this.cartasTotales.add(diezTrebol);
		this.cartasTotales.add(jotaPica);
		this.cartasTotales.add(jotaCorazon);
		this.cartasTotales.add(jotaDiamante);
		this.cartasTotales.add(jotaTrebol);
		this.cartasTotales.add(quPica);
		this.cartasTotales.add(quCorazon);
		this.cartasTotales.add(quDiamante);
		this.cartasTotales.add(quTrebol);
		this.cartasTotales.add(kaPica);
		this.cartasTotales.add(kaCorazon);
		this.cartasTotales.add(kaDiamante);
		this.cartasTotales.add(kaTrebol);
	}

	public LinkedList<Carta> getCartas() {
		return cartas;
	}
	
}

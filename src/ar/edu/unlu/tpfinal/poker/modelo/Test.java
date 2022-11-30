package ar.edu.unlu.tpfinal.poker.modelo;

import java.util.LinkedList;

public class Test {
	
	public static void main(String[] args) {
		Jugador j = new Jugador("pepe");
		Jugador j2 = new Jugador("paco");
		ResultadoJugadaJugador r = new ResultadoJugadaJugador();
		LinkedList<Jugador> l = new LinkedList<Jugador>();
		l.add(j);
		l.add(j2);
		Dealer d = new Dealer();
		d.repartirCartasRonda(l, 0);
		for (Jugador ja : l) {
			System.out.println("Jugador: " + ja.getNombre());// + " resultado: " + ja.getResultadoValoresCartas());
			for (Carta c : ja.getCartas()) {
				System.out.println(c.getValor() + " de " + c.getPalo());
			}
			System.out.println("");
		}
		
			for (Jugador jas : l) {
				System.out.println(r.devolverValor(jas.getCartas()));
			}
		
		
		for (Jugador ja : l) {
			System.out.println("Jugador: " + ja.getNombre());// + " resultado: " + ja.getResultadoValoresCartas());
			for (Carta c : ja.getCartas()) {
				System.out.println(c.getValor() + " de " + c.getPalo());
			}
			System.out.println("");
		}
		
		/**System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		
		for (Jugador ja : l) {
			System.out.println("Jugador: " + ja.getNombre());// + " resultado: " + ja.getResultadoValoresCartas());
			for (Carta c : ja.getCartas()) {
				System.out.println(c.getValor() + " de " + c.getPalo());
			}
			System.out.println("");
		}
		
		for (Jugador ja : l) {
			ja.calcularValorCartas();
		}
		
		/**Carta c1 = new Carta("2", "CORAZON");
		Carta c2 = new Carta("4", "DIAMANTE");
		Carta c3 = new Carta("6", "TREBOL");
		Carta c4 = new Carta("3", "CORAZON");
		Carta c5 = new Carta("5", "PICA");
		LinkedList<Carta> cart = new LinkedList<Carta>();
		cart.add(c1);
		cart.add(c2);
		cart.add(c3);
		cart.add(c4);
		cart.add(c5);
		
		for (Carta c : cart) {
			System.out.println(c.getValor() + " de " + c.getPalo());
		}
		
		System.out.println(r.devolverValor(cart));
		
		for (Carta c : cart) {
			System.out.println(c.getValor() + " de " + c.getPalo());
		}
	*/	
	}

}

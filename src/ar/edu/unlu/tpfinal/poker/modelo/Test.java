package ar.edu.unlu.tpfinal.poker.modelo;

import java.util.LinkedList;

public class Test {

	public static void main(String[] args) {
		
		ResultadoJugadaJugador j = new ResultadoJugadaJugador();
		
		LinkedList<Carta> cartas = new LinkedList<Carta>();
		
		Carta c1 = new Carta("J", "PICA");
		Carta c2 = new Carta("10", "PICA");
		Carta c3 = new Carta("Q", "PICA");
		Carta c4 = new Carta("K", "DIAMANTE");
		Carta c5 = new Carta("AS", "PICA");

		cartas.add(c1);
		cartas.add(c2);
		cartas.add(c3);
		cartas.add(c4);
		cartas.add(c5);
		
		//EscaleraColor

		System.out.println(j.escaleraColor(cartas));
		
		/**ProbarEscalera
		LinkedList<Carta> cartasOrdenadas = j.ordenarCartas(cartas);
		for(Carta c : cartasOrdenadas) {
			System.out.println(c.getValor());
		}
		System.out.println(j.escalera(cartas));
		
		//ProbarCartaMasAlta
		System.out.println(j.cartaMasAlta(cartas).getValor() + " " + j.cartaMasAlta(cartas).getPalo());
		//FaltaProbarLetras
		
		//ProbarPar
		try {
		System.out.println(j.cartaPar(cartas).getValor());
		} catch(Exception e){
			System.out.println("Carga nula");
		}
		//FaltaProbarLetras
		
		//ProbarDoblePar
		System.out.println(j.doblePar(cartas));
		//FaltaProbarLetras
		
		//ProbarTrio
		System.out.println(j.trio(cartas));
		//FaltaProbarLetras
		
		//ProbarColor
		System.out.println(j.color(cartas));
		
		//ProbarFull
		System.out.println(j.full(cartas));
		
		//ProbarPoker
		System.out.println(j.poker(cartas));
		*/
	}

}

package ar.edu.unlu.tpfinal.poker.modelo;

import java.util.LinkedList;

public class Test {

	public static void main(String[] args) {
		
		ResultadoJugadaJugador j = new ResultadoJugadaJugador();
		
		LinkedList<Carta> cartas = new LinkedList<Carta>();
		
		Carta c1 = new Carta("2", "DIAMANTE");
		Carta c2 = new Carta("5", "PICA");
		Carta c3 = new Carta("2", "PICA");
		Carta c4 = new Carta("3", "PICA");
		Carta c5 = new Carta("5", "PICA");

		cartas.add(c1);
		cartas.add(c2);
		cartas.add(c3);
		cartas.add(c4);
		cartas.add(c5);
		
		//ProbarCartaMasAlta
		//System.out.println(j.cartaMasAlta(cartas).getValor() + " " + j.cartaMasAlta(cartas).getPalo());
		//FaltaProbarLetras
		
		//ProbarPar
		//System.out.println(j.cartaPar(cartas).getValor());
		//FaltaProbarLetras
		
		//ProbarDoblePar
		//System.out.println(j.doblePar(cartas));
		//FaltaProbarLetras
		
		//ProbarTrio
		//System.out.println(j.trio(cartas));
		//FaltaProbarLetras
		
		//ProbarEscalera
		/**LinkedList<Carta> cartasOrdenadas = j.ordenarCartas(cartas);
		for(Carta c : cartasOrdenadas) {
			System.out.println(c.getValor());
		}
		System.out.println(j.escalera(cartas));*/
		
		//DA MAL
		
		//ProbarColor
		//System.out.println(j.color(cartas));
		
		//ProbarFull
		System.out.println(j.full(cartas));
		
		//ProbarPoker
		//System.out.println(j.poker(cartas));
		
	}

}

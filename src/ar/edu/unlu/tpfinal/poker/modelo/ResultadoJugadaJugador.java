package ar.edu.unlu.tpfinal.poker.modelo;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

public class ResultadoJugadaJugador {
	private Carta cart = new Carta("10", "CORAZON");
	private static HashMap<String, Integer> valorCarta= new HashMap<String, Integer>();
	
	public ResultadoJugadaJugador() {
		valorCarta.put("2", 2);
		valorCarta.put("3", 3);
		valorCarta.put("4", 4);
		valorCarta.put("5", 5);
		valorCarta.put("6", 6);
		valorCarta.put("7", 7);
		valorCarta.put("8", 8);
		valorCarta.put("9", 9);
		valorCarta.put("10", 10);
		valorCarta.put("J", 11);
		valorCarta.put("Q", 12);
		valorCarta.put("K", 13);
		valorCarta.put("AS", 14);
	}

	public Resultado devolverValor(LinkedList<Carta> cartas) {
		if (escaleraColor(cartas) == true) {
			return Resultado.ESCALERA_COLOR;	
		} else if(poker(cartas) == true){
			return Resultado.POKER;
		} else if (full(cartas) == true) {
			return Resultado.FULL;
		} else if (color(cartas) == true) {
			return Resultado.COLOR;
		} else if (escalera(cartas) == true) {
			return Resultado.ESCALERA;
		} else if(trio(cartas) == true) {
			return Resultado.TRIO;
		} else if (doblePar(cartas) == true) {
			return Resultado.DOBLE_PAR;
		} else if(par(cartas) == true) {
			return Resultado.PAR;
		} else {
			return Resultado.CARTA_MAYOR;
		}
	}
	
	
	public boolean color(LinkedList<Carta> cartas) {
		cart = cartas.getFirst();
		String paloCarta = cart.getPalo(); 
		for (Carta c : cartas) {
			//SiPaloDeCartaDistinto
			if (!(c.getPalo().equals(paloCarta))) {
				return false;
			}
		}
		return true;
	}
	
	public LinkedList<Carta> ordenarCartas(LinkedList<Carta> cartas){
		cartas.sort(Comparator.comparing(carta -> valorCarta.get(carta.getValor())));
		return cartas;
	}
	
	public boolean escaleraColor(LinkedList<Carta> cartas) {
		return ((escalera(cartas)) && (color(cartas)));
	}
	
	public boolean escalera(LinkedList<Carta> cartas) {
		LinkedList<Carta> cartasOrdenadas = this.ordenarCartas(cartas);
		boolean flagAS = false;
		int cartaAnt = valorCarta.get(cartasOrdenadas.getFirst().getValor());
		if (cartasOrdenadas.getLast().getValor().equals("AS")) {
			flagAS = true;
		}
		if (cartaAnt == 2) {
			for(int i = 1; i < cartasOrdenadas.size() - 1; i++) {
				cartaAnt++;
				if(cartaAnt != valorCarta.get((cartasOrdenadas.get(i).getValor()))) {
					return false;
				}
			}
		} else {
			for(int i = 1; i < cartasOrdenadas.size(); i++) {
				cartaAnt++;
				if(cartaAnt != valorCarta.get((cartasOrdenadas.get(i).getValor()))) {
					return false;
				}
			}
		}
		return true;
	}
	
	public int contarRepeticiones(LinkedList<Carta> cartas, Carta carta) {
		int cant = 0;
		for (Carta c : cartas) {
			if (c.getValor() == carta.getValor()) {
				cant ++;
			}
		}
		return cant;
	}
	
	public boolean par(LinkedList<Carta> cartas) {
		for (Carta c : cartas) {
			if (contarRepeticiones(cartas, c) == 2) {
				return true;
			}
		}
		return false;
	}
	
	public boolean trio(LinkedList<Carta> cartas) {
		for (Carta c : cartas) {
			if (contarRepeticiones(cartas, c) == 3) {
				return true;
			}
		}
		return false;
	}
	
	public boolean poker(LinkedList<Carta> cartas) {
		for (Carta c : cartas) {
			if (contarRepeticiones(cartas, c) == 4) {
				return true;
			}
		}
		return false;
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
	
	public Carta mayorDeDosCartas(Carta c1, Carta c2) {
		if (valorCarta.get(c1.getValor()) > valorCarta.get(c2.getValor())) {
			return c1;
		} else if (valorCarta.get(c1.getValor()) < valorCarta.get(c2.getValor())) {
			return c2;
		} else {
			//EnCasoDeSerIguales
			return null;
		}
	}
	
	public Carta cartaMasAlta(LinkedList<Carta> cartas) {
		LinkedList<Carta> cartasOrdenadas = this.ordenarCartas(cartas);
		return cartasOrdenadas.getLast();
	}
	
	public Carta cartaPar(LinkedList<Carta> cartas) {
		Carta cartaPar = null;
		for (Carta c : cartas) {
			if (contarRepeticiones(cartas, c) == 2) {
				cartaPar = c;
			}
		}
		return cartaPar;
	}
	
	private boolean cartaDoblePar(LinkedList<Carta> cartas, Carta cartaPar) {
		for (Carta c : cartas) {
			if (c.getValor() != cartaPar.getValor() && c.getPalo() != cartaPar.getPalo()) {
				if (contarRepeticiones(cartas, c) == 2) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean doblePar(LinkedList<Carta> cartas) {
		LinkedList<Carta> cartasAux = cartas;
		Carta cartaPar = cartaPar(cartasAux);
		if (cartaPar != null) {
			if (cartaDoblePar(cartasAux, cartaPar)){
				return true;
			}
		}
		return false;
	}
	
	private Carta cartaTrio(LinkedList<Carta> cartas) {
		Carta cartaTrio = null;
		for (Carta c : cartas) {
			if (contarRepeticiones(cartas, c) == 3) {
				cartaTrio = c;
			}
		}
		return cartaTrio;
	}
	
	private Carta buscarCartaTrio(Carta carta, LinkedList<Carta> cartas) {
		for (Carta c : cartas) {
			if (carta.getValor() == c.getValor()) {
				return c;
			}
		}
		return null;
	}
	
	public boolean full(LinkedList<Carta> cartas) {
		LinkedList<Carta> cartasAux = cartas;
		if (trio(cartasAux)) {
			Carta trioFull = cartaTrio(cartasAux);
			cartasAux.remove(trioFull);
			Carta c = buscarCartaTrio(trioFull, cartasAux);
			if (c != null) {
				cartasAux.remove(c);
				if (par(cartasAux)) {
					return true;
				}
			}
		}
		return false;
	}

}

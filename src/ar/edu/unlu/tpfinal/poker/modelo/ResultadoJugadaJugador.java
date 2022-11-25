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
		boolean flag = true;
		cart = cartas.getFirst();
		String paloCarta = cart.getPalo(); 
		for (Carta c : cartas) {
			//SiPaloDeCartaDistinto
			if (!(c.getPalo().equals(paloCarta))) {
				flag = false;
			}
		}
		return flag;
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
		boolean flag = false;
		for (Carta c : cartas) {
			if (contarRepeticiones(cartas, c) == 2) {
				flag = true;
			}
		}
		return flag;
	}
	
	public boolean trio(LinkedList<Carta> cartas) {
		boolean flag = false;
		for (Carta c : cartas) {
			if (contarRepeticiones(cartas, c) == 3) {
				flag = true;
			}
		}
		return flag;
	}
	
	public boolean poker(LinkedList<Carta> cartas) {
		boolean flag = false;
		for (Carta c : cartas) {
			if (contarRepeticiones(cartas, c) == 4) {
				flag = true;
			}
		}
		return flag;
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
	
	
	public Carta cartaMasAlta(LinkedList<Carta> cartas) {
		int mayor = obtenerPosicion(cartas.getFirst());
		Carta cartaMayor = null;
		int actual;
		Carta cartaActual = null;
		for (Carta c : cartas) {
			actual = obtenerPosicion(c);
			cartaActual = c;
			if (actual == 0) {
				mayor = actual;
				cartaMayor = cartaActual;
			} else if (actual > mayor && mayor != 0) {
				mayor = actual;
				cartaMayor = cartaActual;
			}
		}
		
		return cartaMayor;
		
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
	
	public boolean doblePar(LinkedList<Carta> cartas) {
		LinkedList<Carta> cartasAux = cartas;
		Carta primerPar = cartaPar(cartasAux);
		if (primerPar != null) {
			cartasAux.remove(primerPar);
			if (par(cartasAux)) {
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
	
	public boolean full(LinkedList<Carta> cartas) {
		LinkedList<Carta> cartasAux = cartas;
		Carta trioFull = cartaTrio(cartasAux);
		if (trioFull != null) {
			cartasAux.remove(trioFull);
			cartasAux.remove(trioFull);
			if (par(cartasAux)) {
				return true;
			}
		}
		return false;
	}

}

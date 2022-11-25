package ar.edu.unlu.tpfinal.poker.modelo;

import java.util.LinkedList;

public class ResultadoJugadaJugador {
	
	private Carta cart = new Carta("10", "CORAZON");

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
	
	private void setearVector(Carta[] vector) {
		for (int i = 0; i < vector.length; i++) {
			vector[i] = null;
		}
	}
	
	private int buscarPosicionCarta(Carta carta) {
		String [] ordenCartas = carta.getOrdenCartas();
		for (int i = 0; i < ordenCartas.length; i++) {
			if (carta.getValor() == ordenCartas[i]) {
				return i;
			}
		}
		return -1;
	}
	
	private int proximaPosicion(Carta[] vector, int posicion) {
		while (vector[posicion] != null) {
			posicion ++;
		}
		return posicion;
	}
	
	public LinkedList<Carta> ordenarCartas(LinkedList<Carta> cartas){
		Carta[] vector = new Carta[50];
		this.setearVector(vector);
		LinkedList<Carta> cartasOrdenadas = new LinkedList<Carta>();
		for (Carta c : cartas) {
			if (vector[buscarPosicionCarta(c)] != null) {
				vector[buscarPosicionCarta(c)] = c;
			} else {
				vector[proximaPosicion(vector, buscarPosicionCarta(c))] = c;
			}
		}
		for (int i = 0; i < vector.length; i++) {
			if (vector[i] != null) {
				cartasOrdenadas.add(vector[i]);
			}
		}
		return cartasOrdenadas;
	}
	
	public boolean escaleraColor(LinkedList<Carta> cartas) {
		Carta c = cartas.get(1);
		if (escalera(cartas) == true) {
			for (Carta car : cartas) {
				if (car.getPalo() != c.getPalo()) {
					return false;
				}
			}
		} else {
			return false;
		}
		return true;
	}
	
	public boolean escalera(LinkedList<Carta> cartas) {
		LinkedList<Carta> cartasOrdenadas = this.ordenarCartas(cartas);
		String [] vectorOrdenCartas = cart.getOrdenCartas();
		//MirarEnCasoDeQueSeaAs
		if (cartasOrdenadas.get(1).getValor() == "A" && cartasOrdenadas.get(2).getValor() == "10") {
			for (int i = 3; i <= cartasOrdenadas.size(); i++) {
				if (cartasOrdenadas.get(i).getValor() != vectorOrdenCartas[i - 1]) {
					return false;
				}
			}
		} else if(cartasOrdenadas.get(1).getValor() == "A" && cartasOrdenadas.get(2).getValor() != "10") {
			for (int i = 2; i <= cartasOrdenadas.size(); i++) {
				if (cartasOrdenadas.get(i).getValor() != vectorOrdenCartas[i - 1]) {
					return false;
				}
			}
		} else {
			for (int i = 1; i <= cartasOrdenadas.size(); i++) {
				if (cartasOrdenadas.get(i).getValor() != vectorOrdenCartas[i - 1]) {
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

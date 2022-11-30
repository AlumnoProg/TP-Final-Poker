package ar.edu.unlu.tpfinal.poker.vista;

import java.util.LinkedList;
import java.util.Scanner;

import ar.edu.unlu.tpfinal.poker.controlador.Controlador;
import ar.edu.unlu.tpfinal.poker.modelo.Carta;
import ar.edu.unlu.tpfinal.poker.modelo.Jugador;
import ar.edu.unlu.tpfinal.poker.modelo.Mesa;

public class VistaConsola {
	
	private Controlador controlador;
	
	public void inicioJuego() {
		int opcion = -1;
		System.out.println("------------------------------------------------------------------------------");
		System.out.println("--                              POKER                                       --");
		System.out.println("------------------------------------------------------------------------------");
		System.out.println("--                juegue bajo su propio riesgo                              --");
		System.out.println("------------------------------------------------------------------------------");
		//inicializarFondoApuestas();
		while (opcion != 0) {
			opcion = mostrarMenuInicio();
			switch (opcion) {
				case 1:
					crearJugador();
				break;
				case 2:
					mostrarJugadores();
					esperarEnter();
				break;
				case 3:
					controlador.iniciarGame();
					esperarEnter();
				break;
				case 0:
					opcion = 0;
					System.out.println("Se salio del juego exitosamente saludos!");
				break;
			}
		}
		
	}
	
	private void mostrarJugadores() {
		LinkedList<Jugador> lista = controlador.obtenerJugadores();
		System.out.println("------------------------------------");
		System.out.println("Lista de jugadores");
		System.out.println("------------------------------------");
		for (Jugador j : lista) {
			System.out.println(" ");
			System.out.println("Nombre: " + j.getNombre());
		}
	}
	
	private void crearJugador() {
		String nombre;
		System.out.println("Ingrese el nombre de jugador:");
		Scanner sc = new Scanner(System.in);
		nombre = sc.nextLine();
		Jugador j = new Jugador(nombre);
		controlador.agregarJugador(j);
	}
	/**
	private void inicializarFondoApuestas() {
		int fondo;
		System.out.println("Ingrese el fondo con el que empezaran todos los jugadores");
		Scanner sc = new Scanner(System.in);
		fondo = sc.nextInt();
		controlador.setearFondoApuestas(fondo);
	}*/
	
	
	
	
	
	private int mostrarMenuInicio() {
		int opcion = -1;
		while (opcion < 0 || opcion > 3) {
			
			System.out.println("");
			System.out.println("------------------------------------------------------------------------------");
			System.out.println("--                        Menú de Configuración                         --");
			System.out.println("------------------------------------------------------------------------------");
			System.out.println("--                                Opciones                                      --");
			System.out.println("------------------------------------------------------------------------------");
			System.out.println("-- 1 - Agregar jugador              --");
			System.out.println("-- 2 - Mostrar Lista Jugador                                                     --");
			System.out.println("-- 3 - Comenzar Partida                                                   --");
			System.out.println("------------------------------------------------------------------------------");
			System.out.println("-- 0 - Salir del Juego                                                        --");
			System.out.println("------------------------------------------------------------------------------");
			Scanner sc = new Scanner(System.in);
			opcion = sc.nextInt();
		}
		return opcion;
	}
	
	
	
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
	

	public void mostrarJugadorMano(Jugador obtenerJugadorMano) {
		System.out.println("Jugador mano: " + obtenerJugadorMano.getNombre());
	}

	public void mostrarCartasJugador(LinkedList<Jugador> jugadoresMesa) {
		for (Jugador j : jugadoresMesa) {
			System.out.println("");
			System.out.println("Jugador: " + j.getNombre());
			System.out.println("");
			for (Carta c : j.getCartas()) {
				System.out.println(c.getValor() + " de " + c.getPalo());
			}
		}
	}
/**
	public void mostrarMenuApuestas() {
		int opcion = -1;
		while (opcion != 0) {
			opcion = mostrarMenuApuesta();
			switch (opcion) {
				case 1:
					subirApuesta(j);
				break;
				case 2:
					controlador.pasarApuesta
					esperarEnter();
				break;
			}
		}
	}
	
	private 
	
	private int mostrarMenuApuesta() {
		int opcion = -1;
		while (opcion < 0 || opcion > 3) {
			System.out.println("");
			System.out.println("------------------------------------------------------------------------------");
			System.out.println("--                        Menú de Apuestas                         --");
			System.out.println("------------------------------------------------------------------------------");
			System.out.println("-- 1 - Revisar Cartas              --");
			System.out.println("-- 2 - Realizar Apuesta                                                     --");
			System.out.println("-- 3 - PasarApuesta                                                  --");
			System.out.println("------------------------------------------------------------------------------");
			Scanner sc = new Scanner(System.in);
			opcion = sc.nextInt();
		}
		return opcion;
	}*/

	public void informarJugadoresInsuficientes() {
		System.out.println("La cantidad de jugadores es insuficiente para iniciar el juego");
	}

	public void informarCantJugadoresExcedidos() {
		System.out.println("La cantidad de jugadores excede el limite maximo que permite el juego");
	}
	
	
	/**private int apuestas() {
		int a = -1;
		System.out.println("Ingrese lo que desea apostar:");
		Scanner sc = new Scanner(System.in);
		a = sc.nextInt();
		return a;
	}
	
	public void revisarCartasApuesta() {
		int apuestaAnterior = -1;
		//LinkedList<Jugador> jugadoresQueApostaron = new LinkedList<Jugador>();
		LinkedList<Jugador> listaJugador =  controlador.getListaOrdenadaJugadorMano();
		LinkedList<Jugador> listaJugadorAux = listaJugador;
		System.out.println("APUESTAS");
		for(Jugador j : listaJugadorAux) {
			System.out.println("Nombre jugador" + j.getNombre());
			System.out.println("");
			int opcion = -1;
			while (opcion != 3) {
				opcion = mostrarMenuApuesta();
				switch(opcion) {
					case 1:
						mostrarCartasJugador(j);
						esperarEnter();
					break;
					case 2:
						int a = apuestas();
						controlador.enviarApuestas(a, j);
					break;
					case 3:
						opcion = 3;
						listaJugadorAux.remove(j);
						System.out.println("El jugador ha decidido dejar la ronda");
						esperarEnter();
					break;
				}
			}
		}
	}*/
	
	private void mostrarCartasJugador(Jugador j) {
		System.out.println("Jugador: " + j.getNombre());
		for (Carta c : j.getCartas()) {
			System.out.println(c.getValor() + " de " + c.getPalo());
		}
	}
	
	private void esperarEnter() {
		System.out.println("Presione ENTER para continuar...");
		Scanner sc = new Scanner(System.in);
		String pausa = sc.nextLine();
	}

	public void mostrarGanador(LinkedList<Jugador> devolverGanador) {
		for(Jugador j : devolverGanador) {
			System.out.println("Ganador: " + j.getNombre() + " con " + j.getResultadoValoresCartas());
			System.out.println("");
		}
	}

	/**public void igualarDemasJugadoresApuestaMayor(LinkedList<Jugador> jugadoresMesa) {
		int apuestaMayor = buscarApuestaMayor(jugadoresMesa);
		System.out.println("Se ha ingresado una apuesta mayor" + apuestaMayor + " se deben igualar las demas apuestas");
		for (Jugador j : jugadoresMesa) {
			if (j.getApuesta() < apuestaMayor) {
				System.out.println("Jugador " + j.getNombre() + " aumente su apuesta a " + apuestaMayor + " o pase");
				int opcion = mostrarMenuPase();
			}
		}
	}
	
	private int buscarApuestaMayor(LinkedList<Jugador> jugadoresMesa) {
		int apuestaMayor = 0;
		for (Jugador j : jugadoresMesa) {
			if (j.getApuesta() >= apuestaMayor) {
				apuestaMayor = j.getApuesta();
			}
		}
		return apuestaMayor;
	}
	
	private int mostrarMenuPase() {
		int opcion = -1;
		while (opcion < 0 || opcion > 3) {
			System.out.println("");
			System.out.println("------------------------------------------------------------------------------");
			System.out.println("-- 1 - Subir apuesta              --");
			System.out.println("-- 2 - Pasar                                                     --");
			System.out.println("------------------------------------------------------------------------------");
			Scanner sc = new Scanner(System.in);
			opcion = sc.nextInt();
		}
		return opcion;
	}*/
		
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


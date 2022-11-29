package ar.edu.unlu.tpfinal.poker.vista;

import java.util.LinkedList;
import java.util.Scanner;

import ar.edu.unlu.tpfinal.poker.controlador.Controlador;
import ar.edu.unlu.tpfinal.poker.modelo.Carta;
import ar.edu.unlu.tpfinal.poker.modelo.Jugador;

public class VistaConsola {
	
	private Controlador controlador;
	
	/**
	private void iniciar() throws Exception{
		
		int opcion = -1;
		System.out.println("------------------------------------------------------------------------------");
		System.out.println("--                              POKER                                       --");
		System.out.println("------------------------------------------------------------------------------");
		System.out.println("--                juegue bajo su propio riesgo                              --");
		System.out.println("------------------------------------------------------------------------------");
		inicializarJuego();
		while (opcion != 0) {
			opcion = mostrarMenuInicio();
			switch (opcion) {
				case 1:
					agregarJugador();
					
				break;
				case 2:
					
				break;
				case 3:
					
				break;
				case 0:
					opcion = 0;
					System.out.println("Se salio del juego exitosamente saludos!");
				break;
			}
		}
		
	}
	
	private void inicializarJuego() {
		int fondo;
		System.out.println("Ingrese el fondo con el que empezaran todos los jugadores");
		Scanner sc = new Scanner(System.in);
		fondo = sc.nextInt();
	}
	
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
	
	private void procesoDescarte(LinkedList<Jugador> jugadoresRonda) {
		for (Jugador j : jugadoresRonda) {
			int opcion = -1;
			System.out.println("Jugador: " + j.getNombre());
			j.mostrarCartas();
			while (opcion != 2) {
				opcion = mostrarMenuDescarte();
				switch(opcion) {
					case 1 :
						realizarDescarte(j);
						System.out.println("Cartas nuevas");
						j.mostrarCartas();
					break;
					case 2 :
						opcion = 2;
					break;
				}
			}
			esperarEnter();
		}
	}
	
	private void realizarDescarte(Jugador j) {
		System.out.println("Cartas del jugador:" );
		j.mostrarCartas();
		System.out.println("Indique que cartas desea descartar");
		String op = "Y";
		String numero;
		String palo;
		int cantCartasEliminadas = 0;
		while (op.equals("Y") || cantCartasEliminadas == 5) {
			System.out.println("Ingrese el Numero de la carta que desea eliminar");
			Scanner sc = new Scanner(System.in);
			numero = sc.nextLine();
			System.out.println("Ingrese el palo de la carta que desea eliminar");
			Scanner scc = new Scanner(System.in);
			palo = scc.nextLine();
			Carta c = new Carta(numero, palo);
			if (j.descartarCarta(c)) {
				cantCartasEliminadas++;
				System.out.println("Carta descartada con exito");
			} else {
				System.out.println("Esta carta no se puede descartar");
			}
			System.out.println("Desea continuar descartando cartas (Y/N)");
			Scanner sccc = new Scanner(System.in);
			op = scc.nextLine();
		}
		j.recibirCarta(dealer.repartirCarta());
	}
	
	private int mostrarMenuDescarte() {
		int opcion = -1;
		while (opcion < 0 || opcion > 2) {
			System.out.println("");
			System.out.println("------------------------------------------------------------------------------");
			System.out.println("--                        Menú de Descarte                                  --");
			System.out.println("------------------------------------------------------------------------------");
			System.out.println("-- 1 - Realizar Descarte              --");
			System.out.println("-- 2 - Continuar con las cartas                                             --");
			System.out.println("------------------------------------------------------------------------------");
			Scanner sc = new Scanner(System.in);
			opcion = sc.nextInt();
		}
		return opcion;
	}
	
	private void revisarCartasApuesta(LinkedList<Jugador> listaJugador) {
		int apuestaAnterior = -1;
		int cantidad = -1;
		for (Jugador j : listaJugador) {
			int opcion = -1;
			System.out.println("Jugador: " + j.getNombre());
			System.out.println("");
			while (opcion != 3) {
				opcion = mostrarMenuApuesta();
				switch (opcion) {
					case 1 :
						j.mostrarCartas();
						esperarEnter();
					break;
					case 2 :
						System.out.println("Fondo disponible para apostar: " + j.getApuestaDisponible());
						System.out.println("Ingrese la cantidad de desea apostar: ");
						Scanner sc = new Scanner(System.in);
						cantidad = sc.nextInt();
						while ((cantidad < apuestaAnterior) || (cantidad > j.getApuestaDisponible())) {
							System.out.println("El valor de la apuesta debe ser igual o superior a las demas apuestas, y se debe tener los fondos disponibles");
							Scanner scc = new Scanner(System.in);
							cantidad = scc.nextInt();
						}
						apuestaAnterior = cantidad;
						j.realizarApuesta(cantidad);
					break;
					case 3:
						opcion = 3;
						listaJugador.remove(j);
						System.out.println("El jugador ha decidido dejar la ronda");
						esperarEnter();
					break;
				}
			}
		}
		
		
	}
	
	
	
	private void mostrarJugadores() {
		System.out.println("------------------------------------------------------------------------------");
		System.out.println("--                            Lista de Jugadores                             --");
		System.out.println("------------------------------------------------------------------------------");
		int i = 1;
		for (Jugador j : jugadores) {
			System.out.println(i + " Nombre: " + j.getNombre() + " fondo apuestas: " + j.getApuestaDisponible());
			i++;
			System.out.println("");
		}
	}
	
	private Jugador agregarJugador() {
		String nombre = "";
		int fondoApuesta = -1;
		while (nombre.equals("") && fondoApuesta == -1) {
			System.out.println("Ingrese el nombre del jugador");
			Scanner sc = new Scanner(System.in);
			nombre = sc.nextLine();
			System.out.println("Ingrese el fondo para apostar del jugador");
			Scanner scc = new Scanner(System.in);
			fondoApuesta = scc.nextInt();
			if (nombre.equals("") && fondoApuesta == -1) {
				System.out.println("Error, ingrese correctamente el nombre del jugador y el fondo para apostar");
			}
		}
		Jugador jugador = new Jugador(nombre, fondoApuesta);
		return jugador;
	}*/

	public void mostrarJugadorMano(Jugador obtenerJugadorMano) {
		System.out.println("Jugador mano: " + obtenerJugadorMano.getNombre());
	}

	public void mostrarCartasJugador(LinkedList<Jugador> jugadoresMesa) {
		for (Jugador j : jugadoresMesa) {
			System.out.println("");
			System.out.println("Jugador: " + j.getNombre());
			System.out.println("");
			j.mostrarCartas();
		}
	}

	public void mostrarMenuApuestas() {
		mostrarMenuApuesta();
	}
	
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
	}

	public void informarJugadoresInsuficientes() {
		System.out.println("La cantidad de jugadores es insuficiente para iniciar el juego");
	}

	public void informarCantJugadoresExcedidos() {
		System.out.println("La cantidad de jugadores excede el limite maximo que permite el juego");
	}
	
	
	private int apuestas() {
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
						j.mostrarCartas();
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
	}
	
	private void esperarEnter() {
		System.out.println("Presione ENTER para continuar...");
		Scanner sc = new Scanner(System.in);
		String pausa = sc.nextLine();
	}
		
			
			
			
			
				/**switch (opcion) {
					
					case 2 :
						System.out.println("Fondo disponible para apostar: " + j.getApuestaDisponible());
						System.out.println("Ingrese la cantidad de desea apostar: ");
						Scanner sc = new Scanner(System.in);
						cantidad = sc.nextInt();
						while ((cantidad < apuestaAnterior) || (cantidad > j.getApuestaDisponible())) {
							System.out.println("El valor de la apuesta debe ser igual o superior a las demas apuestas, y se debe tener los fondos disponibles");
							Scanner scc = new Scanner(System.in);
							cantidad = scc.nextInt();
						}
						apuestaAnterior = cantidad;
						j.realizarApuesta(cantidad);
					break;
					*/

			
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


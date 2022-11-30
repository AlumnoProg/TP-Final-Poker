package ar.edu.unlu.tpfinal.poker.todoapp;

import ar.edu.unlu.tpfinal.poker.controlador.Controlador;
import ar.edu.unlu.tpfinal.poker.modelo.Mesa;
import ar.edu.unlu.tpfinal.poker.vista.VistaConsola;

public class Main {

	public static void main(String[] args) {
		VistaConsola vista = new VistaConsola();
		Mesa mesa = new Mesa();
		Controlador controlador = new Controlador(vista, mesa);
		mesa.agregarObservador(controlador);
		vista.setControlador(controlador);
		vista.inicioJuego();
		
		
	}

}

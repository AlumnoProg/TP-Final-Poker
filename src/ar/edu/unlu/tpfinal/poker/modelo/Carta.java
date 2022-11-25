package ar.edu.unlu.tpfinal.poker.modelo;

import java.util.HashMap;

public class Carta {

	private String valor;
	private String palo;
	private String [] ordenCartas = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "AS"};
	
	public Carta(String valor, String palo) {
		this.setPalo(palo);
		this.setValor(valor); 
	}

	public String getValor() {
		return valor;
	}
	

	private void setValor(String valor) {
		this.valor = valor;
	}

	public String getPalo() {
		return palo;
	}

	private void setPalo(String palo) {
		if (palo.equals("PICA") || palo.equals("CORAZON") || palo.equals("TREBOL") || palo.equals("DIAMANTE")) {
		this.palo = palo;
		}
	}

	public String [] getOrdenCartas() {
		return ordenCartas;
	}
	
	

}

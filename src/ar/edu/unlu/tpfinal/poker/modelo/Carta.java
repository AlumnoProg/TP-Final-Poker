package ar.edu.unlu.tpfinal.poker.modelo;

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
		if (valor.equals("2") || valor.equals("3") || valor.equals("4") || valor.equals("5")  || valor.equals("6") || valor.equals("7") ||
				valor.equals("8") || valor.equals("9") || valor.equals("10") || valor.equals("J") || valor.equals("Q") || valor.equals("K") || valor.equals("AS")) {
			this.valor = valor;
		}
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

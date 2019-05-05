package ar.edu.unlam.entidades;

import ar.edu.unlam.graficos.IRender;

public abstract class Entidad implements IRender {

	protected int x;
	protected int y;
	protected int ancho;
	protected int largo;
	protected char simbolo;
	protected boolean esVisible;		
	
	/** Constructores */
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getLargo() {
		return largo;
	}

	public void setLargo(int largo) {
		this.largo = largo;
	}

	public char getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(char simbolo) {
		this.simbolo = simbolo;
	}

	public boolean esVisible() {
		return esVisible;
	}
	
	public void setEsVisible(boolean esVisible) {
		this.esVisible = esVisible;
	}	
}

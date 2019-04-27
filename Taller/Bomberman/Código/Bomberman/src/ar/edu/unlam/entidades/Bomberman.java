package ar.edu.unlam.entidades;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unlam.juego.Juego;
import ar.edu.unlam.mapa.Mapa;
import ar.edu.unlam.input.Teclado;

public class Bomberman extends Entidad {
		
	private List<Bomba> bombas;
	private int puntajeActual;	
	private Teclado input;
	private Mapa mapa;
	
	public Bomberman(int x, int y, Teclado input, Mapa mapa) {
		this.simbolo = '@';
		this.x = x;
		this.y = y;
		this.input = input;
		this.mapa = mapa;
		this.esVisible = true;
		this.puntajeActual = 0;		
		this.bombas = new ArrayList<Bomba>();
		this.añadirBombas(Juego.cantBombas, Juego.tiempoExplosion, Juego.rangoExplosion);
	}
	
	 public void añadirBombas(int cant, double tiempo, int rango) {		
		for (int i = 0; i < cant; i++) {
			this.bombas.add(new Bomba(tiempo, rango));
		}
	}

	@Override
	public void update() {
		controlarInput();
		//System.out.println("");
	}
	
	public void controlarInput() {
		int xd = 0, yd = 0;
		
		if (input.arriba) {
			yd--;
		}
		if (input.abajo) {
			yd++;
		}
		if (input.izquierda) {
			xd--;
		}
		if (input.derecha) {
			xd++;
		}
		
		System.out.println(".");
		if (xd != 0 || yd != 0) {
			moverse(xd, yd);
		}			
	}
	
	public void moverse(int xd, int yd) {		
		if (puedeMoverse(xd, 0)) {
			this.x += xd;
		}
		
		if (puedeMoverse(0, yd)) {
			this.y += yd;
		}		
		
		System.out.println("BOMBERMAN, X: " + this.x + " Y: " + this.y);
	}
	
	public boolean puedeMoverse(int xd, int yd) {
		int x = this.x + xd, y = this.y + yd;		
		
		if (mapa.getEntidad(x, y) != null) {
			return false;
		}
		
		return true;			
	}
	
	public boolean estaVivo() {
		return this.esVisible;
	}
	
}

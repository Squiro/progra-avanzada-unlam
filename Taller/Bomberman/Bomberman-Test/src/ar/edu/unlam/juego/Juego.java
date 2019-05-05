package ar.edu.unlam.juego;

import java.awt.Canvas;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import ar.edu.unlam.input.Teclado;
import ar.edu.unlam.mapa.Mapa;
import ar.edu.unlam.gui.Frame;

public class Juego extends Canvas {

	/** CONSTANTES Y OPCIONES */
	
	public static final double VERSION = 1.9;
	
	public static final int TILES_SIZE = 16,
							WIDTH = TILES_SIZE * (int)(31 / 2), //minus one to ajust the window,
							HEIGHT = 13 * TILES_SIZE;

	public static int SCALE = 3;
	
	
	public static final int cantBombas = 3;	
	public static final int cantObstaculos = 10;
	
	public static double tiempoExplosion = 1.0; 
	public static int rangoExplosion = 1;
	
	private Teclado input;
	private Mapa mapa;
	
	public Juego(Frame frame) {
		this.input = new Teclado();				
		this.mapa = new Mapa(this);
		addKeyListener(input);
	}
	
	/*ublic static void main(String[] args) {			
		Juego juego = new Juego();
		
		while (true) {
			juego.update();
		}
	}*/
	
	public void update() {
		this.input.update();
		this.mapa.update();
	}
	
	/** Getters y Setters */
	
	public Teclado getInput() {
		return input;
	}

	public void setInput(Teclado input) {
		this.input = input;
	}
	
}

package com.emmettbrown.principal;

import com.emmettbrown.entidades.Bomberman;
import com.emmettbrown.entorno.grafico.JVentanaGrafica;
import com.emmettbrown.mapa.Mapa;

public class Motor {

	public static int tileSize = 75;	
	public static final int ANCHO = tileSize*Mapa.ANCHO;
	public static final int ALTO = tileSize*Mapa.ALTO+25;
	
	final int FPS = 30;
	private Mapa miMapa;
	private JVentanaGrafica miVentana;
	private boolean iniciado;

	 
	public Motor() {
		miMapa = new Mapa();
		miMapa.generarMapa();
		//Bomberman propio del usuario conectado.
		Bomberman miBomber = new Bomberman(Motor.tileSize, Motor.tileSize, Bomberman.defaultWidth, Bomberman.defaultHeight); 
		Bomberman miBomber2 = new Bomberman(Motor.tileSize*(Mapa.ANCHO-2), Motor.tileSize*(Mapa.ALTO-2), Bomberman.defaultWidth, Bomberman.defaultHeight); 
		miMapa.agregarBomberman(miBomber);
		miMapa.agregarBomberman(miBomber2);
		miVentana = new JVentanaGrafica(miMapa,ANCHO, ALTO);
	}

	private void iniciarJuego() {
		this.iniciado = true;
		miVentana.setVisible(true);
	}

	private void gameLoop() {
		long initialTime = System.nanoTime();
		//final double timeU = 1000000000 / UPS;
		final double timeF = 1000000000 / FPS;
		double deltaF = 0; // deltaU = 0, 
		//int frames = 0, ticks = 0;

		    while (iniciado) {

		        long currentTime = System.nanoTime();
		        //deltaU += (currentTime - initialTime) / timeU;
		        deltaF += (currentTime - initialTime) / timeF;
		        initialTime = currentTime;

		        /*if (deltaU >= 1) {
		            getInput();
		            update();
		            ticks++;
		            deltaU--;
		        }*/

		        if (deltaF >= 1) {
		            actualizar();
		            //frames++;
		            deltaF--;
		        }
		    }
		}
	
	private void actualizar() {
		miVentana.refrescar();
	}

	public void cerrarJuego() {
		iniciado = false;
	}

	public static void main(String[] args) {
		Motor m = new Motor();
		m.iniciarJuego();
		m.gameLoop();
	}

}

package com.emmettbrown.principal;

import com.emmettbrown.entidades.Bomberman;
import com.emmettbrown.entidades.DefConst;
import com.emmettbrown.entorno.grafico.JVentanaGrafica;
import com.emmettbrown.mapa.Mapa;

public class Motor {

	private Mapa miMapa;
	private JVentanaGrafica miVentana;
	private boolean iniciado;

	
	public Motor() {
		miMapa = new Mapa();
		miMapa.generarMapa();
		//Bomberman propio del usuario conectado.
		Bomberman miBomber = new Bomberman(DefConst.TILESIZE, DefConst.TILESIZE, DefConst.DEFAULTWIDTH,DefConst.DEFAULTHEIGHT); 
		Bomberman miBomber2 = new Bomberman(DefConst.TILESIZE*(DefConst.ANCHOMAPA-2), DefConst.TILESIZE*(DefConst.ANCHOMAPA-2), DefConst.DEFAULTWIDTH,DefConst.DEFAULTHEIGHT); 
		miMapa.agregarBomberman(miBomber);
		miMapa.agregarBomberman(miBomber2);
		miVentana = new JVentanaGrafica(miMapa,DefConst.ANCHO, DefConst.ALTO);
	}

	public void iniciarJuego() {
		this.iniciado = true;
		miVentana.setVisible(true);
	}

	public void gameLoop() {
		long initialTime = System.nanoTime();
		final double timeF = 1000000000 / DefConst.FPS;
		double deltaF = 0; // deltaU = 0, 

		    while (iniciado) {
		    	
		        long currentTime = System.nanoTime();
		        deltaF += (currentTime - initialTime) / timeF;
		        initialTime = currentTime;
		        if (deltaF >= 1) {
		            actualizar();
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

	public void jugar(){
		Motor m = new Motor();
		m.iniciarJuego();
		m.gameLoop();
	}
	
	public static void main(String[] args) {
		Motor m = new Motor();
		m.iniciarJuego();
		m.gameLoop();
	}

}

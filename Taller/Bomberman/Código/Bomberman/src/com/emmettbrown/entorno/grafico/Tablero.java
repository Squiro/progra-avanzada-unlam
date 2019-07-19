package com.emmettbrown.entorno.grafico;

import java.util.HashMap;

public class Tablero {
	
	private HashMap<String, Integer> puntajes; //Recorrer para saber quien gano la partida jugada <Usuario, Puntaje>
	
	public Tablero () {
		this.puntajes = new HashMap<String,Integer>();
	}
	
	public void agregarPuntuacion (String k,Integer v) {
		puntajes.put(k, v);
	}
	
	public HashMap<String, Integer> getPuntajes() {
		return puntajes;
	}
}

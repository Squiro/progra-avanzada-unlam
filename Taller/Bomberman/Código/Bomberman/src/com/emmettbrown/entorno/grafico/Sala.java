package com.emmettbrown.entorno.grafico;

import java.io.Serializable;

public class Sala implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idSala;
	private int idCreador;
	private String nombre;
	private int jugConectados;
	private int limJugadores;
	//private ArrayList<>
	
	public Sala (int id, int idCreador, String nombre, int jugConectados, int limJugadores) {
		this.idSala = id;
		this.idCreador = idCreador;
		this.nombre = nombre;
		this.jugConectados = jugConectados;
		this.limJugadores = limJugadores;
	}

	@Override
	public String toString() {
		return nombre + " ---- Jugadores conectados: " + jugConectados + "/" + limJugadores;
	}
	
	public int getId() {
		return this.idSala;
	}
	
	public int getIdCreador() {
		return this.idCreador;
	}
	
	
}

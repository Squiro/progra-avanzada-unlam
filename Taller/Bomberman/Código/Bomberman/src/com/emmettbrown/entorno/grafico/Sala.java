package com.emmettbrown.entorno.grafico;

import java.util.ArrayList;

public class Sala {

	private int idSala;
	private int idCreador;
	private String nombre;
	private int limJugadores;
	private int jugConectados;	
	private ArrayList<String> usuarios;
	private Tablero tableroPuntos;
	private Reloj reloj;
	private int rondaActual;
	private boolean privada;
	
	public Sala (int id, int idCreador, String nombre, int jugConectados, int limJugadores, boolean privada) {
		this.idSala = id;
		this.idCreador = idCreador;
		this.nombre = nombre;
		this.jugConectados = jugConectados;
		this.limJugadores = limJugadores;
		this.usuarios = new ArrayList<String>();
		this.tableroPuntos = new Tablero();
		this.reloj = new Reloj(00, 00, DefConst.SEG);		
		this.rondaActual = 1;
		this.privada = privada;
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
	
	public void setJugConectados(int jugConectados) {
		this.jugConectados = jugConectados;
	}
	
	public ArrayList<String> getUsuarios() {
		return this.usuarios;
	}
	
	public void agregarUsuario(String cliente) {
		usuarios.add(cliente);
	}
	
	public void setUsuarios(ArrayList<String> usuarios) {
		this.usuarios = usuarios;
	}
	
	public Tablero getTableroPuntos() {
		return tableroPuntos;
	}
	
	public void setRondaActual(int ronda) {
		this.rondaActual = ronda;
	}
	
	public int getRondaActual() {
		return this.rondaActual;
	}
	
	public void agregarPuntaje(String nombre, int puntos) {
		tableroPuntos.agregarPuntuacion(nombre, puntos);
	}
		
	public Reloj getReloj () {
		return reloj;
	}
	
	public void iniciarReloj() {
		this.reloj.startTimer();
	}

	public void setIdCreador(int nueIdCreador) {
		this.idCreador = nueIdCreador;
	}
	
	public boolean esPrivada() {
		return privada;
	}
}

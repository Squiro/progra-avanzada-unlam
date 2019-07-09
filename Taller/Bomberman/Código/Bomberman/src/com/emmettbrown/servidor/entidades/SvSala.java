package com.emmettbrown.servidor.entidades;


import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.emmettbrown.entorno.grafico.DefConst;
import com.emmettbrown.entorno.grafico.Tablero;
import com.emmettbrown.mensajes.cliente.MsgActualizarPuntajes;
import com.emmettbrown.mensajes.cliente.MsgActualizarRonda;
import com.emmettbrown.mensajes.cliente.MsgFinPartida;
import com.emmettbrown.mensajes.cliente.MsgFinRonda;
import com.emmettbrown.mensajes.cliente.MsgIniciarMotor;
import com.emmettbrown.servidor.HiloCliente;
import com.emmettbrown.servidor.mapa.ServerMap;

public class SvSala {

	private int idSala;
	private int idCreador;
	private String nombre;
	private int limJugadores;
	private ArrayList<HiloCliente> clientesConectados;
	private ArrayList<ObjectOutputStream> outputStreams;
	private ArrayList<String> nombresUsuariosConectados;
	private Tablero tablero;
	//El mapa de la sala
	private ServerMap map;
	private SvReloj reloj;
	private int rondaActual;
	private int cantRondas;

	public SvSala(int id, int idCreador, String nombre, int limJugadores) {
		this.idSala = id;
		this.idCreador = idCreador;
		this.nombre = nombre;
		this.limJugadores = limJugadores;
		this.clientesConectados = new ArrayList<HiloCliente>();
		this.nombresUsuariosConectados = new ArrayList<String>();
		this.outputStreams = new ArrayList<ObjectOutputStream>();
		this.tablero = new Tablero();
		this.reloj = new SvReloj(0, 0, DefConst.SEG);
		this.cantRondas = DefConst.MAXROUND;
		this.rondaActual = 1;
	}

	public ArrayList<ObjectOutputStream> getOutputStreams() {
		ArrayList<ObjectOutputStream> obs = new ArrayList<ObjectOutputStream>();
		
		for (HiloCliente hilo : clientesConectados) {
			obs.add(hilo.getOutputStream());
		}
		
		return obs;		
	}
	
	public int getClientesConectadosSize() {
		return this.clientesConectados.size();
	}

	public int getId() {
		return this.idSala;
	}

	public int getIdCreador() {
		return this.idCreador;
	}

	public void agregarUsuario(HiloCliente cliente, String usuario) {
		clientesConectados.add(cliente);
		nombresUsuariosConectados.add(usuario);
		outputStreams.add(cliente.getOutputStream());
	}

	public String getNombre() {
		return nombre;
	}
	
	public Tablero obtenerTablero() {
		return tablero;
	}
	
	public ArrayList<HiloCliente> obtenerHilosSala() {
		return clientesConectados;
	}

	public ArrayList<String> obtenerUsuarios() {
		return nombresUsuariosConectados;
	}
	
	public int getLimJugadores() {
		return this.limJugadores;
	}
	
	public ServerMap getMap() {
		return this.map;
	}
	
	public void setMap(ServerMap map) {
		this.map = map;
	}
	
	public int getRondaActual() {
		return this.rondaActual;
	}
	
	public void iniciarPartida() {
		this.map = new ServerMap();
		this.map.generarMapa();
		
		//Removemos la sala actual
		HiloCliente creador = clientesConectados.get(0);
		creador.eliminarSala(creador.getIdCliente());
		
		//Inicializamos esta ronda
		creador.broadcast(new MsgActualizarRonda(this.rondaActual), outputStreams);
		
		for (HiloCliente hiloCliente : clientesConectados) {
			//Agregamos un usuario al tablero de puntuaciones
			tablero.agregarPuntuacion(hiloCliente.getNombreUsuario(), 0);	
			//Inicializamos al cliente
			hiloCliente.inicializarCliente(map);
			//Inicializamos el motor despues de todo asi le damos la ilusion al cliente de que todo es rapido
			hiloCliente.enviarMsg(new MsgIniciarMotor());
		}

		//Indicamos a cada cliente de la sala que actualice el puntaje
		creador.broadcast(new MsgActualizarPuntajes(tablero.getPuntajes()), outputStreams);	
		reloj.startTimer(this);
	}
	
	public void reiniciarReloj() {
		reloj.startTimer(this);
	}
	
	//Finaliza la ronda anterior y comienza una nueva
	public void finalizarRonda() {
		//Incrementamos en uno la cantidad de rondas
		this.rondaActual++;
		
		HiloCliente creador = this.clientesConectados.get(0); //this.obtenerHilosSala().get(0);
		List<SvBomberman> bombers = this.map.obtenerListaBomberman(); //creador.getMap().obtenerListaBomberman();
		HashMap <String,Integer> puntajes = this.tablero.getPuntajes();				
		
		//Refrescamos los puntajes
		for (SvBomberman bman : bombers) {
			if (bman.estaVivo()) {
				puntajes.put(bman.getNombre(), 1 + puntajes.get(bman.getNombre()));
			}
		}
		
		//Indicamos a cada cliente de la sala que actualice el puntaje
		creador.broadcast(new MsgActualizarPuntajes(puntajes), outputStreams);	
		

		if (rondaActual > cantRondas) {
			creador.broadcast(new MsgFinPartida(), outputStreams);
		} else {
			creador.broadcast(new MsgFinRonda(), outputStreams);
		}			
	}
	
	public void nuevaRonda() {
		//Creamos y generamos un nuevo mapa
		this.map = new ServerMap();
		this.map.generarMapa();
		
		HiloCliente creador = clientesConectados.get(0);		
		creador.broadcast(new MsgActualizarRonda(this.rondaActual), outputStreams);
		
		//Le enviamos a cada cliente el nuevo mapa
		for (HiloCliente hiloCliente : clientesConectados) {
			hiloCliente.getMovementThread().matarHandle();
			hiloCliente.inicializarCliente(map); 			
		}
		
		//Comenzamos el reloj nuevamente
		reiniciarReloj();		
	}
}
package com.emmettbrown.servidor.entidades;


import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.emmettbrown.base.datos.base.Configuracion;
import com.emmettbrown.base.datos.base.GestionBD;
import com.emmettbrown.base.datos.base.Usuario;
import com.emmettbrown.entorno.grafico.DefConst;
import com.emmettbrown.entorno.grafico.Tablero;
import com.emmettbrown.mensajes.cliente.MsgActualizarCreadorSala;
import com.emmettbrown.mensajes.cliente.MsgActualizarPuntajes;
import com.emmettbrown.mensajes.cliente.MsgActualizarRonda;
import com.emmettbrown.mensajes.cliente.MsgEliminarBomberman;
import com.emmettbrown.mensajes.cliente.MsgFinPartida;
import com.emmettbrown.mensajes.cliente.MsgFinRonda;
import com.emmettbrown.mensajes.cliente.MsgIniciarMotor;
import com.emmettbrown.servidor.HiloCliente;
import com.emmettbrown.servidor.SvControlVivos;
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
	private SvControlVivos hiloVivos;
	private GestionBD gestion;
	
	private String password;

	public SvSala(int id, int idCreador, String nombre, int limJugadores, String password) {
		this.idSala = id;
		this.idCreador = idCreador;
		this.nombre = nombre;
		this.limJugadores = limJugadores;
		this.clientesConectados = new ArrayList<HiloCliente>();
		this.nombresUsuariosConectados = new ArrayList<String>();
		this.outputStreams = new ArrayList<ObjectOutputStream>();
		this.tablero = new Tablero();
		this.reloj = new SvReloj(0, 0, DefConst.SEG);
		this.rondaActual = 1;
		this.password = password;
		hiloVivos = new SvControlVivos(this);
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
	
	public String getPassword() {
		return this.password;
	}
	
	public boolean esPrivada() {
		return !password.equals("");
	}
	
	public void removerCliente(HiloCliente hiloCliente) {
		int nuevoIdCliente;
		
		if (map != null)
		{
			map.eliminarBomberman(hiloCliente.getBomber());
			hiloCliente.broadcast(new MsgEliminarBomberman(hiloCliente.getBomber().getIdBomberman()), this.outputStreams);
		}
		clientesConectados.remove(hiloCliente);
		outputStreams.remove(hiloCliente.getOutputStream());
		if(clientesConectados.size() > 0 && idCreador != (nuevoIdCliente = clientesConectados.get(0).getIdCliente())){
			idCreador = nuevoIdCliente;
			hiloCliente.broadcast(new MsgActualizarCreadorSala(nuevoIdCliente), this.outputStreams);
		}

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
		hiloVivos.start();
	}
	
	public void reiniciarReloj() {
		reloj.startTimer(this);
	}
	
	
	//Finaliza la ronda anterior y comienza una nueva
	public void finalizarRonda() {
		int vivos = 0;
		//Incrementamos en uno la cantidad de rondas
		this.rondaActual++;
		HiloCliente creador = this.clientesConectados.get(0); //this.obtenerHilosSala().get(0);
		List<SvBomberman> bombers = this.map.obtenerListaBomberman(); //creador.getMap().obtenerListaBomberman();
		HashMap <String,Integer> puntajes = this.tablero.getPuntajes();				
		for(SvBomberman bman : bombers) {
			if (bman.estaVivo()) {
				vivos++;
			}
		}
		
		//Refrescamos los puntajes en caso de que haya quedado solo un bomber vivo
		if(vivos > 1) {
			for (SvBomberman bman : bombers) {
				if (bman.estaVivo()) {
					puntajes.put(bman.getNombre(), 0 + puntajes.get(bman.getNombre()));
				}
			}
		}else {
			for (SvBomberman bman : bombers) {
				if (bman.estaVivo()) {
					puntajes.put(bman.getNombre(), 1 + puntajes.get(bman.getNombre()));
				}
			}
		}
		
		//Indicamos a cada cliente de la sala que actualice el puntaje
		creador.broadcast(new MsgActualizarPuntajes(puntajes), outputStreams);	
		
		boolean finalizar = false;
		for (String key : puntajes.keySet()) {
			if(puntajes.get(key) == DefConst.PUNTAJEMAXIMO)
				finalizar = true;
		}
		
		
		if (finalizar == true) {
			creador.broadcast(new MsgFinPartida(), outputStreams);
			///aca se computa el puntaje con tablero
			Configuracion configuracion= new Configuracion();
			gestion=new GestionBD(configuracion.getFactory());
			HashMap<String, Integer> puntuacionParaBD=tablero.getPuntajes();
			String usuarioPuntajeMaximo;
			int puntajeMaximo=0;
			
			//guardamos el puntaje maximo:
			for(Map.Entry<String, Integer> entry :puntuacionParaBD.entrySet()) {
				if(entry.getValue() > puntajeMaximo) {
					puntajeMaximo = entry.getValue();
					usuarioPuntajeMaximo = entry.getKey();
				}
			}
			//recorremos el array por si hay empate en cant. de rondas ganadas
			for(Map.Entry<String, Integer> entry :puntuacionParaBD.entrySet()) {
				
				if(entry.getValue() == puntajeMaximo) {
					puntajeMaximo = entry.getValue();
					usuarioPuntajeMaximo = entry.getKey();
					Session s = gestion.getFactory().openSession();
					Transaction t = s.beginTransaction();
					Usuario user = s.get(Usuario.class, usuarioPuntajeMaximo);
					user.setPuntaje(user.getPuntaje()+1);
					s.update(user);
					t.commit();
					s.close();	
				}
			}
		} else {
			creador.broadcast(new MsgFinRonda(), outputStreams);
		}
		
		reloj.stop();
		hiloVivos.matarHilo();
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
		this.hiloVivos = new SvControlVivos(this);
		this.hiloVivos.start();
	}
}
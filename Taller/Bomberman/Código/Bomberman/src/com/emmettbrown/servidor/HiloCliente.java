package com.emmettbrown.servidor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.emmettbrown.base.datos.base.GestionBD;
import com.emmettbrown.entorno.grafico.DefConst;
import com.emmettbrown.mapa.Ubicacion;
import com.emmettbrown.mensajes.Msg;
import com.emmettbrown.mensajes.cliente.MsgAgregarBomberman;
import com.emmettbrown.mensajes.cliente.MsgEliminarSala;
import com.emmettbrown.mensajes.cliente.MsgGenerarObstaculos;
import com.emmettbrown.mensajes.cliente.MsgIdCliente;
import com.emmettbrown.servidor.entidades.SvBomberman;
import com.emmettbrown.servidor.entidades.SvSala;
import com.emmettbrown.servidor.mapa.ServerMap;


public class HiloCliente extends Thread {

	private int idCliente;
	private boolean estaConectado;
	private transient Socket readSocket;
	private transient Socket writeSocket;
	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream;
	//Bomberman relacionado a este cliente
	private SvBomberman bomber;
	//Lista de usuarios conectados (de todo el server)
	//private ArrayList<Socket> usuariosConectados;
	private ArrayList<ObjectOutputStream> usuariosConectados;
	//Thread de movimiento
	private HandleMovement movimiento;	
	//Listado de salas del servidor
	private ArrayList<SvSala> listaSalas;
	//Contador estï¿½tico de ids de los clientes
	private static int idCounter = 0;	
	private SvSala salaConectada;
	private ServerMap map;
	private String nombreUsuario;
	private HashMap<String, Integer> puntajes;
	private GestionBD gestionBD;
	
	public HiloCliente(Socket writeSocket, Socket readSocket, ArrayList<ObjectOutputStream> usuariosConectados, ArrayList<SvSala> salas, GestionBD gestion) {
		this.idCliente = idCounter++;
		this.writeSocket = writeSocket;
		this.readSocket = readSocket;
		this.gestionBD = gestion;
		try {
			this.outputStream = new ObjectOutputStream(new BufferedOutputStream(writeSocket.getOutputStream()));
			//Necesitamos flushear el buffer ni bien creamos el outputStream. De otra forma, al crear el inputStream,
			//va a quedar todo bloqueado para siempre
			outputStream.flush();
			usuariosConectados.add(outputStream);
			this.inputStream = new ObjectInputStream(new BufferedInputStream(readSocket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.usuariosConectados = usuariosConectados;
		this.estaConectado = true;
		this.listaSalas = salas;
		//Le comunicamos al cliente cual es su ID
		enviarMsg(new MsgIdCliente(this.idCliente));
		this.puntajes = new HashMap<>();
	}
	
	public ObjectOutputStream getOutputStream() {
		return this.outputStream;
	}
	
	public Socket getWriteSocket() {
		return this.writeSocket;
	}

	public boolean siEstaConectado() {
		return estaConectado;
	}

	public void setEstaConectado(boolean estaConectado) {
		this.estaConectado = estaConectado;
	}

	public ArrayList<ObjectOutputStream> getUsuariosConectados() {
		return usuariosConectados;
	}
	
	public ServerMap getMap() {
		return this.map;
	}
	
	public HandleMovement getMovementThread() {
		return this.movimiento;
	}
	
	public SvBomberman getBomber() {
		return this.bomber;
	}
	
	public ArrayList<SvSala> getSalas() {
		return this.listaSalas;
	}
	
	public SvSala getSalaConectada() {
		return this.salaConectada;
	}

	public void inicializarCliente(ServerMap map) {
		this.map = map;
		Ubicacion ubic = map.obtenerUbicacionInicio();		
		this.bomber = new SvBomberman(ubic.getPosX()*75, ubic.getPosY()*75, DefConst.DEFAULTWIDTH, DefConst.DEFAULTHEIGHT, this.getNombreUsuario());
		this.movimiento = new HandleMovement(this, salaConectada.getOutputStreams());
		this.movimiento.start();
		
		//Enviamos los obtasculos al cliente actual
		this.enviarMsg(new MsgGenerarObstaculos(map.getObstaculos()));
		//Agregamos el bomber del cliente al mapa
		map.agregarBomberman(bomber);
		//Le decimos a los clientes que añadan el bomber
		this.broadcast(new MsgAgregarBomberman(bomber, idCliente), salaConectada.getOutputStreams());
	}
	
	public void enviarMsg(Msg msg) {
		try {
			outputStream.writeObject(msg);
			outputStream.reset();
			outputStream.flush();
		} catch (Exception e) {
			System.out.println("NO SE PUDO ENVIAR EL MENSAJE EN HILOCLIENTE");
		}
	}
	
	public void broadcast(Msg msg, ArrayList<ObjectOutputStream> usuariosConectados) {		
		this.enviarMsg(msg);	
		for (ObjectOutputStream salidaACliente : usuariosConectados) {
			try {
				if (!salidaACliente.equals(outputStream) ) {
					  salidaACliente.writeObject(msg);
					  salidaACliente.reset(); 
					  salidaACliente.flush();
				}
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}

	@Override
	public void run() {
		try {			
			while (estaConectado) {
				/* Recibo Consulta de cliente */
				Msg msgRecibo = (Msg) inputStream.readObject();
				msgRecibo.realizarAccion(this);
			}

			inputStream.close();
			readSocket.close();
		} catch (IOException | ClassNotFoundException ex) {
			System.out.println("Un mensaje no se recibio correctamente en HiloCliente: " + ex.getMessage());
			desconectarDeSala();
			this.usuariosConectados.remove(outputStream);			
			eliminarSala(this.idCliente);
			this.estaConectado = false;
		}
	}
	
	public void desconectarDeSala() {
		//Si se encuentra en una sala, lo borramos de ahi
		
		if (this.salaConectada != null)
			this.salaConectada.removerCliente(this);
	}

	public void agregarSala(SvSala sala) {
		this.listaSalas.add(sala);
	}	
	
	public void setSalaConectada(SvSala sala) {
		this.salaConectada = sala;
	}
	
	//Elimina una sala tanto del lado servidor como cliente
	public void eliminarSala(int idCreador) {
		Iterator<SvSala> iter = listaSalas.iterator();
		
		while (iter.hasNext()) {
			SvSala sala = iter.next();
			
			if (sala.getIdCreador() == idCreador) {
				broadcast(new MsgEliminarSala(sala.getId()), usuariosConectados);
				iter.remove();
			}
		}
	}
	
	public int getIdCliente() {
		return this.idCliente;
	}

	public void setNombreUsuario(String nombre) {
		nombreUsuario = nombre;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void guardarPuntaje(HashMap<String, Integer> puntajes) {
		this.puntajes = puntajes;
	}

	public HashMap<String, Integer> getPuntajes() {
		return puntajes;
	}

	public GestionBD getGestionBD() {
		// TODO Auto-generated method stub
		return this.gestionBD;
	}
}

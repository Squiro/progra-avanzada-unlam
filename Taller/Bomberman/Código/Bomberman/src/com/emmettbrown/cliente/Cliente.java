package com.emmettbrown.cliente;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.emmettbrown.entidades.Bomberman;
import com.emmettbrown.entorno.grafico.JPanelGrafico;
import com.emmettbrown.entorno.grafico.JVentanaGrafica;
import com.emmettbrown.entorno.grafico.JVentanaInicial;
import com.emmettbrown.entorno.grafico.JVentanaLobby;
import com.emmettbrown.entorno.grafico.Login;
import com.emmettbrown.entorno.grafico.Sala;
import com.emmettbrown.mapa.Mapa;
import com.emmettbrown.mensajes.Msg;
import com.emmettbrown.mensajes.servidor.MsgActualizarNombre;

public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	private String username;
	private String host;
	private String mensajeError;
	private transient Socket readSocket;
	private transient Socket writeSocket;
	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream;
	private Bomberman bomber;
	private Mapa mapa;
	private int idCliente;
	private JVentanaLobby lobbyActual;
	private ConcurrentLinkedQueue<Sala> listaSalas;
	private Sala salaActual;
	private JPanelGrafico panelGrafico;
	private JVentanaGrafica ventanaGrafica;
	private Login pantallaLogin;
	private JVentanaInicial ventanaInicial;

	public Cliente(String ip, int puerto, String username) {
		try {
			this.username = username;
			this.host = ip;
			//Creamos los sockets de escritura y lectura
			this.readSocket = new Socket(host, puerto);
			this.writeSocket = new Socket(host, puerto);
			
			this.outputStream = new ObjectOutputStream(new BufferedOutputStream(writeSocket.getOutputStream()));
			//Necesitamos flushear el buffer ni bien creamos el outputStream. De otra forma, al crear el inputStream,
			//va a quedar todo bloqueado para siempre
			outputStream.flush();
			this.inputStream = new ObjectInputStream(new BufferedInputStream(readSocket.getInputStream()));
			
			this.mapa = new Mapa();
			this.listaSalas = new ConcurrentLinkedQueue<Sala>();

			//Creamos un hilo escucha que se encargará de leer las cosas que se envíen al readSocket
			ListenerThread listener = new ListenerThread(this);
			listener.start();
			//Enviamos un mensaje al servidor para que setee el nombre de usuario del cliente
			enviarMsg(new MsgActualizarNombre(this.username));
		} catch (IOException e) {
			this.mensajeError = "No se encontro ningun servidor al cual conectarse!";
			System.out.println(mensajeError);
		}
	}
	
	public int getIdCliente() {
		return this.idCliente;
	}
	
	public void setIdCliente(int num) {
		this.idCliente = num;
	}
	
	public Bomberman getBomber() {
		return this.bomber;
	}
	
	public void setBomber(Bomberman bomber) {
		this.bomber = bomber;
	}
	
	public void setMapa(Mapa mapa) {
		this.mapa = mapa;
	}
	
	public Mapa getMapa() {
		return this.mapa;
	}	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String obtenerMsgErr() {
		return mensajeError;
	}
		
	public void enviarMsg(Msg consultaAlServidor) {
		try {
			outputStream.writeObject(consultaAlServidor);
			//Reseteamos el outputStream desp de enviar un mensaje
			outputStream.reset();
			outputStream.flush();
		} catch (IOException e) {
			System.out.println("PROBLEMA AL ENVIAR MENSAJE EN CLIENTE " + e);
		}
	}

	public 	Object recibirMsg() {
		Object obj = null;
		try {
			obj = inputStream.readObject();
		} catch (IOException | ClassNotFoundException e) {
			this.mensajeError = "Comunicacion cerrada en recibir msg1. " + e;
			System.out.println(mensajeError);
		}
		return obj;
	}

	public void cerrarComunicacion() {
		try {
			inputStream.close();
			outputStream.close();
		} catch (IOException e) {
			this.mensajeError = "problemas al cerrar comunicacion. " + e;
			System.out.println(mensajeError);
		}
	}

	public ConcurrentLinkedQueue<Sala> getListaSalas() {
		return this.listaSalas;
	}

	public void limpiarSalas() {
		listaSalas.clear();		
	}

	public void setLobby(JVentanaLobby lobby) {
		this.lobbyActual = lobby;
	}
	
	public JVentanaLobby getLobby() {
		return this.lobbyActual;
	}

	public void setPanelGrafico(JPanelGrafico jPanelGrafico) {
		this.panelGrafico = jPanelGrafico;
	}
	
	public JPanelGrafico getPanelGrafico() {
		return this.panelGrafico ;
	}
	
	public void setVentanaGrafica(JVentanaGrafica ventana) {
		this.ventanaGrafica = ventana;
	}
	
	public JVentanaGrafica getVentanaGrafica() {
		return this.ventanaGrafica;
	}
	
	public void setVentanaInicial(JVentanaInicial ventana) {
		this.ventanaInicial = ventana;
	}
	
	public JVentanaInicial getVentanaInicial() {
		return this.ventanaInicial;
	}
	
	public void setSalaActual(Sala sala) {
		this.salaActual = sala;
	}
	
	public Sala getSalaActual() {
		return this.salaActual;
	}

	public void setPantallaLogin(Login login) {
		this.pantallaLogin = login;
	}

	public Login getPantallaLogin() {
		// TODO Auto-generated method stub
		return this.pantallaLogin;
	}
	
}
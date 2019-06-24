package com.emmettbrown.cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

import com.emmettbrown.entidades.Bomberman;
import com.emmettbrown.mapa.Mapa;
import com.emmettbrown.mensajes.Msg;

public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	private String username;
	private String host;
	private String mensajeError;
	private transient Socket clientSocket;
	private Bomberman bomber;
	private Mapa mapa;
	private int nroCliente;

	public Cliente(String ip, int puerto, String username) {
		try {
			this.host = ip;
			this.clientSocket = new Socket(host, puerto);
			this.username = username;
			this.mapa = new Mapa();
		} catch (IOException e) {
			this.mensajeError = "No se encontro ningun servidor al cual conectarse!";
			System.out.println(mensajeError);
		}
	}
	
	public int getNroCliente() {
		return this.nroCliente;
	}
	
	public void setNroCliente(int num) {
		this.nroCliente = num;
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
			ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
			outputStream.writeObject(consultaAlServidor);
		} catch (IOException e) {
			System.out.println("Error al querer enviar peticion al sv " + e);
		}
	}

	public Object recibirMsg() {
		Object obj = null;
		try {
			ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());
			obj = inputStream.readObject();
		} catch (IOException | ClassNotFoundException e) {
			this.mensajeError = "Comunicacion cerrada en recibir msg1. " + e;
			System.out.println(mensajeError);
		}
		return obj;
	}

	public void cerrarComunicacion(ObjectInputStream inputStream, ObjectOutputStream outputStream) {
		try {
			inputStream.close();
			outputStream.close();
		} catch (IOException e) {
			System.out.println(mensajeError);
			this.mensajeError = "problemas al cerrar comunicacion. " + e;
		}
	}

}
package com.emmettbrown.cliente;

import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;

import com.emmettbrown.entidades.Bomberman;
import com.emmettbrown.entorno.grafico.Sala;
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
	private int idCliente;
	private ArrayList<Sala> listaSalas;

	public Cliente(String ip, int puerto, String username) {
		try {
			this.host = ip;
			this.clientSocket = new Socket(host, puerto);
			this.username = username;
			this.mapa = new Mapa();
			this.listaSalas = new ArrayList<Sala>();
			ListenerThread listener = new ListenerThread(this);
			listener.start();
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
			this.mensajeError = "problemas al cerrar comunicacion. " + e;
			System.out.println(mensajeError);
		}
	}

	public ArrayList<Sala> getListaSalas() {
		return this.listaSalas;
	}

}
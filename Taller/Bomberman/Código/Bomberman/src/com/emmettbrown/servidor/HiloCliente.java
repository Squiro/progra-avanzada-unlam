package com.emmettbrown.servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import com.emmettbrown.entidades.DefConst;
import com.emmettbrown.mensajes.Msg;
import com.emmettbrown.mensajes.MsgAgregarBomberman;
import com.emmettbrown.mensajes.MsgEliminarBomberman;
import com.emmettbrown.mensajes.MsgGenerarMurosExteriores;
import com.emmettbrown.mensajes.MsgGenerarObstaculos;
import com.emmettbrown.mensajes.MsgNroCliente;
import com.emmettbrown.servidor.mapa.ServerMap;
import com.emmettbrown.servidor.entidades.*;


public class HiloCliente extends Thread {

	private int nroCliente;
	private SvBomberman bomber;
	private ServerMap map;
	private transient Socket clientSocket;
	private boolean estaConectado;
	private ArrayList<Socket> usuariosConectados;
	private HandleMovement movimiento;
	private static int posX = 1;
	private static int posY = 1;
	private static int fin = 1;
	
	public HiloCliente(Socket cliente, ArrayList<Socket> usuariosConectados, ServerMap map, int nroCliente) {
		this.nroCliente = nroCliente;
		this.map = map;
		this.clientSocket = cliente;
		this.usuariosConectados = usuariosConectados;
		this.estaConectado = true;
		this.bomber = new SvBomberman(posX*75,posY*75, DefConst.DEFAULTWIDTH, DefConst.DEFAULTHEIGHT);
		//System.out.println("El ID del bomber cli: "+bomber.obtenerID());
		this.inicializarCliente();
		
		if (posY == 1 && posX == 7) {
			posY = 7;
			posX = 7;
		}
		if (posY == 7 && posX != 7) {
			posY = 1;
			posX = 7;
			fin = 0;
		}
		if (fin == 1) {
			posY = 7;
		}	
	}

	public Socket getClientSocket() {
		return clientSocket;
	}

	public void setClientSocket(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	public boolean isEstaConectado() {
		return estaConectado;
	}

	public void setEstaConectado(boolean estaConectado) {
		this.estaConectado = estaConectado;
	}

	public ArrayList<Socket> getUsuariosConectados() {
		return usuariosConectados;
	}

	public void setUsuariosConectados(ArrayList<Socket> usuariosConectados) {
		this.usuariosConectados = usuariosConectados;
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

	public void inicializarCliente() {
		this.movimiento = new HandleMovement(this);
		this.movimiento.start();
		enviarMsg(new MsgNroCliente(this.nroCliente));
		//Le enviamos el  mapa al servidor
		this.broadcast(new MsgGenerarMurosExteriores(), usuariosConectados);
		this.broadcast(new MsgGenerarObstaculos(map.getObstaculos()), usuariosConectados);
		//Agregamos el bomber del cliente al mapa
		map.agregarBomberman(bomber);
		//Le decimos al cliente que añada el bomber
		this.broadcast(new MsgAgregarBomberman(map.obtenerListaBomberman(), nroCliente), usuariosConectados);
	}
	
	public void enviarMsg(Msg msg) {
		try {
			ObjectOutputStream salidaACliente = new ObjectOutputStream(clientSocket.getOutputStream());
			salidaACliente.writeObject(msg);
		} catch (Exception e) {
			System.out.println("LA CONCHA DE TU MADRE");
		}
	}
	
	public void broadcast(Msg msg, ArrayList<Socket> usuariosConectados) {		
		for (Socket clientSocket : usuariosConectados) {
			try {
				ObjectOutputStream salidaACliente = new ObjectOutputStream(clientSocket.getOutputStream());
				salidaACliente.writeObject(msg);
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}

	@Override
	public void run() {

		try {
			ObjectInputStream reciboMsg = new ObjectInputStream(clientSocket.getInputStream());

			while (estaConectado) {
				/* Recibo Consulta de cliente */
				Msg msgRecibo = (Msg) reciboMsg.readObject();
				msgRecibo.realizarAccion(this);
				reciboMsg = new ObjectInputStream(clientSocket.getInputStream());
			}

			reciboMsg.close();
			clientSocket.close();
		} catch (IOException | ClassNotFoundException ex) {
			System.out.println("Problemas al querer leer otra petición: " + ex.getMessage());
			this.map.eliminarBomberman(this.bomber);
			this.usuariosConectados.remove(this.clientSocket);
			broadcast(new MsgEliminarBomberman(this.bomber.getIdBomberman()), usuariosConectados);
			this.estaConectado = false;
		}
	}	
}

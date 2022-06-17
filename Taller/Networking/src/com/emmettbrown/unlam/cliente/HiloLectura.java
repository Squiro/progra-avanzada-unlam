package com.emmettbrown.unlam.cliente;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class HiloLectura extends Thread {
	
	private Socket clientSocket;
	private boolean running;
	
	public HiloLectura(Socket cliente) {
		this.clientSocket = cliente;
		this.running = true;
	}
	
	@Override
	public void run() {
		while (running) {
			System.out.println(recibirMsg());
		}
	}
	
	public synchronized String recibirMsg() {
		DataInputStream inCliente;
		String infoRecibida = null;
		try {
			inCliente = new DataInputStream(clientSocket.getInputStream());
			infoRecibida = inCliente.readUTF();
			
		} catch (IOException e) {
			this.running = false;
			e.printStackTrace();
			System.out.println("SE CAYO EL SISTEMAAAAAAAA");
		}
		
		return infoRecibida;
	}

}

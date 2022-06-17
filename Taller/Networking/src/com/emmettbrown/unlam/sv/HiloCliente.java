package com.emmettbrown.unlam.sv;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class HiloCliente extends Thread {
	
	private String nombre;
	private boolean running;
	private Socket clientSocket;
	private ArrayList<Socket> clientes;

	public HiloCliente (Socket socket, ArrayList<Socket> clientes, String nombre){
		this.running = true;
		this.clientSocket = socket;
		this.clientes = clientes;
		this.nombre = nombre;
	}
	
	@Override
	public void run(){
		
		while(running){
			System.out.println("Escuchando desde el hilo");
			try {
				//Leemos el input del cliente
				DataInputStream inCliente = new DataInputStream(clientSocket.getInputStream());
				String infoRecibida = inCliente.readUTF();
				
				/*System.out.println("SERVER SIDE - CLIENT SAYS: " + infoRecibida);				
				//Enviamos mensajes al cliente
				DataOutputStream outCliente = new DataOutputStream(clientSocket.getOutputStream());
				outCliente.writeUTF(infoRecibida);*/
				broadcast(infoRecibida);
				
			} catch (IOException e) {
				//e.printStackTrace();
				this.running = false;
				System.out.println("El cliente se ha desconectado.");
				this.clientes.remove(this.clientSocket);
			}
		}
	}

	public void broadcast(String msg) throws IOException {		
		for (Socket cliente : clientes) {
			try {
				DataOutputStream outCliente = new DataOutputStream(cliente.getOutputStream());
				outCliente.writeUTF(nombre + " dijo: " + msg);
			} catch (IOException e) {
				
			}
		}
	}
	
}

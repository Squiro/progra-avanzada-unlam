package com.emmettbrown.unlam.sv;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {
	
	private int puerto;
	private ArrayList<Socket> clientes;

	public Servidor(int puerto){
		this.puerto = puerto;
		this.clientes = new ArrayList<Socket>();
	}
	
	public void iniciarServidor() throws IOException{
		ServerSocket sv = new ServerSocket(puerto);
		
		while (true) {

			System.out.println("Servidor escuchando a clientes!");
			Socket socketClienteOn = sv.accept();
			System.out.println("Se conecto un cliente!");
			this.clientes.add(socketClienteOn);
			
			DataInputStream inCliente = new DataInputStream(socketClienteOn.getInputStream());
			String nombre = inCliente.readUTF();
			
			
			HiloCliente hilo = new HiloCliente(socketClienteOn, clientes, nombre);
			hilo.start();
		}
		
	}
	
	public static void main(String[] args) {
		Servidor sv = new Servidor(20000);
		try {
			sv.iniciarServidor();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

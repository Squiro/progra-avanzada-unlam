package com.emmettbrown.servidor;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.emmettbrown.entorno.grafico.DefConst;
import com.emmettbrown.servidor.entidades.SvSala;

public class Servidor {
	//El puerto del servidor
	private int port;
	//El socket del servidor
	private ServerSocket serverSocket;
	//Lista de Sockets de los clientes conectados
	//private ArrayList<Socket> usuariosConectados;
	private ArrayList<ObjectOutputStream> usuariosConectados;
	//Listado de salas creadas en el server
	private ArrayList<SvSala> listaSalas;
	//private int nroCliente;
	public static int idSalas = 0;

	public Servidor(int puerto) {
		this.port = puerto;
		this.usuariosConectados = new ArrayList<ObjectOutputStream>();
		this.listaSalas = new ArrayList<SvSala>();
	}

	public static void main(String[] args) {
		Servidor sv = new Servidor(DefConst.PORT);
		sv.iniciarServidor();
	}
	
	public void iniciarServidor() {		
		try {
			serverSocket = new ServerSocket(this.port);
			// Sockets del cliente
			Socket writeSocket;
			Socket readSocket;
			while (true) {
				System.out.println("Servidor esperando clientes!");
				//Este método bloquea la ejecución del Thread hasta que se reciba una conexión entrante
				//de un cliente
				
				//Aceptamos ambos sockets
				writeSocket = serverSocket.accept();
				readSocket = serverSocket.accept();
				//Añadimos el socket del cliente a la lista de sockets
				//this.usuariosConectados.add(writeSocket.get);
				//this.usuariosConectados.add(new ObjectOutputStream(writeSocket.getOutputStream()));
				System.out.println("¡Conexion aceptada!");
				
				//Creamos un hilo para el cliente (evitando así el bloqueo que se genera en este mismo hilo)
				//Le envíamos como datos el socket del cliente, y los la lista de usuarios conectados
				HiloCliente hiloCliente = new HiloCliente(writeSocket, readSocket, usuariosConectados, listaSalas);
				//Iniciamos el hilo
				hiloCliente.start();

			}
		} catch (IOException ex) {
			System.out.println(ex);
		}
		
	}

}

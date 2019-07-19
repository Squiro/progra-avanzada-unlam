package com.emmettbrown.servidor;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.emmettbrown.base.datos.base.Configuracion;
import com.emmettbrown.base.datos.base.GestionBD;
import com.emmettbrown.entorno.grafico.DefConst;
import com.emmettbrown.servidor.entidades.SvSala;

public class Servidor {
	//El puerto del servidor
	private int port;
	//El socket del servidor
	private ServerSocket serverSocket;
	//Lista de Sockets de los clientes conectados
	private ArrayList<ObjectOutputStream> usuariosConectados;
	//Listado de salas creadas en el server
	private ArrayList<SvSala> listaSalas;
	//private int nroCliente;
	public static int idSalas = 0;
	Configuracion configuracion;
	GestionBD gestion;

	public Servidor(int puerto) {
		this.port = puerto;
		this.usuariosConectados = new ArrayList<ObjectOutputStream>();
		this.listaSalas = new ArrayList<SvSala>();
		Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
		//solo para que no muestre mucho
		configuracion = new Configuracion();
		gestion = new GestionBD(configuracion.getFactory());
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
				System.out.println("¡Conexion aceptada!");
				
				//Creamos un hilo para el cliente (evitando así el bloqueo que se genera en este mismo hilo)
				//Le envíamos como datos el socket del cliente, y los la lista de usuarios conectados
				HiloCliente hiloCliente = new HiloCliente(writeSocket, readSocket, usuariosConectados, listaSalas,gestion);
				//Iniciamos el hilo
				hiloCliente.start();

			}
		} catch (IOException ex) {
			System.out.println(ex);
		}
		
	}

}

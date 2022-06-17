package com.emmettbrown.unlam.cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
	
	private int id;
	private Socket socketc;

	public Cliente (int id) {
		this.id  = id ;
		try {
			socketc = new Socket("localhost", 20000);
			enviarNombre();
		} catch (UnknownHostException e) {
			System.out.println("No se pudo encontrar al servidor indicado.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Hubo un error de IO");
			e.printStackTrace();
		}
	}
	
	public void enviarNombre() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Ingresá un nombre para tu cliente: ");
		enviarMsg(sc.nextLine());		
	}
	
	public synchronized void enviarMsg(String msg){;
		try {
			DataOutputStream out = new DataOutputStream(socketc.getOutputStream());
			out.writeUTF(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}	
	
	public void chatear() {
		//HiloEscritura esc = new HiloEscritura(this);
		HiloLectura lec = new HiloLectura(this.socketc);
		
		//esc.start();
		lec.start();
		
		Scanner sc = new Scanner(System.in);

		String msg = sc.nextLine();
		while (!msg.equals("0")) {
			enviarMsg(msg);
			msg = sc.nextLine();
		}
		
		sc.close();
	}
	
	public static void main(String[] args) {
		Cliente cli = new Cliente(1);		
		cli.chatear();		
	}
}

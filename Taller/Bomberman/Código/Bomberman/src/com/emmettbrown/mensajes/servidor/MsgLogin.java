package com.emmettbrown.mensajes.servidor;

import java.io.ObjectOutputStream;

import com.emmettbrown.mensajes.Msg;
import com.emmettbrown.servidor.HiloCliente;


public class MsgLogin extends Msg {

	private static final long serialVersionUID = 1L;
	private String usuario;
	private String clave;

	public MsgLogin(String usuario, String contrasena) {
		this.usuario = usuario;
		this.clave = contrasena;
	}

	@Override
	public Object realizarAccion(Object obj) {
		HiloCliente hilo =(HiloCliente) obj;
		try { 
			ObjectOutputStream salidaACliente = new ObjectOutputStream(hilo.getClientSocket().getOutputStream()); 
			
			if ((this.usuario.equals("Usuario") && this.clave.equals("clave"))
					|| (this.usuario.equals("Usuario2") && this.clave.equals("clave2"))) {
				
				
				
				hilo.getUsuariosConectados().add(hilo.getClientSocket());			
				//Le mandamos un OK al cliente
				salidaACliente.writeObject("OK");				
			}
			else {
				//Le mandamos un FAIL al cliente
				salidaACliente.writeObject("FAIL");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
}
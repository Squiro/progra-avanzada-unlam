package com.emmettbrown.mensajes.servidor;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.emmettbrown.base.datos.base.GestionBD;
import com.emmettbrown.base.datos.base.Usuario;
import com.emmettbrown.mensajes.Msg;
import com.emmettbrown.mensajes.cliente.MsgResLogin;
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
		GestionBD gestion = hilo.getGestionBD();
		
		Session sesion = gestion.getFactory().openSession();
		Transaction transaccion = sesion.beginTransaction();
		Usuario user = sesion.get(Usuario.class, this.usuario);
		transaccion.commit();
		sesion.close();
		
		boolean resultado = true;
		if (user == null || !user.getContraseña().equals(clave)) {
			resultado = false;
		}
		hilo.enviarMsg(new MsgResLogin(resultado));
		return null;
	}
}
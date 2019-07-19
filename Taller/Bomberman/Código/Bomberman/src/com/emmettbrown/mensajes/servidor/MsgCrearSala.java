package com.emmettbrown.mensajes.servidor;

import com.emmettbrown.entorno.grafico.DefConst;
import com.emmettbrown.mensajes.Msg;
import com.emmettbrown.mensajes.cliente.MsgActualizarListaSalas;
import com.emmettbrown.servidor.HiloCliente;
import com.emmettbrown.servidor.Servidor;
import com.emmettbrown.servidor.entidades.SvSala;

public class MsgCrearSala extends Msg {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idCliente; 
	private String password;
	
	public MsgCrearSala(int idCli, String password) {
		this.idCliente = idCli;
		this.password = password;
	}
	
	@Override
	public Object realizarAccion(Object obj) {
		HiloCliente hilo = (HiloCliente) obj;
		Servidor.idSalas++;
		SvSala svSala = new SvSala(Servidor.idSalas, this.idCliente, "Sala "+Servidor.idSalas+" de " +hilo.getNombreUsuario(), DefConst.LIMITEJUGADORES, password);
		svSala.agregarUsuario(hilo,hilo.getNombreUsuario());
		hilo.agregarSala(svSala);
		
		//Seteamos la sala del cliente
		hilo.setSalaConectada(svSala);
		
		hilo.broadcast(new MsgActualizarListaSalas(svSala.getId(), svSala.getIdCreador(), svSala.getNombre(), 
				svSala.getClientesConectadosSize(), svSala.getLimJugadores(), svSala.esPrivada()), hilo.getUsuariosConectados());
		
		return null;
	}

}

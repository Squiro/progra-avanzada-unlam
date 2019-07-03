package com.emmettbrown.mensajes.servidor;

import com.emmettbrown.entorno.grafico.DefConst;
import com.emmettbrown.entorno.grafico.Sala;
import com.emmettbrown.mensajes.Msg;
import com.emmettbrown.mensajes.cliente.MsgActualizarListaSalas;
import com.emmettbrown.servidor.HiloCliente;
import com.emmettbrown.servidor.Servidor;

public class MsgCrearSala extends Msg {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idCliente ;
	
	public MsgCrearSala(int s) {
		this.idCliente = s;
	}
	
	@Override
	public Object realizarAccion(Object obj) {
		HiloCliente hilo = (HiloCliente) obj;

		Sala sala = new Sala(Servidor.idSalas++, this.idCliente, "Sala de " + this.idCliente, 0, DefConst.LIMITEJUGADORES);
		hilo.agregarSala(sala);
		//System.out.println("MsgCrearSala");
		hilo.broadcast(new MsgActualizarListaSalas(sala), hilo.getUsuariosConectados());
		
		return null;
	}

}

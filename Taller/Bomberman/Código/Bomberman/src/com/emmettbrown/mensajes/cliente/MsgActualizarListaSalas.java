package com.emmettbrown.mensajes.cliente;

import com.emmettbrown.cliente.Cliente;
import com.emmettbrown.entorno.grafico.Sala;
import com.emmettbrown.mensajes.Msg;

public class MsgActualizarListaSalas extends Msg {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idSala;
	private int idCreador;
	private String nombre;
	private int jugConectados;
	private int limJug;
	private boolean privada;
	
	public MsgActualizarListaSalas(int idSala, int idCreador, String nombre, int jugConectados, int limJug, boolean privada) {
		this.idSala = idSala;
		this.idCreador = idCreador;
		this.nombre = nombre;
		this.jugConectados = jugConectados;
		this.limJug = limJug;
		this.privada = privada;
	}
	
	@Override
	public Object realizarAccion(Object obj) {
		Cliente cliente = (Cliente) obj;
		cliente.getListaSalas().add(new Sala(idSala, idCreador, nombre, jugConectados, limJug, privada));
		return null;
	}
}

package com.emmettbrown.mensajes.cliente;

import java.util.ArrayList;
import java.util.HashMap;

import com.emmettbrown.cliente.Cliente;
import com.emmettbrown.entidades.Entidad;
import com.emmettbrown.entidades.Obstaculo;
import com.emmettbrown.servidor.entidades.SvObstaculo;
import com.emmettbrown.mapa.Ubicacion;
import com.emmettbrown.mensajes.Msg;

public class MsgGenerarObstaculos extends Msg {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<SvObstaculo> obstaculos;
	
	public MsgGenerarObstaculos(ArrayList<SvObstaculo> obs ) {
		this.obstaculos = obs;
	}

	@Override
	public Object realizarAccion(Object obj) {
		Cliente cliente = (Cliente) obj;
		HashMap<Ubicacion, Entidad> conjuntoEntidades = cliente.getMapa().getListaEntidades();
		
		for (SvObstaculo obstaculo : obstaculos) {
			Obstaculo obs = new Obstaculo(obstaculo.getX(), obstaculo.getY());
			conjuntoEntidades.put(obs.obtenerUbicacion(), obs);
		}
		
		return null;
	}
}

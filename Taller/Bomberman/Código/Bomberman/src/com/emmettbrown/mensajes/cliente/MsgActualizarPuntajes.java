package com.emmettbrown.mensajes.cliente;

import java.util.HashMap;
import java.util.Map.Entry;

import com.emmettbrown.cliente.Cliente;
import com.emmettbrown.entorno.grafico.Sala;
import com.emmettbrown.mensajes.Msg;

public class MsgActualizarPuntajes extends Msg {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<String, Integer> hash;
	
	public MsgActualizarPuntajes(HashMap<String,Integer> hp) {
    	this.hash = hp;
	}
	
	@Override
	public Object realizarAccion(Object obj) {
		Cliente cliente = (Cliente) obj;
		
		Sala sala = cliente.getSalaActual();
		
		for (Entry<String, Integer> entry : hash.entrySet()) {
			sala.agregarPuntaje(entry.getKey(), entry.getValue());
		}
		return null;
	}

}

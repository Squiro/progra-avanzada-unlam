package com.emmettbrown.mensajes;

import java.util.List;
import com.emmettbrown.cliente.Cliente;
import com.emmettbrown.entidades.DefConst;
import com.emmettbrown.entidades.Bomberman;
import com.emmettbrown.servidor.entidades.SvBomberman;

public class MsgAgregarBomberman extends Msg {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<SvBomberman> listaBomberman; 
	private int nroCliente;

	public MsgAgregarBomberman(List<SvBomberman> lista, int nroCliente) {
		this.listaBomberman = lista;
		this.nroCliente = nroCliente;
	}
	
	@Override
	public Object realizarAccion(Object obj) {
		Cliente cliente = ((Cliente) obj);
		List<Bomberman> lista = cliente.getMapa().obtenerListaBomberman();
		
		
		for (SvBomberman bomberman : listaBomberman) {
			boolean creado = false;
			
			for (Bomberman bman : lista) {
				if (bomberman.getIdBomberman() == bman.getIdBomberman()) {
					creado = true;
				}
			}
			
			if (!creado) {
				Bomberman bomber = new Bomberman(bomberman.getX(), bomberman.getY(), DefConst.DEFAULTWIDTH, DefConst.DEFAULTHEIGHT);
				
				if (cliente.getNroCliente() == nroCliente) {
					cliente.setBomber(bomber);
				}
				
				cliente.getMapa().agregarBomberman(bomber);
			}
				
		}
		return null;
	}

}

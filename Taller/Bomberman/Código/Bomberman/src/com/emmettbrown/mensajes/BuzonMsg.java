package com.emmettbrown.mensajes;

import java.util.List;
import javax.swing.SwingWorker;
import com.emmettbrown.cliente.Cliente;
import com.emmettbrown.mapa.Mapa;

public class BuzonMsg extends SwingWorker<Void, Msg> {

	private Cliente cliente;
	private Mapa mapa;
	private boolean running;

	public BuzonMsg(Cliente cliente, Mapa mapa) {
		this.cliente = cliente;
		this.mapa = mapa;
		this.running = true;
	}	
	
	@Override
	protected Void doInBackground() throws Exception {
		while (running) {
			Msg msg =(Msg) cliente.recibirMsg();
			publish(msg);
		}

		return null;
	}
	
	@Override
    protected void process(List<Msg> chunks) {		
		chunks.get(0).realizarAccion(mapa);
	}

}
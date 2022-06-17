package com.emmettbrown.servidor;

import java.util.ArrayList;

import com.emmettbrown.servidor.entidades.SvSala;

public class SvControlVivos extends Thread{

	private SvSala sala;
	private boolean corriendo;
	private ArrayList<HiloCliente> listaHilos;
	private int cont;
	public SvControlVivos (SvSala sala) {
		this.sala = sala;
		this.listaHilos = sala.obtenerHilosSala();
	}
	
	public void matarHilo()
	{
		corriendo = false;
	}
	
	@Override
	public void run() {
		this.corriendo = true;
		long initialTime = System.nanoTime();
		final double timeF = 1000000000;
		double deltaF = 0;
		while (corriendo) {
			long currentTime = System.nanoTime();
			deltaF += (currentTime - initialTime) / timeF;
			initialTime = currentTime;
			
			if (deltaF >= 1) {
				cont = 0;
				for (HiloCliente hiloCliente : listaHilos) {
					if(hiloCliente.getBomber().estaVivo() == true)
						cont++;
				}
				if(cont == 1 || cont == 0) {
					sala.finalizarRonda();
					corriendo = false;
				}
				deltaF--;
			}
		}
	}
	
	
	
	
	
}

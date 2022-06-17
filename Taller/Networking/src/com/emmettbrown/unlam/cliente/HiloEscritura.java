package com.emmettbrown.unlam.cliente;

import java.util.Scanner;

public class HiloEscritura extends Thread  {

	private boolean running;
	
	public HiloEscritura(Cliente cliente) {
		//this.cliente = cliente;
		this.running = true;
	}
	
	@Override
	public void run() {
		while (running) {

		}
	}
	
}

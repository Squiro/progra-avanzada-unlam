package com.emmettbrown.entorno.grafico;


import javax.swing.JFrame;

public class RefreshThread extends Thread {

	private JFrame ventana;
	private int FPS; //Refresh rate
	
	public RefreshThread(JFrame ventana, int FPS) {
		this.ventana = ventana;
		this.FPS = FPS;
	}

	public void run() {
		long initialTime = System.nanoTime();
		final double timeF = 1000000000 / FPS;
		double deltaF = 0;

		while (true) {
			long currentTime = System.nanoTime();
			deltaF += (currentTime - initialTime) / timeF;
			initialTime = currentTime;
			if (deltaF >= 1) {
				ventana.repaint();
				//refrescar();
				deltaF--;
			}
		}
	}

	public void refrescar() {
		ventana.repaint();
	}
}

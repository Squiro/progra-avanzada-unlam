package com.emmettbrown.entorno.grafico;


import javax.swing.JFrame;

public class RefreshThread extends Thread {

	private JFrame ventana;
	private int FPS; //Refresh rate
	private boolean corriendo;
	public RefreshThread(JFrame ventana, int FPS) {
		this.ventana = ventana;
		this.FPS = FPS;
		this.corriendo = true;
	}

	public void run() {
		long initialTime = System.nanoTime();
		final double timeF = 1000000000 / FPS;
		double deltaF = 0;
		ventana.requestFocus(); // Para que al iniciar el juego, obtenga automatico el foco la ventana y se muevan directamente
		while (corriendo) {
			long currentTime = System.nanoTime();
			deltaF += (currentTime - initialTime) / timeF;
			initialTime = currentTime;
			
			if (deltaF >= 1) {
				ventana.repaint();
				deltaF--;
			}
		}
	}
	
	public void matarThread() {
		this.corriendo = false;
	}

	public void refrescar() {
		ventana.repaint();
	}
}

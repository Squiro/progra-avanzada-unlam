package com.emmettbrown.principal;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.emmettbrown.cliente.Cliente;
import com.emmettbrown.cliente.ListenerThread;
import com.emmettbrown.entidades.DefConst;
import com.emmettbrown.entorno.grafico.JVentanaGrafica;
import com.emmettbrown.mapa.Mapa;
import com.emmettbrown.mensajes.Msg;

public class Motor {

	private Mapa mapa;
	private JVentanaGrafica miVentana;
	private boolean iniciado;
	private Cliente cliente;
	private ListenerThread listener; 
	
	public Motor(Cliente cliente) {
		this.mapa = new Mapa();
		
		this.cliente = cliente;
		Msg msgRecibido = (Msg) this.cliente.recibirMsg();
		msgRecibido.realizarAccion(this);
		/*this.listener = new ListenerThread(this);
		this.listener.start();*/

		//Bomberman propio del usuario conectado.
		//Bomberman miBomber = new Bomberman(75, 75, DefConst.DEFAULTWIDTH,DefConst.DEFAULTHEIGHT); 
		
		miVentana = new JVentanaGrafica(mapa, DefConst.ANCHO, DefConst.ALTO, cliente);
		miVentana.setVisible(true);	//Ivokelater
		

	}
	
	public Cliente getCliente() {
		return this.cliente;
	}
	
	public Mapa getMapa() {
		return this.mapa;
	}

	public void iniciarJuego() {
		this.iniciado = true;
		//miVentana.setVisible(true);
	}

	public void gameLoop() {
		long initialTime = System.nanoTime();
		final double timeF = 1000000000 / DefConst.FPS;
		double deltaF = 0; // deltaU = 0, 

	    while (iniciado) {	    	
	        long currentTime = System.nanoTime();
	        deltaF += (currentTime - initialTime) / timeF;
	        initialTime = currentTime;
	        if (deltaF >= 1) {
	            actualizar();
	            deltaF--;
	        }
	    }
	}
	
	private void actualizar() {
		miVentana.refrescar();
		
	}

	public void cerrarJuego() {
		iniciado = false;
	}

//	public void jugar(String u){
//		Motor m = new Motor(u);
//		m.iniciarJuego();
//		m.gameLoop();
//	}
//	
//	public static void main(String[] args) {
//		String miUsuario = "Usuario1";
//		Motor m = new Motor(miUsuario);
//		m.iniciarJuego();
//		m.gameLoop();
//	}

}

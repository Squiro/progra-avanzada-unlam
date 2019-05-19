package com.emmettbrown.controles;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclado implements KeyListener{
	private boolean arriba;
	private boolean abajo;
	private boolean izq;
	private boolean der;
	private boolean esc;
	
	
	public boolean isArriba() {
		return arriba;
	}

	public boolean isAbajo() {
		return abajo;
	}

	public boolean isIzq() {
		return izq;
	}

	public boolean isEsc() {
		return esc;
	}

	public boolean isDer() {
		return der;
	}

	public boolean isPonerBomba() {
		return ponerBomba;
	}


	private boolean ponerBomba;
	
	public Teclado() {
		this.ponerBomba = false;
		this.arriba = false;
		this.abajo = false;
		this.izq = false;
		this.der = false;
		this.esc = false;
	}
	
	//@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_ESCAPE) {
			this.esc = true;
		}
		if (key == KeyEvent.VK_RIGHT) {
			this.der = true;			
		}
		if (key == KeyEvent.VK_LEFT) {
			this.izq = true;
		}
		if (key == KeyEvent.VK_UP) {
			this.arriba = true;
		}
		if (key == KeyEvent.VK_DOWN) {
			this.abajo = true;
		}
		
		if (key == KeyEvent.VK_SPACE) {
			this.ponerBomba = true;
		}
	}

	//@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_RIGHT) {
			this.der = false;
		}
		if (key == KeyEvent.VK_LEFT) {
			this.izq = false;
		}
		if (key == KeyEvent.VK_UP) {
			this.arriba = false;
		}
		if (key == KeyEvent.VK_DOWN) {
			this.abajo = false;
		}
		if (key == KeyEvent.VK_SPACE) {
			this.ponerBomba = false;
		}
	}
	

	//@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
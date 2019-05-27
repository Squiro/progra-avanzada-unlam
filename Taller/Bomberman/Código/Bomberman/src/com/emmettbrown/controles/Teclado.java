package com.emmettbrown.controles;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclado implements KeyListener{
	private boolean arriba;
	private boolean abajo;
	private boolean izq;
	private boolean der;
	private boolean l;
	private boolean esc;
	private boolean w;
	private boolean a;
	private boolean d;
	private boolean s;	
	private boolean f;
	
	public boolean isF() {
		return f;
	}	
	
	public boolean isW() {
		return w;
	}

	public boolean isA() {
		return a;
	}

	public boolean isD() {
		return d;
	}

	public boolean isS() {
		return s;
	}
	
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

	public boolean isL() {
		return l;
	}
	
	public Teclado() {
		this.arriba = false;
		this.abajo = false;
		this.izq = false;
		this.der = false;
		this.esc = false;
		this.f = false;
		this.l = false;
		this.w = false;
		this.a = false;
		this.s = false;
		this.d = false;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		//Jugador 1 (local)
		
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
		
		if (key == KeyEvent.VK_L) {
			this.l = true;
		}
		
		//Jugador 2 (local)
		
		if (key == KeyEvent.VK_W) {
			this.w = true;			
		}
		if (key == KeyEvent.VK_A) {
			this.a = true;
		}
		if (key == KeyEvent.VK_S) {
			this.s = true;
		}
		if (key == KeyEvent.VK_D) {
			this.d = true;
		}
		
		if (key == KeyEvent.VK_F) {
			this.f = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		//Jugador 1 (local)

		if (key == KeyEvent.VK_ESCAPE) {
			this.esc = false;
		}
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
		
		if (key == KeyEvent.VK_L) {
			this.l = false;
		}
		
		//Jugador 2 (local)
		
		if (key == KeyEvent.VK_W) {
			this.w = false;			
		}
		if (key == KeyEvent.VK_A) {
			this.a = false;
		}
		if (key == KeyEvent.VK_S) {
			this.s = false;
		}
		if (key == KeyEvent.VK_D) {
			this.d = false;
		}
		
		if (key == KeyEvent.VK_F) {
			this.f = false;
		}		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
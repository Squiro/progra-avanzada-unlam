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
	public boolean isF() {
		return f;
	}

	public void setF(boolean f) {
		this.f = f;
	}

	public void setArriba(boolean arriba) {
		this.arriba = arriba;
	}

	public void setAbajo(boolean abajo) {
		this.abajo = abajo;
	}

	public void setIzq(boolean izq) {
		this.izq = izq;
	}

	public void setDer(boolean der) {
		this.der = der;
	}

	public void setL(boolean l) {
		this.l = l;
	}

	public void setEsc(boolean esc) {
		this.esc = esc;
	}

	public void setW(boolean w) {
		this.w = w;
	}

	public void setA(boolean a) {
		this.a = a;
	}

	public void setD(boolean d) {
		this.d = d;
	}

	public void setS(boolean s) {
		this.s = s;
	}

	private boolean f;
	
	
	
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
			this.l = true;
		}
		
		
		
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
//
	//@Override
	public void keyReleased(KeyEvent e) {
//		int key = e.getKeyCode();
//
//		if (key == KeyEvent.VK_RIGHT) {
//			this.der = false;
//		}
//		if (key == KeyEvent.VK_LEFT) {
//			this.izq = false;
//		}
//		if (key == KeyEvent.VK_UP) {
//			this.arriba = false;
//		}
//		if (key == KeyEvent.VK_DOWN) {
//			this.abajo = false;
//		}
//		if (key == KeyEvent.VK_SPACE) {
//			this.ponerBomba = false;
//		}
	}
//	

	//@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
package ar.edu.unlam.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclado implements KeyListener{

	private boolean[] keys = new boolean[120]; //120 is enough to this game
	public boolean arriba, abajo, izquierda, derecha, espacio;
	
	public void update() {
		arriba = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		abajo = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		izquierda = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		derecha = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		espacio = keys[KeyEvent.VK_SPACE] || keys[KeyEvent.VK_X];
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;		
	}
	
	
}

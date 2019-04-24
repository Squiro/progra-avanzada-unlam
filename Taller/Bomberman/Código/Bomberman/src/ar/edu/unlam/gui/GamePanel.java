package ar.edu.unlam.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import ar.edu.unlam.juego.Juego;

public class GamePanel extends JPanel {

	private Juego juego;
	
	public GamePanel(Frame frame) {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(Juego.WIDTH * Juego.SCALE, Juego.HEIGHT * Juego.SCALE));
		
		try {
			juego = new Juego(frame);
			
			add(juego);
			
			juego.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
			//TODO: so we got a error hum..
			System.exit(0);
		}
		setVisible(true);
		setFocusable(true);
		
	}
	
	public void changeSize() {
		setPreferredSize(new Dimension(Juego.WIDTH * Juego.SCALE, Juego.HEIGHT * Juego.SCALE));
		revalidate();
		repaint();
	}
	
	public Juego getGame() {
		return juego;
	}
	
}
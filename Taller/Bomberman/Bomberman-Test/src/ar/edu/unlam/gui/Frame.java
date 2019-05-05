package ar.edu.unlam.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;


import ar.edu.unlam.juego.Juego;

public class Frame extends JFrame {
	
	public GamePanel _gamepane;
	private JPanel _containerpane;
	//private InfoPanel _infopanel;
	
	private Juego juego;

	public Frame() {
		//setJMenuBar(new Menu(this));
		
		_containerpane = new JPanel(new BorderLayout());
		_gamepane = new GamePanel(this);
		//_infopanel = new InfoPanel(_gamepane.getGame());
		
		//_containerpane.add(_infopanel, BorderLayout.PAGE_START);
		_containerpane.add(_gamepane, BorderLayout.PAGE_END);
		
		juego = _gamepane.getGame();
		
		add(_containerpane);
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);	
		
		//juego.start();
	}
	
	public static void main(String[] args) {
		Frame frame = new Frame();
		
		while(true) {
			frame.juego.update(); 
			//System.out.println("hola");
		}
	}
	
	/*
	|--------------------------------------------------------------------------
	| Game Related
	|--------------------------------------------------------------------------
	 */
	/*public void newGame() {
		juego.getBoard().newGame();
	}
	
	public void changeLevel(int i) {
		juego.getBoard().changeLevel(i);
	}
	
	public void pauseGame() {
		juego.getBoard().gamePause();
	}
	
	public void resumeGame() {
		juego.getBoard().gameResume();
	}
	
	public boolean isRunning() {
		return juego.isRunning();
	}
	
	public void setTime(int time) {
		_infopanel.setTime(time);
	}
	
	public void setLives(int lives) {
		_infopanel.setLives(lives);
	}
	
	public void setPoints(int points) {
		_infopanel.setPoints(points);
	}
	
	public boolean validCode(String str) {
		return _game.getBoard().getLevel().validCode(str) != -1;
	}
	
	public void changeLevelByCode(String str) {
		_game.getBoard().changeLevelByCode(str);
	}*/
	
}
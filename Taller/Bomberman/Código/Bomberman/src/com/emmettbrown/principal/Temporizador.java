package com.emmettbrown.principal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import com.emmettbrown.entidades.Bomba;
import com.emmettbrown.mapa.Mapa;

public class Temporizador {

	private Bomba bomb;
	private int sMax;
	private Timer t;
	private Mapa map;

	class miOyente implements ActionListener {
		private Bomba b;

		public miOyente(Bomba bomb) {
			this.b = bomb;
		}

		//@Override
		public void actionPerformed(ActionEvent ae) {
			//System.out.println("Pasaron 3 segundos entonces BUM");
			this.b.explotar(map);
			t.stop();
		}
	}

	public Temporizador(int ms, Bomba bomb, Mapa map) {
		this.bomb = bomb;
		this.map = map;
		t = new Timer(ms, new miOyente(this.bomb));
	}

	public void iniciarTimer() {
		t.start();
	}
}

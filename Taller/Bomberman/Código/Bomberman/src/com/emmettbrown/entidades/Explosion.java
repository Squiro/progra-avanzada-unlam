package com.emmettbrown.entidades;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import com.emmettbrown.mapa.Mapa;
import com.emmettbrown.mapa.Ubicacion;

public class Explosion extends Entidad {

	private final static int DURACION = 1;
	private Timer t;
	private ImageIcon fuegoArrAba;
	private ImageIcon fuegoIzqDer;
	private ImageIcon fuegoArribaPunta;
	private ImageIcon fuegoAbajoPunta;
	private ImageIcon fuegoDerechaPunta;
	private ImageIcon fuegoIzquierdaPunta;
	
	public Explosion(int posX, int posY, int width, int height) {
		super(posX, posY, width, height);
		fuegoArrAba = new ImageIcon("./src/resources/bomb/FuegoArrAba.png");
		fuegoIzqDer = new ImageIcon("./src/resources/bomb/FuegoIzqDer.png");
		fuegoArribaPunta = new ImageIcon("./src/resources/bomb/finFuegoArriba.png");
		fuegoAbajoPunta = new ImageIcon("./src/resources/bomb/finFuegoAbajo.png");
		fuegoDerechaPunta = new ImageIcon("./src/resources/bomb/finFuegoDerecha.png");
		fuegoIzquierdaPunta = new ImageIcon("./src/resources/bomb/finFuegoIzquierda.png");
		img = new ImageIcon("./src/resources/bomb/fuegoCen.png");
	}
	
	class miOyente implements ActionListener {
		private Mapa map;
		private Ubicacion ubic;
		
		public miOyente(Mapa map, Ubicacion ubic) {
			this.map = map;
			this.ubic = ubic;
		}
		
		@Override
		public void actionPerformed(ActionEvent ae) {
			map.removerEntidadDelConjunto(ubic);
			t.stop();
		}
	}	
	
	public void startTimer(Mapa map) {
		t = new Timer(DURACION*1000, new miOyente(map, this.ubicacion));
		t.start();
	}


	@Override
	public void explotar(Mapa map) {
		// TODO Auto-generated method stub
		
	}
	
	public void cambiarImagenArrAba() {
		setImage(fuegoArrAba);
	}
	
	public void cambiarImagenIzqDer() {
		setImage(fuegoIzqDer);
	}
	
	public void cambiarImagenArribaPunta() {
		setImage(fuegoArribaPunta);
	}
	
	public void cambiarImagenAbajoPunta() {
		setImage(fuegoAbajoPunta);
	}
	
	public void cambiarImagenIzquierdaPunta() {
		setImage(fuegoIzquierdaPunta);
	}
	
	public void cambiarImagenDerechaPunta() {
		setImage(fuegoDerechaPunta);
	}
}

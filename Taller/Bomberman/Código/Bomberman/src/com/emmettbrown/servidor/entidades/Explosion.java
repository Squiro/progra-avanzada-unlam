package com.emmettbrown.servidor.entidades;

import java.awt.event.ActionEvent;


import java.awt.event.ActionListener;
import javax.swing.Timer;


import com.emmettbrown.servidor.entidades.SvEntidad;
import com.emmettbrown.servidor.mapa.ServerMap;
import com.emmettbrown.mapa.Ubicacion;

public class Explosion extends SvEntidad {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static int DURACION = 1;
	private Timer t;

	public Explosion(int posX, int posY, int width, int height) {
		super(posX, posY, width, height);

	}
	
	class miOyente implements ActionListener {
		private ServerMap map;
		private Ubicacion ubic;
		
		public miOyente(ServerMap map, Ubicacion ubic) {
			this.map = map;
			this.ubic = ubic;
		}
		
		@Override
		public void actionPerformed(ActionEvent ae) {
			map.removerEntidadDelConjunto(ubic);
			t.stop();
		}
	}	
	
	public void startTimer(ServerMap map) {
		t = new Timer(DURACION*1000, new miOyente(map, this.ubicacion));
		t.start();
	}


	@Override
	public void explotar(ServerMap map) {
		// TODO Auto-generated method stub
		
	}
}


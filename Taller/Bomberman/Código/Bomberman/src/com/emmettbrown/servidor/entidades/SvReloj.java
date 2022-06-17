package com.emmettbrown.servidor.entidades;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import com.emmettbrown.entorno.grafico.DefConst;

public class SvReloj {

	private int hora,min,seg;
	private Timer t;
	
	public SvReloj (int h,int m,int s ) {
		this.hora = h;
		this.min = m;
		this.seg = s;
	}
	
	public void restarSegundo () {

		if (this.seg == 0) {
			if (this.min == 0) {
				if (this.hora > 0) {
					this.hora--;
					this.min = 59;
					this.seg = 59;
				}
			}else {
				this.min--;
				this.seg = 60;
			}
		}else {
			this.seg--;
		}
	}

	public boolean timeOut() {
		return this.hora==0 && this.min == 0 && this.seg == 0 ? true:false;
	}
	
	public void startTimer(SvSala sala) {
		t = new Timer(1000, new miOyente(sala, seg));
		t.start();
	}
	
	class miOyente implements ActionListener {
		private int limit;
		//La sala asociada al timer
		private SvSala sala;
		
		public miOyente(SvSala sala, int limit) {
			this.limit = limit;
			this.sala = sala;
		}
		
		@Override
		public void actionPerformed(ActionEvent ae) {
			restarSegundo();
			if (timeOut()) {				
				hora= 0;
				min = 0;
				seg = limit;				
				sala.finalizarRonda();
				t.stop();
			}	
		}
	}

	public void stop() {
		t.stop();
		hora= 0;
		min = 0;
		seg = DefConst.SEG;
	}
}
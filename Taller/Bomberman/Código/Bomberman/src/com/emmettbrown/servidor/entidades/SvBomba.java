package com.emmettbrown.servidor.entidades;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import com.emmettbrown.servidor.entidades.SvBomberman;
import com.emmettbrown.servidor.entidades.SvEntidad;
import com.emmettbrown.servidor.entidades.Explosion;
import com.emmettbrown.servidor.mapa.ServerMap;
import com.emmettbrown.entorno.grafico.DefConst;
import com.emmettbrown.mapa.Ubicacion;

public class SvBomba extends SvEntidad {

	private static final long serialVersionUID = 1L;
	private int segsExplosion;
	private int rango;
	private SvBomberman creador;
	private boolean ignorarColisionCreador;
	private Timer timer;

	///////////////////////////////////////
	// 									//
	// 			CONSTRUCTORES			//
	// 									//
	/////////////////////////////////////

	public SvBomba(int posX, int posY, SvBomberman bman) {
		super(posX, posY, DefConst.TILESIZE, DefConst.TILESIZE);
		segsExplosion = 3;
		this.destructible = true;
		this.rango = 2;
		this.creador = bman;
		this.ignorarColisionCreador = true;
	}

	public SvBomba(Ubicacion ubic, SvBomberman creador) {
		super(ubic, DefConst.TILESIZE, DefConst.TILESIZE);
		segsExplosion = 3;
		this.destructible = true;
		this.rango = 2;
		this.creador = creador;
		this.ignorarColisionCreador = true;
	}

	///////////////////////////////////////
	// 									//
	// 			GETTERS Y SETTERS 	   //
	// 								  //
	///////////////////////////////////

	public boolean getIgnorarColisionCreador() {
		return this.ignorarColisionCreador;
	}
	
	public void setIgnorarColisionCreador(boolean param) {
		this.ignorarColisionCreador = param;
	}

	///////////////////////////////////////
	// 									//
	// 				METODOS 			//
	// 									//
	/////////////////////////////////////

	/**
	 * Realiza un par de acciones comunes (remover la bomba, setear visibilidad
	 * en false), y crea explosiones en las cuatro direcciones hasta llegar al
	 * rango maximo... o toparse con un obstaculo/muro
	 * 
	 * @param map El mapa del juego
	 */
	@Override
	public void explotar(ServerMap map) {
		// Seteamos visible = false para dejar de renderizar la bomba
		this.cambiarVisibilidad();
		// Removemos la bomba de la lista de entidades
		map.removerEntidadDelConjunto(this.ubicacion);
		//Y de la lista del creador
		creador.removerBomba(this);
		
		// Creamos una "explosión" en en lugar
		explosion(new Ubicacion(this.ubicacion.getPosX(), this.ubicacion.getPosY()), map);

		// Creamos "explosiones" en las cuatro direcciones, dependiendo del rango
		explosionIzquierda(map);
		explosionDerecha(map);
		explosionArriba(map);
		explosionAbajo(map);
	}

	private void explosionIzquierda(ServerMap map) {
		boolean hayObstaculo = false;

		// Se ejecuta hasta llegar al rango maximo, o toparse con un
		// obstaculo/muro siempre dentro del ANCHO y ALTO
		for (int i = 1; i <= this.rango && !hayObstaculo && this.ubicacion.getPosX() - i >= 0;  i++) {
			hayObstaculo = explosion(new Ubicacion(this.ubicacion.getPosX() - i, this.ubicacion.getPosY()), map);
		}
	}

	private void explosionDerecha(ServerMap map) {
		boolean hayObstaculo = false;

		// Se ejecuta hasta llegar al rango maximo, o toparse con un
		// obstaculo/muro siempre dentro del ANCHO y ALTO
		for (int i = 1; i <= this.rango && !hayObstaculo && this.ubicacion.getPosX() + i < DefConst.ANCHOMAPA;  i++) {
			hayObstaculo = explosion(new Ubicacion(this.ubicacion.getPosX() + i, this.ubicacion.getPosY()), map);
		}
	}

	private void explosionArriba(ServerMap map) {
		boolean hayObstaculo = false;

		// Se ejecuta hasta llegar al rango maximo, o toparse con un
		// obstaculo/muro siempre dentro del ANCHO y ALTO
		for (int i = 1; i <= this.rango && !hayObstaculo && this.ubicacion.getPosY() - i >= 0 ; i++) {
			hayObstaculo = explosion(new Ubicacion(this.ubicacion.getPosX(), this.ubicacion.getPosY() - i), map);
		}
	}

	private void explosionAbajo(ServerMap map) {
		boolean hayObstaculo = false;

		// Se ejecuta hasta llegar al rango maximo, o toparse con un
		// obstaculo/muro siempre dentro del ANCHO y ALTO
		for (int i = 1; i <= this.rango && !hayObstaculo && this.ubicacion.getPosY() + i <= DefConst.ALTOMAPA; i++) {
			hayObstaculo = explosion(new Ubicacion(this.ubicacion.getPosX(), this.ubicacion.getPosY() + i), map);
		}
	}

	/**
	 * Crea una explosión en un punto único del mapa.
	 * 
	 * @param ubic
	 *            ubicación a crear la explosión
	 * @param map
	 *            mapa del juego
	 * @return true si encontró un obstaculo o un muro, false si no encontró
	 */

	private boolean explosion(Ubicacion ubic, ServerMap map) {		
		// Buscamos una entidad en dicha ubicación. Solo puede haber una, así
		// que buscamos la que esté ahí
		SvEntidad ent = map.obtenerEntidadDelConjunto(ubic);
		SvBomberman bomber = map.obtenerBombermanEn(ubic);

		// en la ubicacion no explota.
		if (bomber != null && bomber.esVisible == true) {
			bomber.morir();
		}

		//ent != this no queremos explotarnos de vuelta a nosotros mismos
		if (ent != null && ent.esVisible && ent != this) {
			//¡Polimorfismo!
			ent.explotar(map);
			return true;
		}
		
		//Creamos una explosion (el grafico) en la ubicacion
		Explosion expl = new Explosion(ubic.getPosX()*DefConst.TILESIZE, ubic.getPosY()*DefConst.TILESIZE, DefConst.TILESIZE, DefConst.TILESIZE);
		expl.startTimer(map);
		map.agregarEntidadAlConjunto(expl.ubicacion, expl);
		
		return false;
	}

	public int getMsExplosion() {
		return this.segsExplosion * 1000;
	}
	
	public boolean hayColisionConCreador(SvBomberman bman) {
		if (bman.equals(this.creador)) {
			return !this.ignorarColisionCreador;
		}
		
		return true;
	}
	
	public void startTimer(ServerMap map) {
		timer = new Timer(getMsExplosion(), new miOyente(map, this));
		timer.start();
	}
	
	public void stopTimer() {
		timer.stop();
	}
	
	class miOyente implements ActionListener {
		private ServerMap map;
		private SvBomba bomba;
		
		public miOyente(ServerMap map, SvBomba bomba) {
			this.map = map;
			this.bomba = bomba;
		}
		
		@Override
		public void actionPerformed(ActionEvent ae) {
			if (bomba.esVisible) {
				this.bomba.explotar(map);
			}			
			timer.stop();
		}
	}
}
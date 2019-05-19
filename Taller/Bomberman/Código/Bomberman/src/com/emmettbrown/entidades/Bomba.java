package com.emmettbrown.entidades;

import javax.swing.ImageIcon;

import com.emmettbrown.mapa.Mapa;
import com.emmettbrown.mapa.Ubicacion;
import com.emmettbrown.principal.Motor;

public class Bomba extends Entidad {
	private static int nroBomba = 0;
	private int idBomba;
	private int segsExplosion;
	private int rango;

	///////////////////////////////////////
	// 									//
	// 			CONSTRUCTORES			//
	// 									//
	/////////////////////////////////////

	public Bomba(int posX, int posY) {
		super(posX, posY, Motor.tileSize, Motor.tileSize);
		idBomba = nroBomba++;
		segsExplosion = 3;
		this.destructible = true;
		this.rango = 2;
		this.img = new ImageIcon("./src/resources/bomb/bomba.png");
	}

	public Bomba(Ubicacion ubic) {
		super(ubic);
		idBomba = nroBomba++;
		segsExplosion = 3;
		this.destructible = true;
		this.rango = 2;
		this.img = new ImageIcon("./src/resources/bomb/bomba.png");
	}

	///////////////////////////////////////
	// 									//
	// 			GETTERS Y SETTERS 	   //
	// 								  //
	///////////////////////////////////

	public int getIdBomba() {
		return idBomba;
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
	 * @param map:
	 *            el mapa del juego
	 */

	public void explotar(Mapa map) {
		//System.out.println("BUM, la bomba " + idBomba + " Exploto");
		// Seteamos visible = false para dejar de renderizar la bomba
		this.cambiarVisibilidad();

		// Creamos una "explosión" en en lugar
		explosion(new Ubicacion(this.ubicacion.getPosX(), this.ubicacion.getPosY()), map);

		// Creamos "explosiones" en las cuatro direcciones, dependiendo del rango
		explosionIzquierda(map);
		explosionDerecha(map);
		explosionArriba(map);
		explosionAbajo(map);

		// Removemos la bomba de la lista de entidades
		map.removerEntidadDelConjunto(this.ubicacion);
	}

	private void explosionIzquierda(Mapa map) {
		boolean hayObstaculo = false;

		// Se ejecuta hasta llegar al rango maximo, o toparse con un
		// obstaculo/muro siempre dentro del ANCHO y ALTO
		for (int i = 1; i <= this.rango && !hayObstaculo && this.ubicacion.getPosX() - i >= 0;  i++) {
			hayObstaculo = explosion(new Ubicacion(this.ubicacion.getPosX() - i, this.ubicacion.getPosY()), map);
			
			if (!hayObstaculo) {
				Explosion expl = new Explosion((ubicacion.getPosX()-i)*Motor.tileSize, ubicacion.getPosY()*Motor.tileSize, Motor.tileSize, Motor.tileSize);
				expl.ubicacion = new Ubicacion(ubicacion.getPosX()-i, ubicacion.getPosY());
				expl.startTimer(map);
				map.agregarEntidadAlConjunto(expl.ubicacion, expl);
			}
		}
	}

	private void explosionDerecha(Mapa map) {
		boolean hayObstaculo = false;

		// Se ejecuta hasta llegar al rango maximo, o toparse con un
		// obstaculo/muro siempre dentro del ANCHO y ALTO
		for (int i = 1; i <= this.rango && !hayObstaculo && this.ubicacion.getPosX() + i < Mapa.ANCHO;  i++) {
			hayObstaculo = explosion(new Ubicacion(this.ubicacion.getPosX() + i, this.ubicacion.getPosY()), map);
			
			if (!hayObstaculo) {
				Explosion expl = new Explosion((ubicacion.getPosX()+i)*Motor.tileSize, ubicacion.getPosY()*Motor.tileSize, Motor.tileSize, Motor.tileSize);
				expl.ubicacion = new Ubicacion(ubicacion.getPosX()+i, ubicacion.getPosY());
				expl.startTimer(map);
				map.agregarEntidadAlConjunto(expl.ubicacion, expl);
			}
		}
	}

	private void explosionArriba(Mapa map) {
		boolean hayObstaculo = false;

		// Se ejecuta hasta llegar al rango maximo, o toparse con un
		// obstaculo/muro siempre dentro del ANCHO y ALTO
		for (int i = 1; i <= this.rango && !hayObstaculo && this.ubicacion.getPosY() - i >= 0 ; i++) {
			hayObstaculo = explosion(new Ubicacion(this.ubicacion.getPosX(), this.ubicacion.getPosY() - i), map);
			
			if (!hayObstaculo) {
				Explosion expl = new Explosion(ubicacion.getPosX()*Motor.tileSize, (ubicacion.getPosY()-i)*Motor.tileSize, Motor.tileSize, Motor.tileSize);
				expl.ubicacion = new Ubicacion(ubicacion.getPosX(), ubicacion.getPosY()-i);
				expl.startTimer(map);
				map.agregarEntidadAlConjunto(expl.ubicacion, expl);
			}
		}
	}

	private void explosionAbajo(Mapa map) {
		boolean hayObstaculo = false;

		// Se ejecuta hasta llegar al rango maximo, o toparse con un
		// obstaculo/muro siempre dentro del ANCHO y ALTO
		for (int i = 1; i <= this.rango && !hayObstaculo && this.ubicacion.getPosY() + i <= Mapa.ALTO; i++) {
			hayObstaculo = explosion(new Ubicacion(this.ubicacion.getPosX(), this.ubicacion.getPosY() + i), map);
			
			if (!hayObstaculo) {
				Explosion expl = new Explosion(ubicacion.getPosX()*Motor.tileSize, (ubicacion.getPosY()+i)*Motor.tileSize, Motor.tileSize, Motor.tileSize);
				expl.ubicacion = new Ubicacion(ubicacion.getPosX(), ubicacion.getPosY()+i);
				expl.startTimer(map);
				map.agregarEntidadAlConjunto(expl.ubicacion, expl);
			}
		}
	}

	/**
	 * Crea una explosión en un punto único del mapa.
	 * 
	 * @param ubic:
	 *            ubicación a crear la explosión
	 * @param map:
	 *            mapa del juego
	 * @return true si encontró un obstaculo o un muro, false si no encontró
	 */

	private boolean explosion(Ubicacion ubic, Mapa map) {		
		// Buscamos una entidad en dicha ubicación. Solo puede haber una, así
		// que buscamos la que esté ahí
		Entidad ent = map.obtenerEntidadDelConjunto(ubic);
		Bomberman bomber = map.obtenerBombermanEn(ubic);

		// en la ubicacion no explota.
		if (bomber != null) {
			bomber.morir();
		}

		if (ent != null && ent.esVisible) {
			// Si la entidad es una instancia de bomba...
			if (ent instanceof Bomba) {
				// Casteo furioso a Bomba
				((Bomba) ent).explotar(map);
				return false;
			} else if (ent instanceof Obstaculo) {
				// Casteo furioso a Obstaculo
				((Obstaculo) ent).destruir();
				// Removemos a la entidad
				map.removerEntidadDelConjunto(ubic);
				// Encontramos un obstaculo, retornamos true
				return true;
			} else if (ent instanceof Muro) {
				// Encontramos un muro, retornamos true
				return true;
			}
		}
		return false;
	}

	public int getMs() {
		return this.segsExplosion * 1000;
	}

}

package com.emmettbrown.entidades;

import com.emmettbrown.mapa.Mapa;
import com.emmettbrown.mapa.Ubicacion;

public class Bomba extends Entidad {
	private static int nroBomba = 0;
	private int idBomba;
	private int segsExplosion;
	private int rango;

	
	/////////////////////////////////////// 
	//								    //
	//	       CONSTRUCTORES 		   //
	//								  //
	///////////////////////////////////	
	
	public Bomba(int posIniX, int posIniY) {
		super(posIniX, posIniY);
		idBomba = nroBomba++;
		segsExplosion = 3;
		this.destructible = true;
		this.rango = 1;
	}

	public Bomba(Ubicacion ubic) {
		super(ubic);
		idBomba = nroBomba++;
		segsExplosion = 3;
		this.destructible = true;
		this.rango = 1;
	}

	/////////////////////////////////////// 
	//								    //
	//	      GETTERS Y SETTERS		   //
	//								  //
	///////////////////////////////////	
	
	public int getIdBomba() {
		return idBomba;
	}
	
	/////////////////////////////////////// 
	//								    //
	//	      	  METODOS 		       //
	//								  //
	///////////////////////////////////	
	
	/** Realiza un par de acciones comunes (remover la bomba, setear visibilidad en false), y crea explosiones
	 * en las cuatro direcciones hasta llegar al rango maximo... o toparse con un obstaculo/muro
	 * 
	 * @param map: el mapa del juego
	 */
	
	public void explotar(Mapa map) {
		System.out.println("BUM, la bomba " + idBomba + " Exploto");
		//Seteamos visible = false para dejar de renderizar la bomba
		this.cambiarVisibilidad();
		
		//Creamos una "explosión" en en lugar
		explosion(new Ubicacion(this.ubicacion.getPosX(), this.ubicacion.getPosY()), map);
		
		//Creamos "explosiones" en las cuatro direcciones, dependiendo del rango
		explosionIzquierda(map);
		explosionDerecha(map);
		explosionArriba(map);
		explosionAbajo(map);
		
		//Removemos la bomba de la lista de entidades
		map.removerEntidadDelConjunto(this.ubicacion);
	}
	
	private void explosionIzquierda(Mapa map) {
		boolean hayObstaculo = false;		
		
		//Se ejecuta hasta llegar al rango maximo, o toparse con un obstaculo/muro
		for (int i = 1; i <= this.rango && !hayObstaculo; i++) {			
			hayObstaculo = explosion(new Ubicacion(this.ubicacion.getPosX()-i, this.ubicacion.getPosY()), map);
		}
	}
	
	private void explosionDerecha(Mapa map) {
		boolean hayObstaculo = false;		
		
		//Se ejecuta hasta llegar al rango maximo, o toparse con un obstaculo/muro
		for (int i = 1; i <= this.rango && !hayObstaculo; i++) {			
			hayObstaculo = explosion(new Ubicacion(this.ubicacion.getPosX()+i, this.ubicacion.getPosY()), map);
		}
	}
	
	private void explosionArriba(Mapa map) {
		boolean hayObstaculo = false;		
		
		//Se ejecuta hasta llegar al rango maximo, o toparse con un obstaculo/muro
		for (int i = 1; i <= this.rango && !hayObstaculo; i++) {			
			hayObstaculo = explosion(new Ubicacion(this.ubicacion.getPosX(), this.ubicacion.getPosY()-i), map);
		}
	}
	
	private void explosionAbajo(Mapa map) {
		boolean hayObstaculo = false;		
		
		//Se ejecuta hasta llegar al rango maximo, o toparse con un obstaculo/muro
		for (int i = 1; i <= this.rango && !hayObstaculo; i++) {			
			hayObstaculo = explosion(new Ubicacion(this.ubicacion.getPosX()+i, this.ubicacion.getPosY()+i), map);
		}
	}
	
	/** Crea una explosión en un punto único del mapa. 
	 * 
	 * @param ubic: ubicación a crear la explosión
	 * @param map: mapa del juego
	 * @return true si encontró un obstaculo o un muro, false si no encontró
	 */
	
	private boolean explosion(Ubicacion ubic, Mapa map) {
		//Buscamos una entidad en dicha ubicación. Solo puede haber una, así que buscamos 
		//la que esté ahí
		Entidad ent = map.obtenerEntidadEn(ubic);
		
		if (ent != null && ent.destructible && ent.esVisible) {
			//Si la entidad es una instancia de bomba...
			if (ent instanceof Bomba) {
				//Casteo furioso a Bomba
				((Bomba) ent).explotar(map);
				return false;
			} else if (ent instanceof Obstaculo) {
				//Casteo furioso a Obstaculo
				((Obstaculo) ent).destruir();
				//Removemos a la entidad
				map.removerEntidadDelConjunto(ubic);	
				//Encontramos un obstaculo, retornamos true
				return true;				
			} else if (ent instanceof Muro) {
				//Encontramos un muro, retornamos true
				return true;
			}			
			else if (ent instanceof Bomberman) {
				//Casteo furioso a Bomberman
				((Bomberman) ent).morir();
				return false;
			}
		}		
		return false;
	}	
}

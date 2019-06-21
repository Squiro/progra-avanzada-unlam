package com.emmettbrown.servidor.mapa;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;

import javax.swing.ImageIcon;

import com.emmettbrown.servidor.entidades.Bomberman;
import com.emmettbrown.servidor.entidades.Entidad;
import com.emmettbrown.entidades.DefConst;
import com.emmettbrown.servidor.entidades.Muro;
import com.emmettbrown.servidor.entidades.Obstaculo;
import com.emmettbrown.mapa.Ubicacion;

public class ServerMap {
	
	private HashMap<Ubicacion, Entidad> conjuntoEntidades;
	private List<Bomberman> listaBomberman;
	
	///////////////////////////////////////
	// 									//
	// 			CONSTUCTORES		   //
	// 								  //
	///////////////////////////////////

	public ServerMap() {
		conjuntoEntidades = new HashMap<Ubicacion, Entidad>();
		listaBomberman = new ArrayList<Bomberman>();
	}

	////////////////////////////////////////
	// 									 //
	// 				METODOS 			//
	// 								   //
	////////////////////////////////////

	public void generarMapa() {
			for(int l = 0;l<DefConst.ALTOMAPA;l++) {
				conjuntoEntidades.put(new Ubicacion(0, l), new Muro(0 * DefConst.TILESIZE, l * DefConst.TILESIZE));
				conjuntoEntidades.put(new Ubicacion(l, 0), new Muro(l * DefConst.TILESIZE, 0 * DefConst.TILESIZE));
				conjuntoEntidades.put(new Ubicacion(DefConst.ANCHOMAPA-1, l), new Muro((DefConst.ANCHOMAPA-1) *DefConst.TILESIZE, l * DefConst.TILESIZE));
				conjuntoEntidades.put(new Ubicacion(l, DefConst.ALTOMAPA-1), new Muro(l * DefConst.TILESIZE, (DefConst.ALTOMAPA-1)* DefConst.TILESIZE));
			}
				
		for (int i = 1; i < DefConst.ANCHOMAPA-1; i++) {
			for (int j = 1; j < DefConst.ALTOMAPA-1; j++) {
				if (i % 2 == 0 && j % 2 == 0) { 
					//EN LAS POSICIONES I,J IMPARES PONDREMOS INDESTRUCTIBLES, EN CASO
					//CONTRARIO EVALUAREMOS PONER OBSTACULOS
					conjuntoEntidades.put(new Ubicacion(i, j), new Muro(i * DefConst.TILESIZE, j * DefConst.TILESIZE));
				} else if ((posicionValida(i, j)) && Math.random() >= DefConst.TOLCREAROBST) {
					//75% DE PROBABILIDAD DE CREAR UN OBSTACULO
					conjuntoEntidades.put(new Ubicacion(i, j), new Obstaculo(i * DefConst.TILESIZE, j * DefConst.TILESIZE));
				}
			}
		}
	}
	/// LIBERA LAS POSICIONES 11 12 21 PARA QUE EL BOMBERMAN PUEDA RESPAWNEAR EN ESAS POSICIONES
	private boolean posicionValida(int posX, int posY) {
		if (posX == 1 && posY == 1 || posX == 1 && posY == 2 || posX == 2 && posY == 1) {
			return false;
		}

		if (posX == DefConst.ANCHOMAPA - 2 && posY == 1 || posX == DefConst.ANCHOMAPA - 3 && posY == 1 || posX == DefConst.ANCHOMAPA - 2 && posY == 2) {
			return false;
		}

		if (posX == 1 && posY == DefConst.ALTOMAPA - 2 || posX == 1 && posY == DefConst.ALTOMAPA - 3 || posX == 2 && posY == DefConst.ALTOMAPA - 2) {
			return false;
		}

		if (posX == DefConst.ANCHOMAPA - 2 && posY == DefConst.ALTOMAPA - 2 || posX == DefConst.ANCHOMAPA - 3 && posY == DefConst.ALTOMAPA - 2
				|| posX == DefConst.ANCHOMAPA - 2 && posY == DefConst.ALTOMAPA - 3) {
			return false;
		}
		return true;
	}
	
	
	///////////////////////////////////////
	// 									//
	// 			ENTIDADES 				//
	// 									//
	/////////////////////////////////////

	public HashMap<Ubicacion, Entidad> obtenerListaEntidades() {
		return conjuntoEntidades;
	}

	/**
	 * Busca todas las posibles entidades en una ubicacion.
	 * 
	 * @param ubic la ubicacion a buscar.
	 * @return
	 */

	public Entidad obtenerEntidadEn(Ubicacion ubic) {
		Entidad ent;

		ent = conjuntoEntidades.get(ubic);

		if (ent != null)
			return ent;

		ent = obtenerBombermanEn(ubic);

		if (ent != null)
			return ent;

		return null;
	}

	/**
	 * Devuelve una entidad del HashMap de entidades del mapa.
	 * 
	 * @param ubic la ubicacion en la que se encuentra la entidad (KEY).
	 * @return
	 */

	public Entidad obtenerEntidadDelConjunto(Ubicacion ubic) {
		return conjuntoEntidades.get(ubic);
	}

	/**
	 * Remueve una entidad del Treemap de entidades del mapa.
	 * 
	 * @param ubic: la ubicacion de la entidad
	 */

	public void removerEntidadDelConjunto(Ubicacion ubic) {
		conjuntoEntidades.remove(ubic);
	}
	
	public void agregarEntidadAlConjunto(Ubicacion ubic, Entidad ent) {
		if(conjuntoEntidades.containsKey(ubic)==false)
			conjuntoEntidades.put(ubic, ent);
	}
	
	/**
	 * Agrega un bomberman nuevo a la lista de bombermans del mapa.
	 * 
	 * @param b bomberman a agregar
	 */

	public void agregarBomberman(Bomberman b) {
		this.listaBomberman.add(b);
	}

	/**
	 * Retorna la lista de bombermans del mapa.
	 * 
	 * @return List<Bomberman> listaBomberman
	 */

	public List<Bomberman> obtenerListaBomberman() {
		return listaBomberman;
	}	

	/**
	 * Recorre la lista de bombermans y retorna al que encuentre en la ubicacion
	 * indicada.
	 * 
	 * @param ubic la ubicacion a buscar
	 * @return instanceof Bomberman si lo encuentra, null si no
	 */

	public Bomberman obtenerBombermanEn(Ubicacion ubic) {
		for (Bomberman bomberman : listaBomberman) {
			if (bomberman.obtenerUbicacion().equals(ubic))
				return bomberman;
		}

		return null;
	}

}

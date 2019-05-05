package com.emmettbrown.entidades;

import com.emmettbrown.mapa.*;
import java.util.Map;

public class Bomba extends Entidad {
	private static int nroBomba = 0;
	private int idBomba;
	private int segsExplosion;
	private int rango;

	public Bomba(final int posIniX, final int posIniY) {
		super(posIniX, posIniY);
		idBomba = nroBomba;
		nroBomba++;
		segsExplosion = 3;
		this.destructible = true;
		this.rango = 2;
	}

	public Bomba(Ubicacion miUbicacion) {
		super(miUbicacion);
		idBomba = nroBomba;
		nroBomba++;
		segsExplosion = 3;
	}

	private boolean explotarEnCadena(Ubicacion ubic, Mapa map) {
		Bomberman[] listaBomb = map.obtenerBombermans();
		Map<Ubicacion, Entidad> listaEnt = map.obtenerListaEntidades();
		Entidad ent = listaEnt.get(ubic);
		Bomba exp;
		if (ent != null && ent.destructible && ent.esVisible == true) {

			if (ent.getClass().getSimpleName().equals("Bomba") ) {
				exp = (Bomba) ent;
				exp.explotar(map);

			} else if (ent.getClass().getSimpleName().equals("Obstaculo")) {
				((Obstaculo)ent).destruir();
				listaEnt.remove(ubic);
			}

			return true;

		} else if (ent != null && !ent.destructible) {
			return false;
		}
		if (listaBomb != null) {
			for (int j = 0; j < listaBomb.length; j++) {
				if (listaBomb[j].ubicacion.equals(ubic)) {
					listaBomb[j].morir();
				}
			}			
		}

		return false;
	}

	public void explotar(Mapa m) {
		System.out.println("BUM, la bomba " + idBomba + " Exploto");
		this.cambiarVisibilidad();
		explotarEnCadena(new Ubicacion(this.ubicacion.getPosX(),this.ubicacion.getPosY()),m);	
		explotarEnCadena(new Ubicacion(this.ubicacion.getPosX()+1,this.ubicacion.getPosY()),m);	
		explotarEnCadena(new Ubicacion(this.ubicacion.getPosX()-1,this.ubicacion.getPosY()),m);	
		explotarEnCadena(new Ubicacion(this.ubicacion.getPosX(),this.ubicacion.getPosY()+1),m);	
		explotarEnCadena(new Ubicacion(this.ubicacion.getPosX(),this.ubicacion.getPosY()-1),m);	
		m.eliminarBomba(this.ubicacion);
	}
}

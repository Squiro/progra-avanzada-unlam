package com.emmettbrown.entidades;

import com.emmettbrown.mapa.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestBomberman {

	@Test
	public void seMueveElBombermanArriba() {
		Mapa m = new Mapa();
		Bomberman[] listaBomb = new Bomberman[2];
		listaBomb[0] = new Bomberman(0, 4, m);
		m.agregarBombermans(listaBomb);
		listaBomb[0].moverArriba();
		listaBomb[0].moverArriba();
		listaBomb[0].moverArriba();
		listaBomb[0].moverArriba();
		Ubicacion expected = new Ubicacion(0, 0);
		Ubicacion actual = listaBomb[0].obtenerUbicacion();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void seMueveElBombermanAbajo() {
		Mapa m = new Mapa();
		Bomberman[] listaBomb = new Bomberman[2];
		listaBomb[0] = new Bomberman(0, 0, m);
		m.agregarBombermans(listaBomb);
		listaBomb[0].moverAbajo();		
		listaBomb[0].moverAbajo();		
		listaBomb[0].moverAbajo();			
		listaBomb[0].moverAbajo();
		Ubicacion expected = new Ubicacion(0, 4);
		Ubicacion actual = listaBomb[0].obtenerUbicacion();
		Assert.assertEquals(expected, actual);
	}	
	
	
	@Test
	public void seMueveElBombermanDer() {
		Mapa m = new Mapa();
		Bomberman[] listaBomb = new Bomberman[2];
		listaBomb[0] = new Bomberman(0, 4, m);
		m.agregarBombermans(listaBomb);
		listaBomb[0].moverDerecha();
		listaBomb[0].moverDerecha();
		listaBomb[0].moverDerecha();
		listaBomb[0].moverDerecha();
		Ubicacion expected = new Ubicacion(4, 4);
		Ubicacion actual = listaBomb[0].obtenerUbicacion();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void seMueveElBombermanIzq() {
		Mapa m = new Mapa();
		Bomberman[] listaBomb = new Bomberman[2];
		listaBomb[0] = new Bomberman(4, 0, m);
		m.agregarBombermans(listaBomb);
		listaBomb[0].moverIzquierda();
		listaBomb[0].moverIzquierda();
		listaBomb[0].moverIzquierda();
		listaBomb[0].moverIzquierda();
		Ubicacion expected = new Ubicacion(0, 0);
		Ubicacion actual = listaBomb[0].obtenerUbicacion();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void queColisionaExtremoMapaArriba() {
		Mapa m = new Mapa();
		Bomberman[] listaBomb = new Bomberman[1];
		listaBomb[0] = new Bomberman(0, 0, m);
		m.agregarBombermans(listaBomb);
		listaBomb[0].moverArriba();
		Ubicacion expected = new Ubicacion(0, 0);
		Ubicacion actual = listaBomb[0].obtenerUbicacion();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void queColisionaExtremoMapaAbajo() {
		Mapa m = new Mapa();
		Bomberman[] listaBomb = new Bomberman[1];
		listaBomb[0] = new Bomberman(0, 4, m);
		m.agregarBombermans(listaBomb);
		listaBomb[0].moverAbajo();
		Ubicacion expected = new Ubicacion(0, 4);
		Ubicacion actual = listaBomb[0].obtenerUbicacion();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void queColisionaExtremoMapaIzquierda() {
		Mapa m = new Mapa();
		Bomberman[] listaBomb = new Bomberman[1];
		listaBomb[0] = new Bomberman(0, 0, m);
		m.agregarBombermans(listaBomb);
		listaBomb[0].moverIzquierda();
		Ubicacion expected = new Ubicacion(0, 0);
		Ubicacion actual = listaBomb[0].obtenerUbicacion();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void queColisionaExtremoMapaDerecha() {
		Mapa m = new Mapa();
		Bomberman[] listaBomb = new Bomberman[1];
		listaBomb[0] = new Bomberman(4, 0, m);
		m.agregarBombermans(listaBomb);
		listaBomb[0].moverDerecha();
		Ubicacion expected = new Ubicacion(4, 0);
		Ubicacion actual = listaBomb[0].obtenerUbicacion();
		Assert.assertEquals(expected, actual);
	}
	@Test
	public void queColisionaConBombaIzquierda() {
		Mapa m = new Mapa();
		Bomberman[] listaBomb = new Bomberman[1];
		listaBomb[0] = new Bomberman(0, 0, m);
		m.agregarBombermans(listaBomb);
		listaBomb[0].ponerBomba();
		listaBomb[0].moverDerecha();
		listaBomb[0].moverIzquierda();
		Assert.assertEquals(new Ubicacion(1, 0), listaBomb[0].obtenerUbicacion());
	}
	@Test
	public void queColisionaConBombaDerecha() {
		Mapa m = new Mapa();
		Bomberman[] listaBomb = new Bomberman[1];
		listaBomb[0] = new Bomberman(4, 0, m);
		m.agregarBombermans(listaBomb);
		listaBomb[0].ponerBomba();
		listaBomb[0].moverIzquierda();
		listaBomb[0].moverDerecha();
		Assert.assertEquals(new Ubicacion(3, 0), listaBomb[0].obtenerUbicacion());
	}
	@Test
	public void queColisionaConBombaAbajo() {
		Mapa m = new Mapa();
		Bomberman[] listaBomb = new Bomberman[1];
		listaBomb[0] = new Bomberman(0, 4, m);
		m.agregarBombermans(listaBomb);
		listaBomb[0].ponerBomba();
		listaBomb[0].moverArriba();
		listaBomb[0].moverAbajo();
		Assert.assertEquals(new Ubicacion(0, 3), listaBomb[0].obtenerUbicacion());
	}
	@Test
	public void queColisionaConBombaArriba() {
		Mapa m = new Mapa();
		Bomberman[] listaBomb = new Bomberman[1];
		listaBomb[0] = new Bomberman(0, 0, m);
		m.agregarBombermans(listaBomb);
		listaBomb[0].ponerBomba();
		listaBomb[0].moverAbajo();
		listaBomb[0].moverArriba();
		
		Assert.assertEquals(new Ubicacion(0, 1), listaBomb[0].obtenerUbicacion());
	}
}

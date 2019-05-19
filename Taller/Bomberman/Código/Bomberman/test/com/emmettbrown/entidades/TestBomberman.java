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
		Bomberman bman = new Bomberman(0, 4, Bomberman.defaultHeight, Bomberman.defaultWidth);

		m.agregarBomberman(bman);		
		m.moverBombermanArriba(bman, Bomberman.VELOCIDAD);
		m.moverBombermanArriba(bman, Bomberman.VELOCIDAD);
		m.moverBombermanArriba(bman, Bomberman.VELOCIDAD);
		m.moverBombermanArriba(bman, Bomberman.VELOCIDAD);
		Ubicacion expected = new Ubicacion(0, 0);
		Ubicacion actual = bman.obtenerUbicacion();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void seMueveElBombermanAbajo() {
		Mapa m = new Mapa();
		Bomberman bman = new Bomberman(0, 0, Bomberman.defaultHeight, Bomberman.defaultWidth);
		
		m.agregarBomberman(bman);	
		m.moverBombermanAbajo(bman, Bomberman.VELOCIDAD);
		m.moverBombermanAbajo(bman, Bomberman.VELOCIDAD);
		m.moverBombermanAbajo(bman, Bomberman.VELOCIDAD);
		m.moverBombermanAbajo(bman, Bomberman.VELOCIDAD);
		Ubicacion expected = new Ubicacion(0, 4);
		Ubicacion actual = bman.obtenerUbicacion();
		Assert.assertEquals(expected, actual);
	}	
	
	
	@Test
	public void seMueveElBombermanDer() {
		Mapa m = new Mapa();
		Bomberman bman = new Bomberman(0, 4, Bomberman.defaultHeight, Bomberman.defaultWidth);	

		m.agregarBomberman(bman);
		m.moverBombermanDer(bman, Bomberman.VELOCIDAD);
		m.moverBombermanDer(bman, Bomberman.VELOCIDAD);
		m.moverBombermanDer(bman, Bomberman.VELOCIDAD);
		m.moverBombermanDer(bman, Bomberman.VELOCIDAD);
		Ubicacion expected = new Ubicacion(4, 4);
		Ubicacion actual = bman.obtenerUbicacion();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void seMueveElBombermanIzq() {
		Mapa m = new Mapa();
		Bomberman bman = new Bomberman(4, 0, Bomberman.defaultHeight, Bomberman.defaultWidth);	
		
		m.agregarBomberman(bman);
		m.moverBombermanIzq(bman, Bomberman.VELOCIDAD);
		m.moverBombermanIzq(bman, Bomberman.VELOCIDAD);
		m.moverBombermanIzq(bman, Bomberman.VELOCIDAD);
		m.moverBombermanIzq(bman, Bomberman.VELOCIDAD);
		Ubicacion expected = new Ubicacion(0, 0);
		Ubicacion actual = bman.obtenerUbicacion();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void queColisionaExtremoMapaArriba() {
		Mapa m = new Mapa();
		Bomberman bman = new Bomberman(0, 0, Bomberman.defaultHeight, Bomberman.defaultWidth);

		m.agregarBomberman(bman);
		m.moverBombermanArriba(bman, Bomberman.VELOCIDAD);
		Ubicacion expected = new Ubicacion(0, 0);
		Ubicacion actual = bman.obtenerUbicacion();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void queColisionaExtremoMapaAbajo() {
		Mapa m = new Mapa();
		Bomberman bman = new Bomberman(0, 9, Bomberman.defaultHeight, Bomberman.defaultWidth);
		
		m.agregarBomberman(bman);
		m.moverBombermanAbajo(bman, Bomberman.VELOCIDAD);
		Ubicacion expected = new Ubicacion(0, 9);
		Ubicacion actual = bman.obtenerUbicacion();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void queColisionaExtremoMapaIzquierda() {
		Mapa m = new Mapa();
		Bomberman bman = new Bomberman(0, 0, Bomberman.defaultHeight, Bomberman.defaultWidth);
		
		m.agregarBomberman(bman);
		m.moverBombermanIzq(bman, Bomberman.VELOCIDAD);
		Ubicacion expected = new Ubicacion(0, 0);
		Ubicacion actual = bman.obtenerUbicacion();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void queColisionaExtremoMapaDerecha() {
		Mapa m = new Mapa();
		Bomberman bman = new Bomberman(9, 0, Bomberman.defaultHeight, Bomberman.defaultWidth);
		
		m.agregarBomberman(bman);
		m.moverBombermanDer(bman, Bomberman.VELOCIDAD);
		Ubicacion expected = new Ubicacion(9, 0);
		Ubicacion actual = bman.obtenerUbicacion();
		Assert.assertEquals(expected, actual);
	}
	@Test
	public void queColisionaConBombaIzquierda() {
		Mapa m = new Mapa();
		Bomberman bman = new Bomberman(0, 0, Bomberman.defaultHeight, Bomberman.defaultWidth);
		
		m.agregarBomberman(bman);
		m.agregarBomba(bman.getX(), bman.getY());		
		m.moverBombermanDer(bman, Bomberman.VELOCIDAD);
		m.moverBombermanIzq(bman, Bomberman.VELOCIDAD);
		Assert.assertEquals(new Ubicacion(1, 0), bman.obtenerUbicacion());
	}
	@Test
	public void queColisionaConBombaDerecha() {
		Mapa m = new Mapa();
		Bomberman bman = new Bomberman(4, 0, Bomberman.defaultHeight, Bomberman.defaultWidth);
		
		m.agregarBomberman(bman);
		m.agregarBomba(bman.getX(), bman.getY());
		m.moverBombermanIzq(bman, Bomberman.VELOCIDAD);
		m.moverBombermanDer(bman, Bomberman.VELOCIDAD);
		Assert.assertEquals(new Ubicacion(3, 0), bman.obtenerUbicacion());
	}
	@Test
	public void queColisionaConBombaAbajo() {
		Mapa m = new Mapa();
		Bomberman bman = new Bomberman(0, 4, Bomberman.defaultHeight, Bomberman.defaultWidth);		
				
		m.agregarBomberman(bman);
		m.agregarBomba(bman.getX(), bman.getY());
		m.moverBombermanArriba(bman, Bomberman.VELOCIDAD);
		m.moverBombermanAbajo(bman, Bomberman.VELOCIDAD);
		Assert.assertEquals(new Ubicacion(0, 3), bman.obtenerUbicacion());
	}
	@Test
	public void queColisionaConBombaArriba() {
		Mapa m = new Mapa();
		Bomberman bman = new Bomberman(0, 0, Bomberman.defaultHeight, Bomberman.defaultWidth);
		
		m.agregarBomberman(bman);
		m.agregarBomba(bman.getX(), bman.getY());
		m.moverBombermanAbajo(bman, Bomberman.VELOCIDAD);
		m.moverBombermanArriba(bman, Bomberman.VELOCIDAD);		
		Assert.assertEquals(new Ubicacion(0, 1), bman.obtenerUbicacion());
	}
}

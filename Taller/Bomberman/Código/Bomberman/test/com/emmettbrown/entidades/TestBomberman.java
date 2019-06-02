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
		Bomberman bman = new Bomberman(0, 4, DefConst.DEFAULTHEIGHT, DefConst.DEFAULTWIDTH);

		m.agregarBomberman(bman);		
		m.moverBombermanArriba(bman, DefConst.VELOCIDAD);
		m.moverBombermanArriba(bman, DefConst.VELOCIDAD);
		m.moverBombermanArriba(bman, DefConst.VELOCIDAD);
		m.moverBombermanArriba(bman, DefConst.VELOCIDAD);
		Ubicacion expected = new Ubicacion(0, 0);
		Ubicacion actual = bman.obtenerUbicacion();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void seMueveElBombermanAbajo() {
		Mapa m = new Mapa();
		Bomberman bman = new Bomberman(0, 0, DefConst.DEFAULTHEIGHT, DefConst.DEFAULTWIDTH);
		
		m.agregarBomberman(bman);	
		m.moverBombermanAbajo(bman, DefConst.VELOCIDAD);
		m.moverBombermanAbajo(bman, DefConst.VELOCIDAD);
		m.moverBombermanAbajo(bman, DefConst.VELOCIDAD);
		m.moverBombermanAbajo(bman, DefConst.VELOCIDAD);
		Ubicacion expected = new Ubicacion(0, 4);
		Ubicacion actual = bman.obtenerUbicacion();
		Assert.assertEquals(expected, actual);
	}	
	
	
	@Test
	public void seMueveElBombermanDer() {
		Mapa m = new Mapa();
		Bomberman bman = new Bomberman(0, 4, DefConst.DEFAULTHEIGHT, DefConst.DEFAULTWIDTH);	

		m.agregarBomberman(bman);
		m.moverBombermanDer(bman, DefConst.VELOCIDAD);
		m.moverBombermanDer(bman, DefConst.VELOCIDAD);
		m.moverBombermanDer(bman, DefConst.VELOCIDAD);
		m.moverBombermanDer(bman, DefConst.VELOCIDAD);
		Ubicacion expected = new Ubicacion(4, 4);
		Ubicacion actual = bman.obtenerUbicacion();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void seMueveElBombermanIzq() {
		Mapa m = new Mapa();
		Bomberman bman = new Bomberman(4, 0, DefConst.DEFAULTHEIGHT, DefConst.DEFAULTWIDTH);	
		
		m.agregarBomberman(bman);
		m.moverBombermanIzq(bman, DefConst.VELOCIDAD);
		m.moverBombermanIzq(bman, DefConst.VELOCIDAD);
		m.moverBombermanIzq(bman, DefConst.VELOCIDAD);
		m.moverBombermanIzq(bman, DefConst.VELOCIDAD);
		Ubicacion expected = new Ubicacion(0, 0);
		Ubicacion actual = bman.obtenerUbicacion();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void queColisionaExtremoMapaArriba() {
		Mapa m = new Mapa();
		Bomberman bman = new Bomberman(0, 0, DefConst.DEFAULTHEIGHT, DefConst.DEFAULTWIDTH);

		m.agregarBomberman(bman);
		m.moverBombermanArriba(bman, DefConst.VELOCIDAD);
		Ubicacion expected = new Ubicacion(0, 0);
		Ubicacion actual = bman.obtenerUbicacion();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void queColisionaExtremoMapaAbajo() {
		Mapa m = new Mapa();
		Bomberman bman = new Bomberman(0, 9, DefConst.DEFAULTHEIGHT, DefConst.DEFAULTWIDTH);
		
		m.agregarBomberman(bman);
		m.moverBombermanAbajo(bman, DefConst.VELOCIDAD);
		Ubicacion expected = new Ubicacion(0, 9);
		Ubicacion actual = bman.obtenerUbicacion();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void queColisionaExtremoMapaIzquierda() {
		Mapa m = new Mapa();
		Bomberman bman = new Bomberman(0, 0, DefConst.DEFAULTHEIGHT, DefConst.DEFAULTWIDTH);
		
		m.agregarBomberman(bman);
		m.moverBombermanIzq(bman, DefConst.VELOCIDAD);
		Ubicacion expected = new Ubicacion(0, 0);
		Ubicacion actual = bman.obtenerUbicacion();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void queColisionaExtremoMapaDerecha() {
		Mapa m = new Mapa();
		Bomberman bman = new Bomberman(9, 0, DefConst.DEFAULTHEIGHT, DefConst.DEFAULTWIDTH);
		
		m.agregarBomberman(bman);
		m.moverBombermanDer(bman, DefConst.VELOCIDAD);
		Ubicacion expected = new Ubicacion(9, 0);
		Ubicacion actual = bman.obtenerUbicacion();
		Assert.assertEquals(expected, actual);
	}
	@Test
	public void queColisionaConBombaIzquierda() {
		Mapa m = new Mapa();
		Bomberman bman = new Bomberman(0, 0, DefConst.DEFAULTHEIGHT, DefConst.DEFAULTWIDTH);
		
		m.agregarBomberman(bman);
		m.agregarBomba(bman.getX(), bman.getY(), bman);		
		m.moverBombermanDer(bman, DefConst.VELOCIDAD);
		m.moverBombermanIzq(bman, DefConst.VELOCIDAD);
		Assert.assertEquals(new Ubicacion(1, 0), bman.obtenerUbicacion());
	}
	@Test
	public void queColisionaConBombaDerecha() {
		Mapa m = new Mapa();
		Bomberman bman = new Bomberman(4, 0, DefConst.DEFAULTHEIGHT, DefConst.DEFAULTWIDTH);
		
		m.agregarBomberman(bman);
		m.agregarBomba(bman.getX(), bman.getY(), bman);
		m.moverBombermanIzq(bman, DefConst.VELOCIDAD);
		m.moverBombermanDer(bman, DefConst.VELOCIDAD);
		Assert.assertEquals(new Ubicacion(3, 0), bman.obtenerUbicacion());
	}
	@Test
	public void queColisionaConBombaAbajo() {
		Mapa m = new Mapa();
		Bomberman bman = new Bomberman(0, 4, DefConst.DEFAULTHEIGHT, DefConst.DEFAULTWIDTH);		
				
		m.agregarBomberman(bman);
		m.agregarBomba(bman.getX(), bman.getY(), bman);
		m.moverBombermanArriba(bman, DefConst.VELOCIDAD);
		m.moverBombermanAbajo(bman, DefConst.VELOCIDAD);
		Assert.assertEquals(new Ubicacion(0, 3), bman.obtenerUbicacion());
	}
	@Test
	public void queColisionaConBombaArriba() {
		Mapa m = new Mapa();
		Bomberman bman = new Bomberman(0, 0, DefConst.DEFAULTHEIGHT, DefConst.DEFAULTWIDTH);
		
		m.agregarBomberman(bman);
		m.agregarBomba(bman.getX(), bman.getY(), bman);
		m.moverBombermanAbajo(bman, DefConst.VELOCIDAD);
		m.moverBombermanArriba(bman, DefConst.VELOCIDAD);		
		Assert.assertEquals(new Ubicacion(0, 1), bman.obtenerUbicacion());
	}
}

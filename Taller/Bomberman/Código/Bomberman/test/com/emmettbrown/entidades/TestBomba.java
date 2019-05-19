package com.emmettbrown.entidades;

import com.emmettbrown.mapa.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestBomba {
	
	@Test
	public void queBombaMataBomberman() {
		Mapa m = new Mapa();
		Bomberman bman = new Bomberman(2, 2, Bomberman.defaultHeight, Bomberman.defaultWidth);
		
		m.agregarBomberman(bman);
		m.agregarBomba(bman.getX(), bman.getY());
		m.moverBombermanIzq(bman, Bomberman.VELOCIDAD);				
		m.explotarBomba(2,2);
		Assert.assertEquals(false, bman.verSiEsVisible());
	}
	
	@Test
	public void queBombaMataBombermans() {
		Mapa m = new Mapa();
		Bomberman bman = new Bomberman(2, 2, Bomberman.defaultHeight, Bomberman.defaultWidth);
		Bomberman bman2 = new Bomberman(1, 2, Bomberman.defaultHeight, Bomberman.defaultWidth);
		m.agregarBomberman(bman);
		m.agregarBomberman(bman2);		
		m.agregarBomba(bman.getX(), bman.getY());
		m.moverBombermanDer(bman, Bomberman.VELOCIDAD);		
		m.explotarBomba(2,2);
		Assert.assertEquals(false, bman.verSiEsVisible());
		Assert.assertEquals(false, bman2.verSiEsVisible());
	}

	@Test
	public void queExplotaEnCadena() {
		Mapa m = new Mapa();
		Bomberman bman = new Bomberman(2, 2, Bomberman.defaultHeight, Bomberman.defaultWidth);
		Bomberman bman2 = new Bomberman(1, 2, Bomberman.defaultHeight, Bomberman.defaultWidth);
		
		m.agregarBomberman(bman);
		m.agregarBomberman(bman2);	
		
		m.agregarBomba(bman.getX(), bman.getY());
		m.moverBombermanDer(bman, Bomberman.VELOCIDAD);
		m.agregarBomba(bman.getX(), bman.getY());
		m.moverBombermanArriba(bman, Bomberman.VELOCIDAD);
		m.agregarBomba(bman.getX(), bman.getY());
		m.moverBombermanDer(bman, Bomberman.VELOCIDAD);

		m.explotarBomba(2,2);
		Assert.assertEquals(false, bman.verSiEsVisible());
		Assert.assertEquals(false, bman2.verSiEsVisible());
	}
	
	@Test
	public void queExplotaObstaculos() {
		Mapa m = new Mapa();
		Bomba b = new Bomba(new Ubicacion (0,0));
		m.agregarBomba(b.ubicacion.getPosX(), b.ubicacion.getPosY());
		
		m.explotarBomba(0,0);
		Assert.assertEquals(true, m.estaLibre(new Ubicacion(0,1)));
	}
	
	@Test
	public void queNoPuedeExplotaMuros() {
		Mapa m = new Mapa();
		m.generarMapa();
		Bomba b = new Bomba(new Ubicacion (1,0));
		m.agregarBomba(b.ubicacion.getPosX(), b.ubicacion.getPosY());
		
		m.explotarBomba(1,0);
		Assert.assertEquals(false, m.estaLibre(new Ubicacion(1,1)));
	}	
}

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
		Bomberman bman = new Bomberman(2,2);
		
		/*Bomberman[] listaBomb = new Bomberman[1];
		listaBomb[0] = new Bomberman(2, 2, m);*/
		
		m.agregarBomberman(bman);
		m.agregarBomba(bman.obtenerUbicacion());
		m.moverBombermanIzq(bman, Bomberman.VELOCIDAD);				
		m.explotarBomba(2,2);
		Assert.assertEquals(false, bman.verSiEsVisible());
	}
	
	@Test
	public void queBombaMataBombermans() {
		Mapa m = new Mapa();
		Bomberman bman = new Bomberman(2,2);
		Bomberman bman2 = new Bomberman(1,2);
		
		/*Bomberman[] listaBomb = new Bomberman[2];
		listaBomb[0] = new Bomberman(2, 2, m);
		listaBomb[1] = new Bomberman(1, 2, m);*/
		
		m.agregarBomberman(bman);
		m.agregarBomberman(bman2);		
		m.agregarBomba(bman.obtenerUbicacion());
		m.moverBombermanDer(bman, Bomberman.VELOCIDAD);		
		m.explotarBomba(2,2);
		Assert.assertEquals(false, bman.verSiEsVisible());
		Assert.assertEquals(false, bman2.verSiEsVisible());
	}

	@Test
	public void queExplotaEnCadena() {
		Mapa m = new Mapa();
		Bomberman bman = new Bomberman(2,2);
		Bomberman bman2 = new Bomberman(1,2);
		
		/*Bomberman[] listaBomb = new Bomberman[2];
		listaBomb[1] = new Bomberman(1, 2, m);
		listaBomb[0] = new Bomberman(2, 2, m);*/
		
		m.agregarBomberman(bman);
		m.agregarBomberman(bman2);	
		
		m.agregarBomba(bman.obtenerUbicacion());
		m.moverBombermanDer(bman, Bomberman.VELOCIDAD);
		m.agregarBomba(bman.obtenerUbicacion());
		m.moverBombermanArriba(bman, Bomberman.VELOCIDAD);
		m.agregarBomba(bman.obtenerUbicacion());
		m.moverBombermanDer(bman, Bomberman.VELOCIDAD);

		m.explotarBomba(2,2);
		Assert.assertEquals(false, bman.verSiEsVisible());
		Assert.assertEquals(false, bman2.verSiEsVisible());
	}
	
	@Test
	public void queExplotaObstaculos() {
		Mapa m = new Mapa();
		Bomba b = new Bomba(new Ubicacion (0,0));
		m.agregarBomba(b.ubicacion);
		
		m.explotarBomba(0,0);
		Assert.assertEquals(true, m.estaLibre(new Ubicacion(0,1)));
	}
	
	@Test
	public void queNoPuedeExplotaMuros() {
		Mapa m = new Mapa();
		m.generarMapa();
		Bomba b = new Bomba(new Ubicacion (1,0));
		m.agregarBomba(b.ubicacion);
		
		m.explotarBomba(1,0);
		Assert.assertEquals(false, m.estaLibre(new Ubicacion(1,1)));
	}	
}

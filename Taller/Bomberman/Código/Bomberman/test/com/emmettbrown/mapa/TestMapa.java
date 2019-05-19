package com.emmettbrown.mapa;

import org.junit.Assert;
import org.junit.Test;

import com.emmettbrown.entidades.Bomberman;

public class TestMapa {
	@Test
	public void mapaConObstaculos() {
		Mapa m = new Mapa();
		m.generarMapa();
		Assert.assertEquals(false,m.estaLibre(new Ubicacion(1,1)));
	}
	
	@Test
	public void mapaSinObstaculos() {
		Mapa m = new Mapa();
		Assert.assertEquals(true,m.estaLibre(new Ubicacion(1,1)));
	}
	@Test
	public void mapaSiEstaLibrePosicion() {
		Mapa m = new Mapa();
		m.generarMapa();
		Assert.assertEquals(false,m.estaLibre(new Ubicacion(1,1)));
		Assert.assertEquals(true,m.estaLibre(new Ubicacion(0,0)));
		Assert.assertEquals(false,m.estaLibre(new Ubicacion(3,3)));
	}
	
	@Test
	public void mostrarMapaGenerado() {
		Mapa m = new Mapa();
		m.generarMapa();
		
		Bomberman bman = new Bomberman(0, 0, Bomberman.defaultHeight, Bomberman.defaultWidth);		
		m.agregarBomberman(bman);
		
		m.moverBombermanDer(bman, Bomberman.VELOCIDAD);
		System.out.println(bman.obtenerUbicacion());
	}

}

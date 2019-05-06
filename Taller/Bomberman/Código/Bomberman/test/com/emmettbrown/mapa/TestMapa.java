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
		m.mostrarMapa();
		Bomberman[] bombermans = new Bomberman[1]; 
		bombermans[0] = new Bomberman(0,0);
		m.agregarBombermans(bombermans);
		m.moverBomberman(1, Bomberman.DERECHA);
		bombermans = m.obtenerBombermans();
		System.out.println(bombermans[0].obtenerUbicacion());
	}

}

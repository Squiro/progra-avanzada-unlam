package ar.edu.unlam.razas;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class TestSel {

	@Test
	public void serAtacado() {
		Sel s = new Sel(2);
		Sel s2 = new Sel(3);
		s.serAtacado(s2);
		Assert.assertEquals((295-43), s.saludActual);
		Assert.assertEquals(38, s.danioActual);
	}	
	@Test
	public void queAtacaAOtroShoix() {
		Sel s = new Sel(2);
		Sel s2 = new Sel(3);
		s.atacar(s2);
		Assert.assertEquals((295-43), s2.saludActual);	
	}
	
	@Test
	public void queAtacaFueraDeRango() {
		Sel s = new Sel(1);
		Sel s2 = new Sel(1);
		s.atacar(s2);
		Assert.assertEquals(s2.saludActual, s2.saludActual);	
	}
	
	@Test
	public void queDescansa() {
		Sel s = new Sel(2);
		Sel s2 = new Sel(123);
		s.atacar(s2);
		int salAct1 = (int) ((int) s2.saludActual + (s2.saludMaxima - s2.saludActual)*0.5);
		s2.descansar();
		Assert.assertEquals(s2.saludActual, salAct1);	
	}

}

package ar.edu.unlam.razas;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;




public class TestShoix {
	@Test
	public void serAtacado() {
		Shoix s = new Shoix(2);
		Shoix s2 = new Shoix(3);
		s.serAtacado(s2);
		Assert.assertEquals((295-43), s.saludActual);
		Assert.assertEquals(38, s.danioActual);
	}	
	@Test
	public void queAtacaAOtroShoix() {
		Shoix s = new Shoix(2);
		Shoix s2 = new Shoix(3);
		s.atacar(s2);
		Assert.assertEquals((295-43), s2.saludActual);	
	}
	
	@Test
	public void queAtacaFueraDeRango() {
		Shoix s = new Shoix(2);
		Shoix s2 = new Shoix(123);
		s.atacar(s2);
		Assert.assertEquals(s2.saludActual, s2.saludActual);	
	}
	
	@Test
	public void queDescansa() {
		Shoix s = new Shoix(2);
		Shoix s2 = new Shoix(123);
		s.atacar(s2);
		int salAct1 = (int) ((int) s2.saludActual + (s2.saludMaxima - s2.saludActual)*0.5);
		s2.descansar();
		Assert.assertEquals(s2.saludActual, salAct1);	
	}

}

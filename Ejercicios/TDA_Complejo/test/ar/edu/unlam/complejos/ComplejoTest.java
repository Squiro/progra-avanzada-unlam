package ar.edu.unlam.complejos;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class ComplejoTest {
	
	@Test
	public void queSumaComplejos() {
		Complejo comp = new Complejo(5, 2);
		Complejo comp2 = new Complejo(6, 2);
		Complejo res = comp.sumar(comp2);	
		
		Complejo exp = new Complejo(11, 4);
		
		Assert.assertEquals(exp, res);
	}
	
	
	@Test
	public void queRestaComplejos() {
		Complejo comp = new Complejo(5, 2);
		Complejo comp2 = new Complejo(6, 2);
		Complejo res = comp.restar(comp2);	
		
		Complejo exp = new Complejo(-1, 0);
		
		Assert.assertEquals(exp, res);
	}
	
	@Test
	public void queMultiplicaComplejos() {
		Complejo comp = new Complejo(1, 1);
		Complejo comp2 = new Complejo(1, 1);
		Complejo res = comp.multiplicar(comp2);	
		
		Complejo exp = new Complejo(0, 2);
		
		Assert.assertEquals(exp, res);
	}
	
	@Test
	public void queClona() {
		Complejo comp = new Complejo(1, 1);
		
		Complejo clon = comp.clone();
		
		Assert.assertEquals(comp, clon);
		Assert.assertNotSame(comp, clon);
	}
	
	@Test	
	public void queCalculaModulo() {
		Complejo comp = new Complejo(2, 2);
		double mod = Math.sqrt(8);
		Assert.assertEquals(mod, comp.modulo(), 0.0);
	}
	
}

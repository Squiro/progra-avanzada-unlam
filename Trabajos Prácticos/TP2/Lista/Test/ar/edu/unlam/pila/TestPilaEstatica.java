package ar.edu.unlam.pila;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.edu.unlam.pila.PilaEstatica;
import junit.framework.Assert;

public class TestPilaEstatica {

	/*@Test
	public void test() {
		fail("Not yet implemented");
	}*/
	
	@Test
	public void queApilaCorrectamenteUnNum() {
		PilaEstatica<Integer> pila = new PilaEstatica<Integer>(1);
		pila.push(1);
		Assert.assertEquals(1, (int) pila.peek());		
	}
	
	@Test
	public void queDesapilaCorrectamenteUnNum() {
		PilaEstatica<Integer> pila = new PilaEstatica<Integer>(1);
		pila.push(1);
		Assert.assertEquals(1, (int) pila.pop());		
	}
	
	@Test
	public void queApilarYDesapilaCorrectamenteVariosNum() {
		PilaEstatica<Integer> pila = new PilaEstatica<Integer>(10);
		for (int i = 0; i < 10; i++) {
			pila.push(i);
		}
		
		for (int i = 0; i < 10; i++) {
			Assert.assertEquals(9-i, (int) pila.pop());
		}				
	}
		
	@Test
	public void queSeLlenaCorrectamente() {
		PilaEstatica<Integer> pila = new PilaEstatica<Integer>(10);
		for (int i = 0; i < 10; i++) {
			pila.push(i);
		}
		Assert.assertEquals(false,  pila.push(111));
	}
	
	@Test
	public void queVaciaCorrectamente() {
		PilaEstatica<Integer> pila = new PilaEstatica<Integer>(10);
		for (int i = 0; i < 10; i++) {
			pila.push(i);
		}
		
		pila.empty();
		Assert.assertEquals(true,  pila.isEmpty());
		Assert.assertEquals(null,  pila.pop());
	}
	
	@Test
	public void queIsEmptyFuncionaCorrectamente() {
		PilaEstatica<Integer> pila = new PilaEstatica<Integer>(10);
		for (int i = 0; i < 10; i++) {
			pila.push(i);
		}
		Assert.assertEquals(false,  pila.isEmpty());
	}
}

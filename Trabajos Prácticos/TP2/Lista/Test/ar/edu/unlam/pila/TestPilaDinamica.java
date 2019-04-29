package ar.edu.unlam.pila;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.edu.unlam.pila.PilaDinamica;
import junit.framework.Assert;

public class TestPilaDinamica {

	@Test
	public void queApilaCorrectamenteUnNum() {
		PilaDinamica<Integer> pila = new PilaDinamica<Integer>();
		pila.push(1);
		Assert.assertEquals(1, (int) pila.peek());		
	}
	
	@Test
	public void queDesapilaCorrectamenteUnNum() {
		PilaDinamica<Integer> pila = new PilaDinamica<Integer>();
		pila.push(1);
		Assert.assertEquals(1, (int) pila.pop());		
	}
	
	@Test
	public void queApilarYDesapilaCorrectamenteVariosNum() {
		PilaDinamica<Integer> pila = new PilaDinamica<Integer>();
		for (int i = 0; i < 10; i++) {
			pila.push(i);
		}
		
		for (int i = 0; i < 10; i++) {
			Assert.assertEquals(9-i, (int) pila.pop());
		}				
	}
	
	@Test
	public void queVaciaCorrectamente() {
		PilaDinamica<Integer> pila = new PilaDinamica<Integer>();
		for (int i = 0; i < 10; i++) {
			pila.push(i);
		}
		
		pila.empty();
		Assert.assertEquals(true,  pila.isEmpty());
		Assert.assertEquals(null,  pila.pop());
	}
	
	@Test
	public void queIsEmptyFuncionaCorrectamente() {
		PilaDinamica<Integer> pila = new PilaDinamica<Integer>();
		for (int i = 0; i < 10; i++) {
			pila.push(i);
		}
		Assert.assertEquals(false,  pila.isEmpty());
	}

}

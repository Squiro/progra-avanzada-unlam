package ar.edu.unlam.cola;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.edu.unlam.cola.ColaEstatica;
import junit.framework.Assert;

public class TestColaEstatica {

	/*@Test
	public void test() {
		fail("Not yet implemented");
	}*/
	
	@Test
	public void queAcolaCorrectamenteUnNum() {
		ColaEstatica<Integer> cola = new ColaEstatica<Integer>(1);
		cola.offer(1);
		Assert.assertEquals(1, (int) cola.peek());
	}
	
	@Test
	public void queDesacolaCorrectamenteUnNum() {
		ColaEstatica<Integer> cola = new ColaEstatica<Integer>(1);
		cola.offer(1);		
		Assert.assertEquals(1, (int) cola.poll());
	}
	
	@Test
	public void queAcolaYDesacolaCorrectamenteVariosNum() {
		ColaEstatica<Integer> cola = new ColaEstatica<Integer>(10);
		for (int i = 0; i < 10; i++) {
			cola.offer(i);
		}
		
		for (int i = 0; i < 10; i++) {
			Assert.assertEquals(i, (int) cola.poll());
		}		
	}
	
	@Test
	public void queSeLlenaCorrectamente() {
		ColaEstatica<Integer> cola = new ColaEstatica<Integer>(10);
		for (int i = 0; i < 10; i++) {
			cola.offer(i);
		}
		Assert.assertEquals(false,  cola.offer(111));
	}	
	
	@Test
	public void queVaciaCorrectamente() {
		ColaEstatica<Integer> cola = new ColaEstatica<Integer>(10);
		for (int i = 0; i < 10; i++) {
			cola.offer(i);
		}
		
		cola.empty();
		Assert.assertEquals(true,  cola.isEmpty());
		Assert.assertEquals(null,  cola.poll());
	}
	
	@Test
	public void queIsEmptyFuncionaCorrectamente() {
		ColaEstatica<Integer> cola = new ColaEstatica<Integer>(10);
		for (int i = 0; i < 10; i++) {
			cola.offer(i);
		}
		Assert.assertEquals(false,  cola.isEmpty());
	}
}

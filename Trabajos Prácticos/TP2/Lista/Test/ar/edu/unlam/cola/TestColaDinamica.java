package ar.edu.unlam.cola;

import org.junit.Assert;
import org.junit.Test;

import ar.edu.unlam.cola.ColaDinamica;


public class TestColaDinamica {

	/*@Test
	public void test() {
		fail("Not yet implemented");
	}*/
	
	@Test
	public void queAcolaCorrectamenteUnNum() {
		ColaDinamica<Integer> cola = new ColaDinamica<Integer>();
		cola.offer(1);
		Assert.assertEquals(1, (int) cola.peek());
	}
	
	@Test
	public void queDesacolaCorrectamenteUnNum() {
		ColaDinamica<Integer> cola = new ColaDinamica<Integer>();
		cola.offer(1);		
		Assert.assertEquals(1, (int) cola.poll());
	}
	
	@Test
	public void queAcolaYDesacolaCorrectamenteVariosNum() {
		ColaDinamica<Integer> cola = new ColaDinamica<Integer>();
		for (int i = 0; i < 10; i++) {
			cola.offer(i);
		}
		
		for (int i = 0; i < 10; i++) {
			Assert.assertEquals(i, (int) cola.poll());
		}		
	}
	
	@Test
	public void queVaciaCorrectamente() {
		ColaDinamica<Integer> cola = new ColaDinamica<Integer>();
		for (int i = 0; i < 10; i++) {
			cola.offer(i);
		}
		
		cola.empty();
		Assert.assertEquals(true,  cola.isEmpty());
	}
	
	@Test
	public void queIsEmptyFuncionaCorrectamente() {
		ColaDinamica<Integer> cola = new ColaDinamica<Integer>();
		for (int i = 0; i < 10; i++) {
			cola.offer(i);
		}
		Assert.assertEquals(false,  cola.isEmpty());
	}

}

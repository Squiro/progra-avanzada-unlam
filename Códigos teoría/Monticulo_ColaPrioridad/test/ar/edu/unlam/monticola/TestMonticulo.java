package ar.edu.unlam.monticola;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestMonticulo {

	/*@Test
	public void test() {
		fail("Not yet implemented");
	}*/
	
	@Test
	public void queFunciona() {
		Monticulo<Integer> mont = new Monticulo<Integer>();
		
		mont.insertar(1);
		mont.insertar(3);
		mont.insertar(4);
		mont.insertar(6);
		mont.insertar(8);
		mont.insertar(5);
		mont.insertar(7);
		
		System.out.println("Antes de quitar: ");
		mont.mostrarMonticulo();
		
		System.out.println("Quitado : " + mont.quitar());
		
		System.out.println("Despues de quitar: ");
		
		mont.mostrarMonticulo();
		
		System.out.println("Quitado : " + mont.quitar());
		
		System.out.println("Despues de quitar: ");
		
		mont.mostrarMonticulo();
	}
	
	

}

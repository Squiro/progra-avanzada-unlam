package ar.edu.unlam.lista;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;

public class TestLista {

	@Test
	public void queFuncionaPushBack() {
		Lista<Integer> lista = new Lista<Integer>();
		lista.pushBack(1);
		
		Assert.assertEquals(1, lista.searchAt(0).intValue());				
	}
	
	@Test
	public void queFuncionaPopBack() {
		Lista<Integer> lista = new Lista<Integer>();
		lista.pushBack(1);
		
		Assert.assertEquals(1, lista.popBack().intValue());				
	}
	
	@Test
	public void queFuncionaPushBackVariosNum() {
		Lista<Integer> lista = new Lista<Integer>();
		for (int i = 0; i < 10; i++) {
			lista.pushBack(i);
		}
		
		for (int i = 0; i < 10; i++) {
			Assert.assertEquals(i, lista.searchAt(i).intValue());		
		}		
	}
	
	@Test
	public void queFuncionaPopBackVariosNum() {
		Lista<Integer> lista = new Lista<Integer>();
		
		for (int i = 0; i < 10; i++) {
			lista.pushBack(i);
		}
		
		for (int i = 0; i < 10; i++) {
			Assert.assertEquals(9-i, lista.popBack().intValue());		
		}					
	}
	
	@Test
	public void queFuncionaPushFrontyPopFront() {
		Lista<Integer> lista = new Lista<Integer>();
		
		for (int i = 0; i < 10; i++) {
			lista.pushFront(i);
		}
		
		for (int i = 0; i < 10; i++) {
			Assert.assertEquals(9-i, lista.popFront().intValue());
		}
						
	}
	
	@Test
	public void queFuncionaRemoveYSearch() {
		Lista<Integer> lista = new Lista<Integer>();
		
		for (int i = 0; i < 10; i++) {
			lista.pushFront(i);
		}
		
		lista.remove(5);		
		Assert.assertEquals(-1, lista.search(5));						
	}
	
	@Test
	public void queFuncionaInsertAt() {
		Lista<Integer> lista = new Lista<Integer>();
		
		for (int i = 0; i < 10; i++) {
			lista.pushFront(i);
		}
		
		lista.insertAt(5, 60);
			
		Assert.assertEquals(5, lista.search(60));						
	}
	
	@Test
	public void queFuncionaEraseAt() {
		Lista<Integer> lista = new Lista<Integer>();
		
		for (int i = 0; i < 10; i++) {
			lista.pushFront(i);
		}
		
		lista.eraseAt(4);
			
		Assert.assertEquals(-1, lista.search(5));						
	}
	
	@Test
	public void queFuncionaIsEmpty() {
		Lista<Integer> lista = new Lista<Integer>();
			
		Assert.assertEquals(true, lista.isEmpty());						
	}
	
	@Test
	public void queFuncionaEmpty() {
		Lista<Integer> lista = new Lista<Integer>();
				
		for (int i = 0; i < 10; i++) {
			lista.pushFront(i);
		}
		lista.empty();
			
		Assert.assertEquals(true, lista.isEmpty());						
	}
	
	@Test
	public void queFuncionaSize() {
		Lista<Integer> lista = new Lista<Integer>();
				
		for (int i = 0; i < 10; i++) {
			lista.pushFront(i);
		}

			
		Assert.assertEquals(10, lista.size());						
	}
	
	
	/*@Test
	public void queFuncionaReverse() {
		Lista<Integer> lista = new Lista<Integer>();
		for (int i = 0; i < 10; i++) {
			lista.pushBack(i);
		}
		
		lista.printList();
		
		lista.reverse();
		System.out.println("REVERSE");
		lista.printList();
	}*/
	
	
	

}

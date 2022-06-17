package ar.edu.unlam.operaciones;

//import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

public class OperacionesTest {
	
	Operaciones op;

	@Before
	public void setUp() {
		op = new Operaciones();
	}
	
	@Test
	public void queFuncionaCon2() {
		Assert.assertEquals(2, op.operaciones(2));
	}
	
	@Test
	public void queFuncionaCon5() {
		Assert.assertEquals(4, op.operaciones(5));
	}
	
	@Test
	public void queFuncionaCon10() {
		Assert.assertEquals(5, op.operaciones(10));
	}
	
	@Test
	public void queFuncionaCon16() {
		Assert.assertEquals(5, op.operaciones(16));
	}
	
	@Test
	public void queFuncionaCon1023() {
		Assert.assertEquals(19, op.operaciones(1023));
	}
	
	@Test
	public void queFuncionaCon2017() {
		Assert.assertEquals(17, op.operaciones(2017));
	}	
}
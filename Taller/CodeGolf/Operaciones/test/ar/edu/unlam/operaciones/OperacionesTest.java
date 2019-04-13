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
	public void queFuncionaCon2017() {
		Assert.assertEquals(17, op.operaciones(2017));
	}

}
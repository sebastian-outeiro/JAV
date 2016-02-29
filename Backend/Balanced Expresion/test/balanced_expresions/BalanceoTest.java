package balanced_expresions;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class BalanceoTest {
	
	private Balanceo balanceo;
	
	@Before
	public void setup(){
		this.balanceo = new Balanceo();
	}

	@Test
	public void testPrimerCaso() {
		String ecuacion = "{aa(bb[]cc)(dd)}";
		assertTrue(balanceo.estaBalanceado(ecuacion) );
	}
	
	@Test
	public void testSegundoCaso() {
		String ecuacion = "([xxx]{zzz})";
		assertTrue(balanceo.estaBalanceado(ecuacion) );
	}
	
	@Test
	public void testTercerCaso() {
		String ecuacion = "abc(d[)]";
		assertFalse(balanceo.estaBalanceado(ecuacion) );
	}
	
	@Test
	public void testCuartoCaso() {
		String ecuacion = "(({]))";
		assertFalse(balanceo.estaBalanceado(ecuacion) );
	}
	
	@Test
	public void testQuintoCaso() {
		String ecuacion = "(()[]())";
		assertTrue(balanceo.estaBalanceado(ecuacion) );
	}
	
	@Test
	public void testSextoCaso() {
		String ecuacion = "{x[y(zz)y[]]}";
		assertTrue(balanceo.estaBalanceado(ecuacion) );
	}
	
	@Test
	public void testSeptimoCaso() {
		String ecuacion = "a(b+c{)]";
		assertFalse(balanceo.estaBalanceado(ecuacion) );
	}
	
	

}


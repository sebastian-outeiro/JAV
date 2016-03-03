package clientesYPaquetes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import clientesYPaquetes.Cliente;
import clientesYPaquetes.EstrategiaPrecio;
import clientesYPaquetes.EstrategiaPrecioFijo;
import clientesYPaquetes.Paquete;


public class ClienteTest2 {
	
	private Paquete unPaquete;
	private Paquete otroPaquete;

	@Before
	public void setup(){
		this.unPaquete = mock(Paquete.class);
		this.otroPaquete = mock(Paquete.class);
	}
	
	@Test
	public void testSeDebePoderObtenerElsaldoDeUnIndividuo(){
		double saldo = 200.0;
		Cliente unCliente = new Individuo(saldo);
		assertEquals(saldo, unCliente.getSaldo(), 0.0);		
	}
	
	@Test
	public void testUnClienteAlComprarUnPaqueteSeDebitaElCostoDeSuSaldo() throws SaldoInsuficienteException {
		/**
		 * Prueba 1: Que el cliente juan tenga $2000 de saldo 
		 * y compre un paquete de $500 
		 * con 25% de impuesto. 
		 * Debe quedar con $1375.
		 * Costo Paquete = $625
		 */
		Cliente cliente = new Individuo(2000.0);
		when(this.unPaquete.costo()).thenReturn(Double.valueOf(625.0));
		
		cliente.comprarPaquete(this.unPaquete);
		
		assertEquals(1375.0,cliente.getSaldo(),0.0 );
	}
	
	@Test
	public void testUnClienteAlComprarUnPaqueteSeDebitaElCostoDeSuSaldo2() throws SaldoInsuficienteException {
		/**
		 * Prueba 2: Que el cliente juan tenga $2000 de saldo y 
		 * compre un paquete de $100 con 10% de impuesto. 
		 * Debe quedar con saldo $1890.
		 */
		Cliente cliente = new Individuo(2000.0);
		when(this.unPaquete.costo()).thenReturn(Double.valueOf(110.0));
	
		cliente.comprarPaquete(this.unPaquete);

		assertEquals(1890.0,cliente.getSaldo(),0.0 );
	}
	
	@Test
	public void testUnClienteDebeConocerLaTotalidadDeSusGastosYLaCantidadDePaquetesQueCompro() throws SaldoInsuficienteException {
		/**
		 *	Poder conocer cuánto gastó en total un cliente, y cuántos paquetes compró.
		 *	Prueba 1: Juan ($2000) compra un paquete como el de la Prueba 1 anterior, 
		 *  y un paqueteMardel como el ejercicio anterior (por habitación, sin cambiar). 
		 *  La cantidad de paquetes debe ser 2 y debe haber gastado $745 en total.
		 */
		Cliente juan = new Individuo(2000.0);
		when(this.unPaquete.costo()).thenReturn(Double.valueOf(625.0));
		when(this.otroPaquete.costo()).thenReturn(Double.valueOf(120.0));
		
		juan.comprarPaquete(this.unPaquete);
		juan.comprarPaquete(this.otroPaquete);
		
		assertEquals(2,juan.cantidadPaquetesComprados());
		assertEquals(745.0,juan.gastoTotal(),0.0);			
	}
	
	@Test
	public void testUnClienteDebeConocerElPaqueteMasCaroQueCompro() throws SaldoInsuficienteException {
		/**
		 *	Poder conocer cuánto gastó en total un cliente, y cuántos paquetes compró.
		 *	Prueba 1: Juan ($2000) compra un paquete como el de la Prueba 1 anterior, 
		 *  y un paqueteMardel como el ejercicio anterior (por habitación, sin cambiar). 
		 *  La cantidad de paquetes debe ser 2 y debe haber gastado $745 en total.
		 */
		Cliente juan = new Individuo(2000.0);
		
		when(this.unPaquete.costo()).thenReturn(Double.valueOf(625.0));
		when(this.otroPaquete.costo()).thenReturn(Double.valueOf(120.0));
		
		juan.comprarPaquete(this.unPaquete);
		juan.comprarPaquete(this.otroPaquete);
		
		assertEquals(unPaquete,juan.getPaqueteConMayorCosto() );	
		assertNotEquals(otroPaquete, juan.getPaqueteConMayorCosto() );
	}
	
	@Test (expected = SaldoInsuficienteException.class)
	public void testSeLanzaExcepcionSiUnClienteIntentaComprarUnPaqueteQueNoPuedePagar() throws SaldoInsuficienteException{
		Cliente juan = new Individuo(2000.0);
		
		EstrategiaPrecio unaEstrategia = new EstrategiaPrecioFijo();
		Paquete paquete = new Paquete(unaEstrategia);
		paquete.setPrecioFijo(2000.0);
		paquete.setImpuesto(25);
		
		juan.comprarPaquete(paquete);
	}
	
	

	

}

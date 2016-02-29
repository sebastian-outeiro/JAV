package clientesYPaquetes;

import static org.junit.Assert.*;
import org.junit.Test;

import clientesYPaquetes.Cliente;
import clientesYPaquetes.EstrategiaPrecio;
import clientesYPaquetes.EstrategiaPrecioFijo;
import clientesYPaquetes.EstrategiaPrecioHabitaciones;
import clientesYPaquetes.Paquete;

public class ClienteTest {
	
	@Test
	public void testSeDebePoderObtenerElsaldoDeUnIndividuo(){
		double saldo = 200.0;
		Cliente unCliente = new Individuo(saldo);
		assertEquals(saldo, unCliente.getSaldo(), 0.0);		
	}
	
	private Paquete clienteCompraPaquete1(Cliente cliente){
		EstrategiaPrecio unaEstrategia = new EstrategiaPrecioFijo();
		Paquete paquete = new Paquete(unaEstrategia);
		paquete.setPrecioFijo(500.0);
		paquete.setImpuesto(25);
		try{
			cliente.comprarPaquete(paquete);	
		} catch (SaldoInsuficienteException e){
			
		}
		return paquete;
	}
	
	private Paquete clienteCompraPaquete2(Cliente cliente){
		EstrategiaPrecio unaEstrategia = new EstrategiaPrecioHabitaciones();
		Paquete paquete = new Paquete(unaEstrategia);
		paquete.setHabitaciones(2);
		paquete.setCostoHabitacion(50.0);
		paquete.setImpuesto(20);
		try {
			cliente.comprarPaquete(paquete);
		} catch (SaldoInsuficienteException e) {
			
		}
		return paquete;
	}
	
	
	@Test
	public void testUnClienteAlComprarUnPaqueteSeDebitaElCostoDeSuSaldo() {
		/**
		 * Prueba 1: Que el cliente juan tenga $2000 de saldo 
		 * y compre un paquete de $500 
		 * con 25% de impuesto. 
		 * Debe quedar con $1375.
		 */
		Cliente cliente = new Individuo(2000.0);
				
		this.clienteCompraPaquete1(cliente);
		
		assertEquals(1375.0,cliente.getSaldo(),0.0 );
	}
	
	@Test
	public void testUnClienteAlComprarUnPaqueteSeDebitaElCostoDeSuSaldo2() {
		/**
		 * Prueba 2: Que el cliente juan tenga $2000 de saldo y 
		 * compre un paquete de $100 con 10% de impuesto. 
		 * Debe quedar con saldo $1890.
		 */
		Cliente cliente = new Individuo(2000.0);
		EstrategiaPrecio unaEstrategia = new EstrategiaPrecioFijo();
		Paquete paquete = new Paquete(unaEstrategia);
		paquete.setPrecioFijo(100.0);
		paquete.setImpuesto(10);
		
		try {
			cliente.comprarPaquete(paquete);
		} catch (SaldoInsuficienteException e) {

		}
		
		assertEquals(1890.0,cliente.getSaldo(),0.0 );
	}
	
	@Test
	public void testUnClienteDebeConocerLaTotalidadDeSusGastosYLaCantidadDePaquetesQueCompro() {
		/**
		 *	Poder conocer cuánto gastó en total un cliente, y cuántos paquetes compró.
		 *	Prueba 1: Juan ($2000) compra un paquete como el de la Prueba 1 anterior, 
		 *  y un paqueteMardel como el ejercicio anterior (por habitación, sin cambiar). 
		 *  La cantidad de paquetes debe ser 2 y debe haber gastado $745 en total.
		 */
		Cliente juan = new Individuo(2000.0);
		
		this.clienteCompraPaquete1(juan);
		this.clienteCompraPaquete2(juan);
		
		assertEquals(2,juan.cantidadPaquetesComprados());
		assertEquals(745.0,juan.gastoTotal(),0.0);			
	}
	
	@Test
	public void testUnClienteDebeConocerElPaqueteMasCaroQueCompro() {
		/**
		 *	Poder conocer cuánto gastó en total un cliente, y cuántos paquetes compró.
		 *	Prueba 1: Juan ($2000) compra un paquete como el de la Prueba 1 anterior, 
		 *  y un paqueteMardel como el ejercicio anterior (por habitación, sin cambiar). 
		 *  La cantidad de paquetes debe ser 2 y debe haber gastado $745 en total.
		 */
		Cliente juan = new Individuo(2000.0);
		
		Paquete paqueteFijo = this.clienteCompraPaquete1(juan);
		Paquete paqueteConHabitaciones = this.clienteCompraPaquete2(juan);
		
		assertEquals(paqueteFijo,juan.getPaqueteConMayorCosto() );	
		assertNotEquals(paqueteConHabitaciones, juan.getPaqueteConMayorCosto() );
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

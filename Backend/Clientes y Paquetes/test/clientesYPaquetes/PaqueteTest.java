package clientesYPaquetes;

import static org.junit.Assert.*;

import org.junit.Test;

import clientesYPaquetes.Cliente;
import clientesYPaquetes.EstrategiaPrecio;
import clientesYPaquetes.EstrategiaPrecioFijo;
import clientesYPaquetes.EstrategiaPrecioHabitaciones;
import clientesYPaquetes.EstrategiaPrecioVariable;
import clientesYPaquetes.Paquete;

public class PaqueteTest {

	@Test
	public void testUnPaqueteDebeSoportarElCambioDeEstrategiaDelCalculoDePrecio() {
		/**
		 * Prueba 1: Crear el paqueteMardel, con 2 habitaciones de $50, 
		 * 20% de impuestos, y verificar que sale $120. 
		 * Cambiarlo a precio base fijo de $200 y verificar que cueste $240.
		 */
		EstrategiaPrecio unaEstrategia = new EstrategiaPrecioHabitaciones();
		Paquete paquete = new Paquete(unaEstrategia);
		paquete.setHabitaciones(2);
		paquete.setCostoHabitacion(50.0);
		paquete.setImpuesto(20);
		
		assertEquals(120.0,paquete.costo(),0.0);
		
		unaEstrategia = new EstrategiaPrecioFijo();
		paquete.setEstrategia(unaEstrategia);
		paquete.setPrecioFijo(200);
		
		assertEquals(240.0,paquete.costo(),0.0);
	}
	
	@Test
	public void testCalculoDelPrecioDelPaqueteEnFuncionDelSaldoDelCliente() {
		/**
		 * Prueba 2: Que si juan ($2000) compra un paquete con cálculo por saldo con
		 *  30% de impuestos, quede con $1740
		 */
		Cliente unCliente = new Individuo(2000.0);
		EstrategiaPrecio unaEstrategia = new EstrategiaPrecioVariable();
		Paquete paquete = new Paquete(unaEstrategia);
		paquete.setImpuesto(30);
		paquete.setCliente(unCliente);
		
		try {
			unCliente.comprarPaquete(paquete);
		} catch (SaldoInsuficienteException e) {

		}
		
		assertEquals(1740.0,unCliente.getSaldo(),0.0);
	}
	
}

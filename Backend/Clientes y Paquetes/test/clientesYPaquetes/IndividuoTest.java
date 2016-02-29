package clientesYPaquetes;

import static org.junit.Assert.*;

import org.junit.Test;

public class IndividuoTest {
	
	private Paquete generarPaqueteFijo(){
		EstrategiaPrecio unaEstrategia = new EstrategiaPrecioFijo();
		
		Paquete paquete = new Paquete(unaEstrategia);
		paquete.setPrecioFijo(500.0);
		paquete.setImpuesto(25);
		
		return paquete;
	}

	@Test
	public void testUnIndividuoDebePoderReservarUnPaqueteAbonandoLaMitadDelMismo() {
		double saldoInicial = 2000.0;
		Individuo unIndividuo = new Individuo(saldoInicial);
		Paquete unPaquete = this.generarPaqueteFijo();
		
		unIndividuo.reservarPaquete(unPaquete);
		
		assertEquals(saldoInicial - unPaquete.costoReserva(), unIndividuo.getSaldo() , 0.0);
		assertTrue(unIndividuo.reservoElPaquete(unPaquete) );
	}
	
	@Test
	public void testUnIndividuoDebePoderFinalizarLaCompraDeUnPaquete() {
		double saldoInicial = 2000.0;
		Individuo unIndividuo = new Individuo(saldoInicial);
		Paquete unPaquete = this.generarPaqueteFijo();
		
		unIndividuo.reservarPaquete(unPaquete);
		unIndividuo.finalizarComprar(unPaquete);
		
		assertEquals(saldoInicial - unPaquete.costo(), unIndividuo.getSaldo() , 0.0);
		assertFalse(unIndividuo.reservoElPaquete(unPaquete) );
	}

}

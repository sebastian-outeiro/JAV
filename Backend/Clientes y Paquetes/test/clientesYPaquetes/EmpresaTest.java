package clientesYPaquetes;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

public class EmpresaTest {
	
	private Paquete generarPaqueteFijo(){
		EstrategiaPrecio unaEstrategia = new EstrategiaPrecioFijo();
		
		Paquete paquete = new Paquete(unaEstrategia);
		paquete.setPrecioFijo(500.0);
		paquete.setImpuesto(25);
		
		return paquete;
	}
	
	private Paquete generarPaqueteConHabitaciones(){
		EstrategiaPrecio unaEstrategia = new EstrategiaPrecioHabitaciones();
		Paquete paquete = new Paquete(unaEstrategia);
		
		paquete.setHabitaciones(2);
		paquete.setCostoHabitacion(50.0);
		paquete.setImpuesto(20);
		
		return paquete;	
	}
	
	private Paquete generarPaqueteConPrecioVariable(Cliente unCliente){
		EstrategiaPrecio unaEstrategia = new EstrategiaPrecioVariable();
		Paquete paquete = new Paquete(unaEstrategia);
		
		paquete.setCliente(unCliente);
		paquete.setImpuesto(30);
		
		return paquete;
	}

	@Test
	public void testUnaEmpresaPuedeComprarMultiplesPaquetesSiPoseeElSaldoSuficiente() {
		double saldoInicial = 10000.0;
		Empresa unaEmpresa = new Empresa(saldoInicial);
		ArrayList<Paquete> listadoAComprar = new ArrayList<Paquete>();
		
		listadoAComprar.add(this.generarPaqueteConPrecioVariable(unaEmpresa) );
		listadoAComprar.add(this.generarPaqueteFijo() );
		listadoAComprar.add(this.generarPaqueteConHabitaciones() );
	
		
		double costoTotal = 0.0;
		for (Paquete unPaquete : listadoAComprar)
			costoTotal = costoTotal + unPaquete.costo();
		
		try {
			unaEmpresa.comprarPaquetes(listadoAComprar);
		} catch (SaldoInsuficienteException e) {

		}
		
		assertEquals(saldoInicial - costoTotal, unaEmpresa.saldo, 0.0);
		
	}

}

package clientesYPaquetes;

import static org.junit.Assert.*;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class EmpresaTest2 {
	
	private Paquete paqueteFijo;
	private Paquete paqueteHabitaciones;
	private Paquete paqueteVariable;
	private Empresa unaEmpresa;
	ArrayList<Paquete> listadoAComprar;
	
	@Before
	public void setup(){
		this.paqueteFijo = mock(Paquete.class);
		this.paqueteHabitaciones = mock(Paquete.class);
		this.paqueteVariable = mock(Paquete.class);
		
		when(this.paqueteFijo.costo()).thenReturn(Double.valueOf(625.0));
		when(this.paqueteHabitaciones.costo()).thenReturn(Double.valueOf(120.0));
		when(this.paqueteVariable.costo()).thenReturn(Double.valueOf(1333.33));
		
		this.unaEmpresa = new Empresa(10000.0);
		
		this.listadoAComprar = new ArrayList<Paquete>();
		listadoAComprar.add(paqueteFijo);
		listadoAComprar.add(paqueteHabitaciones);
		listadoAComprar.add(paqueteVariable);
	}
	


	@Test
	public void testUnaEmpresaPuedeComprarMultiplesPaquetesSiPoseeElSaldoSuficiente() throws SaldoInsuficienteException {
		double costoTotal = 0.0;
		double saldoInicial = unaEmpresa.getSaldo();
		
		for (Paquete unPaquete : listadoAComprar)
			costoTotal = costoTotal + unPaquete.costo();
		
		unaEmpresa.comprarPaquetes(listadoAComprar);
		
		assertEquals(saldoInicial - costoTotal, unaEmpresa.saldo, 0.0);
	}

}

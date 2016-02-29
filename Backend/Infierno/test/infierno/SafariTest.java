package infierno;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

public class SafariTest {

	private Lugar lugar;
	private DemonioDeFuego demonio;
	private int porcentajeCazable;
	
	private void crearLugar() {
		// Se genera un lugar donde el 70% es cazable
		Lugar unLugar = new Lugar();
		int cazables = 7;
		int noCazables = 3;
		// Almas Cazables
		for (int cantidadAlmas = 1 ; cantidadAlmas <= cazables ; cantidadAlmas++ )
			unLugar.agregarAlma(new Alma(50,50,false) );
		// Almas No Cazables
		for (int cantidadAlmas = 1 ; cantidadAlmas <= noCazables ; cantidadAlmas++ )
			unLugar.agregarAlma(new Alma(50,50,true) );
		this.lugar = unLugar;
		this.porcentajeCazable = (cazables ) * 10;
	}

	@Before
	public void setup(){
		this.demonio = new DemonioDeFuego(100);
		this.crearLugar();
	}
	
	@Test
	public void testUnDemonioNoPuedeRealizarConExitoLaMisionSiSeLePideUnValorMasAltoDeLoQuePuedeCazar(){
		Mision unaMision = new Safari(this.lugar,this.porcentajeCazable + 1);
		
		assertFalse(unaMision.puedeRealizarse(demonio));
	}
	

	@Test
	public void testUnDemonioPuedeRealizarConExitoLaMisionSiSeLePideUnValorMenorALoQuePuedeCazar(){
		Mision unaMision = new Safari(this.lugar,this.porcentajeCazable);
		
		assertTrue(unaMision.puedeRealizarse(demonio));
	}
	
	
	

}

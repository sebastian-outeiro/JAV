package infierno;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class DemonioDeFuegoTest {
	
	private DemonioDeFuego unDemonio;
	
	@Before
	public void setup(){
		this.unDemonio = new DemonioDeFuego(50.0);
	}

	private Alma nuevaAlmaFriolenta(double bondad){
		return new Alma(bondad,100,true);
	}
	
	private Alma nuevaAlmaNoFriolenta(double bondad){
		return new Alma(bondad,100,false);
	}
	

	@Test
	public void testNoPuedeCazarUnAlmaFriolenta() {
		Alma unAlma = this.nuevaAlmaFriolenta(this.unDemonio.getNivelDeMaldad() - 10);
		
		assertFalse(this.unDemonio.puedeCazar(unAlma));
	}
	
	@Test
	public void testNoPuedeCazarUnAlmaConMayorPuntosDeBondad() {
		Alma unAlma = this.nuevaAlmaNoFriolenta(this.unDemonio.getNivelDeMaldad() + 10);
		
		assertFalse(this.unDemonio.puedeCazar(unAlma));
	}
	
	@Test
	public void testPuedeCazarUnAlmaConMenorPuntosDeBondadYNoFriolenta() {
		Alma unAlma = this.nuevaAlmaNoFriolenta(this.unDemonio.getNivelDeMaldad() - 10);
		
		assertTrue(this.unDemonio.puedeCazar(unAlma));
	}
	
	@Test
	public void testPuedeAtormentarAUnAlma(){
		double bondadInicial = 1000.0;
		Alma unAlma = this.nuevaAlmaFriolenta(bondadInicial);
		
		unDemonio.atormentar(unAlma);
		
		assertTrue(bondadInicial > unAlma.getBondad() );
	}
	
	private Lugar nuevoLugar(){
		Lugar unLugar = new Lugar();
		unLugar.agregarAlma( this.nuevaAlmaFriolenta(this.unDemonio.getNivelDeMaldad() - 10.0) );
		unLugar.agregarAlma( this.nuevaAlmaFriolenta(this.unDemonio.getNivelDeMaldad() - 10.0) );
		unLugar.agregarAlma( this.nuevaAlmaNoFriolenta(this.unDemonio.getNivelDeMaldad() - 10.0) );
		unLugar.agregarAlma( this.nuevaAlmaNoFriolenta(this.unDemonio.getNivelDeMaldad() - 10.0) );
		
		return unLugar;
	}
	
	@Test
	public void testAlCazarEnUnLugarLasAlmasCazadasSonRemovidasDelLugar(){
		Lugar unLugar = this.nuevoLugar();
		int poblacionInicial = unLugar.getPoblacion();
		List<Alma> almasCazables = unLugar.getAlmasCazables(unDemonio);
		
		this.unDemonio.cazarEnUnLugar(unLugar);
		
		assertEquals(unLugar.getPoblacion() , poblacionInicial - almasCazables.size() );
	}
	
	@Test
	public void testAlCazarEnUnLugarLasAlmasNoCazadasSonAtormentadas(){
		Lugar unLugar = this.nuevoLugar();
		List<Alma> almasNoCazables = unLugar.getAlmasNoCazables(unDemonio);
		
		double bondadTotalInicial = 0;
		for (Alma unAlma : almasNoCazables)
			bondadTotalInicial = bondadTotalInicial + unAlma.getBondad();
		
		this.unDemonio.cazarEnUnLugar(unLugar);
		
		double bondadTotalFinal = 0;
		for (Alma unAlma : almasNoCazables)
			bondadTotalFinal = bondadTotalFinal + unAlma.getBondad();
			
		assertTrue(bondadTotalFinal < bondadTotalInicial);
	}
	
	@Test
	public void testAlCazarEnUnLugarLasAlmasNoCazadasSeQuedanEnElLugar(){
		Lugar unLugar = this.nuevoLugar();
		List<Alma> almasNoCazablesInicial = unLugar.getAlmasNoCazables(unDemonio);
		
		this.unDemonio.cazarEnUnLugar(unLugar);
		
		assertEquals(0, unLugar.getPoblacion() - almasNoCazablesInicial.size() );
	}

}

package infierno;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class DemonioDeSombrasTest {

	private DemonioDeSombras unDemonio;
	
	@Before
	public void setup(){
		this.unDemonio = new DemonioDeSombras(100.0);
	}

	private Alma nuevaAlma(double bondad){
		return new Alma(bondad,100,true);
	}

	@Test
	public void testNoPuedeCazarUnAlmaConMenorPuntosDeBondadYMayorAlLimiteDeBondad() {
		Alma unAlma = this.nuevaAlma(60);
		
		assertFalse(this.unDemonio.puedeCazar(unAlma));
	}
	
	@Test
	public void testNoPuedeCazarUnAlmaConMayorPuntosDeBondad() {
		Alma unAlma = this.nuevaAlma(this.unDemonio.getNivelDeMaldad() + 10);
		
		assertFalse(this.unDemonio.puedeCazar(unAlma));
	}
	
	@Test
	public void testPuedeCazarUnAlmaConMenorPuntosDeBondadYMenorAlLimiteDeBondad() {
		Alma unAlma = this.nuevaAlma(40);
		
		assertTrue(this.unDemonio.puedeCazar(unAlma));
	}
	
	@Test
	public void testPuedeAtormentarAUnAlma(){
		double bondadInicial = 1000.0;
		Alma unAlma = this.nuevaAlma(bondadInicial);
		
		unDemonio.atormentar(unAlma);
		
		assertTrue(bondadInicial > unAlma.getBondad() );
	}
	
	private Lugar nuevoLugar(){
		Lugar unLugar = new Lugar();
		// Cazables
		unLugar.agregarAlma( this.nuevaAlma(10) );
		// No Cazables
		unLugar.agregarAlma( this.nuevaAlma(this.unDemonio.getNivelDeMaldad() - 10.0) );
		unLugar.agregarAlma( this.nuevaAlma(this.unDemonio.getNivelDeMaldad() + 10.0) );
		unLugar.agregarAlma( this.nuevaAlma(60) );
		
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

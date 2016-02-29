package infierno;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DiabloTest {

	private Demonio unDemonioSinAlmas;
	private Demonio unDemonioConAlmas;
	private Diablo diablo;

	@Before
	public void setup(){
		this.unDemonioSinAlmas = new DemonioDeHielo(100);
		this.unDemonioConAlmas = new DemonioDeFuego(100);
		Alma unAlma = new Alma(10,10,false);
		unDemonioConAlmas.cazar(unAlma);
		this.diablo = new Diablo();
	}
	
	@Test
	public void testUnDemonioSinAlmasParaRendirEsCastigado(){
		double nivelDeMaldadInicial = unDemonioSinAlmas.getNivelDeMaldad();
		diablo.agregarDemonio(this.unDemonioSinAlmas);
		
		diablo.rendicionDeCuentas();
		
		assertTrue(nivelDeMaldadInicial > unDemonioSinAlmas.getNivelDeMaldad() );	
	}
	
	@Test
	public void testUnDemonioConAlmasParaRendirNoEsCastigado(){
		double nivelDeMaldadInicial = unDemonioConAlmas.getNivelDeMaldad();
		diablo.agregarDemonio(this.unDemonioConAlmas);
		
		diablo.rendicionDeCuentas();
		
		assertTrue(nivelDeMaldadInicial == unDemonioConAlmas.getNivelDeMaldad() );	
	}
	
	@Test
	public void testElPequenioDemonioEsAquelQueHallaEntregadoMenosAlmasHastaElMomento(){
		diablo.agregarDemonio(unDemonioConAlmas);
		diablo.agregarDemonio(unDemonioSinAlmas);
		
		diablo.rendicionDeCuentas();
		
		assertEquals(unDemonioSinAlmas, diablo.pequenioDemonio());	
	}
	
	@Test
	public void testElPoderDelDiabloEsLaSumaDeLaBondadYElValorDeLasAlmasCaputradas(){
		diablo.agregarDemonio(unDemonioConAlmas);
		diablo.agregarDemonio(unDemonioSinAlmas);
		
		double poderDiablo = unDemonioConAlmas.calcularPoderParaEntregar();
		
		diablo.rendicionDeCuentas();
		
		assertEquals(poderDiablo, diablo.calcularPoder(),0.0);
	}
	

}

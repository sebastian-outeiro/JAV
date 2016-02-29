package infierno;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DemonioTest {


	private Demonio demonio;
	private Mision mision;
	
	@Before
	public void setup(){
		this.demonio = new DemonioDeSombras(100);
	}

	public void cazar20AlmasParaElDemonio(){
		for (int cont = 1; cont <= 20; cont++){
			Alma unAlma = new Alma(10,10,true);
			demonio.cazar(unAlma);
		}
	}
	
	@Test
	public void testUnDemonioDeprimidoSiCaza20AlmasSePoneFeliz(){
		demonio.setEstadoDeprimido();
		
		this.cazar20AlmasParaElDemonio();
		
		assertEquals(Feliz.getInstance(), demonio.getEstadoAnimico());
	}
	
	@Test
	public void testUnDemonioNormalSiCaza20AlmasSePoneFeliz(){
		demonio.setEstadoNormal();
		
		this.cazar20AlmasParaElDemonio();
		
		assertEquals(Feliz.getInstance(), demonio.getEstadoAnimico());
	}
	
	@Test
	public void testSiCastiganAUnDemonioSeDeprime(){
		demonio.setEstadoFeliz();
		
		demonio.castigar();
		
		assertEquals(Deprimido.getInstance(), demonio.getEstadoAnimico());
	}
	
	private void crearMision(){
		Lugar unLugar = new Lugar();
		unLugar.agregarAlma(new Alma(10,10,true) );
		this.mision = new Safari(unLugar,50);	
	}
	
	@Test
	public void testUnDemonioDeprimidoQueCumpleLaMisionSePoneNormal(){
		this.crearMision();
		this.demonio.setEstadoDeprimido();
		
		this.demonio.realizarLaMision(this.mision);
		
		assertEquals(Normal.getInstance(), this.demonio.getEstadoAnimico());	
	}
	
	@Test
	public void testUnDemonioNormalQueCumpleLaMisionSePoneFeliz(){
		this.crearMision();
		this.demonio.setEstadoNormal();
		
		this.demonio.realizarLaMision(this.mision);
		
		assertEquals(Feliz.getInstance(), this.demonio.getEstadoAnimico());	
	}
	
	@Test
	public void testUnDemonioFelizQueCumpleLaMisionAumentaSuMaldad(){
		this.crearMision();
		this.demonio.setEstadoFeliz();
		double maldadInicial = this.demonio.getNivelDeMaldad();
		
		this.demonio.realizarLaMision(this.mision);
		
		assertTrue(maldadInicial < this.demonio.getNivelDeMaldad());
	}
	
	@Test
	public void testUnDemonioDeprimidoNoPuedeCazarSiElDobleDeMaldad(){
		Alma unAlma = new Alma(30, 100, true);
		this.demonio.setEstadoDeprimido();
		this.demonio.setNivelDeMaldad( unAlma.getBondad() * 1.9);
		
		assertFalse(this.demonio.puedeCazar(unAlma));
	}
	
	@Test
	public void testUnDemonioDeprimidoPuedeCazarSiTieneElDobleDeMaldadQueDeBondadDelAlma(){
		Alma unAlma = new Alma(30, 100, true);
		this.demonio.setEstadoDeprimido();
		this.demonio.setNivelDeMaldad( unAlma.getBondad() * 2.0);
		
		assertTrue(this.demonio.puedeCazar(unAlma));
	}
	
	@Test
	public void testUnDemonioFelizSoloNecesitaLaMitadDeLaBondadParaCazarlo(){
		Alma unAlma = new Alma(30, 100, true);
		this.demonio.setEstadoFeliz();
		this.demonio.setNivelDeMaldad( unAlma.getBondad() * 0.5);
		
		assertTrue(this.demonio.puedeCazar(unAlma));
	}
	
	@Test
	public void testUnDemonioFelizNoPuedeCazarSiNoTieneAlMenosLaMitadDeBondad(){
		Alma unAlma = new Alma(30, 100, true);
		this.demonio.setEstadoFeliz();
		this.demonio.setNivelDeMaldad( unAlma.getBondad() * 0.4);
		
		assertFalse(this.demonio.puedeCazar(unAlma));
	}
	
	@Test
	public void testUnDemonioDeHieloDeprimidoNoHacenFriolentaElAlmaAlAtormentar(){
		Demonio unDemonio = new DemonioDeHielo(100);
		unDemonio.setEstadoDeprimido();
		Alma unAlma = new Alma(30,100,false);
		
		unDemonio.atormentar(unAlma);
		
		assertFalse(unAlma.isFriolenta());
	}
	
	@Test
	public void testUnDemonioDeFuegoDeprimidoNoHaceNoFriolentaElAlmaAlAtormentar(){
		Demonio unDemonio = new DemonioDeFuego(100);
		unDemonio.setEstadoDeprimido();
		Alma unAlma = new Alma(30,100,true);
		
		unDemonio.atormentar(unAlma);
		
		assertTrue(unAlma.isFriolenta());
	}
	
	
	
	
	
	
}

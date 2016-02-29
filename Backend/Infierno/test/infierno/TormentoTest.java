package infierno;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TormentoTest {
	
	private DemonioDeFuego demonio;
	
	private List<Lugar> crearLugaresTormentablesHastaLaLocura() {
		List<Lugar> lugares = new LinkedList<Lugar>();
		for (int cantLugares = 1 ; cantLugares <= 3 ; cantLugares++ ){
			Lugar unLugar = new Lugar();
			for (int cantAlmas = 1 ; cantAlmas <= 9 ; cantAlmas++ )
				unLugar.agregarAlma(new Alma(100,100,false));
			// Agrego un Alma Tormentable
			unLugar.agregarAlma(new Alma(10,50,false) );
			lugares.add(unLugar);
		}
		return lugares;
	}
	
	private Lugar crearLugarNoTormentableHastaLaLocura(){
		Lugar unLugar = new Lugar();
		unLugar.agregarAlma( new Alma(100,100,false) );
		return unLugar;
	}

	@Before
	public void setup(){
		this.demonio = new DemonioDeFuego(100);
	}
	
	@Test
	public void testUnDemonioNoPuedeRealizarConExitoLaMisionSiExisteAlMenosUnLugarNoTormentableHastaLaLocura(){
		List<Lugar> lugares = new LinkedList<Lugar>();
		lugares.addAll(this.crearLugaresTormentablesHastaLaLocura());
		lugares.add(this.crearLugarNoTormentableHastaLaLocura());
		
		Mision unaMision = new Tormento(lugares);
		
		assertFalse(unaMision.puedeRealizarse(demonio));
	}
	
	@Test
	public void testUnDemonioPuedeRealizarConExitoLaMisionSiTodosLosLugaresSonTormentableHastaLaLocura(){
		List<Lugar> lugares = new LinkedList<Lugar>();
		lugares.addAll(this.crearLugaresTormentablesHastaLaLocura());
		
		Mision unaMision = new Tormento(lugares);
		
		assertTrue(unaMision.puedeRealizarse(demonio));
	}
}

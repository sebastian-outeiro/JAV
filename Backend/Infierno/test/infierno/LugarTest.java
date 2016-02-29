package infierno;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class LugarTest {

	private List<Alma> almasCazablesPorDemonioDeHielo;
	private List<Alma> almasCazablesPorDemonioDeFuego;
	private double maldad;
	private DemonioDeFuego demonioDeFuego;
	private DemonioDeHielo demonioDeHielo;
	private Lugar lugar;

	
	private Alma crearAlmaCazablePorDemonioDeHielo(){
		return new Alma(maldad -  10,100,true);
	}
	
	private Alma crearAlmaCazablePorDemonioDeFuego(){
		return new Alma(maldad -  10,100,false);
	}

	@Before
	public void setup(){
		this.almasCazablesPorDemonioDeHielo = new LinkedList<Alma>();
		this.almasCazablesPorDemonioDeFuego = new LinkedList<Alma>();
		this.maldad = 100.0;
		this.demonioDeFuego = new DemonioDeFuego(maldad);
		this.demonioDeHielo = new DemonioDeHielo(maldad);
		
		this.almasCazablesPorDemonioDeHielo.add(this.crearAlmaCazablePorDemonioDeHielo() );
		this.almasCazablesPorDemonioDeHielo.add(this.crearAlmaCazablePorDemonioDeHielo() );
		this.almasCazablesPorDemonioDeHielo.add(this.crearAlmaCazablePorDemonioDeHielo() );
		
		this.almasCazablesPorDemonioDeFuego.add(this.crearAlmaCazablePorDemonioDeFuego() );
		this.almasCazablesPorDemonioDeFuego.add(this.crearAlmaCazablePorDemonioDeFuego() );
		this.almasCazablesPorDemonioDeFuego.add(this.crearAlmaCazablePorDemonioDeFuego() );	
		
		this.lugar = new Lugar();
		this.lugar.agregarAlmas(this.almasCazablesPorDemonioDeHielo);
		this.lugar.agregarAlmas(this.almasCazablesPorDemonioDeFuego);
		
	}
	
	@Test
	public void testElLugarSabeCualesAlmasSonCazablesPorUnDemonioDeFuego(){
		List<Alma> almasCazables = this.lugar.getAlmasCazables(demonioDeFuego);
		
		assertTrue(almasCazables.containsAll(this.almasCazablesPorDemonioDeFuego) );
		
		int almasCazablesInicial = almasCazables.size();
		almasCazables.removeAll(this.almasCazablesPorDemonioDeHielo);
		
		assertTrue(almasCazablesInicial  == almasCazables.size() );
	}
	
	@Test
	public void testElLugarSabeCualesAlmasSonCazablesPorUnDemonioDeHielo(){
		List<Alma> almasCazables = this.lugar.getAlmasCazables(demonioDeHielo);
		
		assertTrue(almasCazables.containsAll(this.almasCazablesPorDemonioDeHielo) );
		
		int almasCazablesInicial = almasCazables.size();
		almasCazables.removeAll(this.almasCazablesPorDemonioDeFuego);
		
		assertTrue(almasCazablesInicial  == almasCazables.size() );
	}


}

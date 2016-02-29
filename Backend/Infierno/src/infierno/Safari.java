package infierno;

import java.util.List;

public class Safari implements Mision {
	
	private double porcentajeMinimoDeAlmas;
	private Lugar lugar;
	
	public Safari(Lugar unLugar, double porcentajeMinimoDeAlmasACazar){
		this.porcentajeMinimoDeAlmas = porcentajeMinimoDeAlmasACazar;
		this.lugar = unLugar;
	}
	
	@Override
	public boolean puedeRealizarse(Demonio unDemonio) {
		List<Alma> almasCazables = this.lugar.getAlmasCazables(unDemonio);
		double porcentajeAlmasPuedeCazar = ( almasCazables.size() * 100 ) / lugar.getPoblacion();
		return ( porcentajeAlmasPuedeCazar >= this.porcentajeMinimoDeAlmas );	
	}
	
	@Override
	public boolean realizar(Demonio unDemonio) {
		if ( !this.puedeRealizarse(unDemonio) )
			return false;
		
		unDemonio.cazarEnUnLugar(this.lugar);
		return true;
	}
	

	/*
	@Override
	public boolean puedeRealizarse(Demonio unDemonio) {
		List<Alma> almasDelLugar = this.lugar.getAlmas();
		int cantidadAlmasPuedeCazar = 0;
		
		for (Alma unAlma : almasDelLugar){
			if ( unDemonio.puedeCazar(unAlma) )
				cantidadAlmasPuedeCazar++;
		}
		double porcentajeAlmasPuedeCazar = ( cantidadAlmasPuedeCazar * 100 ) / almasDelLugar.size();
		return ( porcentajeAlmasPuedeCazar >= this.porcentajeMinimoDeAlmas );
	}
	

	@Override
	public boolean realizar(Demonio unDemonio) {
		if ( !this.puedeRealizarse(unDemonio) )
			return false;
		
		List<Alma> almasDelLugar = this.lugar.getAlmas();
		Iterator<Alma> iterador = almasDelLugar.iterator();
		while( iterador.hasNext() ){
			Alma unAlma = iterador.next();
			unDemonio.cazar(unAlma);
		}
		return true;
	}
	*/

}

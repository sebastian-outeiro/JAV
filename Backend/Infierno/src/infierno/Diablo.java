package infierno;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Diablo {
	
	private HashMap<Demonio, List<Alma>> almasPorDemonio;

	public Diablo(){
		this.almasPorDemonio = new HashMap<Demonio, List<Alma> >();
	}
	
	public void agregarDemonio(Demonio unDemonio){
		if ( this.almasPorDemonio.containsKey(unDemonio) )
			return;
		List<Alma> almasCapturadasPorElDemonio = new LinkedList<Alma>();
		this.almasPorDemonio.put(unDemonio, almasCapturadasPorElDemonio);
	}
	
	public void rendicionDeCuentas(){
		for (Demonio unDemonio : this.almasPorDemonio.keySet() ){
			List<Alma> almasCapturadas = unDemonio.rendirCuenta();
			if (almasCapturadas.isEmpty())
				unDemonio.castigar();
			this.almasPorDemonio.get(unDemonio).addAll(almasCapturadas);
		}	
	}
	
	public Demonio pequenioDemonio(){
		Set<Demonio> demonios = this.almasPorDemonio.keySet();
		if ( demonios.isEmpty() )
			return null;
		Iterator<Demonio> iterador = demonios.iterator();
		Demonio pequenioDemonio = iterador.next();
		while ( iterador.hasNext() ){
			Demonio demonioActual = iterador.next();
			if ( this.almasPorDemonio.get(demonioActual).size() < this.almasPorDemonio.get(pequenioDemonio).size() )
				pequenioDemonio = demonioActual;
		}
		return pequenioDemonio;
	}
	
	public double calcularPoder(){
		double poder = 0;
		for (Collection<Alma> almas : this.almasPorDemonio.values() )
			for (Alma unAlma : almas)
				poder = poder + unAlma.poder();
		return poder;		
	}

}

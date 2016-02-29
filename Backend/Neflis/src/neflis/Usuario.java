package neflis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class Usuario {
	
	List<Mirable> visto;
	
	public Usuario(){
		this.visto = new LinkedList<Mirable>();
	}
	
	public boolean visteTotalmente(Contenido unContenido){
		return unContenido.vistoTotalmente(this);
	}

	public boolean viste(Mirable unMirable) {
		return (visto.contains(unMirable));
	}
	
	public List<Genero> generosVistos(){
		List<Genero> generos = new LinkedList<Genero>();
		for (Mirable unMirable : visto){
			Genero unGenero = unMirable.getGenero();
			if ( ! generos.contains(unGenero) )
				generos.add(unGenero);
		}
		return generos;		
	}
	
	public Genero generoPreferido(){
		return obtenerGeneroMasVisto();
	}
	
	private Map<Genero,Double> obtenerMapaGeneroVsTiempoVisto(){
		Map<Genero,Double> mapa = new HashMap<Genero,Double>();
		
		List<Genero> generos = this.generosVistos();
		for (Genero unGenero : generos)
			mapa.put(unGenero, 0.0);
		
		for (Mirable unMirable : visto){
			Genero unGenero = unMirable.getGenero();
			double valorActual = mapa.get(unGenero);
			mapa.put(unGenero, valorActual + unMirable.getDuracion() );	
		}
		return mapa;
	}

	private Genero obtenerGeneroMasVisto() {
		Map<Genero, Double> mapa = this.obtenerMapaGeneroVsTiempoVisto();
		if (mapa.isEmpty())
			return null;
		Iterator<Genero> iterador = (mapa.keySet()).iterator();
		Genero generoMasVisto = iterador.next();
		while (iterador.hasNext()){
			Genero generoActual = iterador.next();
			if (mapa.get(generoActual) > mapa.get(generoMasVisto))
				generoMasVisto = generoActual;
		}
		return generoMasVisto;	
	}
	
	public boolean esFanDe(Actor unActor){
		for (Mirable unMirable : this.visto)
			if ( ! unMirable.actuoElActor(unActor))
				return false;
		return true;
	}
	
}

package infierno;

import java.util.LinkedList;
import java.util.List;

public class Lugar {
	
	private List<Alma> almas;
	
	public Lugar(){
		this.almas = new LinkedList<Alma>();
	}
	
	public void agregarAlma(Alma unAlma){
		if ( this.estaElAlma(unAlma) )
			return;
		almas.add(unAlma);
		unAlma.setLugarActual(this);
	}
	
	public void agregarAlmas(List<Alma> nuevasAlmas) {
		for (Alma unAlma : nuevasAlmas)
			this.agregarAlma(unAlma);
	}
	
	public boolean quitarAlma(Alma unAlma){
		if ( !this.estaElAlma(unAlma) )
			return false;
		almas.remove(unAlma);
		return true;
	}

	public boolean quitarAlmas(List<Alma> almasAQuitar) {
		for (Alma unAlma : almasAQuitar)
			if( !this.quitarAlma(unAlma) )
				return false;
		return true;
	}
	
	public boolean estaElAlma(Alma unAlma){
		if (unAlma == null)
			return false;
		return almas.contains(unAlma);
	}
	
	public List<Alma> getAlmas(){
		return almas;
	}
	
	public List<Alma> getAlmasCazables(Demonio unDemonio){
		List<Alma> almasCazables = new LinkedList<Alma>();
		if (this.estaVacioElLugar() )
			return almasCazables;
		for (Alma unAlma : this.almas)
			if (unDemonio.puedeCazar(unAlma))
				almasCazables.add(unAlma);
		return almasCazables;
	}
	

	public List<Alma> getAlmasNoCazables(Demonio unDemonio) {	
		List<Alma> almasNoCazables = new LinkedList<Alma>();
		if (this.estaVacioElLugar() )
			return almasNoCazables;
		for (Alma unAlma : this.almas)
			if ( !unDemonio.puedeCazar(unAlma))
				almasNoCazables.add(unAlma);
		return almasNoCazables;
	}
	

	public int getPoblacion() {
		return almas.size();
	}
	
	public boolean estaVacioElLugar(){
		return (this.getPoblacion() == 0);
	}





}

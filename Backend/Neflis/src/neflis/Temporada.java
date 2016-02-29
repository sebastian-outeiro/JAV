package neflis;

import java.util.LinkedList;
import java.util.List;

public class Temporada {
	
	private List<Capitulo> capitulos;
	private int numeroDeTemporada;
	private int cantidadCapitulos;
	private Serie miSerie;
	
	public Temporada(int cantidadDeCapitulos, int elNumeroDeTemporada){
		this.cantidadCapitulos = cantidadDeCapitulos;
		this.numeroDeTemporada = elNumeroDeTemporada;
		this.capitulos = new LinkedList<Capitulo>();
	}
	
	public int getNumeroDeTemporada(){
		return this.numeroDeTemporada;
	}
	
	public int getCantidadCapitulos(){
		return this.cantidadCapitulos;
	}

	public double getDuracion() {
		double duracionTotal = 0;
		for (Capitulo unCapitulo : capitulos)
			duracionTotal = duracionTotal + unCapitulo.getDuracion();
		return duracionTotal;
	}
	

	public void setSerie(Serie unaSerie) {
		this.miSerie = unaSerie;
	}
	
	private Capitulo obtenerUltimoCapituloEmitido(){
		return this.capitulos.get(this.capitulos.size() - 1);
	}
	
	public boolean agregarCapitulo(Capitulo unCapitulo){
		/** Si la temporada no tiene capitulos debo revisar que este agregando 
		 * 	el primer capitulo de la misma. En caso contrario, debo verificar 
		 * 	que sea el capitulo subsiguiente al ultimo emitido */
		if ( this.capitulos.isEmpty() )
			if (unCapitulo.esElPrimerCapituloDeLaTemporada())
				return this.agregarNuevoCapitulo(unCapitulo);
		else
			if (unCapitulo.esElCapituloSiguienteDe( this.obtenerUltimoCapituloEmitido() ) )
				return this.agregarNuevoCapitulo(unCapitulo);
		return false;
	}
	
	private boolean agregarNuevoCapitulo(Capitulo unCapitulo){
		// Faltaria verificar el caso de que el capitulo exceda la cantidad de capitulos de la temporada
		if ( this.capitulos.contains(unCapitulo) )
			return false;
		this.capitulos.add(unCapitulo);
		unCapitulo.setTemporada(this);
		return true;
	}

	public boolean vistoTotalmente(Usuario unUsuario) {
		for (Capitulo unCapitulo : capitulos)
			if ( ! unCapitulo.vistoTotalmente(unUsuario) )
				return false;
		return true;
	}

	public Capitulo getUltimoCapituloEmitido() {
		if (this.capitulos.isEmpty())
			return null;
		return this.capitulos.get(this.capitulos.size() - 1);
	}

	public Genero getGenero() {
		return this.miSerie.getGenero();
	}

	public boolean actuoElActorComoInvitado(Actor unActor) {
		for (Capitulo unCapitulo : this.capitulos)
			if (unCapitulo.actuoElActorComoInvitado(unActor))
				return true;
		return false;
	}

	public boolean actuoElActorComoProtagonista(Actor unActor) {
		return this.miSerie.actuoElActorComoProtagonista(unActor);
	}

	
	
	


}

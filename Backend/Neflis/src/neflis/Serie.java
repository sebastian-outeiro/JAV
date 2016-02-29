package neflis;

import java.util.List;

public class Serie extends Contenido {
	
	public Serie(Genero unGenero) {
		super(unGenero);
	}

	private List<Temporada> temporadas;

	@Override
	public double getDuracion() {
		double duracionTotal = 0;
		for (Temporada unaTemporada : temporadas)
			duracionTotal = duracionTotal + unaTemporada.getDuracion();
		return duracion;
	}
	
	
	public void agregarTemporada(Temporada unaTemporada){
		if (  temporadas.contains(unaTemporada))
			return;
		temporadas.add(unaTemporada);
		unaTemporada.setSerie(this);
	}


	@Override
	public boolean vistoTotalmente(Usuario unUsuario) {
		for (Temporada unaTemporada : this.temporadas)
			if ( ! unaTemporada.vistoTotalmente(unUsuario))
				return false;
		return true;
	}
	
	public Capitulo getUltimoCapituloEmitido(){
		if (temporadas.isEmpty())
			return null;
		Temporada ultimaTemporada = temporadas.get(temporadas.size() - 1);
		return ultimaTemporada.getUltimoCapituloEmitido();
	}


	@Override
	public boolean actuoElActor(Actor unActor) {
		return (super.actuoElActor(unActor) || this.actuoElActorComoInvitado(unActor) );
	}


	public boolean actuoElActorComoInvitado(Actor unActor) {
		for (Temporada unaTemporada : this.temporadas)
			if (unaTemporada.actuoElActorComoInvitado(unActor))
				return true;
		return false;
	}


	public boolean actuoElActorComoProtagonista(Actor unActor) {
		return this.actoresProtagonistas.contains(unActor);
	}

}

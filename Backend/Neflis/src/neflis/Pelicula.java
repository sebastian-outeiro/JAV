package neflis;

import java.util.List;

public class Pelicula extends Contenido implements Mirable {
	
	public Pelicula(Genero unGenero, List<Actor> losActoresProtagonistas, double unaDuracion ){
		super(unGenero);
		this.actoresProtagonistas = losActoresProtagonistas;
		this.duracion = unaDuracion;
	}

	@Override
	public double getDuracion() {
		return this.duracion;
	}

	@Override
	public boolean vistoTotalmente(Usuario unUsuario) {
		return unUsuario.viste( this );
	}

	@Override
	public boolean actuoElActor(Actor unActor) {
		return super.actuoElActor(unActor);
	}
	

}

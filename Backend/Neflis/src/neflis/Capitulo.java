package neflis;

import java.util.List;

public class Capitulo implements Mirable{

	private int numeroDeCapitulo;
	private double duracion;
	private List<Actor> actoresInvitados;
	private Temporada miTemporada;

	public Capitulo(int numeroDeCapitulo, double duracion, List<Actor> actoresInvitados){
		this.numeroDeCapitulo = numeroDeCapitulo;
		this.duracion = duracion;
		this.actoresInvitados = actoresInvitados;
	}
	
	public int getNumeroDeCapitulo(){
		return this.numeroDeCapitulo;
	}
	
	public List<Actor> getActoresInvitados(){
		return this.actoresInvitados;
	}
	
	public double getDuracion() {
		return duracion;
	}
	
	public void setTemporada(Temporada unaTemporada) {
		this.miTemporada = unaTemporada;
	}
	
	@Override
	public Genero getGenero() {
		return this.miTemporada.getGenero();
	}

	public boolean vistoTotalmente(Usuario unUsuario) {
		return unUsuario.viste( this );
	}

	public boolean actuoElActorComoInvitado(Actor unActor) {
		return this.actoresInvitados.contains(unActor);
	}

	@Override
	public boolean actuoElActor(Actor unActor) {
		return (this.actuoElActorComoInvitado(unActor) || this.miTemporada.actuoElActorComoProtagonista(unActor));
	}

	public boolean esElPrimerCapituloDeLaTemporada() {
		return (this.numeroDeCapitulo == 1);
	}

	public boolean esElCapituloSiguienteDe(Capitulo capituloAnterior) {
		return (this.numeroDeCapitulo == capituloAnterior.getNumeroDeCapitulo() + 1);
	}
	
	

	

	
	

}

package neflis;

import java.util.List;

public abstract class Contenido {
	
	protected double duracion;
	protected List<Actor> actoresProtagonistas;
	private Genero genero;
	private boolean destacadoDelMes;
	
	public Contenido(Genero unGenero){
		this.genero = unGenero;
	}
	
	public abstract double getDuracion();

	public List<Actor> getActoresProtagonistas() {
		return actoresProtagonistas;
	}

	public void setActoresProtagonistas(List<Actor> actoresProtagonistas) {
		this.actoresProtagonistas = actoresProtagonistas;
	}
	
	public Genero getGenero(){
		return this.genero;
	}

	public abstract boolean vistoTotalmente(Usuario unUsuario);
	
	public boolean actuoElActor(Actor unActor){
		return this.actoresProtagonistas.contains(unActor);
	}
	
	public void destacarContenido(){
		this.destacadoDelMes = true;
	}
	
	public void retirarContenidoDeDestacado(){
		this.destacadoDelMes = false;
	}
	
	public boolean esDestacadoDelMes(){
		return this.destacadoDelMes;
	}

	

}

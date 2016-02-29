package neflis;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Repositorio {
	
	private Set<Contenido> contenidos;
	private Set<Usuario> usuarios;
	
	public Repositorio(){
		this.contenidos = new HashSet<Contenido>();
		this.usuarios = new HashSet<Usuario>();
	}
	
	public boolean agregarContenido(Contenido unContenido){
		return contenidos.add(unContenido);	
	}
	
	public boolean agregarUsuario(Usuario unUsuario){
		return usuarios.add(unUsuario);
	}
	
	public List<Contenido> contenidosRecomendadosPara(Usuario unUsuario){
		List<Contenido> recomendados = new LinkedList<Contenido>();
		recomendados.addAll(this.recomendadosDelMes());
		recomendados.addAll(this.recomendadosParaUnUsuario(unUsuario));
		return recomendados;
	}

	private Collection<Contenido> recomendadosParaUnUsuario(Usuario unUsuario) {
		Collection<Contenido> recomendados = new HashSet<Contenido>();
		for (Contenido unContenido : this.contenidos)
			if (unUsuario.visteTotalmente(unContenido))
				recomendados.add(unContenido);
		return recomendados;
	}

	public Collection<Contenido> recomendadosDelMes() {
		Collection<Contenido> recomendados = new HashSet<Contenido>();
		for (Contenido unContenido : this.contenidos)
			if (unContenido.esDestacadoDelMes())
				recomendados.add(unContenido);
		return recomendados;
	}
	

}

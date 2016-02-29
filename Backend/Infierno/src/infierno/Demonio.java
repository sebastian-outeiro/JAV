package infierno;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public abstract class Demonio {
	
	private static double valorDeTormento = 5;
	
	private List<Alma> almasCazadas;
	private double nivelDeMaldad;
	protected EstadoAnimico estadoAnimico;

	public Demonio(double nivelDeMaldad){
		this.nivelDeMaldad = nivelDeMaldad;
		this.almasCazadas = new LinkedList<Alma>();
		this.estadoAnimico = Normal.getInstance();
	}
	
	public double getNivelDeMaldad() {
		return nivelDeMaldad;
	}

	public void setNivelDeMaldad(double nivelDeMaldad) {
		this.nivelDeMaldad = nivelDeMaldad;
	}
	
	public List<Alma> getAlmasCazadas(){
		return this.almasCazadas;
	}
	
	public boolean cazar(Alma unAlma){
		if ( ! this.puedeCazar(unAlma) ){
			this.atormentar(unAlma);
			return false;
		}
		// Acciones comunes de cazar
		this.almasCazadas.add(unAlma);
		unAlma.meCazaron();
		this.controlarEstado();
		return true;
	}
	
	public void cazarEnUnLugar(Lugar unLugar){
		List<Alma> almasCazables = unLugar.getAlmasCazables(this);
		
		Iterator<Alma> iterador = almasCazables.iterator();
		while( iterador.hasNext() ){
			Alma unAlma = iterador.next();
			this.cazar(unAlma);
		}
		
		this.atormentarEnUnLugarAlmasNoCazables(unLugar);
	}
	
	private void controlarEstado() {
		if (this.almasCazadas.size() == 20)
			this.setEstadoFeliz();
	}

	public boolean puedeCazar(Alma unAlma){
		return (this.estadoAnimico.puedeCazar(this, unAlma));
	}
	
	public boolean puedeAtormentarHastaLaLocura(Alma unAlma){
		if (unAlma.getBondad() - valorDeTormento < 10)
			return true;
		return false;
	}
	
	public boolean puedeAtormentarHastaLaLocuraEnUnLugar(Lugar unLugar){
		// Necesita encontrar al menos un alma que pueda ser atormentada hasta la locura
		for (Alma unAlma : unLugar.getAlmas() )
			if (this.puedeAtormentarHastaLaLocura(unAlma) )
				return true;
		return false;
	}
	
	
	public void atormentar(Alma unAlma){
		unAlma.reducirBondad(valorDeTormento);
	}
	
	public void atormentarEnUnLugarAlmasNoCazables(Lugar unLugar){
		List<Alma> almasNoCazables = unLugar.getAlmasNoCazables(this);
		for (Alma unAlma : almasNoCazables)
			this.atormentar(unAlma);
	}
	
	public void atormentarEnUnLugar(Lugar unLugar){
		List<Alma> almasDelLugar = unLugar.getAlmas();
		for (Alma unAlma : almasDelLugar)
			this.atormentar(unAlma);
	}


	public List<Alma> rendirCuenta() {
		List<Alma> listadoRetorno = new LinkedList<Alma>();
		listadoRetorno.addAll(this.almasCazadas);
		this.almasCazadas.clear();
		return listadoRetorno;
	}

	public void castigar() {
		this.nivelDeMaldad = this.nivelDeMaldad / 10;	
		this.setEstadoDeprimido();
	}
	
	public boolean puedeRealizarLaMision(Mision unaMision){
		return unaMision.puedeRealizarse(this);
	}
	
	public boolean realizarLaMision(Mision unaMision){
		boolean resultado = unaMision.realizar(this);
		if (resultado)
			this.estadoAnimico.premiar(this);
		return resultado;
	}
	
	
	public void setEstadoFeliz(){
		this.estadoAnimico = Feliz.getInstance();
	}
	
	public void setEstadoNormal(){
		this.estadoAnimico = Normal.getInstance();
	}
	
	public void setEstadoDeprimido(){
		this.estadoAnimico = Deprimido.getInstance();
	}
	
	public void setEstado(EstadoAnimico nuevoEstado){
		this.estadoAnimico = nuevoEstado;
	}
	
	public EstadoAnimico getEstadoAnimico() {
		return this.estadoAnimico;
	}

	public void aumentarMaldad(double puntosDeMaldad) {
		this.nivelDeMaldad = this.nivelDeMaldad + puntosDeMaldad;	
	}
	
	public double calcularPoderParaEntregar(){
		double poder = 0;
		for (Alma unAlma : this.almasCazadas)
			poder = poder + unAlma.poder();
		return poder;		
	}



}

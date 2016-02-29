package infierno;

public interface EstadoAnimico {
	
	public boolean puedeCazar(Demonio unDemonio, Alma unAlma);
	public boolean puedeAtormentar(Demonio unDemonio, Alma unAlma);
	public void premiar(Demonio unDemonio);

}

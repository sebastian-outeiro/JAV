package infierno;

public class Deprimido implements EstadoAnimico {
	
	private static Deprimido instancia;
	private static double multiplicador = 2;
	
	private Deprimido(){}
	
	public static Deprimido getInstance(){
		if (instancia == null)
			instancia = new Deprimido();
		return instancia;
	}

	@Override
	public boolean puedeCazar(Demonio unDemonio, Alma unAlma) {
		// Necesita el Doble de puntos de Maldad que Bondad para capturar
		return (unDemonio.getNivelDeMaldad() >= unAlma.getBondad() * multiplicador);
	}

	@Override
	public boolean puedeAtormentar(Demonio unDemonio, Alma unAlma) {
		return false;
	}

	@Override
	public void premiar(Demonio unDemonio) {
		unDemonio.setEstado(Normal.getInstance());	
	}

}

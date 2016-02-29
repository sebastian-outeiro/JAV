package infierno;

public class Feliz implements EstadoAnimico {
	
	private static Feliz instancia;
	private static double multiplicador = 0.5;
	
	private Feliz(){}
	
	public static Feliz getInstance(){
		if (instancia == null)
			instancia = new Feliz();
		return instancia;
	}

	@Override
	public boolean puedeCazar(Demonio unDemonio, Alma unAlma) {
		// Necesita la mitad de puntos de Maldad que Bondad para capturar
		return (unDemonio.getNivelDeMaldad() >= unAlma.getBondad() * multiplicador);
	}

	@Override
	public boolean puedeAtormentar(Demonio unDemonio, Alma unAlma) {
		return true;
	}

	@Override
	public void premiar(Demonio unDemonio) {
		unDemonio.aumentarMaldad(5);	
	}

}

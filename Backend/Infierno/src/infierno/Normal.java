package infierno;

public class Normal implements EstadoAnimico {
	
	private static Normal instancia;
	
	private Normal(){}
	
	public static Normal getInstance(){
		if (instancia == null)
			instancia = new Normal();
		return instancia;
	}

	@Override
	public boolean puedeCazar(Demonio unDemonio, Alma unAlma) {
		return (unDemonio.getNivelDeMaldad() > unAlma.getBondad());
	}

	@Override
	public boolean puedeAtormentar(Demonio unDemonio, Alma unAlma) {
		return true;
	}

	@Override
	public void premiar(Demonio unDemonio) {
		unDemonio.setEstado(Feliz.getInstance());
	}

}

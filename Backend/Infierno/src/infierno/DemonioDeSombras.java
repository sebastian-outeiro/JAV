package infierno;

public class DemonioDeSombras extends Demonio {
	
	private static double nivelMaximoDeBondad = 50;
	
	public DemonioDeSombras(double nivelDeMaldad) {
		super(nivelDeMaldad);
	}

	@Override
	public boolean puedeCazar(Alma unAlma) {
		if ( ! super.puedeCazar(unAlma) ||  (unAlma.getBondad() >=  nivelMaximoDeBondad))
			return false;
		return true;
	}

	@Override
	public void atormentar(Alma unAlma) {
		super.atormentar(unAlma);
		unAlma.reducirValor(50);
	}

}

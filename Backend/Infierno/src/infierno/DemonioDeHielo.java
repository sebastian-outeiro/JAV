package infierno;

public class DemonioDeHielo extends Demonio {

	public DemonioDeHielo(double nivelDeMaldad) {
		super(nivelDeMaldad);
	}

	@Override
	public boolean puedeCazar(Alma unAlma) {
		if ( ! super.puedeCazar(unAlma) || ! unAlma.isFriolenta() )
			return false;
		return true;
	}

	@Override
	public void atormentar(Alma unAlma) {
		super.atormentar(unAlma);
		if (this.estadoAnimico.puedeAtormentar(this, unAlma))
			unAlma.setFriolenta(true);
	}

}

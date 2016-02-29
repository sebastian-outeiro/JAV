package infierno;

public class Alma {
	
	private double bondad;
	private double valor;
	private boolean friolenta;
	private Lugar lugarActual;
	
	public Alma(double bondad, double valor, boolean friolenta){
		this.setBondad(bondad);
		this.setValor(valor);
		this.setFriolenta(friolenta);
		this.lugarActual = null;
	}
	
	public double getBondad() {
		return bondad;
	}
	public void setBondad(double bondad) {
		this.bondad = bondad;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public boolean isFriolenta() {
		return friolenta;
	}
	public void setFriolenta(boolean friolenta) {
		this.friolenta = friolenta;
	}
	
	public void reducirBondad(double reduccionDeBondad) {
		this.bondad = this.bondad - reduccionDeBondad;
	}
	
	public void reducirValor(double porcentajeReduccion) {
		this.valor = this.valor * (1 - porcentajeReduccion / 100 );
		
	}

	public Lugar getLugarActual() {
		return lugarActual;
	}

	public void setLugarActual(Lugar lugarActual) {
		this.lugarActual = lugarActual;
	}

	public void meCazaron() {
		if (lugarActual != null)
			this.lugarActual.quitarAlma(this);
		this.lugarActual = null;
	}
	
	public double poder(){
		return this.bondad + this.valor;
	}

	

}

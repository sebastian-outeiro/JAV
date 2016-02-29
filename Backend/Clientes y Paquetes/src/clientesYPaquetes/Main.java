package clientesYPaquetes;

public class Main {

	public static void main(String[] args) throws SaldoInsuficienteException {
		Cliente pax = new Individuo(2.0);
		Paquete unPaquete = new Paquete(new EstrategiaPrecioFijo());
		unPaquete.setPrecioFijo(3000.0);
		
		pax.comprarPaquete(unPaquete);

	}

}

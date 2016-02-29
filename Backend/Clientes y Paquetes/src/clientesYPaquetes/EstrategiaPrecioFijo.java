package clientesYPaquetes;

public class EstrategiaPrecioFijo implements EstrategiaPrecio {

	@Override
	public double calcularPrecioBase(Paquete paquete) {
		return paquete.getPrecioFijo();
	}

}

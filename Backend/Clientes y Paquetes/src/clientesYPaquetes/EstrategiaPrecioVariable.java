package clientesYPaquetes;

public class EstrategiaPrecioVariable implements EstrategiaPrecio {
	
	private static double porcentajeDelSueldoDelCliente = 0.10;

	@Override
	public double calcularPrecioBase(Paquete paquete) {
		return paquete.getCliente().getSaldo() * porcentajeDelSueldoDelCliente;
	}

}

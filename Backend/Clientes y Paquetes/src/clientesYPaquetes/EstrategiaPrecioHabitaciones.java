package clientesYPaquetes;

public class EstrategiaPrecioHabitaciones implements EstrategiaPrecio {

	@Override
	public double calcularPrecioBase(Paquete paquete) {
		return paquete.getHabitaciones() * paquete.getCostoHabitacion();
	}

}

package clientesYPaquetes;

public class Paquete {
	
	private EstrategiaPrecio estrategiaPrecio;
	
	private double impuesto;

	private Cliente cliente;
	
	private double precioFijo;
	
	private int habitaciones;
	private double costoHabitacion;
	


	public Paquete(EstrategiaPrecio unaEstrategiaParaElPrecio){
		this.estrategiaPrecio = unaEstrategiaParaElPrecio;
		this.iniciarVariables();
	}

	
	private void iniciarVariables() {
		this.impuesto = 0;
		this.precioFijo = 0;
		this.habitaciones = 0;
		this.costoHabitacion = 0;
		this.cliente = null;
	}


	public double costo() {
		return ( this.estrategiaPrecio.calcularPrecioBase(this) * ( 1.00 + impuesto/100.0 ) ); 
	}

	public double getPrecioFijo() {
		return this.precioFijo;
	}
	
	public void setPrecioFijo(double precioFijo) {
		this.precioFijo = precioFijo;
	}

	
	public void setImpuesto(double impuesto){
		this.impuesto = impuesto;
	}
	
	public void setHabitaciones(int cantidadHabitaciones){
		this.habitaciones = cantidadHabitaciones;
	}
	
	public void setCostoHabitacion(double unCostoHabitacion){
		this.costoHabitacion = unCostoHabitacion;
	}
	
	public void setCliente(Cliente unCliente){
		this.cliente = unCliente;
	}
	
	public int getHabitaciones(){
		return this.habitaciones;
	}
	
	public double getCostoHabitacion(){
		return this.costoHabitacion;
	}
	
	public Cliente getCliente(){
		return this.cliente;
	}


	public void setEstrategia(EstrategiaPrecio unaEstrategia) {
		this.estrategiaPrecio = unaEstrategia;
		
	}


	public double costoReserva() {
		return this.costo() / 2.0;
	}

}

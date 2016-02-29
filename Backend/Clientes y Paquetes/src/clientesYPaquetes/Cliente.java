package clientesYPaquetes;

import java.util.ArrayList;

public abstract class Cliente {

	protected double saldo;
	private ArrayList<Paquete> paquetesComprados;

	public Cliente(double unSaldo) {
		this.paquetesComprados = new ArrayList<Paquete>();
		this.saldo = unSaldo;
	}

	public Cliente() {
		this(0.0);
	}

	public double getSaldo() {
		return this.saldo;
	}

	public void comprarPaquete(Paquete paquete) throws SaldoInsuficienteException {
		double costoPaquete = paquete.costo();
		if ( costoPaquete > this.saldo ){
			throw new SaldoInsuficienteException();
		}
		this.saldo = this.saldo - costoPaquete;
		this.paquetesComprados.add(paquete);	
	}

	public int cantidadPaquetesComprados() {
		return this.paquetesComprados.size();
	}

	public double gastoTotal() {
		double gastoTotal = 0.0;
		for (Paquete unPaquete : this.paquetesComprados){
			gastoTotal = gastoTotal + unPaquete.costo();
		}
		return gastoTotal;
	}

	public Paquete getPaqueteConMayorCosto() {
		Paquete paqueteConMayorCosto = this.paquetesComprados.get(0);
		for (int posicion = 1; posicion < this.paquetesComprados.size() ; posicion++ ){
			Paquete paqueteActual = paquetesComprados.get(posicion);
			if (paqueteActual.costo() > paqueteConMayorCosto.costo() )
				paqueteConMayorCosto = paqueteActual;
		}
		return paqueteConMayorCosto;
	}

}

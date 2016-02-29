package clientesYPaquetes;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Cliente {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Cliente.class);

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
			SaldoInsuficienteException e = new SaldoInsuficienteException("Saldo Insuficiente");
			LOGGER.error("Saldo Insuficiente",e);
			throw e;	
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

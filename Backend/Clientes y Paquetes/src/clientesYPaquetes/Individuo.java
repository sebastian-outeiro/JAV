package clientesYPaquetes;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import clientesYPaquetes.Cliente;
import clientesYPaquetes.Paquete;

public class Individuo extends Cliente {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Individuo.class);

	private Paquete paquetesReservados;

	public Individuo(double saldo) {
		super(saldo);
		this.paquetesReservados = null;
	}
	
	public boolean reservarPaquete(Paquete unPaquete){
		double costoReserva = unPaquete.costoReserva();
		if ( costoReserva > this.saldo )
			return false;
		
		this.paquetesReservados = unPaquete;
		this.saldo = this.saldo - costoReserva;
		return true;
	}
	
	public boolean finalizarComprar(Paquete unPaquete){
		if ( this.paquetesReservados == null )
			return false;
		
		double costoPorPagar = unPaquete.costo() - unPaquete.costoReserva();
		if ( costoPorPagar > this.saldo )
			return false;
		
		this.saldo = this.saldo - costoPorPagar;
		this.paquetesReservados = null;
		return true;				
	}

	public boolean reservoElPaquete(Paquete unPaquete) {
		return (this.paquetesReservados == unPaquete);
	}
	
	@Override
	public void comprarPaquete(Paquete unPaquete) throws SaldoInsuficienteException{
		if (this.reservoElPaquete(unPaquete)){
			this.finalizarComprar(unPaquete);
			return;
		}
		// En caso de que trate de comprar un paquete con otro reservado
		if (this.paquetesReservados != null){
			RuntimeException e = new ElUsuarioDebeFinalizarLaCompraDelPaqueteReservadoException();	
			LOGGER.error("Se quizo comprar un segundo paquete teniendo uno reservado",e);
			throw e;
		}
		super.comprarPaquete(unPaquete);
		
	}

}

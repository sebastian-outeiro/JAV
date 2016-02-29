package clientesYPaquetes;

import java.util.List;

public class Empresa extends Cliente{
	
	public Empresa(double saldo) {
		super(saldo);
	}

	public void comprarPaquetes(List<Paquete> paquetes) throws SaldoInsuficienteException{
		for (Paquete unPaquete : paquetes){
			super.comprarPaquete(unPaquete);
		}
	}

}

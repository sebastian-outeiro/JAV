package infierno;

import java.util.List;

public class Tormento implements Mision {
	
	private List<Lugar> lugares;

	public Tormento(List<Lugar> listadoLugares){
		this.lugares = listadoLugares;
	}
	

	@Override
	public boolean puedeRealizarse(Demonio unDemonio) {
		for (Lugar unLugar : this.lugares)
			if ( ! unDemonio.puedeAtormentarHastaLaLocuraEnUnLugar(unLugar) )
				return false;
		return true;
	}

	@Override
	public boolean realizar(Demonio unDemonio) {
		boolean resultado =  this.puedeRealizarse(unDemonio);
		for (Lugar unLugar : this.lugares)
			unDemonio.atormentarEnUnLugar(unLugar);
		return resultado;			
	}

}

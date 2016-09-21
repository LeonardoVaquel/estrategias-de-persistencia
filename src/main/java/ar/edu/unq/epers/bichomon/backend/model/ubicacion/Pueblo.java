package ar.edu.unq.epers.bichomon.backend.model.ubicacion;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.bicho.BichoConPorcentaje;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;

public class Pueblo  extends Ubicacion{
	private List<BichoConPorcentaje> bichosQueLoHabitan;
	
	public Pueblo(){
		super();
		this.bichosQueLoHabitan= new ArrayList<>();
	}
	
	@Override
	public void buscar(Entrenador entrenador) {
		
	}

	
	
			
}

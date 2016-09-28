package ar.edu.unq.epers.bichomon.backend.model.ubicacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;

public class Guarderia  extends Ubicacion{
	
	public Guarderia( String nombreGuarderia,Random random){
		super(nombreGuarderia, random);
		
		
	}
	public void abandonar(Entrenador entrenador, Bicho bicho){
		
		this.getBichos().add(bicho);
		entrenador.abandonarBicho(bicho);
	}
	

	public Integer cantidadDeBichosAbandonados(){
		return this.getBichos().size();
	}
	
	public Bicho adoptarBichoAbandonado(){
		Integer index = this.numeroRandom();
		
		return this.getBichos().get(index);
		
	}
	public Integer numeroRandom(){
		Integer valor = random.nextInt(this.cantidadDeBichosAbandonados());
		return valor;
	}
	
	
	
	public Bicho buscar(Entrenador entrenador) {
		
		
		try {
			if (entrenador.puedeBuscar()) {
				Bicho bicho = adoptarBichoAbandonado();
				entrenador.obtenerBicho(bicho);
				return bicho;
			}
			else {
				throw new RuntimeException("balballa");
			}
		}
		catch(RuntimeException e) {
			System.out.println(e.getMessage());
			return null;
		}
		
	}
	
}


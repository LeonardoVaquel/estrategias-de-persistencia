package ar.edu.unq.epers.bichomon.backend.model.ubicacion;

import java.util.Random;

import javax.persistence.Entity;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.collection.BichoCollectionReachedMaximumSize;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;

@Entity
public class Guarderia  extends Ubicacion{
	
	public Guarderia(String nombreGuarderia,Random random){
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
		//falta sacarlo de la lista de bichos
	}
	
	public Integer numeroRandom(){
		return random.nextInt(this.cantidadDeBichosAbandonados());
	}
	
	public Bicho buscar(Entrenador entrenador) {
		
		try {
			if (entrenador.puedeBuscar()) {
				Bicho bicho = adoptarBichoAbandonado();
				entrenador.obtenerBicho(bicho);
				return bicho;
			}
			else {
				throw new BichoCollectionReachedMaximumSize();
			}
		}
		catch(RuntimeException e) {
			System.out.println(e.getMessage());
			return null;
		}
		
	}
	
}


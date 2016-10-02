package ar.edu.unq.epers.bichomon.backend.model.ubicacion;

import java.util.Random;

import javax.persistence.Entity;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.collection.BichoCollectionReachedMaximumSize;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.duelo.ResultadoCombate;

@Entity
public class Guarderia  extends Ubicacion{
	
	public Guarderia(String nombreGuarderia,Random random){
		super(nombreGuarderia, random);
	}
	
	public Guarderia(String nombre) {
		super(nombre);
	}
	
	public Guarderia() {}
	
	public void abandonar(Entrenador entrenador, Bicho bicho){
		
		if(entrenador.puedeAbandonar()) {
			this.getBichos().add(bicho);
			entrenador.abandonarBicho(bicho);
		}
	}
	

	public Integer cantidadDeBichosAbandonados(){
		return this.getBichos().size();
	}
	
	public Bicho adoptarBichoAbandonado(){
		Integer index = this.numeroRandom();
		
		Bicho bicho = this.getBichos().get(index);
		this.bichos.remove(bicho);
		return bicho;
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

	@Override
	public ResultadoCombate duelo(Entrenador entrenador, Bicho bicho) {
		throw new NoSePuedeRealizarDueloEnUbicacion(this.getNombre());
	}
	
}


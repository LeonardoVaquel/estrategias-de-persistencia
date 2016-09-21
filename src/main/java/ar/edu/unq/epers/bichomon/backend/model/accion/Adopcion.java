package ar.edu.unq.epers.bichomon.backend.model.accion;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Guarderia;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Ubicacion;

public class Adopcion extends Accion{
	
	public Adopcion (Entrenador entrenador, Guarderia guarderia){
		this.setUbicacion(guarderia);
		this.setEntrenador(entrenador);
	}
	
	public Bicho adoptarBichoAbandonado(){
		Integer index = this.numeroRandom();
		
		
		
		return this.getUbicacion().getBichosAbandonados().get(index);
		
	}
	public Integer numeroRandom(){
		Integer valor = random.nextInt(this.cantidadDeBichosAbandonados());
		return valor;
	}
}

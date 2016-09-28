package ar.edu.unq.epers.bichomon.backend.model.ubicacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.Transient;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.buscador.Buscador;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;

@Entity
public class Pueblo extends Ubicacion{
	
	@Transient
	private Buscador buscador;
	
	public Pueblo(String nombrePueblo,Random random ){
		super(nombrePueblo,random);
		//this.buscador = new Buscador(this.bichos); descomentar desp del commit
		
		// aca va una instancia de UbicacionManager,
		// que utiliza un servicio de DAO que todavia no esta
		// va a saber decir getListaDeBichos que devuelve
		// una lista HashMap (especie, double)
		// sobre la que trabajará el Buscador
	}
	
	@Override
	public Bicho buscar(Entrenador entrenador) {
		return null;
	}

	
	
			
}

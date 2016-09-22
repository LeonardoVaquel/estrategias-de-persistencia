package ar.edu.unq.epers.bichomon.backend.model.evolucion;

import java.util.Collection;
import java.util.List;

import ar.edu.unq.epers.bichomon.backend.dao.EvolucionDAO;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateEvolucionDAO;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.service.EvolucionManager;

/**
 * {@link EvolutionHandler}
 * @author santiago
 *
 */
public class EvolutionHandler {

	Bicho bicho;
	Entrenador entrenador;
	Especie especie;
	Evolucion evolucion;
	EvolucionManager evolucionManager;
	
	public EvolutionHandler() {
		this.evolucionManager = new EvolucionManager(new HibernateEvolucionDAO()); 
	}

	public Bicho getBicho() {
		return bicho;
	}

	public void setBicho(Bicho bicho) {
		this.bicho = bicho;
	}

	public Entrenador getEntrenador() {
		return entrenador;
	}

	public void setEntrenador(Entrenador entrenador) {
		this.entrenador = entrenador;
	}

	public Especie getEspecie() {
		return especie;
	}

	public void setEspecie(Especie especie) {
		this.especie = especie;
	}

	public Evolucion getEvolucion() {
		return evolucion;
	}

	public void setEvolucion(Evolucion evolucion) {
		this.evolucion = evolucion;
	}
	
	
	public Boolean puedeEvolucionar() {
		
		Evolucion evolution = this.evolucionManager.getEvolucion(this.especie.getNombre());
		Collection<CriterioEvolucion> criterios = this.evolucionManager.getCriteriosDeEvolucion(this.especie.getNombre());
		
		for (CriterioEvolucion criterioEvolucion : criterios) {
			// hacer algo con criterio.seCumple(Bicho, Entrenador)
		}
			
		
		return false;
	}
	
	public void evolucionar() {
		
	}

}

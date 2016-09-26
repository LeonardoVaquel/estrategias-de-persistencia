package ar.edu.unq.epers.bichomon.backend.model.evolucion;

import java.util.List;

import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateEvolucionDAO;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.exceptions.EvolutionException;
import ar.edu.unq.epers.bichomon.backend.service.EvolucionManager;

/**
 * {@link EvolutionHandler} es una clase que representa un objeto capaz de manejar un pedido de evolución
 * por parte de un {@link Bicho}
 * Es necesario proveerlo con una instancia de {@link Bicho} y de {@link Entrenador} mediante los métodos setter
 * públicos provistos. A partir de la instancia de {@link Bicho} podrá conocer la {@link Especie} a la que 
 * pertenece. 
 * Una instancia de {@link EvolutionHandler} tiene la responsabilidad de poder recuperar un objeto {@link Evolucion}
 * de una base de datos, con la que se encargará de realizar la operativa necesaria para manejar el pedido de un
 * {@link Bicho}.
 * @author santiago
 *
 */
public class EvolutionHandler {

	private Bicho bicho;
	private Entrenador entrenador;
	private Especie especie;
	private Evolucion evolucion;
	private EvolucionManager evolucionManager;
	private List<CriterioEvolucion> criterios;
	
	public EvolutionHandler() {
		this.setEvolucionManager(new EvolucionManager(new HibernateEvolucionDAO())); 
	}

	public void setEvolucionManager(EvolucionManager evolucionManager) {
		this.evolucionManager = evolucionManager;
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
	
	public void setCriterios(List<CriterioEvolucion> criterios) {
		this.criterios = criterios;
	}
	
	/**
	 * Se recorre una lista de {@link CriterioEvolucion} evaluando si un {@link Bicho} está apto
	 * para evolucionar. En caso de evaluarse negativamente se levantará la exception del primer
	 * criterio que no cumpla la condición. 
	 * @return - Booleano indicando que un {@link Bicho} está en condiciones de evolucionar.
	 */
	public Boolean puedeEvolucionar() {
		
		criterios = this.evolucionManager.getCriteriosDeEvolucion(this.especie.getNombre());
		Boolean result = true;
		try {
			for (CriterioEvolucion criterio : criterios) {
				result = result && criterio.seCumple(bicho, entrenador);
			}
			return result;
		}
		catch(EvolutionException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public void evolucionar() {
		if (this.puedeEvolucionar()) {
			Evolucion evolucion = this.evolucionManager.getEvolucion(this.especie.getNombre());
			this.bicho.evolucionar(evolucion.getEvolucion());
		}
	}

}

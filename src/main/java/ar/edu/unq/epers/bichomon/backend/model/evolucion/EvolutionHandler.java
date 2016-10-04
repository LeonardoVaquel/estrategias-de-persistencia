package ar.edu.unq.epers.bichomon.backend.model.evolucion;

import java.util.List;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.exceptions.EvolutionException;

/**
 * {@link EvolutionHandler} es una clase que representa un objeto capaz de manejar un pedido de evolución
 * por parte de un {@link Bicho}
 * Es necesario proveerlo con una instancia de {@link Bicho} y de {@link Entrenador} mediante los métodos setter
 * públicos provistos. A partir de la instancia de {@link Bicho} podrá conocer la {@link Especie} a la que 
 * pertenece. 
 * @author santiago
 *
 */
public class EvolutionHandler {

	private Bicho bicho;
	private Entrenador entrenador;
	private Especie especie;
	private List<CriterioEvolucion> criterios;
	
	public EvolutionHandler() {
		
	}

	public Bicho getBicho() {
		return bicho;
	}

	public void setBicho(Bicho bicho) {
		this.bicho = bicho;
		this.setEspecie(this.bicho.getEspecie());
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
	
	public void setCriterios(List<CriterioEvolucion> criterios) {
		this.criterios = criterios;
	}
	
	/**
	 * Se recorre una lista de {@link CriterioEvolucion} evaluando si un {@link Bicho} está apto
	 * para evolucionar. En caso de evaluarse negativamente se levantará la exception del primer
	 * criterio que no cumpla la condición. 
	 * @return - boolean indicando que un {@link Bicho} está en condiciones de evolucionar.
	 */
	public boolean puedeEvolucionar() {
		
		criterios = this.especie.getCriteriosDeEvolucion();
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
	
	/**
	 * Se evalúan las condiciones de un {@link Bicho} para evolucionar.
	 * De ser posible se retorna una instancia de bicho con su especie evolucionada.
	 * @return - una instancia de {@link Bicho}
	 */
	public Bicho evolucionar() {
		if (this.puedeEvolucionar()) {
			this.bicho.evolucionar(especie.getEvolucion());
		}
		return bicho;
	}

}

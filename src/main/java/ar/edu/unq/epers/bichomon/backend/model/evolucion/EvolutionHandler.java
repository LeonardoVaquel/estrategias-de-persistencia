package ar.edu.unq.epers.bichomon.backend.model.evolucion;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;

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
	
	public EvolutionHandler(){};

	public Bicho getBicho() {
		return bicho;
	}

	public void setBicho(Bicho bicho) {
		this.bicho = bicho;
	}
	
	/**
	 * Se recorre una lista de {@link CriterioEvolucion} evaluando si un {@link Bicho} está apto
	 * para evolucionar.
	 * @return - boolean indicando que un {@link Bicho} está en condiciones de evolucionar.
	 */
	public boolean puedeEvolucionar() {
		
		Boolean result = true;
		for (CriterioEvolucion criterio : this.bicho.getCriteriosDeEvolucion()) {
				result = result && criterio.seCumple(bicho, this.bicho.getOwner());
		}
		return result;
	}
	
	/**
	 * Se evalúan las condiciones de un {@link Bicho} para evolucionar.
	 * De ser posible se retorna una instancia de bicho con su especie evolucionada.
	 * @return - una instancia de {@link Bicho}
	 */
	public Bicho evolucionar() {
		if (this.puedeEvolucionar()) {
			this.bicho.evolucionar(this.bicho.getEvolucion());
		}
		return bicho;
	}

}

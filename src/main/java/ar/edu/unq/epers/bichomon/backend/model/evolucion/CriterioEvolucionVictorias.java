package ar.edu.unq.epers.bichomon.backend.model.evolucion;

import javax.persistence.Entity;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.exceptions.NotEnoughVictoriesToEvolve;

/**
 * {@link CriterioEvolucionVictorias} es una clase que representa un {@link CriterioEvolucion} teniendo
 * en cuenta la cantidad de victorias de un {@link Bicho} para poder evolucionar
 * @author santiago
 *
 */
@Entity
public class CriterioEvolucionVictorias extends CriterioEvolucion {

	/**
	 * Se crea una nueva instancia de {@link CriterioEvolucionVictorias}
	 * @param especie La {@link Especie} que representa la evolución
	 * @param tipo String que representa el tipo de criterio basado en las victorias de un {@link Bicho}
	 * @param valor Integer que representa la cantidad de victorias de un {@link Bicho} 
	 */
	public CriterioEvolucionVictorias(Especie especie, Integer valor) {
		super(especie, valor);
		this.setTipo("Victorias");
	}
	
	public CriterioEvolucionVictorias(Integer valor) {
		super(valor);
	}
	
	public CriterioEvolucionVictorias() {}

	/**
	 * Dada una instancia de {@link Bicho} y una instancia de {@link Entrenador}
	 * se evalúa la cantidad de victorias del bicho especificado.
	 * Se espera devolver true si el bicho posee al menos una determinada cantidad de victorias
	 */
	@Override
	public boolean seCumple(Bicho bicho, Entrenador entrenador) {
		Boolean condicion = bicho.getVictorias() > this.getValor(); 
		if (!condicion) {
			throw new NotEnoughVictoriesToEvolve(this.getValor());
		}
		else {
			return condicion;
		}
		
	}

}

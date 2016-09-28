package ar.edu.unq.epers.bichomon.backend.model.evolucion;

import javax.persistence.Entity;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.exceptions.NotEnoughEnergyToEvolve;

/**
 * {@link CriterioEvolucionEnergia} es una clase que representa un {@link CriterioEvolucion}
 * teniendo en cuenta una cierta cantidad de energia con la que un {@link Bicho} debe contar
 * para poder evolucionar. 
 * @author santiago
 *
 */
@Entity
public class CriterioEvolucionEnergia extends CriterioEvolucion {

	/**
	 * Se crea una nueva instancia de {@link CriterioEvolucionEnergia}
	 * @param especie La {@link Especie} que representa la evolución
	 * @param tipo String que representa el tipo de criterio basado en la energía de un {@link Bicho}
	 * @param valor Integer que representa la energía de un {@link Bicho} 
	 */
	public CriterioEvolucionEnergia(Especie especie, Integer valor) {
		super(especie, valor);
		this.setTipo("Energia");
	}

	@Override
	public Boolean seCumple(Bicho bicho, Entrenador entrenador) {
		Boolean condicion = bicho.getEnergia() > this.getValor(); 
		if (!condicion) {
			throw new NotEnoughEnergyToEvolve(this.getValor());
		}
		else {
			return condicion;
		}
	}

}

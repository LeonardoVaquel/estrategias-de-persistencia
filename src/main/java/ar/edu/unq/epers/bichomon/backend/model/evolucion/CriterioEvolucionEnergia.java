package ar.edu.unq.epers.bichomon.backend.model.evolucion;

import javax.persistence.Entity;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;

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
	
	public CriterioEvolucionEnergia(Integer valor) {
		super(valor);
	}

	public CriterioEvolucionEnergia() {}
	
	/**
	 * Dada una instancia de {@link Bicho} y una instancia de {@link Entrenador}
	 * se evalúa la energía actual del bicho especificado.
	 * Se espera devolver true si el bicho posee al menos una determinada cantidad de energía
	 */
	@Override
	public boolean seCumple(Bicho bicho, Entrenador entrenador) {
		return bicho.getEnergia() > this.getValor(); 
	}

}

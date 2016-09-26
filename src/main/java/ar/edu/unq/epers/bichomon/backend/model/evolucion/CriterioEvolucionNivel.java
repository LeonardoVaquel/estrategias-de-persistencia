package ar.edu.unq.epers.bichomon.backend.model.evolucion;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.exceptions.NotEnoughLevelToEvolve;

/**
 * {@link CriterioEvolucionNivel} es una clase que representa un {@link CriterioEvolucion} teniendo
 * en cuenta el nivel actual de un {@link Entrenador} para que un {@link Bicho} que posea, de una {@link Especie}
 * en particular, pueda evolucionar.
 * @author santiago
 *
 */
public class CriterioEvolucionNivel extends CriterioEvolucion {

	/**
	 * Se crea una nueva instancia de {@link CriterioEvolucionNivel}
	 * @param especie La {@link Especie} que representa la evolución
	 * @param tipo String que representa el tipo de criterio basado en el nivel de un {@link Entrenador}
	 * @param valor Integer que representa el nivel actual de un {@link Entrenador} 
	 */
	public CriterioEvolucionNivel(Especie especie, Integer valor) {
		super(especie, valor);
		this.setTipo("Nivel");
	}

	@Override
	public Boolean seCumple(Bicho bicho, Entrenador entrenador) {
		
		Boolean condicion = entrenador.getNivel() >= this.getValor(); 
		if (!condicion) {
			throw new NotEnoughLevelToEvolve(this.getValor());
		}
		else {
			return condicion;
		}
	}

}

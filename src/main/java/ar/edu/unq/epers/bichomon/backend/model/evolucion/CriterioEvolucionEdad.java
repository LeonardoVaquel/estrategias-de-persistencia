package ar.edu.unq.epers.bichomon.backend.model.evolucion;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;

/**
 * {@link CriterioEvolucionEdad} es una clase que representa un {@link CriterioEvolucion} teniendo
 * en cuenta la edad de un {@link Bicho} desde su fecha de captura, calculado en días.
 * @author santiago
 *
 */
@Entity
public class CriterioEvolucionEdad extends CriterioEvolucion {

	/**
	 * Se crea una nueva instancia de {@link CriterioEvolucionEdad}
	 * @param especie La {@link Especie} que representa la evolución
	 * @param tipo String que representa el tipo de criterio basado en la edad de un {@link Bicho}
	 * @param valor Integer que representa la edad en días de un {@link Bicho} 
	 */
	public CriterioEvolucionEdad(Especie especie, Integer valor) {
		super(especie, valor);
		this.setTipo("Edad");
	}
	
	public CriterioEvolucionEdad(Integer valor) {
		super(valor);
	}

	public CriterioEvolucionEdad() {}
	
	/**
	 * Dada una instancia de {@link Bicho} y una instancia de {@link Entrenador}
	 * se evalúa la fecha de captura del bicho especificado.
	 * Se espera devolver true si la fecha de captura es anterior a una fecha resultante.
	 */
	@Override
	public boolean seCumple(Bicho bicho, Entrenador entrenador) {

		LocalDateTime fechaDeCaptura  = bicho.getFechaCaptura();
		LocalDateTime fechaResultante = LocalDateTime.now().minusDays(this.getValor());
		
		return fechaResultante.isAfter(fechaDeCaptura);
	}

}

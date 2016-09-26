package ar.edu.unq.epers.bichomon.backend.model.evolucion.exceptions;

public class NotEnoughAgeToEvolve extends EvolutionException {

	private static final long serialVersionUID = 1L;

	public NotEnoughAgeToEvolve(Integer valor) {
		super("No se puede Evolucionar. Deben haber transcurrido al menos " + valor + " días desde la fecha de captura.");
	}

}

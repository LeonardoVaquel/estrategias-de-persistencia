package ar.edu.unq.epers.bichomon.backend.model.evolucion.exceptions;

public class NotEnoughLevelToEvolve extends EvolutionException {

	private static final long serialVersionUID = 1L;

	public NotEnoughLevelToEvolve(Integer valor) {
		super("No se puede Evolucionar. El entrenador debe ser al menos nivel " + valor + ".");
	}

}

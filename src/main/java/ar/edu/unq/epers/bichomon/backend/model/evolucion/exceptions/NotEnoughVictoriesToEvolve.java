package ar.edu.unq.epers.bichomon.backend.model.evolucion.exceptions;

public class NotEnoughVictoriesToEvolve extends EvolutionException {

	private static final long serialVersionUID = 1L;

	public NotEnoughVictoriesToEvolve(Integer valor) {
		super("No se puede Evolucionar. Las victorias del bicho deben ser mayores a " + valor + ".");
	}

}

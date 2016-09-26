package ar.edu.unq.epers.bichomon.backend.model.evolucion.exceptions;

public class NotEnoughEnergyToEvolve extends EvolutionException {

	private static final long serialVersionUID = 1L;

	public NotEnoughEnergyToEvolve(Integer valor) {
		super("No se puede Evolucionar. La energía del bicho debe ser de al menos " + valor + " puntos.");
	}

}

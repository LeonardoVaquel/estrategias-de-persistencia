package ar.edu.unq.epers.bichomon.backend.model.evento;

/**
 * Modela un evento con el nombre de un {@link Entrenador} derrocado
 * @author santiago
 */
public class Coronacion extends Evento {
	
	protected Coronacion() {}
	
	public Coronacion(String entrenador, String ubicacion, String entrenadorDerrocado) {
		super(entrenador, "Coronacion", ubicacion);
		this.extraProperty = entrenadorDerrocado;
	}

	public String getEntrenadorDerrocado() {
		return extraProperty;
	}
}

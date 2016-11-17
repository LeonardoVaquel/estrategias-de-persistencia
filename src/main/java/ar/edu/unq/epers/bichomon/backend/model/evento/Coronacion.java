package ar.edu.unq.epers.bichomon.backend.model.feed.evento;

/**
 * Modela un evento con el nombre de un {@link Entrenador} derrocado
 * @author santiago
 */
public class Coronacion extends Evento {
	
	private String entrenadorDerrocado;
	
	protected Coronacion() {}
	
	public Coronacion(String ubicacion, long fecha, String entrenadorDerrocado) {
		super("Coronacion", ubicacion, fecha);
		this.entrenadorDerrocado = entrenadorDerrocado;
	}

	public String getEntrenadorDerrocado() {
		return entrenadorDerrocado;
	}
}

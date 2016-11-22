package ar.edu.unq.epers.bichomon.backend.model.evento;

/**
 * Modela un evento con el nombre de un {@link Entrenador} derrocado
 * @author santiago
 */
public class Coronacion extends Evento {
	
	private String destronado;
	
	protected Coronacion() {}
	
	public Coronacion(String entrenador, String ubicacion, String entrenadorDestronado) {
		super(entrenador, "Coronacion", ubicacion);
		this.destronado = entrenadorDestronado;
	}

	public String getEntrenadorDestronado() {
		return this.destronado;
	}
}

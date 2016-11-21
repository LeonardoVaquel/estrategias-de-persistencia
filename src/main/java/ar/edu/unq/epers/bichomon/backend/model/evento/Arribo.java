package ar.edu.unq.epers.bichomon.backend.model.evento;

/**
 * Modela un evento con el nombre de una {@link Ubicacion} de origen
 * @author santiago
 */
public class Arribo extends Evento {
	
	private String ubicacionOrigen;
	
	protected Arribo() {}
	
	public Arribo(String entrenador, String ubicacion, long fecha, String ubicacionOrigen) {
		super(entrenador, "Arribo", ubicacion, fecha);
		this.ubicacionOrigen = ubicacionOrigen;
	}
	
	public Arribo(String entrenador, String ubicacion, String ubicacionOrigen) {
		super(entrenador, "Arribo", ubicacion);
		this.ubicacionOrigen = ubicacionOrigen;
	}

	public String getUbicacionOrigen() {
		return ubicacionOrigen;
	}
}

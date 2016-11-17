package ar.edu.unq.epers.bichomon.backend.model.feed.evento;

/**
 * Modela un evento con el nombre de una {@link Ubicacion} de origen
 * @author santiago
 */
public class Arribo extends Evento {
	
	private String ubicacionOrigen;
	
	protected Arribo() {}
	
	public Arribo(String ubicacion, long fecha, String ubicacionOrigen) {
		super("Arribo", ubicacion, fecha);
		this.ubicacionOrigen = ubicacionOrigen;
	}

	public String getUbicacionOrigen() {
		return ubicacionOrigen;
	}
}

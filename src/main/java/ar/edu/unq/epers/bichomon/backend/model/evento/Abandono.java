package ar.edu.unq.epers.bichomon.backend.model.evento;

/**
 * Modela un evento que representa el abandono de un {@link Bicho}
 * @author santiago
 */
public class Abandono extends Evento {
	
	private String ubicacionOrigen;
	
	protected Abandono() {}
	
	public Abandono(String entrenador, String ubicacion) {
		super(entrenador, "Abandono", ubicacion);
	}

	public String getUbicacionOrigen() {
		return ubicacionOrigen;
	}

}

package ar.edu.unq.epers.bichomon.backend.model.feed.evento;

/**
 * Modela un evento con el nombre de una {@link Especie}
 * @author santiago
 */
public class Captura extends Evento {

	private String especie;
	
	protected Captura() {}
	
	public Captura(String ubicacion, long fecha, String especie) {
		super("Captura", ubicacion, fecha);
		this.especie = especie;
	}

	public String getEspecie() {
		return especie;
	}
	
}
package ar.edu.unq.epers.bichomon.backend.model.evento;

/**
 * Modela un evento con el nombre de una {@link Especie}
 * @author santiago
 */
public class Captura extends Evento {

	private String especie;
	
	protected Captura() {}

	public Captura(String entrenador, String ubicacion, String especie) {
		super(entrenador, "Captura", ubicacion);
		this.extraProperty = especie;
	}
	
	public String getEspecie() {
		return especie;
	}

	@Override
	public String getExtraProperty() {
		return this.extraProperty;
	}
	
}
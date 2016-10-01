package ar.edu.unq.epers.bichomon.backend.model.ubicacion;

public class NoSePuedeAbandonarEnUbicacion extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoSePuedeAbandonarEnUbicacion() {
		super("Ubicación Incorrecta. Unicamente se puede abandonar bichos en Guarderías.");
	}
	
}

package ar.edu.unq.epers.bichomon.backend.model.ubicacion;

public class NoSePuedeAbandonarEnUbicacionException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoSePuedeAbandonarEnUbicacionException() {
		super("Ubicación Incorrecta. Unicamente se puede abandonar bichos en Guarderías.");
	}
	
}

package ar.edu.unq.epers.bichomon.backend.model.ubicacion;

public class NoSePuedeRealizarDueloEnUbicacionException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoSePuedeRealizarDueloEnUbicacionException(String nombreUbicacion) {
		super("Ubicación Incorrecta: " + nombreUbicacion + ". Unicamente se puede abandonar bichos en Guarderías.");
	}
	
}

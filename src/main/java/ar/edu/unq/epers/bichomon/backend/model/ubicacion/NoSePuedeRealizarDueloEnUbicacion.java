package ar.edu.unq.epers.bichomon.backend.model.ubicacion;

public class NoSePuedeRealizarDueloEnUbicacion extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoSePuedeRealizarDueloEnUbicacion(String nombreUbicacion) {
		super("Ubicación Incorrecta: " + nombreUbicacion + ". Unicamente se puede abandonar bichos en Guarderías.");
	}
	
}

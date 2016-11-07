package ar.edu.unq.epers.bichomon.backend.service.mapa;

public class UbicacionMuyLejana extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public UbicacionMuyLejana(String nombreUbicaicon) {
		super("No es posible llegar a la ubicación '" + nombreUbicaicon + "' por medio de un salto");
	}

}

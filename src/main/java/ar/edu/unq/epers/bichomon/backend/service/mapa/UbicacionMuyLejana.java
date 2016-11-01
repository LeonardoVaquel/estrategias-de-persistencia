package ar.edu.unq.epers.bichomon.backend.service.mapa;

public class UbicacionMuyLejana extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public UbicacionMuyLejana() {
		super("No es posible llegar a la ubicación por medio del camino especificado");
	}

}

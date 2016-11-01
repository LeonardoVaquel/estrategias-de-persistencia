package ar.edu.unq.epers.bichomon.backend.service.mapa;

public class CaminoMuyCostoso extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CaminoMuyCostoso() {
		super("Cantidad insuficiente de monedas. No se puede mover.");
	}
	
}

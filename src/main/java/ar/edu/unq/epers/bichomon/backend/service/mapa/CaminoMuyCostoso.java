package ar.edu.unq.epers.bichomon.backend.service.mapa;

public class CaminoMuyCostoso extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public CaminoMuyCostoso(Integer unaCantidadDeMonedas) {
		super("Cantidad insuficiente de monedas. No se puede mover. Te faltan " + printMonedas(unaCantidadDeMonedas));
	}
	
	private static String printMonedas(Integer unaCantidadDeMonedas) {
		if(unaCantidadDeMonedas == 1) {
			return unaCantidadDeMonedas + " moneda."; 
		}
		else {
			return unaCantidadDeMonedas + " monedas.";
		}
	}
	
}

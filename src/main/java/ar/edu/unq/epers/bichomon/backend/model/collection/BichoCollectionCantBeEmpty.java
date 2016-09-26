package ar.edu.unq.epers.bichomon.backend.model.collection;

public class BichoCollectionCantBeEmpty extends RuntimeException {

	/**
	 * Situación excepcional en la que se intenta eliminar el último elemento
	 * de una BichoCollection
	 */
	private static final long serialVersionUID = 1L;

	public BichoCollectionCantBeEmpty() {
		super("No es posible deshacerse del único Bicho en la colección.");
	}
	
}

package ar.edu.unq.epers.bichomon.backend.model.collection;

public class BichoCollectionReachedMaximumSize extends RuntimeException {

	/**
	 * Situación excepcional en la que una colección de bichos
	 * sobrepasó su capacidad de almacenamiento.
	 */
	private static final long serialVersionUID = 1L;

	public BichoCollectionReachedMaximumSize() {
		super("Es imposible guardar bichos en la colección: límite de almacenamiento alcanzado.");
	}
	
}

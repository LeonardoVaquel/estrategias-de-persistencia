package ar.edu.unq.epers.bichomon.backend.model.ubicacion;

public class NoHayBichosEnGuarderia extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoHayBichosEnGuarderia(String nombreGuarderia) {
		super("No se puede buscar. No hay bicho en " + nombreGuarderia + ".");	
	}

}

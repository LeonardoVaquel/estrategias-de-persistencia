package ar.edu.unq.epers.bichomon.backend.service.especie;

/**
 * Situación excepcional en que no existen especies en la base de datos
 */
public class NoHayEspecies extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoHayEspecies() {
		super("No se encuentran especies");
	}
	
}

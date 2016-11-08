package ar.edu.unq.epers.bichomon.backend.model.ubicacion;

public class NoSePuedeBuscarSinCampeonException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoSePuedeBuscarSinCampeonException(){
		super("No hay un Campeon, por lo tanto no podes realizar una busqueda");
	};
}
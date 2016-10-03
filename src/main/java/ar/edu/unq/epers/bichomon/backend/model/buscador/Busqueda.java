package ar.edu.unq.epers.bichomon.backend.model.buscador;

import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Ubicacion;

public interface Busqueda {

	public boolean busquedaEnUbicacion(Entrenador entrenador, Ubicacion ubicacion);
}

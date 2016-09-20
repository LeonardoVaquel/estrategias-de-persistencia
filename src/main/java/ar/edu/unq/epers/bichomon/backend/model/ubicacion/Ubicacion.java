package ar.edu.unq.epers.bichomon.backend.model.ubicacion;

import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;

public abstract class Ubicacion {

	private String nombre;

	public abstract void buscar(Entrenador entrenador);
}

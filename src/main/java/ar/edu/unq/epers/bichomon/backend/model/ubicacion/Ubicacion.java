package ar.edu.unq.epers.bichomon.backend.model.ubicacion;

import java.util.List;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;

public abstract class Ubicacion {

	protected String nombre;
	protected List<Bicho> bichos;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Bicho> getBichos() {
		return bichos;
	}
	public void setBicho(List<Bicho> bichos) {
		this.bichos = bichos;
	}
	public abstract void buscar(Entrenador entrenador);
}

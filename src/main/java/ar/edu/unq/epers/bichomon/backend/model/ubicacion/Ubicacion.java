package ar.edu.unq.epers.bichomon.backend.model.ubicacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;

public abstract class Ubicacion {

	protected String nombre;
	protected List<Bicho> bichos;
	protected Random random;
	public Ubicacion (String nombre, Random random){
		this.setNombre(nombre);
		this.setBicho(new ArrayList <>());
		this.random= random;
	}
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
	public abstract Bicho buscar(Entrenador entrenador);
}

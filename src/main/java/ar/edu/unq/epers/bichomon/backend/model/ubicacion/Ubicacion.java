package ar.edu.unq.epers.bichomon.backend.model.ubicacion;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.buscador.BusquedaEnUbicacion;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.utils.BichomonRandom;
import ar.edu.unq.epers.bichomon.backend.model.duelo.ResultadoCombate;

@Entity
public abstract class Ubicacion implements BusquedaEnUbicacion {

	@Id
	protected String nombre;
	
	@OneToMany(mappedBy="ubicacion", cascade = CascadeType.ALL)
	protected List<Entrenador> entrenadores;
	
	@Transient
	protected BichomonRandom random;
	
	public Ubicacion (String nombre, BichomonRandom random){
		this.setNombre(nombre);
		this.random = random;
	}
	
	public Ubicacion(String nombre) {
		this.nombre = nombre;
	}
	
	public Ubicacion() {}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Entrenador> getEntrenadores() {
		return entrenadores;
	}

	public void setEntrenadores(List<Entrenador> entrenadores) {
		this.entrenadores = entrenadores;
	}
	
	public BichomonRandom getRandom() {
		return random;
	}

	public void setRandom(BichomonRandom random) {
		this.random = random;
	}
	
	public abstract Bicho buscar(Entrenador entrenador);
	
	public abstract void abandonar(Entrenador entrenador, Bicho bicho);
	
	public abstract ResultadoCombate duelo(Entrenador entrenador, Bicho bicho);
	
	public boolean esBusquedaExitosa(Entrenador entrenador) {
		
		return true;
	}

	
}

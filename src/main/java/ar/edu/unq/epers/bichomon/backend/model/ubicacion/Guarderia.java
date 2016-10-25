package ar.edu.unq.epers.bichomon.backend.model.ubicacion;

import java.util.List;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.duelo.ResultadoCombate;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;

@Entity
public class Guarderia extends Ubicacion {
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Bicho> bichos;
	
	
	public Guarderia(String nombreGuarderia, Random random){
		super(nombreGuarderia, random);
	}
	
	public Guarderia(String nombre) {
		super(nombre);
	}
	
	public Guarderia() {}
	
	public List<Bicho> getBichos() {
		return bichos;
	}
	
	public void setBichos(List<Bicho> bichos) {
		this.bichos = bichos;
	}
	
	public void abandonar(Entrenador entrenador, Bicho bicho){
		
		if(entrenador.puedeAbandonar()) {
			this.getBichos().add(bicho);
			entrenador.abandonarBicho(bicho);
		}
	}
	
	public Integer cantidadDeBichosAbandonados(){
		return this.getBichos().size();
	}
	
	/**
	 * Dado un {@link Entrenador} se intentará retornar un bicho que se encuentra en una lista
	 * @param entrenador - una instancia de {@link Entrenador}
	 * @return una instancia de {@link Bicho}
	 */
	public Bicho adoptarBichoAbandonado(Entrenador entrenador){
		if (this.cantidadDeBichosAbandonados() == 0) {
			throw new NoHayBichosEnGuarderia(this.getNombre());
		}
		else {
			if(this.esBusquedaExitosa(entrenador)) {
				Bicho bicho = this.getBichos().get(this.numeroRandom());
				return bicho;
			}
			else {
				// La búsqueda no fue exitosa
				return null;
			}
		}
	}
	
	public Integer numeroRandom(){
		return new Random().nextInt(this.cantidadDeBichosAbandonados()-1);
	}
	
	/**
	 * Dado un {@link Entrenador} se retornará un bicho si es que hay en la ubicación actual.
	 * De lo contrario se levantará una excepción.
	 */
	public Bicho buscar(Entrenador entrenador) {
		Bicho bicho = adoptarBichoAbandonado(entrenador);
		this.bichos.remove(bicho);
		return bicho;

	}

	@Override
	public ResultadoCombate duelo(Entrenador entrenador, Bicho bicho) {
		throw new NoSePuedeRealizarDueloEnUbicacionException(this.getNombre());
	}
	
}


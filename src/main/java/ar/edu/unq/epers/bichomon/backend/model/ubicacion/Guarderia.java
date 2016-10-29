package ar.edu.unq.epers.bichomon.backend.model.ubicacion;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.duelo.ResultadoCombate;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.utils.BichomonRandom;

/**
 * Guarderia es una subclase de {@link Ubicacion} donde un {@link Entrenador} puede
 * buscar instancias de {@link Bicho} que hayan sido abandonadas, así como también
 * abandonar bichos que le sean propios.
 * @author santiago
 *
 */
@Entity
public class Guarderia extends Ubicacion {
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Bicho> bichos;
	
	@Transient
	private BichomonRandom random;
	
	public Guarderia(String nombre) {
		super(nombre, new BichomonRandom());
	}
	
	public Guarderia() {
		this.random = new BichomonRandom();
	}

	public List<Bicho> getBichos() {
		return bichos;
	}
	
	public void setBichos(List<Bicho> bichos) {
		this.bichos = bichos;
	}
	
	/**
	 * Dado un {@link Entrenador} y un {@link Bicho} se intentará abandonar el bicho
	 * en la ubicación actual
	 */
	public void abandonar(Entrenador entrenador, Bicho bicho){

		if(entrenador.puedeAbandonar()) {
			this.getBichos().add(bicho);
			entrenador.abandonarBicho(bicho);
		}
	}
	
	/**
	 * La cantidad de instancias de {@link Bicho} en la lista
	 * @return - un entero
	 */
	public Integer cantidadDeBichosAbandonados(){
		return this.bichos.size();
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
		return this.random.nextInt(this.cantidadDeBichosAbandonados()-1);
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

	/**
	 * Se lanza una excepción indicando que no se pueden realizar duelos en la ubciación actual
	 */
	@Override
	public ResultadoCombate duelo(Entrenador entrenador, Bicho bicho) {
		throw new NoSePuedeRealizarDueloEnUbicacionException(this.getNombre());
	}
	
}


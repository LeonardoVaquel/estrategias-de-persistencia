package ar.edu.unq.epers.bichomon.backend.model.collection;

import java.util.List;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.experiencia.Level;

/**
 * {@link BichoCollection} es una clase que modela la lógica básica que dispone las condiciones 
 * necesarias de un {@link Entrenador} para agregar o borrar un objeto {@link Bicho} de su
 * colección.
 * 
 * @author santiago
 *
 */
public class BichoCollection {
	
	
	public BichoCollection() {}
	
	/**
	 * Dada una lista de {@link Bicho} y un {@link Entrenador} evalúa si 
	 * su colección está llena.
	 * @param bichos - una lista de {@link Bicho}
	 * @param level - una instancia de {@link Level}
	 * @return
	 */
	public Boolean isFull(List<Bicho> bichos, Level level) {
		return !(bichos.size() < level.getCoeficienteBichos());
	}
	
	public Boolean isSingleton(List<Bicho> bichos) {
		return bichos.size() == 1;
	}
	
	/**
	 * El método add intentará agregar un Bicho si la colección no está llena.
	 * En caso de no tener espacio para agregar, se lanza una excepción.
	 * @param bicho - una instancia de {@link Bicho}
	 * @param entrenador - una instancia de {@link Entrenador}
	 */
	public void add(Bicho bicho, Entrenador entrenador) {
		
		if (this.isFull(entrenador.getBichos(), entrenador.getNivel())) {
			throw new BichoCollectionReachedMaximumSize();
		}
		else {
			entrenador.getBichos().add(bicho);
			bicho.setOwner(entrenador);
		}
	}
	
	/**
	 * El método remove intentará eliminar un BichoException de la colección.
	 * En caso de tener un solo elemento, se lanza una excepción.
	 * @param bicho
	 */	
	public void remove(Bicho bicho, List<Bicho> bichos) {
		if(this.isSingleton(bichos)) {
			throw new BichoCollectionCantBeEmpty();
		}
		else {
			bicho.setOwner(null);
			bichos.remove(bicho);
		}
	}
	
}

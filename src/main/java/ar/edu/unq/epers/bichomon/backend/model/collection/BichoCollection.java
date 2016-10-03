package ar.edu.unq.epers.bichomon.backend.model.collection;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;

/**
 * {@link BichoCollection} es una clase que representa una colección de {@link Bicho}, para un 
 * {@link Entrenador}. Una instancia de BichoCollection consta de un nivel, una lista de Bicho,
 * un tamaño máximo que depende del nivel del entrenador y un coeficiente que calcula dicho tamaño.
 * @author santiago
 *
 */

public class BichoCollection {

	private int id;
	
	private Integer maxSize;
	private Integer nivel;
	private Integer coeficiente;

	
	/**
	 * Construye una colección de bichos con un nivel y un coeficiente de capacidad fijo.
	 * La capacidad máxima de la colección se calcula a partir del nivel dado y el coeficiente.
	 * @param trainerLevel
	 */
	public BichoCollection(Integer trainerLevel) {
		this.coeficiente = 3;
		this.setNivel(trainerLevel);
	}
	
	/**
	 * Construye una colección de bichos con un nivel y coeficiente pasados por parámetro.
	 * La capacidad máxima de la colección se calcula a partir del nivel y coeficiente dados.
	 * @param trainerLevel
	 * @param coeficiente
	 */
	public BichoCollection(Integer trainerLevel, Integer coeficiente) {
		this.setCoeficiente(coeficiente);
		this.setNivel(trainerLevel);
		this.updateSize();
	}
	
	private void setMaxSize(Integer maxSize) {
		this.maxSize = maxSize;
	}
	
	public Integer getMaxSize() {
		return this.maxSize;
	}
	
	public void updateSize() {
		this.setMaxSize(this.nivel * this.coeficiente);
	}
	
	public void setCoeficiente(Integer coeficiente) {
		this.coeficiente = coeficiente;
	}
	
	public Integer getCoeficiente() {
		return this.coeficiente;
	}
	
	public void setNivel(Integer trainerLevel) {
		this.nivel = trainerLevel;
		this.updateSize();
	}
	
	public Integer getNivel() {
		return this.nivel;
	}
	
	public Boolean isFull(List<Bicho> bichos) {
		return !(bichos.size() < this.maxSize);
	}
	
	public Boolean isSingleton(List<Bicho> bichos) {
		return bichos.size() == 1;
	}
	
	/**
	 * El método add intentará agregar un Bicho si la colección no está llena.
	 * En caso de no tener espacio para agregar, se lanza una excepción.
	 * @param bicho
	 */
	public void add(Bicho bicho, List<Bicho> bichos) {
		
		if (this.isFull(bichos)) {
			throw new BichoCollectionReachedMaximumSize();
		}
		else {
			bichos.add(bicho);
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
			bichos.remove(bicho);
		}
	}
	
}

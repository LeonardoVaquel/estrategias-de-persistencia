package ar.edu.unq.epers.bichomon.backend.model.collection;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;

/**
 * {@link BichoCollection} es una clase que representa una colección de {@link Bicho}, para un 
 * {@link Entrenador}. Una instancia de BichoCollection consta de un nivel, una lista de Bicho,
 * un tamaño máximo que depende del nivel del entrenador y un coeficiente que calcula dicho tamaño.
 * @author santiago
 *
 */
public class BichoCollection {

	private List<Bicho> bichos;
	private Integer maxSize;
	private Integer nivel;
	private Integer coeficiente;
	
	/**
	 * Construye una colección de bichos con un nivel y un coeficiente de capacidad fijo.
	 * La capacidad máxima de la colección se calcula a partir del nivel dado y el coeficiente.
	 * @param trainerLevel
	 */
	public BichoCollection(Integer trainerLevel) {
		this.bichos  = new ArrayList<>();
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
		this.bichos  = new ArrayList<>();
		this.coeficiente = coeficiente;
		this.setNivel(trainerLevel);
		this.updateSize();
	}
	
	private void setMaxSize(Integer maxSize) {
		this.maxSize = maxSize;
	}
	
	public Integer getMaxSize() {
		return this.maxSize;
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
	
	public Boolean isFull() {
		return !(this.bichos.size() < this.maxSize);
	}
	
	public Boolean isSingleton() {
		return this.bichos.size() == 1;
	}
	
	/**
	 * El método add intentará agregar un Bicho si la colección no está llena.
	 * En caso de no tener espacio para agregar, se lanza una excepción.
	 * @param bicho
	 */
	public void add(Bicho bicho) {
		
		try {
			if(this.isFull()) {
				throw new BichoCollectionReachedMaximumSize();
			} else {
				this.bichos.add(bicho);
			}
			
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	/**
	 * El método remove intentará eliminar un Bicho de la colección.
	 * En caso de tener un solo elemento, se lanza una excepción.
	 * @param bicho
	 */	
	public void remove(Bicho bicho) {
		try {
			if(this.isSingleton()) {
				throw new BichoCollectionCantBeEmpty();
			}
			else {
				this.bichos.remove(bicho);
				}
			}
		catch (Exception e){
			e.getMessage();
		}
	}
	
	public void updateSize() {
		this.setMaxSize(this.nivel * this.coeficiente);
	}
	
}

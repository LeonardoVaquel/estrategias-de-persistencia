package ar.edu.unq.epers.bichomon.backend.model.entrenador;

import javax.persistence.Entity;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.collection.BichoCollection;
import ar.edu.unq.epers.bichomon.backend.model.experiencia.ExpHandler;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Ubicacion;

/**
 * {@link Entrenador} es una clase que representa un jugador del sistema.
 * Consta de un nombre, puntos de experiencia, nivel, una {@link Ubicación} que varía
 * y una {@link BichoCollection}.
 * @author santiago
 *
 */
@Entity
public class Entrenador {

	private String nombre;
	private Double currentExp;
	private Double totalExp;
	protected ExpHandler expHandler;
	private int nivel;
	private BichoCollection bichoCollection;
	private Ubicacion ubicacion;
	
	/**
	 * Crea un {@link Entrenador} con un nombre, experiencia actual, experiencia total, nivel y 
	 * una ubicación por default pasada por parámetro.
	 * 
	 * @param nombre El nombre de un entrendaor
	 * @param exphandler Instancia de {@link ExpHandler} que maneja puntos de experiencia
	 * @param ubicacion Instancia de {@link Ubicacion} por default
	 * @param bichoCollection Una instancia de {@link BichoCollection}
	 */
	public Entrenador(String nombre, ExpHandler exphandler, Ubicacion ubicacion, BichoCollection bichoCollection) {
		this.nombre = nombre;
		this.setCurrentExp(0d);
		this.setTotalExp(0d);
		this.expHandler = exphandler;
		this.bichoCollection = bichoCollection;
		this.setNivel(1);
		this.setUbicacion(ubicacion);
	}
	
	/**
	 * Getters & Setters
	 */
	
	public String getNombre() {
		return this.nombre;
	}
	
	public Double getCurrentExp() {
		return this.currentExp;
	}
	
	public void setCurrentExp(Double exp) {
		this.currentExp = exp;
	}
	
	public Double getTotalExp() {
		return this.totalExp;
	}
	
	public void setTotalExp(Double exp) {
		this.totalExp = exp;
	}
	
	public int getNivel() {
		return this.nivel;
	}
	
	public void setNivel(Integer nivel) {
		this.nivel = nivel;
		this.bichoCollection.setNivel(nivel);
	}
	
	public Ubicacion getUbicacion() {
		return this.ubicacion;
	}
	
	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public BichoCollection getBichos() {
		return bichoCollection;
	}

	public void setBichos(BichoCollection coleccion) {
		this.bichoCollection = coleccion;
	}
	
	/**
	 * Delega en un handler de experiencia para evaluar la cantidad de puntos obtenida
	 * @param exp una cantidad de experiencia
	 */
	public void gainsExp(Double exp) {
		expHandler.evaluateGainedExp(exp, this);
	}
	
	/**
	 * Consulta si una colección de Bicho tiene espacio para almacenar bichos 
	 * @return boolean indicando si puede buscar o no.
	 */
	public Boolean puedeBuscar() {
		return this.bichoCollection.isFull();
	}
	
	public void obtenerBicho(Bicho bicho) {
		this.bichoCollection.add(bicho);
	}
	
	public void abandonarBicho(Bicho bicho) {
		this.bichoCollection.remove(bicho);
	}

	// TODO acciones
	

	
}
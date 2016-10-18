package ar.edu.unq.epers.bichomon.backend.model.entrenador;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.collection.BichoCollection;
import ar.edu.unq.epers.bichomon.backend.model.duelo.ResultadoCombate;
import ar.edu.unq.epers.bichomon.backend.model.experiencia.ExpHandler;
import ar.edu.unq.epers.bichomon.backend.model.experiencia.Experiencia;
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
	
	@Id
	private String nombre;
	
	
	private Double currentExp;
	
	
	private Double totalExp;
	
	@OneToMany(mappedBy="owner", cascade=CascadeType.ALL)
	private List<Bicho> bichos;
	
	private int nivel;
	
	@Transient
	private BichoCollection bichoCollection = new BichoCollection(this.getNivel(), 3);

	@ManyToOne(cascade=CascadeType.ALL)
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
	public Entrenador(String nombre, ExpHandler exphandler, Ubicacion ubicacion) {
		this.nombre = nombre;
		this.setCurrentExp(0d);
		this.setTotalExp(0d);
		this.bichoCollection = new BichoCollection(1);
		this.setNivel(1);
		this.setUbicacion(ubicacion);
		this.bichos = new ArrayList<>();
	}
	
	public Entrenador(String nombre) {
		this.nombre = nombre;
		this.setCurrentExp(0d);
		this.setTotalExp(0d);
		this.bichoCollection = new BichoCollection(1);
		this.setNivel(1);
		this.setUbicacion(null);
		this.bichos = new ArrayList<>();
	}
	
	public Entrenador() {};
	
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
		this.bichoCollection = new BichoCollection(nivel, 3);
		this.bichoCollection.setNivel(nivel);
	}
	
	public Ubicacion getUbicacion() {
		return this.ubicacion;
	}
	
	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	public List<Bicho> getBichos() {
		return this.bichos;
	}
	
	public void setBichos(List<Bicho> bichos) {
		this.bichos = bichos;
	}

	public BichoCollection getBichoCollection() {
		return bichoCollection;
	}

	public void setBichoCollection(BichoCollection coleccion) {
		this.bichoCollection = coleccion;
	}
	
	/**
	 * Delega en un handler de experiencia para evaluar la cantidad de puntos obtenida
	 * @param exp una cantidad de experiencia
	 */
	public void gainsExp(Double exp, Experiencia expCfg) {
		new ExpHandler(expCfg).evaluateGainedExp(exp, this);
	}
	
	/**
	 * Dado un {@link Bicho} se pide que evolucione.
	 * @param bicho - una instancia de {@link Bicho}
	 */
	public void evolucionar(Bicho bicho) {
		bicho.evolucionar();
	}
	
	/**
	 * Consulta si una colección de Bicho tiene espacio para almacenar bichos 
	 * @return boolean indicando si puede buscar o no.
	 */
	public Boolean puedeBuscar() {
		this.bichoCollection = new BichoCollection(this.getNivel(), 3);
		return !this.bichoCollection.isFull(this.bichos);
	}
	
	public void obtenerBicho(Bicho bicho) {
		this.bichoCollection = new BichoCollection(this.getNivel(), 3);
		
		this.bichoCollection.add(bicho, this.bichos);
		bicho.setOwner(this);
	}
	
	/**
	 * Se consulta a una instancia de {@link BichoCollection} para evaluar la capacidad
	 * de abandonar un bicho de una lista de {@link Bicho}
	 * @return
	 */
	public boolean puedeAbandonar() {
		this.bichoCollection = new BichoCollection(this.getNivel(), 3);
		return !this.bichoCollection.isSingleton(bichos);
	}
	
	/**
	 * Dado un bicho se espera que el entrenador pueda abandonarlo en la ubicación actual.
	 * De lo contrario se levanta una excepción
	 * @param bicho - el {@link Bicho} a ser despachado a la ubicación
	 */
	public void abandonar(Bicho bicho) {
		this.ubicacion.abandonar(this, bicho);
	}
	
	/**
	 * Dado un {@link Bicho}, se elimina de la lista de bichos de un {@link Entrenador} 
	 * @param bicho - el bicho a ser abandonado
	 */
	public void abandonarBicho(Bicho bicho) {
		this.setBichoCollection(new BichoCollection(this.getNivel(), 3));
	
		this.bichoCollection.remove(bicho, this.bichos);
	}

	/**
	 * Dado un {@link Bicho} se espera poder realizar un duelo en la {@link Ubicacion} ubicación actual
	 * @param bicho - una instancia de {@link Bicho}
	 * @return una instancia de {@link ResultadoCombate}
	 */
	public ResultadoCombate duelo(Bicho bicho) {
		return this.ubicacion.duelo(this, bicho);
	}
	
	/**
	 * Se delega en una {@link Ubicacion}, que es la actual, para modificar la ubicación actual.
	 * @param ubicacion - una instancia de {@link Ubicacion}
	 */
	public void mover(Ubicacion ubicacion) {
		this.setUbicacion(ubicacion);
	}
	
	/**
	 * Se delega la responsabilidad de buscar a la ubicación actual
	 * @return - una instancia {@link Bicho}
	 */
	public Bicho buscar() {	
		if(this.puedeBuscar()) {
			return this.ubicacion.buscar(this);
		}
		else {
			return null;
		}
	}
	
}
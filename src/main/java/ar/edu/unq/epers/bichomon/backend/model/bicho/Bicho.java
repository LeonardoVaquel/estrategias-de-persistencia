package ar.edu.unq.epers.bichomon.backend.model.bicho;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.CriterioEvolucion;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.EvolutionHandler;

/**
 * Un {@link Bicho} existente en el sistema. 
 * Consta de una {@link Especie}, que puede variar, puntos de energia y se
 * considera que un Bicho pertenece a un {@link Entrenador} al ser capturado.
 * 
 * @author santiago
 */
@Entity
public class Bicho {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@ManyToOne(cascade=CascadeType.ALL)
	private Especie especie;
	private int energia;
	@ManyToOne(cascade=CascadeType.ALL)
	private Entrenador owner;
	private Integer victorias;
	private LocalDateTime fechaCaptura;
	
	public Bicho() {}
	
	public Bicho(Especie especie, String nombre) {
		this.especie = especie;
	}
	
	public Bicho(Especie especie) {
		this.especie      = especie;
		this.energia      = especie.getEnergiaInicial();
		this.victorias    = 0;
		this.setFechaCaptura();
	}
	
	
	/**
	 * @return la especie a la que este bicho pertenece.
	 */
	public Especie getEspecie() {
		return this.especie;
	}
	
	public Especie getEspecieRaiz() {
		if(this.getEspecie() != null) {
			return this.especie.getRaiz();
		}
		else return null;
	}
	
	public int getId() {
		return this.id;
	}
	
	/**
	 * @return la cantidad de puntos de energia de este bicho en
	 * particular. Dicha cantidad crecerá (o decrecerá) conforme
	 * a este bicho participe en combates contra otros bichomones.
	 */
	public int getEnergia() {
		return this.energia;
	}
	public void setEspecie(Especie especie) {
		this.especie = especie;
	}

	public void setEnergia(int energia) {
		this.energia = energia;
	}

	public Entrenador getOwner() {
		return this.owner;
	}

	public void setOwner(Entrenador owner) {
		this.owner = owner;
	}

	public LocalDateTime getFechaCaptura() {
		return fechaCaptura;
	}

	public void setFechaCaptura() {
		this.fechaCaptura = LocalDateTime.now();
	}

	public void setVictorias(Integer victorias) {
		this.victorias = victorias;
	}
	
	public Integer getVictorias() {
		return victorias;
	}
	
	public List<CriterioEvolucion> getCriteriosDeEvolucion() {
		return this.especie.getCriteriosDeEvolucion();
	}
	
	public Especie getEvolucion() {
		return this.especie.getEvolucion();
	}
	
	/**
	 * Se suma una victoria a las victorias totales
	 */
	public void nuevaVictoria() {
		this.setVictorias(victorias + 1);
	}
	
	
	/**
	 * Un objeto {@link EvolutionHandler} determina si un bicho está en condiciones de evolucionar.
	 * De ser positivo, éste objeto llamará el método {@link #evolucionar(Especie)} donde el parámetro
	 * especie es la evolución deseada.
	 * Caso contrario el handler se encargará de se levantar una excepción.
	 * 
	 */
	public Bicho evolucionar() {
		EvolutionHandler handler = new EvolutionHandler();
		handler.setBicho(this);
		return handler.evolucionar();
	}
	
	/**
	 * Una instancia de {@link EvolutionHandler} determina si un {@link Bicho} está en condiciones de
	 * evolucionar
	 * @return boolean - el valor que denota la capacidad de una instancia {@link Bicho} de evolucionar 
	 */
	public boolean puedeEvolucionar() {
		EvolutionHandler handler  = new EvolutionHandler();
		handler.setBicho(this);
		return handler.puedeEvolucionar();
	}
	
	/**
	 * Método que debería ser utilizado únicamente por un objeto {@link EvolutionHandler}
	 * @param especie - la evolución de la especie actual
	 */
	public void evolucionar(Especie especie) {
		this.setEspecie(especie);
	}

	/**
	 * Se aumenta la energia de un {@link Bicho} en 1
	 * @param n - un entero
	 */
	public void aumentarEnergiaEn(int n) {
		energia = energia + n;		
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		Bicho other = (Bicho) obj;
		if (!(this.id == (other.id))) {
			return false;
		} else
		return true;
	}
	

}
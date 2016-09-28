package ar.edu.unq.epers.bichomon.backend.model.bicho;

import javax.persistence.Entity;
import javax.persistence.Transient;

import org.joda.time.DateTime;

import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.EvolutionHandler;

/**
 * Un {@link Bicho} existente en el sistema. 
 * Constade una {@link Especie}, que puede variar, puntos de energia y se
 * considera que un Bicho pertenece a un {@link Entrenador} al ser capturado.
 * 
 * @author santiago
 */
@Entity
public class Bicho {
	
	private Especie especie;
	private int energia;
	private Entrenador owner;
	private Integer victorias;
	private DateTime fechaCaptura;
	@Transient
	private EvolutionHandler handler;
	
	public Bicho(Especie especie, String nombre) {

	}
	
	public Bicho(Especie especie) {
		this.especie      = especie;
		this.energia      = especie.getEnergiaInicial();
		this.owner        = null;
		this.victorias    = 0;
		this.setFechaCaptura();
		this.setHandler(new EvolutionHandler());
		handler.setBicho(this);
		handler.setEntrenador(this.owner);
	}

	/**
	 * @return la especie a la que este bicho pertenece.
	 */
	public Especie getEspecie() {
		return this.especie;
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
		return owner;
	}

	public void setOwner(Entrenador owner) {
		this.owner = owner;
	}

	public DateTime getFechaCaptura() {
		return fechaCaptura;
	}

	public void setFechaCaptura() {
		this.fechaCaptura = new DateTime();
	}

	public void setVictorias(Integer victorias) {
		this.victorias = victorias;
	}
	
	public Integer getVictorias() {
		return victorias;
	}
	
	public void nuevaVictoria() {
		this.setVictorias(victorias + 1);
	}
	
	public void setHandler(EvolutionHandler handler) {
		this.handler = handler;
	}
	
	/**
	 * Un objeto {@link EvolutionHandler} determina si un bicho está en condiciones de evolucionar.
	 * De ser positivo, éste objeto llamará el método {@link #evolucionar(Especie)} donde el parámetro
	 * especie es la evolución deseada.
	 * Caso contrario el handler se encargará de se levantar una excepción.
	 * 
	 */
	public Bicho evolucionar() {
		return handler.evolucionar();
	}
	
	public boolean puedeEvolucionar() {
		return handler.puedeEvolucionar();
	}
	
	/**
	 * Método que debería ser utilizado únicamente por un objeto {@link EvolutionHandler}
	 * @param especie - la evolución de la especie actual
	 */
	public void evolucionar(Especie especie) {
		this.setEspecie(especie);
	}

}

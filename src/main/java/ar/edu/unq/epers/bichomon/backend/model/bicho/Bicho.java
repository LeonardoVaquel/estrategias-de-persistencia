package ar.edu.unq.epers.bichomon.backend.model.bicho;

import javax.persistence.Entity;

import org.joda.time.DateTime;

import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;

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
	
	public Bicho(Especie especie, String nombre) {

	}
	
	public Bicho(Especie especie) {
		this.especie = especie;
		this.energia = especie.getEnergiaInicial();
		this.owner   = null;
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
	
	public void puedeEvolucionar() {
		//this.especie.puedeEvolucionar(this, this.owner)
	}

}

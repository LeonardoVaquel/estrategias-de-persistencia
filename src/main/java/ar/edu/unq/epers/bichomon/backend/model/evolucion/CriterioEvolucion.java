package ar.edu.unq.epers.bichomon.backend.model.evolucion;

import javax.persistence.Entity;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;

/**
 * TODO
 * @author santiago
 *
 */
@Entity
public class CriterioEvolucion {

	Especie especie;
	String tipo;
	Integer valor;
	
	public CriterioEvolucion(Especie especie, String tipo, Integer valor) {
		this.setTipo(tipo);
		this.setValor(valor);
	}
	
	public Especie getEspecie() {
		return especie;
	}

	public void setEspecie(Especie especie) {
		this.especie = especie;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public Boolean seCumple(Bicho bicho, Entrenador entrenador) {
		
		
		return false;
	}
}

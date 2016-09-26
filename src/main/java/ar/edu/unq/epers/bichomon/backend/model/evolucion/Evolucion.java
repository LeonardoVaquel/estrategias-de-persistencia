package ar.edu.unq.epers.bichomon.backend.model.evolucion;

import java.util.List;

import javax.persistence.Entity;

import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;

/**
 * {@link Evolucion}
 * @author santiago
 *
 */

@Entity
public class Evolucion {

	private Especie especie;
	private Especie raiz;
	private Especie evolucion;
	private List<CriterioEvolucion> criterios;

	public Evolucion(Especie especie, Especie raiz, Especie evolucion, List<CriterioEvolucion> criterios) {
	
		this.setEspecie(especie);
		this.setRaiz(raiz);
		this.setEvolucion(evolucion);
		this.setCriterios(criterios);
	}

	public Especie getEspecie() {
		return especie;
	}

	public void setEspecie(Especie especie) {
		this.especie = especie;
	}

	public Especie getRaiz() {
		return raiz;
	}

	public void setRaiz(Especie raiz) {
		this.raiz = raiz; 
	}

	public Especie getEvolucion() {
		return evolucion;
	}

	public void setEvolucion(Especie evolucion) {
		this.evolucion = evolucion;
	}

	public List<CriterioEvolucion> getCriterios() {
		return criterios;
	}

	public void setCriterios(List<CriterioEvolucion> criterios) {
		this.criterios = criterios;
	}
	
}

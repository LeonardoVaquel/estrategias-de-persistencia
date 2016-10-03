package ar.edu.unq.epers.bichomon.backend.model.experiencia;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * TablaDeExperiencia es una clase que contiene valores relacionados
 * con la experiencia en el juego para los distintos eventos, por ejemplo
 * Buscar, Evolucionar, Combatir.
 * @author santiago
 *
 */
@Entity
public class TablaDeExperiencia {

	@Id
	private String evento;
	private int valor;
	
	public TablaDeExperiencia() {}

	public TablaDeExperiencia(String evento, int valor) {
		this.setEvento(evento);
		this.setValor(valor);
	}
	
	public String getEvento() {
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}	
	
}

package ar.edu.unq.epers.bichomon.backend.model.entrenador;

import javax.persistence.Entity;

import ar.edu.unq.epers.bichomon.backend.model.experiencia.ExpHandler;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Ubicacion;

/**
 * Entrenador es una clase que representa un jugador del sistema
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
	private Ubicacion ubicacion;
	
	
	public Entrenador(String nombre, ExpHandler exphandler, Ubicacion ubicacion) {
		this.nombre     = nombre;
		this.setCurrentExp(0d);
		this.setTotalExp(0d);
		this.expHandler = exphandler;
		this.setNivel(1);
		this.setUbicacion(ubicacion);
	}
	
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
	
	public void gainsExp(Double exp) {
		expHandler.evaluateGainedExp(exp, this);
	}
	
	public int getNivel() {
		return this.nivel;
	}
	
	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}
	
	public Ubicacion getUbicacion() {
		return this.ubicacion;
	}
	
	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	// TODO acciones
}
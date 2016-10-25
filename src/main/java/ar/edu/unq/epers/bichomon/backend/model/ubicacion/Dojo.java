package ar.edu.unq.epers.bichomon.backend.model.ubicacion;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.duelo.Campeon;
import ar.edu.unq.epers.bichomon.backend.model.duelo.Duelo;
import ar.edu.unq.epers.bichomon.backend.model.duelo.Historial;
import ar.edu.unq.epers.bichomon.backend.model.duelo.ResultadoCombate;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;

@Entity
public class Dojo extends Ubicacion {
	
	@OneToOne
	private Bicho campeon;
	
	@Transient
	private Historial historial;
		
	public Dojo(String nombreDojo,Random random) {
		super(nombreDojo,random);
	}
	
	public Dojo(String nombreDojo) {
		super(nombreDojo);
	}

	public Dojo() {}
	
	public Bicho getCampeon() {
		return campeon;
	}

	public void setCampeon(Bicho campeon) {
		this.campeon = campeon;
	}
	
	public Historial getHistorial() {
		return historial;
	}

	public void setHistorial(Historial historial) {
		this.historial = historial;
	}
	
	@Override
	public Bicho buscar(Entrenador entrenador) {
		if (esBusquedaExitosa(entrenador)) {
			return new Bicho(this.obtenerEspecieRaizActual());
		}		
		else {
			return null;
		}
	}
	
	/**
	 * Retorna la {@link Especie} raíz del {@link Bicho} que es campeón actualmente
	 * @return
	 */
	public Especie obtenerEspecieRaizActual() {
		return this.campeon.getEspecie().getRaiz();
	}

	/**
	 * Se arroja una excepcion. No se pueden abandonar bichos en un Dojo
	 */
	@Override
	public void abandonar(Entrenador entrenador, Bicho bicho) {
		throw new NoSePuedeAbandonarEnUbicacionException();
	}

	@Override
	public ResultadoCombate duelo(Entrenador entrenador, Bicho bicho) {
		ResultadoCombate duelo = new Duelo(bicho, campeon).iniciarDuelo();
		campeon = duelo.getBichoGanador();
		return duelo;
	}
	
	private void agregarAlHistorial(Bicho bichoCampeon, LocalDateTime fecha, Bicho derrocado){
		new Campeon(bichoCampeon, fecha, bichoCampeon.getOwner(), this);
	}

}
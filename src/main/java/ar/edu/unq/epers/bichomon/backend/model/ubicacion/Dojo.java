package ar.edu.unq.epers.bichomon.backend.model.ubicacion;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.duelo.Campeon;
import ar.edu.unq.epers.bichomon.backend.model.duelo.Duelo;
import ar.edu.unq.epers.bichomon.backend.model.duelo.ResultadoCombate;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.utils.BichomonRandom;

/**
 * Dojo es una {@link Ubicacion} donde un {@link Entrenador} puede buscar y participar en duelos.
 * @author santiago
 */
@Entity
public class Dojo extends Ubicacion {
	
	@OneToOne
	private Bicho campeon;
	@OneToMany(cascade=CascadeType.ALL)
	private List<Campeon> historial;
	
	public Dojo(String nombreDojo, BichomonRandom random) {
		super(nombreDojo, random);
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
	
	public List<Campeon> getHistorial() {
		return historial;
	}

	public void setHistorial(List<Campeon> historial) {
		this.historial = historial;
	}
	
	/**
	 * Dado un {@link Entrenador}, si se produce una búsqueda exitosa se retorna una instancia de
	 * {@link Bicho} cuya {@link Especie} es la raíz del actual campeón de la ubicación actual.
	 * @throws NoSePuedeBuscarSinCampeonException 
	 */
	@Override
	public Bicho buscar(Entrenador entrenador){
		if (this.obtenerEspecieRaizActual() != null) {
			return this.buscarBichoPara(entrenador);
		}
		else {
			return null;
		}
	}
	
	/**
	 * Dado un {@link Entrenador} se evalúa si la búsqueda es exitosa para retornar una nueva instancia de {@link Bicho}
	 * @param entrenador - una instancia de {@link Entrenador}
	 * @return una nueva instancia de {@link Bicho}
	 */
	private Bicho buscarBichoPara(Entrenador entrenador){
		if(this.esBusquedaExitosa(entrenador)){
			return new Bicho(this.obtenerEspecieRaizActual());
		}
		return null;
	}
	
	/**
	 * Retorna la {@link Especie} raíz del {@link Bicho} que es campeón actualmente
	 * @return
	 */
	public Especie obtenerEspecieRaizActual() {
		// TODO caso borde cuando no hay campeón
		if(this.campeon == null) {
			//return null;
		}
		return this.campeon.getEspecieRaiz();
	}

	/**
	 * Se arroja una excepcion. No se pueden abandonar bichos en un Dojo
	 */
	@Override
	public void abandonar(Entrenador entrenador, Bicho bicho) {
		throw new NoSePuedeAbandonarEnUbicacionException();
	}

	/**
	 * Dado un {@link Entrenador} y un {@link Bicho} se crea un {@link Duelo}
	 * Se espera que devolver un {@link ResultadoCombate} con un {@link Bicho} ganador.
	 * A su vez se deja registro del ganador mencionado en una lista de {@link Campeon}
	 */
	@Override
	public ResultadoCombate duelo(Entrenador entrenador, Bicho bicho) {
		ResultadoCombate duelo = new Duelo(bicho, campeon).iniciarDuelo();
		campeon = duelo.getBichoGanador();
		this.agregarAlHistorial(campeon);
		return duelo;
	}
	
	/**
	 * Dado un {@link Bicho} campeón se agrega un nuevo {@link Campeon} al historial de un {@link Dojo}
	 * @param bichoCampeon - una instancia de {@link Bicho}
	 */
	private void agregarAlHistorial(Bicho bichoCampeon){
		this.historial.add(new Campeon(bichoCampeon, LocalDateTime.now(), bichoCampeon.getOwner(), this));
	}

}
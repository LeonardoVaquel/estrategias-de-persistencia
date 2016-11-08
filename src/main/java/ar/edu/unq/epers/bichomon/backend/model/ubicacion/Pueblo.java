package ar.edu.unq.epers.bichomon.backend.model.ubicacion;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.buscador.BuscadorEspecie;
import ar.edu.unq.epers.bichomon.backend.model.buscador.Tupla;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.utils.BichomonRandom;
import ar.edu.unq.epers.bichomon.backend.model.duelo.ResultadoCombate;

/**
 * Pueblo es una subclase de {@link Ubicacion} donde un {@link Entrenador} puede
 * buscar instancias de {@link Bicho}. 
 * @author santiago
 *
 */
@Entity
public class Pueblo extends Ubicacion {
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Tupla> listaDeEspecies;
		
	public Pueblo(String nombrePueblo) {
		super(nombrePueblo, new BichomonRandom());
	}
	
	public Pueblo() {}

	public List<Tupla> getListaDeEspecies() {
		return listaDeEspecies;
	}

	public void setListaDeEspecies(List<Tupla> listaDeEspecies) {
		this.listaDeEspecies = listaDeEspecies;
	}
	
	@Override
	public Bicho buscar(Entrenador entrenador) {

		if (esBusquedaExitosa(entrenador)) {
			BuscadorEspecie buscador = new BuscadorEspecie(this.listaDeEspecies);
			return buscador.buscar();
		}
		return null;
	}

	@Override
	public void abandonar(Entrenador entrenador, Bicho bicho) {
		throw new NoSePuedeAbandonarEnUbicacionException();
	}

	@Override
	public ResultadoCombate duelo(Entrenador entrenador, Bicho bicho) {
		throw new NoSePuedeRealizarDueloEnUbicacionException(this.getNombre());
	}

}

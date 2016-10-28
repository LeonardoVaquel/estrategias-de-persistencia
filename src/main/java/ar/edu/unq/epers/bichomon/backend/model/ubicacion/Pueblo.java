package ar.edu.unq.epers.bichomon.backend.model.ubicacion;

import java.util.List;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.buscador.BuscadorEspecie;
import ar.edu.unq.epers.bichomon.backend.model.buscador.Tupla;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.duelo.ResultadoCombate;

@Entity
public class Pueblo extends Ubicacion{
		
	public List<Tupla> getListaDeEspecies() {
		return listaDeEspecies;
	}

	public void setListaDeEspecies(List<Tupla> listaDeEspecies) {
		this.listaDeEspecies = listaDeEspecies;
	}

	@OneToMany(cascade=CascadeType.ALL)
	private List<Tupla> listaDeEspecies;
	
	public Pueblo(String nombrePueblo,Random random ){
		super(nombrePueblo,random);
		
		// aca va una instancia de UbicacionManager,
		// que utiliza un servicio de DAO que todavia no esta
		// va a saber decir getListaDeBichos que devuelve
		// una lista HashMap (especie, double)
		// sobre la que trabajará el Buscador
	}
	
	public Pueblo(String nombrePueblo) {
		super(nombrePueblo);
	}
	
	public Pueblo() {}
	
	@Override
	public Bicho buscar(Entrenador entrenador) {
		
		if (esBusquedaExitosa(entrenador)) {
			return new BuscadorEspecie(this.listaDeEspecies).buscar();
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

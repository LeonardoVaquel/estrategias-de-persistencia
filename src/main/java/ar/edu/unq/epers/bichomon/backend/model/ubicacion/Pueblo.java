package ar.edu.unq.epers.bichomon.backend.model.ubicacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.buscador.Buscador;
import ar.edu.unq.epers.bichomon.backend.model.buscador.Tupla;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.duelo.ResultadoCombate;

@Entity
public class Pueblo extends Ubicacion{
	
	@Transient
	private Buscador buscador;
	
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
		this.buscador = new Buscador(this.listaDeEspecies, 100);
		
		// por ahora siempre es exitosa
		return this.buscador.buscar(1);
	}

	@Override
	public void abandonar(Entrenador entrenador, Bicho bicho) {
		throw new NoSePuedeAbandonarEnUbicacionException();
	}

	@Override
	public ResultadoCombate duelo(Entrenador entrenador, Bicho bicho) {
		throw new NoSePuedeRealizarDueloEnUbicacion(this.getNombre());
	}

}

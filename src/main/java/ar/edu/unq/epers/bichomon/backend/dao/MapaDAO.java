package ar.edu.unq.epers.bichomon.backend.dao;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Dojo;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Ubicacion;

public interface MapaDAO {

	
	/**
	 * Recibe por parámetro el nombre de una {@link Ubicacion} para devolver la cantidad de
	 * entrenadores que estén ubicados allí actualmente
	 * @param ubicacion - el nombre de una {@link Ubicacion}
	 * @return un número que representa una cantidad de entrenadores
	 */
	public int cantidadEntrenadores(String ubicacion);
	
	/**
	 * Recibe por parámetro el nombre de una {@link Ubicacion} para devolver el actual {@link Bicho} 
	 * campeón de dicha instancia.
	 * Se espera que la ubicación especificada sea de tipo {@link Dojo}
	 * @param dojo - el nombre de una {@link Ubicacion} de tipo {@link Dojo}
	 * @return una instancia de {@link Bicho}
	 */
	public Bicho campeon(String dojo);
	
	/**
	 * TODO
	 * @param dojo
	 * @return
	 */
	public Bicho campeonHistorico(String dojo);
	
}

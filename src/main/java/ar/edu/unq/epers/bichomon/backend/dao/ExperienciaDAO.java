package ar.edu.unq.epers.bichomon.backend.dao;

import java.util.List;

import ar.edu.unq.epers.bichomon.backend.model.experiencia.Experiencia;
import ar.edu.unq.epers.bichomon.backend.model.experiencia.Level;
import ar.edu.unq.epers.bichomon.backend.model.experiencia.TablaDeExperiencia;

/**
 * Tiene la responsabilidad de guardar y recuperar la Experiencia desde
 * el medio persistente
 */
public interface ExperienciaDAO {
	
	/**
	 * @return una lista de {@link Level}
	 */
	public List<Level> getAllLevels();

	/**
	 * Dado un {@link Level} es guardado en una base de datos
	 */
	public void guardarLevel(Level level);
	
	/**
	 * Dada una {@link TablaDeExperiencia} se persiste su información en una base de datos
	 * @param expTable - una instancia de {@link TablaDeExperiencia}
	 */
	public void guardarTablaDeExperiencia(TablaDeExperiencia expTable);
	
	/**
	 * Dado un Evento, por ejemplo, Combatir, Evolucionar, Buscar, se retorna
	 * una cantidad de puntos experiencia.
	 * @param tipoDeEvento - un nombre de evento 
	 * @return - una cantidad de experiencia
	 */
	public int getExperiencieByEvent(String tipoDeEvento);
	
	/**
	 * Dado un string se espera retornar un objeto que representa la configuración de la 
	 * experiencia dentro del juego.
	 * @return - una instancia de {@link Experiencia}
	 */
	public Experiencia getExperienciaConfig(String numeroVersion);
	
	/**
	 * Dada una configuración de experiencia se persiste en una base de datos
	 * @param expConfig - una instancia de {@link Experiencia}
	 */
	public void guardarExperienciaConfig(Experiencia expConfig);
	
}

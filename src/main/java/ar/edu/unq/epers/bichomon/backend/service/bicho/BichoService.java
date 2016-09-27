package ar.edu.unq.epers.bichomon.backend.service.bicho;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.eventos.ResultadoCombate;

/**
 * Interfaz para servicios de bicho que utilizarán los encargados de frontend.
 * @author santiago
 *
 */
public interface BichoService {

	/**
	 * Éste método realiza una busqueda en la ubicación actual de un Entrenador.
	 * Se espera retornar un resultado Bicho si la busqueda resultó exitosa.
	 * TODO Qué devuelve si no es exitosa??!?!?!?!
	 * @param entrenador - el {@link Entrenador} que realiza una búsqueda
	 * @return - una instancia de {@link Bicho}
	 */
	public Bicho buscar(String entrenador);
	
	/**
	 * Éste método tiene como propósito deshacerse de una instancia de {@link Bicho} particular 
	 * @param entrenador - una instancia de {@link Entrenador}
	 * @param bicho - un número que representa el bicho a ser abandonado
	 */
	public void abandonar(String entrenador, int bicho);
	
	/**
	 * Éste método tiene como propósito retar a duelo al actual campeón de un dojo
	 * Se espera levantar una excepción en caso de que la ubicación actual del entrenador
	 * pasado como parámetro no sea un {@link Dojo} 
	 * @param entrenador - el nombre del {@link Entreador} retador
	 * @param bicho - un número que representa un {@link Bicho} con el que se dispondrá el duelo
	 * @return una instancia de {@link ResultadoCombate} con el ganador y detalles de los ataques realizados.
	 */
	public ResultadoCombate duelo(String entrenador, int bicho);
	
	/**
	 * Éste método tiene como propósito evaluar la capacidad de un bicho para evolucionar.
	 * @param entrenador - el nombre de un {@link Entrenador}
	 * @param bicho - un número que representa el {@link Bicho} que se desea evaluar
	 * @return un booleano indicando si un bicho está en condiciones de evolucionar
	 */
	public boolean puedeEvolucionar(String entrenador, int bicho);
	
	/**
	 * El propósito de éste método es evolucionar un bicho determinado
	 * @param entrenador - el nombre de un {@link Entrenador} que posee un bicho
	 * @param bicho - un número que representa un {@link Bicho} que se desea evolucionar.
	 * @return una instancia de {@link Bicho} con su especie evolucionada.
	 */
	public Bicho evolucionar(String entrenador, int bicho);
	
}

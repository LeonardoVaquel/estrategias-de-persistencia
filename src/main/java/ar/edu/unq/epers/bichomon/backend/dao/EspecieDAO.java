package ar.edu.unq.epers.bichomon.backend.dao;

import java.util.List;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.service.especie.EspecieNoExistente;

/**
 * Tiene la responsabilidad de guardar y recuperar personajes desde
 * el medio persistente
 */
public interface EspecieDAO {

	/**
	 * Recibe por parametro un objeto {@link Especie} previamente
	 * construido y se encarga de persistirlo en la base de datos. Tener en cuenta que
	 * el nombre de cada especie debe ser único para toda la aplicación.
	 * 
	 * @param especie - un objeto Especie previamente construido por la gente de frontend
	 */
	void crearEspecie(Especie especie);
	
	/**
	 * Este método devolverá la {@link Especie} cuyo nombre sea igual al provisto por
	 * parámetro.
	 * 
	 * Se espera que este método devuelva, a lo sumo, un solo resultado.
	 * 
	 * @param nombreEspecie - el nombre de la especie que se busca
	 * @return la especie encontrada
	 * @throws la excepción {@link EspecieNoExistente} (no chequeada)
	 */
	Especie getEspecie(String nombreEspecie);

	/**
	 * @return una lista de todas los objetos {@link Especie} existentes ordenados
	 * alfabéticamente por su nombre en forma ascendente
	 */
	List<Especie> getAllEspecies();
	
	/**
	 * Crea un nuevo Bicho perteneciente a la especie especificada.
	 * 
	 * Para llevar una mejor estadística de los bichos que han sido creados cada objeto
	 * {@link Especie} cuenta con un contador cantidadBichos.
	 * 
	 * @param nombreEspecie - el nombre de la especie del bicho a crear
	 * @param nombreBicho - el nombre del bicho a ser creado
	 * @return un objeto {@link Bicho} instanciado
	 */
	Bicho crearBicho(String nombreEspecie, String nombreBicho);
	
	/**
	 * Borra todos los registros de {@link Especie}
	 */
	void removeAllEspecies();
}

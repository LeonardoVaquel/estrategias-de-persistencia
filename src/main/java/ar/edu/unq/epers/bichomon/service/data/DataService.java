package ar.edu.unq.epers.bichomon.service.data;

import ar.edu.unq.epers.bichomon.model.especie.Especie;

/**
 * Este servicio solo será utilizado para pruebas.
 * 
 * @author Steve Frontend
 */
public interface DataService {

	/**
	 *  Se espera que tras ejecutarse esto se elimine toda la información persistida
	 *  en la base de datos, de manera de poder comenzar el siguiente tests desde cero.
	 */
	void eliminarDatos();
	
	/**
	 * Crea un set de datos iniciales (de momento solo objetos {@link Especie}) para
	 * facilitar las pruebas de frontend.
	 */
	void crearSetDatosIniciales();
	
}

package ar.edu.unq.epers.bichomon.backend.model.especie;

import java.util.List;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.CriterioEvolucion;

/**
 * Esta clase simplifica la tarea de crear instancias de clases propias
 * del dominio al obtener información de la base de datos.
 * Mantiene aislada la manipulación de los datos llegados como parámetro
 * para satisfacer el tipo de los atributos que la clase Especie requiere.
 * @author santiago
 *
 */
public class BichomonFactory {

	public BichomonFactory() {
	}
	
	public Especie crearEspecie(String nombre, Especie raiz, Especie evolucion, 
			List<CriterioEvolucion> criteriosDeEvolucion, int altura, int peso, String tipo, 
			int energiaInicial, String urlFoto, int cantidadBichos) {
		
		Especie especie = new Especie();
				especie.setNombre(nombre);
				especie.setRaiz(raiz);
				especie.setEvolucion(evolucion);
				especie.setCriteriosDeEvolucion(criteriosDeEvolucion);
				especie.setAltura(altura);
				especie.setPeso(peso);
				especie.setTipo(TipoBicho.valueOf(tipo));
				especie.setEnergiaIncial(energiaInicial);
				especie.setUrlFoto(urlFoto);
				especie.setCantidadBichos(cantidadBichos);
		return especie;
	}
	
	public Bicho crearBicho(Especie especie, String nombreBicho) {
		Bicho bicho = new Bicho(especie, nombreBicho);
		// TODO A falta de un constructor de Bicho con energia, utilizo un setter - Ver 
		bicho.setEnergia(especie.getEnergiaInicial());
		return bicho;
	}
}

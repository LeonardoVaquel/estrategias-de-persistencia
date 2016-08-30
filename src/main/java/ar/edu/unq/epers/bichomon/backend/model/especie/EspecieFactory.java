package ar.edu.unq.epers.bichomon.backend.model.especie;

/**
 * Esta clase simplifica la tarea de crear instancias de Especie al
 * obtener información de la base de datos.
 * Mantiene aislada la manipulación de los datos llegados como parámetro
 * para satisfacer el tipo de los atributos que la clase Especie requiere.
 * @author santiago
 *
 */
public class EspecieFactory {

	public EspecieFactory() {
	}
	
	public Especie crearEspecie(String nombre, int altura, int peso, String tipo, 
			int energiaInicial, String urlFoto, int cantidadBichos) {
		
		Especie especie = new Especie();
				especie.setNombre(nombre);
				especie.setAltura(altura);
				especie.setPeso(peso);
				especie.setTipo(TipoBicho.valueOf(tipo));
				especie.setEnergiaIncial(energiaInicial);
				especie.setUrlFoto(urlFoto);
				especie.setCantidadBichos(cantidadBichos);
		return especie;
	}
}

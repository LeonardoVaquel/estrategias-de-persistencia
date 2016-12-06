package ar.edu.unq.epers.bichomon.backend.dao.infinispan;

import java.util.function.BiFunction;

import org.infinispan.client.hotrod.RemoteCache;

import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Ubicacion;
/**
 * MapaServiceCache es una Clase que se encarga de administrar
 * una especie de ranking de la cantidad de  {@link Entrenador} 
 * que hay en una {@link Ubicacion} determinada.
 * 
 * @author leonardo
 *
 */
public class ServiceCache {

	private RemoteCache<String, Object> realCache;

	public ServiceCache(RemoteCache<String, Object> realCache){
		this.realCache = realCache;
	};
	
	public void clear() {
		this.realCache.clear();
	}

	@SuppressWarnings("unchecked")
	public <T>T put(String key, T value) {
		return (T) this.realCache.put(key, value);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T get(String key) {
		return (T) this.realCache.get(key);
	}

	public void stop() {
		this.realCache.stop();
	}

	public int size() {
		return this.realCache.size();
	}
	
	public void remove(Object key){
		realCache.remove(key);
	}

	/**
	 * Dada una key, una cantidad a resolver y una función se agrega como valor la cantidad
	 * especificada como parámetro, o el resultado de la función aplicada.
	 * @param key - String - la key de un map
	 * @param n - Integer - una cantidad
	 * @param operation - BiFunction - se espera una suma o una resta
	 */
	public void putValueWithOperation(String key, Integer n, BiFunction<Integer, Integer, Integer> operation) {
		
		Integer v = (Integer) this.get(key);
		if(v == null){
			this.put(key, n);
		}
		else {
			this.put(key, operation.apply(v, n));
		}
	}
	
	/**
	 * Se incrementa el valor de key en n.
	 * @param key - String
	 * @param n - Integer
	 */
	public void incrementValue(String key, int n) {
		try {
			putValueWithOperation(key, n, (Integer a, Integer b) -> { return a+b; });
		}
		catch(ClassCastException e) {
			throw e;
		}
	}

	/**
	 * Se decrementa en n el valor de key.
	 * @param key un String.
	 * @param n un int.
	 */
	public void decrementValue(String key, int n) {
		try {
			putValueWithOperation(key, n, (Integer a, Integer b) -> { return a-b; });
		}
		catch(ClassCastException e) {
			throw e;
		}
	}
}

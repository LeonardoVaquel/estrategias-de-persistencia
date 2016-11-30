package ar.edu.unq.epers.bichomon.backend.dao.infinispan;

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
	 * Se incrementa el valor de key en n.
	 * @param String key y int n
	 */
	public void incrementValue(String key, int n) {
		try {
			Integer v = (Integer) this.get(key);
			if(v == null){
				this.put(key, n);
			}
			else {
				this.put(key, v+n);
			}
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
			Integer v = (Integer) this.get(key);
			if(v == null){
				this.put(key, 0);
			}
			else {
				this.put(key, v-n);
			}
		}
		catch(ClassCastException e) {
			throw e;
		}
	}
}

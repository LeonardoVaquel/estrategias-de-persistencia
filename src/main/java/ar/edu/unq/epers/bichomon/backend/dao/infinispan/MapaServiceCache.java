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
public class MapaServiceCache {

	private RemoteCache<Ubicacion, Integer> realCache;

	public MapaServiceCache(RemoteCache<Ubicacion, Integer> realCache){
		this.realCache = realCache;
	};
	
	public void clear() {
		this.realCache.clear();
	}

	public Integer put(Ubicacion key, Integer value) {
		return this.realCache.put(key, value);
	}
	
	public Integer get(Ubicacion key) {
		return this.realCache.get(key);
	}

	public void stop() {
		this.realCache.stop();
	}

	public int size() {
		return this.realCache.size();
	}
}

package ar.edu.unq.epers.bichomon.backend.dao.infinispan;

import java.util.List;

import org.infinispan.client.hotrod.RemoteCache;

import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Dojo;
/**
 * LeaderboardServiceCache es una Clase que se encarga de administrar
 * una especie de ranking de los {@link Entrenador} campeones en un 
 * {@link Dojo}.
 * 
 * @author leonardo
 *
 */
public class LeaderboardServiceCache {

	private RemoteCache<Dojo, List<Entrenador>> realCache;

	public LeaderboardServiceCache(RemoteCache<Dojo, List<Entrenador>> realCache){
		this.realCache = realCache;
	};
	
	public void clear() {
		this.realCache.clear();
	}

	public List<Entrenador> put(Dojo key, List<Entrenador> value) {
		return this.realCache.put(key, value);
	}
	
	public List<Entrenador> get(List<Entrenador> key) {
		return this.realCache.get(key);
	}

	public void stop() {
		this.realCache.stop();
	}

	public int size() {
		return this.realCache.size();
	}
}

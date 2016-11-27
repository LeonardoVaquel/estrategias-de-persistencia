package ar.edu.unq.epers.bichomon.backend.dao.infinispan;

import java.util.List;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;

import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;

/**
 * La clase CacheProvider tiene la funcionalidad de proveer
 * los distintos tipos de cache donde se van a almacenar y
 * consultar datos en la BBDD.
 * 
 * @author leonardo
 *
 */
public class CacheProvider {
	
	private static CacheProvider INSTANCE;
	
	public synchronized static CacheProvider getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CacheProvider();
		}
		return INSTANCE;
	}

	private RemoteCacheManager cacheManager;
	private MapaServiceCache mapaServiceCache;
	private LeaderboardServiceCache leaderboardCache;
	
	private CacheProvider() {
		ConfigurationBuilder builder = new ConfigurationBuilder();
		builder.addServer().host("127.0.0.1").port(11222);

		this.cacheManager = new RemoteCacheManager(builder.build());
		
		// Cache de MapaService
		RemoteCache<String, Integer> realCache = this.cacheManager.getCache();
		this.mapaServiceCache = new MapaServiceCache(realCache);
		
		// Cache de LeaderboardService
		RemoteCache<String, List<Entrenador>> realCache2 = this.cacheManager.getCache();
		this.leaderboardCache = new LeaderboardServiceCache(realCache2);
		
	}
	

	public MapaServiceCache getMapaServiceCache() {
		return this.mapaServiceCache;
	}
	
	public LeaderboardServiceCache getLeaderboardServiceCache(){
		return this.leaderboardCache;
	}

}

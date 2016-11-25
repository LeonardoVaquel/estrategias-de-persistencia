package ar.edu.unq.epers.bichomon.backend.dao.infinispan;

import java.util.List;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;


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
		
		RemoteCache<String, Integer> realCache = this.cacheManager.getCache();
		this.mapaServiceCache = new MapaServiceCache(realCache);
		
//		RemoteCache<String, List<Entrenador>>  
		
	}
	
	public MapaServiceCache getMapaServiceCache() {
		return this.mapaServiceCache;
	}

}

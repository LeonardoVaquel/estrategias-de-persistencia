package ar.edu.unq.epers.bichomon.backend.dao.infinispan;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;

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
	private ServiceCache serviceCache;
	
	private CacheProvider() {
		ConfigurationBuilder builder = new ConfigurationBuilder();
		builder.addServer().host("127.0.0.1").port(11222);

		this.cacheManager = new RemoteCacheManager(builder.build());
		
		RemoteCache<String, Object> realCache = this.cacheManager.getCache();
		this.serviceCache = new ServiceCache(realCache);
		
	}
	
	public ServiceCache getServiceCache() {
		return this.serviceCache;
	}

}

package infinispan;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.unq.epers.bichomon.backend.dao.infinispan.CacheProvider;
import ar.edu.unq.epers.bichomon.backend.dao.infinispan.MapaServiceCache;

public class TestInfiniSpanMapaServiceCache {

	private MapaServiceCache mapaCache;
	
	@Before
	public void setUp() {
		this.mapaCache = CacheProvider.getInstance().getMapaServiceCache();
		this.mapaCache.put("Dojo-Quilmes", 3);
		this.mapaCache.put("Dojo-Bernal", 1);
		this.mapaCache.put("Dojo-Varela", 23);
		this.mapaCache.put("Dojo-1-11-14", 10);
	}
	
	@After
	public void deleteAll() {
		this.mapaCache.clear();
	}
	
	@Test
	public void dada_una_ubicacion_se_obtiene_su_valor_en_una_cache() {
		Assert.assertEquals((Integer) 3,  this.mapaCache.get("Dojo-Quilmes"));
		Assert.assertEquals((Integer) 1,  this.mapaCache.get("Dojo-Bernal"));
		Assert.assertEquals((Integer) 23, this.mapaCache.get("Dojo-Varela"));
		Assert.assertEquals((Integer) 10, this.mapaCache.get("Dojo-1-11-14"));
		Assert.assertEquals(4, this.mapaCache.size());
	}
	
	@Test
	public void dada_una_ubicacion_que_no_existe_no_se_obtienen_valores_de_la_cache() {
		Assert.assertEquals(null, this.mapaCache.get("Dojo-DonBosco"));
	}
	
}

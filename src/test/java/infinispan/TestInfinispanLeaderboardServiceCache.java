package infinispan;


import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ar.edu.unq.epers.bichomon.backend.dao.infinispan.CacheProvider;
import ar.edu.unq.epers.bichomon.backend.dao.infinispan.ServiceCache;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;

public class TestInfinispanLeaderboardServiceCache {

	private ServiceCache cache;
	private @Mock Entrenador entrenador1, entrenador2, entrenador3;
	private List<Entrenador> listaDeCampeones;
	
	@Before
	public void setup(){
		
		MockitoAnnotations.initMocks(this);
		
		this.cache = CacheProvider.getInstance().getServiceCache();
		
		listaDeCampeones = new ArrayList<>();
		listaDeCampeones.add(entrenador3);
		listaDeCampeones.add(entrenador1);
		listaDeCampeones.add(entrenador2);
		
		cache.put("campeones", listaDeCampeones);
	}
	
	@Test
	public void se_agrega_una_lista_con_3_entrenadores_campeones_a_la_cache(){
		List<Entrenador> result = cache.get("campeones");
		
		Assert.assertNotNull(result);
		Assert.assertEquals(3, result.size());		
	}
	
	@Test
	public void se_limpia_la_cache_y_esta_no_tiene_mas_cacheada_la_lista_de_campeones(){
		cache.clear();
		
		List<Entrenador> result = cache.get("campeones");
		
		Assert.assertNull(result);
	}
	
	@Test
	public void la_cache_se_invalida_en_remove(){

		cache.remove("campeones");
		List<Entrenador> result = cache.get("campeones");
		
		Assert.assertNull(result);
	}
}

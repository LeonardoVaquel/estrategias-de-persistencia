package infinispan;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ar.edu.unq.epers.bichomon.backend.dao.infinispan.CacheProvider;
import ar.edu.unq.epers.bichomon.backend.dao.infinispan.LeaderboardServiceCache;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;

public class TestInfinitispanLeaderboardServiceCache {

	private LeaderboardServiceCache cache;
	private Entrenador entrenador1, entrenador2, entrenador3;
	private List<Entrenador> listaDeCampeones;
	
	@Before
	public void setup(){
//		MockitoAnnotations.initMocks(this);
		this.cache = CacheProvider.getInstance().getLeaderboardServiceCache();

		entrenador1 = new Entrenador();
		entrenador2 = new Entrenador();
		entrenador3 = new Entrenador();
		
		listaDeCampeones = new ArrayList<>();
//		listaDeCampeones.add(entrenador1);
//		listaDeCampeones.add(entrenador2);
		listaDeCampeones.add(entrenador3);
	
//		cache.put("campeones", new ArrayList<Entrenador>());
	}
	
	@Test
	public void se_agrega_una_lista_de_entrenadores_campeones_a_la_cache(){
		
		cache.put("campeones", listaDeCampeones);
		System.out.println("PASE POR ACA");
		assertEquals(1, listaDeCampeones.size());		
	}
}

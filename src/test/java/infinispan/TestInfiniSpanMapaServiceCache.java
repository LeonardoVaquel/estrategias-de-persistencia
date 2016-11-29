package infinispan;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.unq.epers.bichomon.backend.dao.infinispan.CacheProvider;
import ar.edu.unq.epers.bichomon.backend.dao.infinispan.ServiceCache;

public class TestInfiniSpanMapaServiceCache {

	private ServiceCache mapaCache;
	
	@Before
	public void setUp() {
		this.mapaCache = CacheProvider.getInstance().getServiceCache();
		this.mapaCache.put("Dojo-Quilmes", 3);
		this.mapaCache.put("Dojo-Bernal", 1);
		this.mapaCache.put("Dojo-Varela", 23);
		this.mapaCache.put("Dojo-1-11-14", 10);
		
		List<String> falsaLista = new ArrayList<>();
		falsaLista.add("Uno");
		falsaLista.add("Dos");
		this.mapaCache.put("FalsoInteger", falsaLista);
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
		Assert.assertEquals(5, this.mapaCache.size());
	}
	
	@Test
	public void dada_una_ubicacion_que_no_existe_no_se_obtienen_valores_de_la_cache() {
		Assert.assertEquals(null, (Integer) this.mapaCache.get("Dojo-DonBosco"));
	}
	
	@Test
	public void dada_una_ubicacion_se_incrementa_su_valor_en_la_cache() {
		
		this.mapaCache.incrementValue("Dojo-Quilmes", 2);
		Assert.assertEquals((Integer) 5, this.mapaCache.get("Dojo-Quilmes"));
	}
	
	@Test
	public void dada_una_ubicacion_se_decrementa_su_valor_en_la_cache() {
		
		this.mapaCache.decrementValue("Dojo-Quilmes", 2);
		Assert.assertEquals((Integer) 1, this.mapaCache.get("Dojo-Quilmes"));
	}
	
	@Test(expected=ClassCastException.class)
	public void se_intenta_incrementar_un_valor_no_numerico() {
		this.mapaCache.incrementValue("FalsoInteger", 20);
	}
	
	@Test(expected=ClassCastException.class)
	public void se_intenta_decrementar_un_valor_no_numerico() {
		this.mapaCache.decrementValue("FalsoInteger", 15);
	}
	
}

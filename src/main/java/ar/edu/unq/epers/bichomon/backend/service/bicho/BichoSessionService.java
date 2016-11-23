package ar.edu.unq.epers.bichomon.backend.service.bicho;


import ar.edu.unq.epers.bichomon.backend.dao.BichoDAO;
import ar.edu.unq.epers.bichomon.backend.dao.EntrenadorDAO;
import ar.edu.unq.epers.bichomon.backend.dao.ExperienciaDAO;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.duelo.ResultadoCombate;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.experiencia.Experiencia;
import ar.edu.unq.epers.bichomon.backend.model.experiencia.TablaDeExperiencia;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Dojo;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.NoSePuedeAbandonarEnUbicacionException;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.NoSePuedeRealizarDueloEnUbicacionException;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Ubicacion;
import ar.edu.unq.epers.bichomon.backend.service.feed.FeedService;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;

/**
 * BichoSessionService es una clase que modela el comportamiento de un {@link Bicho} al conectarse a 
 * una sesión en una base de datos.
 * Los métodos tendrán la responsabilidad de solicitar o persistir información en una base de datos y 
 * delegar comportamiento sobre las instancias {@link Bicho} que sean necesarias.
 * 
 * @author santiago
 */
public class BichoSessionService implements BichoService {

	private BichoDAO 		bichoDAO;
	private EntrenadorDAO 	entrenadorDAO;
	private ExperienciaDAO 	expDAO;
	private FeedService feedService;
	
	public BichoSessionService(BichoDAO bichoDAO, EntrenadorDAO entrenadorDAO, ExperienciaDAO expDAO, FeedService feedService) {
		this.bichoDAO 		= bichoDAO;
		this.entrenadorDAO 	= entrenadorDAO;
		this.expDAO 		= expDAO;
		this.feedService    = feedService;
	}
	
	public Bicho getBicho(int idBicho) {
		return Runner.runInSession(() -> {
			return bichoDAO.getBicho(idBicho);
		});
	}
	
	/**
	 * Dado un nombre de {@link Entrenador} se realiza una búsqueda de {@link Bicho} en la 
	 * {@link Ubicacion} actual de dicho entrenador, se actualiza su experiencia y devuelve 
	 * una instancia de {@link Bicho} si la búsqueda fue exitosa.  
	 */
	public Bicho buscar(String nombreEntrenador) {
		return Runner.runInSession(() -> {
			Entrenador entrenador = this.entrenadorDAO.getEntrenador(nombreEntrenador); 
			Bicho bicho = entrenador.buscar();
			
			TablaDeExperiencia expTable = this.expDAO.getExperiencieByEvent("Capturar"); 
			Experiencia expCfg = this.expDAO.getExperienciaConfig("v1.0.0");
			
			double experienciaPorCaptura = expTable.getValor();
			
			entrenador.gainsExp(experienciaPorCaptura, expCfg);
			entrenador.obtenerBicho(bicho);
			Especie especie = bicho.getEspecie();
			especie.incrementarCantidad();
			
			System.out.println(bicho.getEspecie().getNombre());
			
			this.feedService.saveCaptura(nombreEntrenador, entrenador.getUbicacion().getNombre(), bicho.getEspecie().getNombre());
			
			return bicho;
		});
	}
	
	/**
	 * Dado un nombre de {@link Entrenador} y un id de {@link Bicho} se evalúa si el bicho
	 * especificado está en condiciones de evolucionar.
	 */
	public boolean puedeEvolucionar(String nobreEntrenador, int idBicho) {
		return Runner.runInSession(() -> {
			return bichoDAO.getBicho(idBicho).puedeEvolucionar();
		});
	}
	
	/**
	 * Dado un nombre de {@link Entrenador} y un id de {@link Bicho} se evoluciona el bicho
	 * especificado y se le asigna la cantidad de experiencia correspondiente al entrenador.
	 * Se retorna un {@link Bicho} con su especie modificada. 
	 */
	public Bicho evolucionar(String nombreEntrenador, int idBicho) {
		return Runner.runInSession(() -> {
			
			// Aclaración: no es necesario utilizar el Entrenador, ya que es posible
			// mandarle mensajes a éste mediante el owner del bicho
			Entrenador entrenador = this.entrenadorDAO.getEntrenador(nombreEntrenador);
			Bicho bicho = this.bichoDAO.getBicho(idBicho);
			TablaDeExperiencia expTable = this.expDAO.getExperiencieByEvent("Evolucionar"); 
			Experiencia expCfg = this.expDAO.getExperienciaConfig("v1.0.0");
			double experienciaPorCombate = expTable.getValor();
			
			entrenador.evolucionar(bicho);
			entrenador.gainsExp(experienciaPorCombate, expCfg);
			
			return bicho.evolucionar();
		});
	}
	
	/**
	 * Dado un nombre de {@link Entrenador} y un id de {@link Bicho} se intenta abandonar el bicho en la
	 * ubicación actual de dicho entrenador.
	 * En caso que la {@link Ubicación} no permita realizar abandonos se arrojará una 
	 * excepción {@link NoSePuedeAbandonarEnUbicacionException}  
	 */
	public void abandonar(String nombreEntrenador, int idBicho) {
		Runner.runInSession(() -> {
			
			Bicho bicho = this.bichoDAO.getBicho(idBicho);
			
			//Entrenador entrenador = this.entrenadorDAO.getEntrenador(nombreEntrenador);
			// el entrenador no es necesario
			//entrenador.abandonar(bicho);
			String nombreUbicacion = bicho.getOwner().getUbicacion().getNombre();
			bicho.getOwner().abandonar(bicho);
			
			this.feedService.saveAbandono(nombreEntrenador, nombreUbicacion);
			
			return null;
		});
	}
	
	/**
	 * Dado un nombre de {@link Entrenador} y un id de {@link Bicho} se realiza un duelo en la
	 * {@link Ubicacion} actual de dicho entrenador y se le asigna la cantidad de experiencia 
	 * correspondiente.
	 * Se espera retornar una instancia {@link ResultadoCombate} con los detalles del duelo y
	 * el actual campeón de un {@link Dojo}
	 * En caso que la {link Ubicacion} no permita realizar duelos se arrojará una
	 * excepción {@link NoSePuedeRealizarDueloEnUbicacionException}
	 */
	public ResultadoCombate duelo(String nombreEntrenador, int idBicho) {
		return Runner.runInSession(()-> {
			
			Bicho bicho = this.bichoDAO.getBicho(idBicho);
			Entrenador entrenador = this.entrenadorDAO.getEntrenador(nombreEntrenador);
			
			ResultadoCombate resultadoCombate = entrenador.duelo(bicho);
			
			TablaDeExperiencia expTable = this.expDAO.getExperiencieByEvent("Combatir"); 
			Experiencia expCfg = this.expDAO.getExperienciaConfig("v1.0.0");
			
			double experienciaPorCombate = expTable.getValor();
			
			Entrenador entrenadorGanador = resultadoCombate.getBichoGanador().getOwner(); 
			Entrenador entrenadorPerdedor = resultadoCombate.getBichoPerdedor().getOwner();
			
			entrenadorGanador.gainsExp(experienciaPorCombate, expCfg);
			entrenadorPerdedor.gainsExp(experienciaPorCombate, expCfg);
			
			if(idBicho == resultadoCombate.getBichoGanador().getId()) {
				this.feedService.saveCoronacion(entrenadorGanador.getNombre(), entrenadorGanador.getUbicacion().getNombre(), entrenadorPerdedor.getNombre());
			}
			
			return resultadoCombate;
		});
	}
}

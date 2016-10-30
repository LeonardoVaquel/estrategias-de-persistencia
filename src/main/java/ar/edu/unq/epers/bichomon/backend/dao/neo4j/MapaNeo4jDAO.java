package ar.edu.unq.epers.bichomon.backend.dao.neo4j;

import java.util.List;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Value;
import org.neo4j.driver.v1.Values;

import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Ubicacion;

/**
 * MapaNeo4jDAO encapsula el acceso a Neo4j
 * 
 * @author santiago
 */
public class MapaNeo4jDAO {

	private Driver driver;
	
	public MapaNeo4jDAO() {
		this.driver = GraphDatabase.driver("bolt://localhost", AuthTokens.basic("neo4j", "root"));
	}
	
	/**
	 * Crea una entidad de grafo a partir de una {@link Ubicacion} pasada como parámetro
	 * @param ubicacion - una instancia de {@link Ubicacion}
	 */
	public void crearUbicacion(Ubicacion ubicacion) {
		Session session = this.driver.session();
		
		try {
			
			//"MERGE (u:Ubicacion {nombre: {elNombre}}) " +
			//"SET n.name = {elNombre} ";
			String query = "CREATE (u:Ubicacion { name: {nombreUbicacion} })"; 
			session.run(query, Values.parameters("nombreUbicacion", ubicacion.getNombre()));

		} finally {
			session.close();
		}
	}
	
	/**
	 * Crea un camino Unidireccional desde la primer ubicación pasado por parámetro
	 * hacia la segunda ubicación del tipo especificado. 
	 * @param ubicacion1 - un nombre de ubicación de origen
	 * @param ubicacion2 - un nombre de ubicacion de destino
	 * @param tipoCamino - un string indicando el tipo de camino
	 */
	public void conectar(String nombreUbicacion1, String nombreUbicacion2, String tipoCamino) {
		Session session = this.driver.session();
		
		try {
			
			String query = 	"MATCH (u1:Ubicacion { nombre: {nombreUbicacion1} }) " +
							"MATCH (u2:Ubicacion { nombre: {nombreUbicacion2} }) " +
							"MERGE (u1)-[:tipoCamino]->(u2)";
			
			session.run(query, Values.parameters(	"nombreUbicacion1", nombreUbicacion1,
													"nombreUbicacion2", nombreUbicacion2));
			
//			String queryExample = "MATCH (padre:Persona {dni: {elDniPadre}}) " +
//					"MATCH (hijo:Persona {dni: {elDniHijo}}) " +
//					"MERGE (padre)-[:padreDe]->(hijo) " + //CREATE
//					"MERGE (hijo)-[:hijoDe]->(padre)"; //CREATE
//			session.run(query, Values.parameters("elDniPadre", padre.getDni(),
//					"elDniHijo", hijo.getDni()));

		} finally {
			session.close();
		}
	}
	
	/**
	 * Dado el nombre de una ubicación y un tipo de camino se espera devolver todas aquellas ubicaciones
	 * conectadas directamente con la ubicación de nombre pasado por parámetro a tracés del camino
	 * especificado. 
	 * @param nombreUbicacion - un string
	 * @param tipoCamino - un string
	 * @return - una lista de {@link Ubicacion}
	 */
	public List<Ubicacion> conectados(String nombreUbicacion, String tipoCamino) {
		Session session = this.driver.session();

		try {
			String query = "MATCH (u:Ubicacion { nombre: {nombreUbicacion}}) " +
					"MATCH (conectada)-[:tipoCamino]->(u) " +
					"RETURN conectada";
			StatementResult result = session.run(query, Values.parameters(	"tipoCamino", 		tipoCamino,
																			"nombreUbicacion", 	nombreUbicacion));
			
			//Similar a list.stream().map(...)
			return result.list(record -> {
				Value conectada = record.get(0);
				String nombre = conectada.get("nombre").asString();
				return new Ubicacion(nombre);
			});
			
			
		} finally {
			session.close();
		}
	}
	
	/**
	 * Dado un nombre de entrenador y un nombre de ubicacion se mueve un entrenador a la
	 * ubicación mediante el camino mas corto posible
	 * @param nombreEntrenador - un string
	 * @param nombreUbicacion - un string
	 */
	public void moverMasCorto(String nombreEntrenador, String nombreUbicacion) {
		Session session = this.driver.session();
		
		// Utilizo Hibernate para recuperar el entrenador?
		// este mensaje va en un service
		
		try {
			
			String query = "";
			StatementResult result = session.run(query);
		}
		finally {
			session.close();
		}
	}
	
}

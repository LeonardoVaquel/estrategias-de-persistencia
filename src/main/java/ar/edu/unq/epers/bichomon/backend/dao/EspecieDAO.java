package ar.edu.unq.epers.bichomon.backend.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.management.BichomonFactory;
import ar.edu.unq.epers.bichomon.backend.service.especie.EspecieNoExistente;
import ar.edu.unq.epers.bichomon.backend.service.especie.EspecieService;
import ar.edu.unq.epers.bichomon.backend.dao.impl.ConnectionBlock;

public class EspecieDAO implements EspecieService {

	private BichomonFactory bichomonFactory;
	private String urlBichomongo;
	
	public EspecieDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("No se puede encontrar la clase del driver", e);
		}
		
		bichomonFactory = new BichomonFactory();
		urlBichomongo  = "jdbc:mysql://localhost:3306/BICHOMONGO?user=root&password=root"; 
	}
	
	@Override
	public void crearEspecie(Especie especie) {
		this.executeWithConnection(conn -> {
			
			PreparedStatement ps = conn.prepareStatement("INSERT INTO Especie ("
														+ 						"nombre, "
														+ 						"altura, "
														+ 						"peso, "
														+ 						"tipo,"
														+ 						"energiaInicial,"
														+ 						"urlFoto,"
														+ 						"cantidadBichos) "
														+ "VALUES (?,?,?,?,?,?,?);"
														);
			ps.setString(1, especie.getNombre());
			ps.setInt	(2, especie.getAltura());
			ps.setInt	(3, especie.getPeso());
			ps.setString(4, especie.getTipo().toString());
			ps.setInt	(5, especie.getEnergiaInicial());
			ps.setString(6, especie.getUrlFoto());
			ps.setInt	(7, especie.getCantidadBichos());
			ps.execute();
			
			if (ps.getUpdateCount() != 1){
				throw new RuntimeException();
			}
			ps.close();
			return null;
		});		
	}

	@Override
	public Especie getEspecie(String nombreEspecie) {
		return this.executeWithConnection(conn -> {
			PreparedStatement ps = conn.prepareStatement( "SELECT "
														+ 			"altura, "
														+ 			"tipo, "
														+ 			"peso, "
														+ 			"energiaInicial, "
														+ 			"urlFoto, "
														+ 			"cantidadBichos "
														+ "FROM Especie "
														+ "WHERE nombre = ?");
			ps.setString(1, nombreEspecie);
			
			ResultSet resultSet = ps.executeQuery();
			
			Especie especie = null;
			while (resultSet.next()) {

				if (especie != null) {
					throw new EspecieNoExistente(nombreEspecie);
				}
				
				especie = bichomonFactory.crearEspecie(
											nombreEspecie,
											resultSet.getInt("altura"),
											resultSet.getInt("peso"),
											resultSet.getString("tipo"),
											resultSet.getInt("energiaInicial"),
											resultSet.getString("urlFoto"),
											resultSet.getInt("cantidadBichos"));
			}
			ps.close();
			return especie;
		});
	}

	@Override
	public List<Especie> getAllEspecies() {
		return this.executeWithConnection(conn -> {
			
			PreparedStatement ps = conn.prepareStatement( "SELECT "
														+ 			"nombre,"
														+ 			"altura, "
														+ 			"tipo, "
														+ 			"peso, "
														+ 			"energiaInicial, "
														+ 			"urlFoto, "
														+ 			"cantidadBichos "
														+ "FROM Especie "
														+ "ORDER BY nombre");
			
			ResultSet resultSet    = ps.executeQuery();
			List<Especie> especies = new ArrayList<>();
			
			while (resultSet.next()) {
				
				//Especie especie = new Especie();
				Especie especie = bichomonFactory.crearEspecie(
						resultSet.getString("nombre"),
						resultSet.getInt("altura"),
						resultSet.getInt("peso"),
						resultSet.getString("tipo"),
						resultSet.getInt("energiaInicial"),
						resultSet.getString("urlFoto"),
						resultSet.getInt("cantidadBichos"));
				especies.add(especie);
			}
			ps.close();
			return especies;
		});
	}	
	
	@Override
	public Bicho crearBicho(String nombreEspecie, String nombreBicho) {

		return this.executeWithConnection(conn -> {
			PreparedStatement ps = conn.prepareStatement( "UPDATE Especie "
														+ "SET cantidadBichos = cantidadBichos + 1 "
														+ "WHERE nombre = ?");
			ps.setString(1, nombreEspecie);
			ps.executeUpdate();

			Bicho bicho = null;
			
			Especie especie = this.getEspecie(nombreEspecie);
			bicho = bichomonFactory.crearBicho(especie, nombreBicho);
			
			ps.close();
			return bicho;
		});		
	}

	// Por ahora no existe la tabla Bicho
	public void removeBicho(Bicho bicho){
		this.executeWithConnection(conn -> {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM Bicho WHERE nombre = ?");
			ps.setString(1, bicho.getNombre());
			ps.execute();
			ps.close();
			return null;
		});		
	};
	
	/**
	 * Dada una especie, la elimina de la tabla Especie
	 * @param especie
	 */
	public void removeEspecie(Especie especie){
		this.executeWithConnection(conn -> {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM Especie WHERE nombre = ?");
			ps.setString(1, especie.getNombre());
			ps.execute();
			ps.close();
			return null;
		});		
	};
	
	public void removeAllEspecies() {
		this.executeWithConnection(conn -> {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM Especie");
			ps.executeUpdate();
			ps.close();
			return null;
		});		
	}
	
	// Connection methods
	
	/**
	 * Ejecuta un bloque de codigo contra una conexion.
	 */
	private <T> T executeWithConnection(ConnectionBlock<T> bloque) {
		Connection connection = this.openConnection(this.urlBichomongo);
		try {
			return bloque.executeWith(connection);
		} catch (SQLException e) {
			throw new RuntimeException("Error no esperado", e);
		} finally {
			this.closeConnection(connection);
		}
	}

	/**
	 * Establece una conexion a la url especificada
	 * @param url - la url de conexion a la base de datos
	 * @return la conexion establecida
	 */
	private Connection openConnection(String url) {
		try {
			return DriverManager.getConnection(url);
		} catch (SQLException e) {
			throw new RuntimeException("No se puede establecer una conexion", e);
		}
	}

	/**
	 * Cierra una conexion con la base de datos (libera los recursos utilizados por la misma)
	 * @param connection - la conexion a cerrar.
	 */
	private void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException("Error al cerrar la conexion", e);
		}
	}	
}

package ar.edu.unq.epers.bichomon.backend.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.especie.TipoBicho;
import ar.edu.unq.epers.bichomon.backend.service.especie.EspecieService;
import ar.edu.unq.epers.bichomon.backend.dao.impl.ConnectionBlock;

public class EspecieDAO implements EspecieService {

	public EspecieDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("No se puede encontrar la clase del driver", e);
		}
	}
	
	@Override
	public void crearEspecie(Especie especie) {
		this.executeWithConnection(conn -> {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO Especie ("
														+ "nombre, "
														+ "altura, "
														+ "peso, "
														+ "tipo,"
														+ "energiaInicial,"
														+ "urlFoto,"
														+ "cantidadBichos) "
														+ "VALUES (?,?,?,?,?,?,?)"
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
				throw new RuntimeException("No se inserto la especie: " + especie.getNombre());
			}
			ps.close();

			return null;
		});		
	}

	@Override
	public Especie getEspecie(String nombreEspecie) {
		return this.executeWithConnection(conn -> {
			PreparedStatement ps = conn.prepareStatement( "SELECT altura, tipo, peso, energiaIicial, urlFoto, cantidadBichos "
														+ "FROM especie"
														+ "WHERE nombre = ?");
			ps.setString(1, nombreEspecie);
			
			ResultSet resultSet = ps.executeQuery();

			Especie especie = null;
			while (resultSet.next()) {
				//si personaje no es null aca significa que el while dio mas de una vuelta, eso
				//suele pasar cuando el resultado (resultset) tiene mas de un elemento.
				if (especie != null) {
					throw new RuntimeException("");
					//throw new EspecieNoExistente(nombreEspecie);
				}
				
				especie = new Especie();
				especie.setNombre(nombreEspecie);
				especie.setAltura(resultSet.getInt("altura"));
				especie.setPeso(resultSet.getInt("peso"));
				especie.setTipo(TipoBicho.valueOf(resultSet.getString("tipo")));
				especie.setEnergiaIncial(resultSet.getInt("energiaInicial"));
				especie.setUrlFoto(resultSet.getString("urlFoto"));
				especie.setCantidadBichos(resultSet.getInt("cantidadBichos"));
			}

			ps.close();
			return especie;
		});
	}

	@Override
	public List<Especie> getAllEspecies() {
		// TODO Auto-generated method stub
		
		//INTENTADO hacer el get especie
		return (List<Especie>) this.executeWithConnection(conn -> {
			PreparedStatement ps = conn.prepareStatement( "SELECT altura, tipo, peso, energiaIicial, urlFoto, cantidadBichos "
														+ "FROM especie");
			
			
			ResultSet resultSet = ps.executeQuery();

			List<Especie> especies = null;
			while (resultSet.next()) {
				Especie especie = new Especie();
				especie.setAltura(resultSet.getInt("altura"));
				especie.setPeso(resultSet.getInt("peso"));
				especie.setTipo(TipoBicho.valueOf(resultSet.getString("tipo")));
				especie.setEnergiaIncial(resultSet.getInt("energiaInicial"));
				especie.setUrlFoto(resultSet.getString("urlFoto"));
				especie.setCantidadBichos(resultSet.getInt("cantidadBichos"));
			}

			ps.close();
			return especies;

			});
	
	}

	@Override
	public Bicho crearBicho(String nombreEspecie, String nombreBicho) {
			// preguntar por el Tipo del objeto especie que falta 
			//y la energia que es el tercer atributo que falta con completar
			Bicho bicho = new Bicho(new Especie(nombreEspecie, null),nombreBicho);
			return bicho;	
	}

	
	public void removeBicho(Bicho bicho){
		this.executeWithConnection(conn -> {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM bicho WHERE nombreBicho = ?");
			ps.setString(1, bicho.getNombre());
			ps.execute();
			ps.close();
			return null;
		});		
		
	
	};
	
	public void removeEspecie(Especie especie){
		this.executeWithConnection(conn -> {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM especie WHERE nombreEspecie = ?");
			ps.setString(1, especie.getNombre());
			ps.execute();
			ps.close();
			return null;
		});		
		
	
	};
	
	// Connection methods
	
	/**
	 * Ejecuta un bloque de codigo contra una conexion.
	 */
	private <T> T executeWithConnection(ConnectionBlock<T> bloque) {
		//Connection connection = this.openConnection("jdbc:mysql://localhost:3306/bichomongo?user=root&password=lock");
		Connection connection = this.openConnection("jdbc:mysql://localhost:3306/bichomongo?user=root&password=21768");
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
			//La url de conexion no deberia estar harcodeada aca
			//return DriverManager.getConnection("jdbc:mysql://localhost:3306/bichomongo?user=root&password=lock");
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/bichomongo?user=root&password=21768");
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

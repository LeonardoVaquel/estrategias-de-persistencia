package ar.edu.unq.epers.bichomon.backend.model.exeption;

import java.lang.RuntimeException;

public class EspecieNoExistente extends RuntimeException {
	
	public EspecieNoExistente(String nombre){
		
		throw new RuntimeException("No existe la especie " + nombre.toString());
	}
}

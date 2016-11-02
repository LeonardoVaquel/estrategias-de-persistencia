package ar.edu.unq.epers.bichomon.backend.model.ubicacion;

public enum CaminoCosto {

	TERRESTRE(1),
	MARITIMO(2),
	AEREO(5);
	
	private final int id;
	
    CaminoCosto(int id) { 
    	this.id = id; 
    }
    
    public int getValue() { 
    	return id; 
    }
    
}

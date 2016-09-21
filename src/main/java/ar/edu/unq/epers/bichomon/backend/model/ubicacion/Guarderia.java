package ar.edu.unq.epers.bichomon.backend.model.ubicacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;

public class Guarderia  extends Ubicacion{
//Debido a que los entrenadores poseen un máximo de bichos en su 
//inventario de capturados los mismos podrán abandonar aquellos
//que no deseen utilizar nuevamente en esta ubicación. 
//Un entrenador no podrá quedarse sin bichos como consecuencia de 
//abandonar.

//Al buscar en esta ubicación un entrenador adoptará bichos que hayan
//sido abandonados anteriormente por algún entrenador distinto a el mismo.
	
	private List<Bicho> bichosAbandonados;
	private Random random;
	public Guarderia(Random random){
		super();
		this.setBichosAbandonados(new ArrayList<>());
		this.random=random;
	}
	
	public void abandonar(Entrenador entrenador, Bicho bicho){
		
		this.bichosAbandonados.add(bicho);

	}
	

	public List<Bicho> getBichosAbandonados() {
		return bichosAbandonados;
	}

	public void setBichosAbandonados(List<Bicho> bichosAbandonados) {
		this.bichosAbandonados = bichosAbandonados;
	}
	
	public Integer cantidadDeBichosAbandonados(){
		return this.getBichosAbandonados().size();
	}
	
	public Bicho adoptarBicho(){
		Integer index = this.numeroRandom();
		
		return this.getBichosAbandonados().get(index);
		
	}
	public Integer numeroRandom(){
		Integer valor = random.nextInt(this.cantidadDeBichosAbandonados());
		return valor;
	}
	@Override
	public void buscar(Entrenador entrenador) {
		
		if (entrenador.puedeBuscar()){
			
			entrenador.obtenerBicho(adoptarBicho());
		}
		
	}
}


package ar.edu.unq.epers.bichomon.backend.model.ubicacion;

import java.util.Random;

import javax.persistence.Entity;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
//import ar.edu.unq.epers.bichomon.backend.model.duelo.Duelo;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;

@Entity
public class Dojo extends Ubicacion {
	private Bicho campeon;
	//private Duelo duelo;
	//private List<String > historialCampeon; deberia ser una tupla que contenga el campeon+un dataTime?
	
	
	public Dojo(String nombreDojo,Random random) {
		super(nombreDojo,random);
	}

	public Bicho getCampeon() {
		return campeon;
	}

	public void setCampeon(Bicho campeon) {
		this.campeon = campeon;
	}
	
	//public List<Bicho> mismaEspecieQueElCampeon(){
		//Especie especieCampeon= this.campeon.getBicho().getEspecie();
		//List<Bicho> bichosDeIgualEspecie= new ArrayList<>();
		//for (Bicho bicho: this.getBichos()){
			//if(bicho.getEspecie()== especieCampeon){
				//bichosDeIgualEspecie.add(bicho);
			//}
		//}
		//return  bichosDeIgualEspecie;
	//}
	
	public Integer cantidadDeBichosEnDojo(){
		return this.getBichos().size();
	}
	
	public Bicho asignarBicho(){
		Integer index = this.numeroRandom();
		
		return this.getBichos().get(index);
		
	}
	public Integer numeroRandom(){
		Integer valor = random.nextInt(this.cantidadDeBichosEnDojo());
		return valor;
	}
	@Override
	public Bicho buscar(Entrenador entrenador) {
		if (entrenador.puedeBuscar()){

			entrenador.obtenerBicho(asignarBicho());
		}

		return null;
	}





}

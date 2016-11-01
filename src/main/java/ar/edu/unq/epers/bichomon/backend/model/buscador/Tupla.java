package ar.edu.unq.epers.bichomon.backend.model.buscador;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
/**
 * Tupla es una clase que representa un HashMap y puede tomar dos values distintos.
 * para representar la probabilidad que tiene una Especie en aparecer cuando un Entrenador
 * busca un Bicho en algun lugar.
 * */
@Entity
public class Tupla{
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@OneToOne(cascade=CascadeType.ALL)
	private Especie key;
	private float value;
	@Transient
	private List<Integer> lsValue;

	public Tupla(Especie especie, float value){
		this.key 	= especie;
		this.value 	= value;
	};
	
	public Tupla(Especie especie, List<Integer> value){
		this.key	 	= especie;
		this.lsValue	= value;
	};
	
	public Tupla() {}

	public Especie getKey(){
		return this.key;
	}
	
	public float getValue(){
		return this.value;
	}
	
	public List<Integer> getLsValue(){
		return this.lsValue;
	}
	

}

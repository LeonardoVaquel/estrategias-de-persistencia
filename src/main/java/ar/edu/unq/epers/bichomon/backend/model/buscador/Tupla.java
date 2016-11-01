package ar.edu.unq.epers.bichomon.backend.model.buscador;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;

@Entity
public abstract class Tupla<T>{
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@OneToOne(cascade=CascadeType.ALL)
	protected Especie key;
	protected T value;

//	public Tupla(Especie especie, float value){
//		this.key 	= especie;
//		this.value 	= value;
//	};
//	
//	public Tupla(Especie especie, List<Integer> value){
//		this.key	 	= especie;
//		this.lsValue	= value;
//	};
	
	public Tupla() {}

	public Especie getKey(){
		return this.key;
	}
	
	public abstract T getValue();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setKey(Especie key) {
		this.key = key;
	}

	public void setValue(T value) {
	}
	
	public void print(){
		System.out.println("("+key+", "+value+")");
	}
	
	
}

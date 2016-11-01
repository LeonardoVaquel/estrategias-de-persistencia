package ar.edu.unq.epers.bichomon.backend.model.buscador;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;

@Entity
public class TuplaEspecieProbabilidad extends Tupla<Float>{

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@OneToOne(cascade=CascadeType.ALL)
	private Especie key;
	private Float value;
	
	public TuplaEspecieProbabilidad(){};
	
	public TuplaEspecieProbabilidad(Especie key, Float value){
		this.key	= key;
		this.value	= value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Especie getKey() {
		return key;
	}

	public void setKey(Especie key) {
		this.key = key;
	}

	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}
	
	public TuplaEspecieProbabilidad convert(Integer coef){
		Float newValue = this.getValue() *coef;
//		this.setValue(newValue);
		return new TuplaEspecieProbabilidad(key, newValue);
	}
	
	public TuplaEspecieLista convertValueInList(){
		TuplaEspecieLista newValue = new TuplaEspecieLista();
		
		return newValue;
	}
}

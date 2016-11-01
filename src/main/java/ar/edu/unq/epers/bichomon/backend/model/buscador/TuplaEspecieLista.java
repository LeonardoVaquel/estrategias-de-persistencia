package ar.edu.unq.epers.bichomon.backend.model.buscador;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;

public class TuplaEspecieLista extends Tupla<List<Integer>>{
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@OneToOne(cascade=CascadeType.ALL)
	private Especie key;
	private List<Integer> value;

	public TuplaEspecieLista(){};
	
	public TuplaEspecieLista(Especie key, List<Integer> vlaue){
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

	public List<Integer> getValue() {
		return value;
	}

	public void setValue(List<Integer> value) {
		this.value = value;
	};
	
	public boolean contieneValor(Integer n){
		return this.getValue().contains(n);
	}
	
}


package ar.edu.unq.epers.bichomon.backend.model.buscador;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;

public class Buscador {

	private double indice;
	private List<Tupla> listDB;
	private List<Tupla> lsResult;
	private Integer coeficiente;
	
	public Buscador(List<Tupla> lsDB, Integer coef){
		this.listDB 		= lsDB;
		this.lsResult 		= new ArrayList<>();
		this.coeficiente	= coef;
	};
	
	public List<Tupla> buscar(){
		for(Tupla tupla:this.listDB){
			List<Integer> newLista = this.mkList(this.indice, tupla.getValue()*this.coeficiente);
			Tupla newTupla = new Tupla(tupla.getKey(), newLista);
			this.indice = 0;
			this.lsResult.add(newTupla);
			this.indice = tupla.getValue() + 1;
		}
		return lsResult;
	}
	
	public List<Integer> mkList(double begin, double end){
		List<Integer> ls = new ArrayList<>();
		while(begin < end){
			ls.add((int) begin);
			begin++;
		}
		return ls;
	}

}

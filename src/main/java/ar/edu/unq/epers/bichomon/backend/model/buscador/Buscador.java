package ar.edu.unq.epers.bichomon.backend.model.buscador;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;

/**
 * {@link Buscador} es la clase encargada de retornar un {@link Bicho} aleatoreamente dependiendo
 * la probabilidad que tenga cada {@link Bicho} en salir.
 * 
 * @author Leonardo
 *
 */
public class Buscador {

	private Bicho bichoEncontrado;
	private Integer indice;
	private List<Tupla> listDB;
	private List<Tupla> lsResult;
	private Integer coeficiente;
	
	public Buscador(List<Tupla> lsDB, Integer coef){
		this.indice 		= 1;
		this.listDB 		= lsDB;
		this.lsResult 		= new ArrayList<>();
		this.coeficiente	= coef;
	};
	
	// listDB es una lista de tuplas [ ( Especie => probalididad ) ]
	// [ ( Charmander => 0.3 ), ( Pikachu, 0.1 ) ] multiplico probabilidad por coef
	// [ (Charmander => 30), (Pikachu => 10) ]
	// genero una lista de [ (Especie, [indice...valor]) ] donde indice es el comiezo de la lista i=1
	// queda [ (Pikachu => [1,2,3,4,5,6,7,8,9,10] ]
	
	/**
	 * 
	 * @param n Es un entero que viene del Random.
	 * @return Un {@link Bicho} de la especie que salio por el numero Random.
	 */
	public Bicho buscar(Integer n){
		
		
		Especie especie = encontrarEspecie(this.buscarAux(), n); // Se busca la especie segun el random.
		bichoEncontrado = new Bicho(especie); // Se crea el bicho que se encontro.
		return bichoEncontrado;
	}
	
	
	public Bicho buscar() {
		
		return this.buscar(this.generarRandom(this.buscarAux()));
	}
	
	
	/**
	 * 
	 * @param ls Una lista de tuplas ( Especie => [ probabilidad ] ).
	 * @param n El numero random sorteado.
	 * @return La {@link Especie} que salio "Random".
	 */
	private Especie encontrarEspecie(List<Tupla> ls, Integer n){
		Especie result = new Especie();
		for( Tupla tupla : ls ){
			if(this.contieneValor(tupla, n)){
				result = tupla.getKey();
				break;
			}
		}
		return result;
	}
	
	private boolean contieneValor(Tupla tupla, Integer n){
		return tupla.getLsValue().contains(n);
	}
	
	public List<Tupla> buscarAux(){
		lsResult = this.tuplasPorCoeficiente(listDB);
		
		return this.listaFinal(lsResult);
		
	}
	
	private List<Tupla> listaFinal(List<Tupla> ls){
		List<Tupla> lsFinal = new ArrayList<>();
		for(Tupla tupla : ls){
			lsFinal.add(mkTuplaWithList(tupla.getKey(), mkList(indice, tupla.getValue())));
			indice++;
		}
		
		return lsFinal;
	}
	
	private Tupla mkTuplaWithList(Especie k, List<Integer> v){
		return new Tupla(k,v);
	}
	
	private List<Tupla> tuplasPorCoeficiente(List<Tupla> listDB){
		List<Tupla> result = new ArrayList<>();
		
		for(Tupla tupla : this.listDB){
			result.add(mkTupla(tupla.getKey(), tupla.getValue()*coeficiente));
		}
		return result;
	}
	
	private Tupla mkTupla(Especie k, float v){
		return new Tupla(k,v);
	}
	
	private List<Integer> mkList(Integer begin, float end){
		List<Integer> ls = new ArrayList<>();
		while(begin <= end){
			ls.add((int) begin);
			begin++;
		}
		return ls;
	}

	/**
	 * Dada una lista de {@link Tupla} devuelve un número random
	 * @param lsTupla - lista de {@link Tupla)} ({@link Especie}, List<Integer)
	 * @return int - el size total de todas las listas sumadas
	 */
	private int generarRandom(List<Tupla> lsTupla){
		
		int result = 0;
		
		for(Tupla tupla:lsTupla) {
			
			result = result + tupla.getLsValue().size();
		}
		
		//result = lsTupla.stream().sum(map(t -> t.getLsValue()));
		
		return new Random().nextInt(result);
	}
}

package ar.edu.unq.epers.bichomon.backend.model.buscador;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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
	private int randomNumber;
	private List<TuplaEspecieProbabilidad> listDB;
//	private List<TuplaEspecieLista> lsResult;
	private Integer coeficiente;
	
	public Buscador(List<TuplaEspecieProbabilidad> lsDB){
//		this.indice 		= 1;
		this.listDB 		= lsDB;
//		this.lsResult 		= new ArrayList<>();
		this.coeficiente	= 100;
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
		System.out.println("%%%%%%%%%%"+listDB);
		List<TuplaEspecieLista> ls = this.convertList(listDB);
		Especie especie = encontrarEspecie(ls, n); // Se busca la especie segun el random.
		bichoEncontrado = new Bicho(especie); // Se crea el bicho que se encontro.
		return bichoEncontrado;
	}
	
	public Bicho buscar() {
		return this.buscar(getARandom());
	}
	
	/**
	 * 
	 * @param ls Una lista de tuplas ( Especie => [ probabilidad ] ).
	 * @param n El numero random sorteado.
	 * @return La {@link Especie} que salio "Random".
	 */
	private Especie encontrarEspecie(List<TuplaEspecieLista> ls, Integer n){
		Especie result = null;
		for(TuplaEspecieLista tupla : ls){
			if(tupla.contieneValor(n)){
				result = tupla.getKey();
				break;
			}
		}
		return result;
	}
	
	public List<TuplaEspecieLista> convertList(List<TuplaEspecieProbabilidad> ls){
		ls.get(0).print();
		ls.get(1).print();
		ls.get(2).print();
		List<TuplaEspecieProbabilidad> listaPorCoef = 
				ls.stream().map((TuplaEspecieProbabilidad t) -> t.convert(this.coeficiente)).collect(Collectors.toList());
//		listaPorCoef.get(0).print();
//		listaPorCoef.get(1).print();
//		listaPorCoef.get(2).print();
		
//		System.out.println("########### lista coef "+listaPorCoef);
//		System.out.println("########### "+listaPorCoef.size());
		List<TuplaEspecieLista> listaFinal = listaPorCoef.stream().map((TuplaEspecieProbabilidad t) -> t.convertValueInList()).collect(Collectors.toList());
//		listaFinal.get(0).print();
//		listaFinal.get(1).print();
//		listaFinal.get(2).print();
		//		System.out.println("########### lista final "+listaFinal);
//		System.out.println("########### "+listaFinal.size());
		
		return listaFinal;
	}
		
//	public List<Tupla> buscarAux(){
//		lsResult = this.tuplasPorCoeficiente(listDB);
//		return this.listaFinal(lsResult);
//		
//	}
//	
//	private List<Tupla> listaFinal(List<Tupla> ls){
//		List<Tupla> lsFinal = new ArrayList<>();
//		for(Tupla tupla : ls){
//			lsFinal.add(mkTuplaWithList(tupla.getKey(), mkList(indice, tupla.getValue())));
//			indice++;
//		}
//		
//		return lsFinal;
//	}
//	
//	private Tupla mkTuplaWithList(Especie k, List<Integer> v){
//		return new Tupla(k,v);
//	}
//	
//	private List<Tupla> tuplasPorCoeficiente(List<Tupla> listDB){
//		List<Tupla> result = new ArrayList<>();
//		
//		for(Tupla tupla : this.listDB){
//			result.add(mkTupla(tupla.getKey(), tupla.getValue()*coeficiente));
//		}
//		return result;
//	}
//	
//	private Tupla mkTupla(Especie k, float v){
//		return new Tupla(k,v);
//	}
//	
//	private List<Integer> mkList(Integer begin, float end){
//		List<Integer> ls = new ArrayList<>();
//		while(begin <= end){
//			ls.add((int) begin);
//			begin++;
//		}
//		return ls;
//	}
	
	private int getARandom(){
		return this.generarRandom(this.convertList(listDB));
	}

	/**
	 * Dada una lista de {@link Tupla} devuelve un número random
	 * @param lsTupla - lista de {@link Tupla)} ({@link Especie}, List<Integer)
	 * @return int - el size total de todas las listas sumadas
	 */
	private int generarRandom(List<TuplaEspecieLista> lsTupla){
		
//		int result = 0;
		
//		for(TuplaEspecieLista tupla:lsTupla) {
//			
//			result = result + tupla.getValue().size();
//		}
		
		lsTupla.stream().
				map((TuplaEspecieLista t) -> randomNumber = randomNumber + t.getValue().size()).
				collect(Collectors.toList()).get(0);
		return new Random().nextInt(randomNumber);
	}
}
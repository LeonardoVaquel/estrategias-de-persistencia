package ar.edu.unq.epers.bichomon.backend.model.buscador;

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

	private Integer indice;
	private Bicho bichoEncontrado;
	private int randomNumber;
	private List<TuplaEspecieProbabilidad> listDB;
	private Integer coeficiente;
	
	public Buscador(List<TuplaEspecieProbabilidad> lsDB){
		this.indice 		= 0;
		this.listDB 		= lsDB;
		this.coeficiente	= 100;
	};
	
	public Bicho buscar() {
		return this.buscar(getARandom());
	}
	
	/**
	 * 
	 * @param n Es un entero que viene del Random.
	 * @return Un {@link Bicho} de la especie que salio por el numero Random.
	 */
	public Bicho buscar(Integer n){
		List<TuplaEspecieLista> ls = this.convertList(this.listDB);
		Especie especie = encontrarEspecie(ls, n); // Se busca la especie segun el random.
		bichoEncontrado = new Bicho(especie); // Se crea el bicho que se encontro.
		return bichoEncontrado;
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
		List<TuplaEspecieLista> listaFinal = listaPorCoef.stream().
														map((TuplaEspecieProbabilidad t) -> t.convertValueInList(indice)).
														collect(Collectors.toList());		
		return listaFinal;
	}
			
	private int getARandom(){
		return this.generarRandom(this.convertList(listDB));
	}

	/**
	 * Dada una lista de {@link Tupla} devuelve un número random
	 * @param lsTupla - lista de {@link Tupla)} ({@link Especie}, List<Integer)
	 * @return int - el size total de todas las listas sumadas
	 */
	private int generarRandom(List<TuplaEspecieLista> lsTupla){
		lsTupla.stream().forEach((TuplaEspecieLista t)-> randomNumber =+ t.getValue().size());
		return new Random().nextInt(randomNumber);
	}

	public Bicho getBichoEncontrado() {
		return bichoEncontrado;
	}

	public void setBichoEncontrado(Bicho bichoEncontrado) {
		this.bichoEncontrado = bichoEncontrado;
	}

	public int getRandomNumber() {
		return randomNumber;
	}

	public void setRandomNumber(int randomNumber) {
		this.randomNumber = randomNumber;
	}

	public List<TuplaEspecieProbabilidad> getListDB() {
		return listDB;
	}

	public void setListDB(List<TuplaEspecieProbabilidad> listDB) {
		this.listDB = listDB;
	}

	public Integer getCoeficiente() {
		return coeficiente;
	}

	public void setCoeficiente(Integer coeficiente) {
		this.coeficiente = coeficiente;
	}
}
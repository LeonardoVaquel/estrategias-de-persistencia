package ar.edu.unq.epers.bichomon.backend.model.utils;

import java.util.Random;

/**
 * BichomonRandom es una clase que adapta el comportamiento de la clase Random	
 * @author santiago
 */
public class BichomonRandom {

	private Random random;
	
	public BichomonRandom() {
		
		this.random = new Random();
	}

	public Random getRandom() {
		return random;
	}

	public void setRandom(Random random) {
		this.random = random;
	}
	
	public int nextInt(Integer n) {
		return this.random.nextInt(n);
	}
	
}

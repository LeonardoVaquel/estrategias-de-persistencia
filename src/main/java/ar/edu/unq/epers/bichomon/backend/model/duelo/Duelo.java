package ar.edu.unq.epers.bichomon.backend.model.duelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.mockito.internal.matchers.And;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;

public class Duelo {// Tiene que devolver un ResultadoCombate con el ganador 
					// y el resultado de cada una de los ataques.

	private Bicho ganador;
	private Bicho retador;
	private Bicho retado;
	private double danioRetador;
	private double danioRetado;
	
	public Duelo(Bicho retador, Bicho retado){
		this.retador 		= retador;
		this.retado	 		= retado;
		this.ganador 		= null;
		this.danioRetador 	= 0;
		this.danioRetado 	= 0;
	}
	
	public Bicho iniciarDuelo(){
//		List<Integer> turnos = generarTurnos(10);
		Integer turno	= 0;
		
		while(noHayGanador() && turno < 10){
			this.ataqueRival();
			this.verSiHayGanador();
			this.ataqueRetado();
			this.verSiHayGanador();
			turno++;
		}
		if(noHayGanador()){
			this.decidirUnGanador();
		}
		
		ganador.nuevaVictoria();
		return ganador;
	}
	
	private void ataqueRival() {
		danioRetador = danioRetador + retador.getEnergia() * 1; // 1 debe ser un random(0.5,1)		
	}
	
	private void ataqueRetado(){
		danioRetado = danioRetado + retado.getEnergia() * 1;
	}

	private void verSiHayGanador(){
		if(danioRetado > retador.getEnergia()){
			ganador = retado;
		}else{
			if(danioRetador > retado.getEnergia()){
				ganador = retador;
			}
		}
	}
	
	private void decidirUnGanador(){
		if(retador.getEnergia() - danioRetado > retado.getEnergia() - danioRetador){
			ganador = retador;
		}else{
			ganador = retado;
		}
	}
	
	private List<Integer> generarTurnos(Integer cantTurnos){
		List<Integer> turnos = new ArrayList<Integer>();
		Integer i = 0;
		while(i < cantTurnos){
			turnos.add(i);
			i++;
		}
		return turnos;
	}
	
	private boolean noHayGanador(){
		return ganador == null;
	}
	
	public Bicho getGanador(){
		return ganador;
	}
}

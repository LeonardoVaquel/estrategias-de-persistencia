package ar.edu.unq.epers.bichomon.backend.model.duelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Dojo;

/**
 * El {@link Duelo} es una modalidad del juego que funciona
 * en los {@link Dojo}. Su propósito es que dos {@link Bicho}
 * luchen en 10 turnos y el ganador, será el que se quede en el {@link Dojo}.
 * 
 * @author Leonardo
 */

@Entity
public class Duelo {// Tiene que devolver un ResultadoCombate con el ganador 
					// y el resultado de cada una de los ataques.
	@Column
	private Bicho ganador;
	private Bicho retador;
	private Bicho retado;
	private Integer danioRetador;
	private Integer danioRetado;
	private ResultadoCombate combatResult;
	
	public Duelo(Bicho retador, Bicho retado){
		this.retador 		= retador;
		this.retado	 		= retado;
		this.ganador 		= null;
		this.danioRetador 	= 0;
		this.danioRetado 	= 0;
		this.combatResult	= new ResultadoCombate();
	}
	
	/**
	 * @return El {@link ResultadoCombate} con el {@link Bicho} ganador del duelo.
	 * Todo dependerá de la energía que tiene cada {@link Bicho}.
	 * Si tienen la misma cantidad de energia, gana el {@link Bicho}
	 * que es retado.
	 */
	public ResultadoCombate iniciarDuelo(){
//		List<Integer> turnos = generarTurnos(10);
		Integer turno	= 0;
		
		while(noHayGanador() && turno < 10){
			this.ataqueRival();
			this.verSiHayGanador();
			this.ataqueRetado();
			this.verSiHayGanador();
			this.agregarTurno();
			turno++;
		}
		if(noHayGanador()){
			this.decidirUnGanador();
		}
		
		ganador.nuevaVictoria();
		return combatResult;
	}
	
	private void ataqueRival() {
		danioRetador = danioRetador + retador.getEnergia() * 1; // 1 debe ser un random(0.5,1)		
	}
	
	private void ataqueRetado(){
		danioRetado = danioRetado + retado.getEnergia() * 1;	// El 1 también debe ser un rando(0.5,1)
	}

	private void verSiHayGanador(){
		if(danioRetado > retador.getEnergia()){
			combatResult.setBichoGanador(retado);
			combatResult.setEntrenadorGanador(retado.getOwner());
			combatResult.setBichoPerdedor(retador);
			ganador = retado;
		}else{
			if(danioRetador > retado.getEnergia()){
				combatResult.setBichoGanador(retador);
				combatResult.setEntrenadorGanador(retador.getOwner());
				combatResult.setBichoPerdedor(retado);
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
	
	private boolean noHayGanador(){
		return ganador == null;
	}
	
	/**
	 * @return El {@link Bicho} ganador.
	 * Siempre se debe usar después de haberse dado un {@link Duelo}.
	 */
	public Bicho getGanador(){
		return ganador;
	}
	
	private Integer energiaRetador(){
		return retador.getEnergia() - danioRetado;
	}
	
	private Integer energiaRetado(){
		return retado.getEnergia() - danioRetador;
	}
	
	private void agregarTurno(){
		Turno turno = new Turno(retador, retado, danioRetador, danioRetado, this.energiaRetador(), this.energiaRetado());
		combatResult.agregarTurno(turno);
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
}

package ar.edu.unq.epers.bichomon.backend.model.duelo;

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
//		try{
//			if(this.estaEnDojo(retador)){
//				this.comenzarCombate();
//			}
//		}catch(UbicacionException e){
//			System.out.println(e.getMessage());
//		}finally{
//			return combatResult;
//		}
		
		this.comenzarCombate();
		return combatResult;
	}
		
	/**
	 * Se inicia un combate entre un {@link Bicho} retador y un {@link Bicho} retado que dura a lo sumo 10 turnos.
	 * El ganador obtiene 3 puntos de energia, mientras que el perdedor obtiene 2 puntos de energia.
	 * El ganador obtiene una victoria.
	 * @return ResultadoCombate - el resultado del combate con el ganador, el perdedor, todos los turnos
	 */
	private ResultadoCombate comenzarCombate(){
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
		retador.aumentarEnergiaEn(3);
		retado.aumentarEnergiaEn(2);
		
		return combatResult;
	}

	/**
	 * Se calcula el dañio del Bicho retador.
	 */
	private void ataqueRival() {
		danioRetador = danioRetador + retador.getEnergia() * 1; // 1 debe ser un random(0.5,1)		
	}
	
	/**
	 * Se calcula el dañio del Bicho retado.
	 */
	private void ataqueRetado(){
		danioRetado = danioRetado + retado.getEnergia() * 1;	// El 1 también debe ser un random(0.5,1)
	}

	/**
	 * Se verifica si ya hay un Bicho ganador del Duelo.
	 */
	private void verSiHayGanador(){
		if(danioRetado > retador.getEnergia()){
			this.refreshCombat(retado, retador);
			ganador = retado;
		}else{
			if(danioRetador > retado.getEnergia()){
				this.refreshCombat(retador, retado);
				ganador = retador;
			}
		}
	}
	
	/**
	 * Se actualiza el resultado del combate, guardando del Duelo, el Bicho ganador,
	 * el Perdedor y el Entrenador ganador.
	 * @param ganador {@link Bicho}
	 * @param perdedor {@link Bicho}
	 */
	private void refreshCombat(Bicho ganador, Bicho perdedor){
		combatResult.setBichoGanador(ganador);
		combatResult.setEntrenadorGanador(ganador.getOwner()); // No haria falta pasar un Entrenador, si ya lo contiene el Bicho Ganador y Perdedor respectivamente.
		combatResult.setBichoPerdedor(perdedor);
	}
	
	/**
	 * Se decide un ganador dependiendo el dañio que recibe cada Bicho y su energia.
	 */
	private void decidirUnGanador(){
		if(retador.getEnergia() - danioRetado > retado.getEnergia() - danioRetador){
			ganador = retador;
		}else{
			ganador = retado;
		}
	}
	
	/**
	 * @return un boolean indicando si ya hay un ganador en del Duelo.
	 */
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
	
	/**
	 * Calcula la energia actual del Bicho retador.
	 * @return un Integer
	 */
	private Integer energiaRetador(){
		return retador.getEnergia() - danioRetado;
	}
	
	/**
	 * Calcula la energia actual del Bicho retado
	 * @return un Integer
	 */
	private Integer energiaRetado(){
		return retado.getEnergia() - danioRetador;
	}
	
	/**
	 * Se registra un nuevo Turno en el resultado del combate.
	 * Quedando guardado el Bicho retado y retador, el daño que hicieron cada uno en ese turno,
	 * y la energia actual de cada Bicho.
	 */
	private void agregarTurno(){
		Turno turno = new Turno(retador, retado, danioRetador, danioRetado, this.energiaRetador(), this.energiaRetado());
		combatResult.agregarTurno(turno);
	}
	
}

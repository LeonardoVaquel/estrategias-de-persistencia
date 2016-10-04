package ar.edu.unq.epers.bichomon.backend.model.duelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;

/**
 * 
 * @author Leonardo
 *
 */
public class ResultadoCombate {

	private Entrenador entrenadorGanador;
	private Bicho bichoGanador;
	private Bicho bichoPerdedor;
	private List<Turno> turnos;
	
	public ResultadoCombate(){
		this.turnos = new ArrayList<>();
	};
	
	public ResultadoCombate(Entrenador entrenadorGanador, Bicho bugWinner, Bicho bugLoser){
		this.entrenadorGanador 	= entrenadorGanador;
		this.bichoGanador		= bugWinner;
		this.bichoPerdedor		= bugLoser;
		this.turnos				= new ArrayList<>();
	};
	
	public void agregarTurno(Turno newTurno){
		this.turnos.add(newTurno);
	}

	public Entrenador getEntrenadorGanador() {
		return entrenadorGanador;
	}

	public void setEntrenadorGanador(Entrenador entrenadorGanador) {
		this.entrenadorGanador = entrenadorGanador;
	}

	public Bicho getBichoGanador() {
		return bichoGanador;
	}

	public void setBichoGanador(Bicho bichoGanador) {
		this.bichoGanador = bichoGanador;
	}

	public Bicho getBichoPerdedor() {
		return bichoPerdedor;
	}

	public void setBichoPerdedor(Bicho bichoPerdedor) {
		this.bichoPerdedor = bichoPerdedor;
	}

	public List<Turno> getTurnos() {
		return turnos;
	}

	public void setTurnos(List<Turno> turnos) {
		this.turnos = turnos;
	}

}

package ar.edu.unq.epers.bichomon.backend.model.duelo;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;

/**
 * La Clase Turno representa un Turno del Duelo, conteniendo
 * al Bicho retador y retado, el daño que hizo cada uno y su energia actual.
 * 
 * @author Leonardo
 *
 */

public class Turno {

	private Bicho retador;
	private Bicho retado;
	private Integer danioRetador;
	private Integer danioRetado;
	private Integer energinaRetador;
	private Integer energiaRetado;

	public Turno(Bicho retador, Bicho retado, Integer danioRetador, Integer danioRetado, Integer energiaRetador, Integer energiaRetado){
		this.retado			= retado;
		this.retador		= retador;
		this.danioRetado	= danioRetado;
		this.danioRetador	= danioRetador;
		this.energiaRetado	= energiaRetado;
		this.energinaRetador= energiaRetador;
	}

	public Bicho getRetador() {
		return retador;
	}

	public void setRetador(Bicho retador) {
		this.retador = retador;
	}

	public Bicho getRetado() {
		return retado;
	}

	public void setRetado(Bicho retado) {
		this.retado = retado;
	}

	public Integer getDanioRetador() {
		return danioRetador;
	}

	public void setDanioRetador(Integer danioRetador) {
		this.danioRetador = danioRetador;
	}

	public Integer getDanioRetado() {
		return danioRetado;
	}

	public void setDanioRetado(Integer danioRetado) {
		this.danioRetado = danioRetado;
	}

	public Integer getEnerginaRetador() {
		return energinaRetador;
	}

	public void setEnerginaRetador(Integer energinaRetador) {
		this.energinaRetador = energinaRetador;
	}

	public Integer getEnergiaRetado() {
		return energiaRetado;
	}

	public void setEnergiaRetado(Integer energiaRetado) {
		this.energiaRetado = energiaRetado;
	};
	
	
}

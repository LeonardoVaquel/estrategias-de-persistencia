package ar.edu.unq.epers.bichomon.backend.model.especie;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import ar.edu.unq.epers.bichomon.backend.model.evolucion.CriterioEvolucion;

/**
 * Representa una {@link Especie} de bicho que consta de un nombre, una {@link Especie} raíz,
 * una {@link Especie} evolución, una lista de {@link CriterioEvolucion}, una altura, peso, un
 * tipo {@link TipoBicho}, una cantidad de energía inicial, un String que representa una url de foto
 * y una cantidad de bichos de la {@link Especie} actual. 
 * 
 * @author santiago
 */
@Entity
public class Especie {

	@Id
	private String nombre;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Especie raiz;

	@OneToOne(cascade = CascadeType.ALL)
	private Especie evolucion;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<CriterioEvolucion> criteriosDeEvolucion;
	
	private int altura;
	private int peso;
	
	@Column(name="tipoBicho")
	@Enumerated(EnumType.STRING)
	private TipoBicho tipo;

	private int energiaInicial;
	
	@Column(name="urlFoto", length=100000)
	private String urlFoto;
	
	private int cantidadBichos;
	
	public Especie(){
	}
	
	public Especie(String nombre, TipoBicho tipo) {
		this.nombre = nombre;
		this.tipo = tipo;
	}
	
	public Especie(String nombre, Especie raiz, Especie evolucion, 
			List<CriterioEvolucion> criteriosEvolucion, int altura, int peso, TipoBicho tipo, 
			int energiaInicial, String urlFoto, int cantidadBichos) {
		this.nombre = nombre;
		this.raiz   = raiz;
		this.evolucion = evolucion;
		this.criteriosDeEvolucion = criteriosEvolucion;
		this.altura = altura;
		this.peso = peso;
		this.tipo = tipo;
		this.energiaInicial = energiaInicial;
		this.urlFoto = urlFoto;
		this.cantidadBichos = cantidadBichos;
	}
	
	public Especie(String nombre, Especie evolucion, 
			List<CriterioEvolucion> criteriosEvolucion, int altura, int peso, TipoBicho tipo, 
			int energiaInicial, String urlFoto, int cantidadBichos) {
		this.nombre = nombre;
		this.evolucion = evolucion;
		this.criteriosDeEvolucion = criteriosEvolucion;
		this.altura = altura;
		this.peso = peso;
		this.tipo = tipo;
		this.energiaInicial = energiaInicial;
		this.urlFoto = urlFoto;
		this.cantidadBichos = cantidadBichos;
	}	

	/**
	 * @return el nombre de la especie (por ejemplo: Perromon)
	 */
	public String getNombre() {
		return this.nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * @return - la {@link Especie} raíz para todos los bichos de ésta especie
	 */
	public Especie getRaiz() {
		return raiz;
	}
	
	public void setRaiz(Especie raiz) {
		this.raiz = raiz;
	}
	
	/**
	 * @return - la {@link Especie} evolución para todos los bichos de ésta especie
	 */
	public Especie getEvolucion() {
		return this.evolucion;
	}
	
	public void setEvolucion(Especie evolucion) {
		this.evolucion = evolucion;
	}
	
	/**
	 * @return - los criterios de evolucion para todos los bichos de ésta especie
	 */
	public List<CriterioEvolucion> getCriteriosDeEvolucion() {
		return this.criteriosDeEvolucion;
	}
	
	public void setCriteriosDeEvolucion(List<CriterioEvolucion> criteriosDeEvolucion) {
		this.criteriosDeEvolucion = criteriosDeEvolucion;
	}
	
	/**
	 * @return la altura de todos los bichos de esta especie
	 */
	public int getAltura() {
		return this.altura;
	}
	public void setAltura(int altura) {
		this.altura = altura;
	}
	
	/**
	 * @return el peso de todos los bichos de esta especie
	 */
	public int getPeso() {
		return this.peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	
	/**
	 * @return una url que apunta al un recurso imagen el cual será
	 * utilizado para mostrar un thumbnail del bichomon por el frontend.
	 */
	public String getUrlFoto() {
		return this.urlFoto;
	}
	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}
	
	/**
	 * @return la cantidad de energia de poder iniciales para los bichos
	 * de esta especie.
	 */
	public int getEnergiaInicial() {
		return this.energiaInicial;
	}
	public void setEnergiaIncial(int energiaInicial) {
		this.energiaInicial = energiaInicial;
	}

	/**
	 * @return el tipo de todos los bichos de esta especie
	 */
	public TipoBicho getTipo() {
		return this.tipo;
	}
	public void setTipo(TipoBicho tipo) {
		this.tipo = tipo;
	}
	
	/**
	 * @return la cantidad de bichos que se han creado para esta
	 * especie.
	 */
	public int getCantidadBichos() {
		return this.cantidadBichos;
	}
	public void setCantidadBichos(int i) {
		this.cantidadBichos = i;
	}
	
	/**
	 * Se incrementa la cantidad de bichos en 1
	 */
	public void incrementarCantidad() {
		this.setCantidadBichos(this.cantidadBichos + 1);
	}
	
}

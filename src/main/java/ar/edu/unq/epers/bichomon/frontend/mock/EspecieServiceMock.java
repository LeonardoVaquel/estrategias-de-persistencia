package ar.edu.unq.epers.bichomon.frontend.mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.especie.TipoBicho;
import ar.edu.unq.epers.bichomon.backend.service.especie.EspecieService;

/**
 * Esta es una implementacion mock de {@link EspecieService}
 * 
 * Esto es codigo de frontend, no deberian tocar nada de aca.
 */
public class EspecieServiceMock implements EspecieService {

	private static Map<String, Especie> DATA = new HashMap<>();
	
	static {
		for (int i = 0; i < 10; i++) {
			Especie bohr = new Especie("Bohrmon" + i, TipoBicho.AGUA);
			bohr.setAltura(180);
			bohr.setPeso(75);
			bohr.setEnergiaIncial(100);
			bohr.setUrlFoto("https://upload.wikimedia.org/wikipedia/commons/5/5e/Niels_Bohr_Date_Unverified_LOC.jpg");
			DATA.put(bohr.getNombre(), bohr);
			
			Especie schrodinger = new Especie("Schrodingermon" + i, TipoBicho.AGUA);
			schrodinger.setAltura(170);
			schrodinger.setPeso(69);
			schrodinger.setEnergiaIncial(300);
			schrodinger.setUrlFoto("https://upload.wikimedia.org/wikipedia/commons/thumb/2/2e/Erwin_Schr%C3%B6dinger_(1933).jpg/220px-Erwin_Schr%C3%B6dinger_(1933).jpg");
			DATA.put(schrodinger.getNombre(), schrodinger);
			
			Especie tesla = new Especie("Teslamon" + i, TipoBicho.ELECTRICIDAD);
			tesla.setAltura(150);
			tesla.setPeso(55);
			tesla.setEnergiaIncial(5000);
			tesla.setUrlFoto("https://upload.wikimedia.org/wikipedia/commons/7/79/Tesla_circa_1890.jpeg");
			DATA.put(tesla.getNombre(), tesla);
		}
	}
	
	@Override
	public void crearEspecie(Especie especie) {
		DATA.put(especie.getNombre(), especie);
	}

	@Override
	public Especie getEspecie(String nombreEspecie) {
		return DATA.get(nombreEspecie);
	}

	@Override
	public List<Especie> getAllEspecies() {
		return new ArrayList<>(DATA.values());
	}

	@Override
	public Bicho crearBicho(String nombreEspecie, String nombreBicho) {
		Especie especie = DATA.get(nombreEspecie);
		especie.setCantidadBichos(especie.getCantidadBichos()+1);
		return new Bicho(especie, nombreBicho);
	}

}

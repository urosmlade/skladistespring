package Kum.ctrl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Kum.jpa.Racun;
import Kum.jpa.Stavkaracuna;
import Kum.reps.RacunRepository;
import Kum.reps.StavkaracunaRepository;

@RestController
public class StavkaRacunaRestController {
	
	@Autowired
	private StavkaracunaRepository stavkaRacunaRepository;
	
	
	@Autowired
	private RacunRepository racunRepository;
	
	
	@CrossOrigin
	@GetMapping("stavkaracuna/{id}")
	public Collection<Stavkaracuna> stavkeZaRacun(@PathVariable Integer id) {
		Racun predracun = racunRepository.getOne(id);
		return stavkaRacunaRepository.findByRacunBeanOrderById(predracun);
	}

	@CrossOrigin
	@PostMapping("stavkaracuna")
	public ResponseEntity<HttpStatus> dodajStavkuRacuna(@RequestBody Stavkaracuna stavkaRacuna){
		int stopaPdv;
		int vrednostPdv;
		int vrednostBezPdv;
		int vrednostSaPdv;
		
		stopaPdv = 20;
		vrednostBezPdv = stavkaRacuna.getKolicina() * stavkaRacuna.getProizvodBean().getCena();
		
		vrednostPdv = vrednostBezPdv * stopaPdv / 100;
		
		vrednostSaPdv = (vrednostBezPdv + vrednostPdv);
		
		stavkaRacuna.setStopapdv(stopaPdv);
		stavkaRacuna.setVrednostbezpdv(vrednostBezPdv);
		stavkaRacuna.setVrednostpdv(vrednostPdv);
		stavkaRacuna.setVrednostsapdv(vrednostSaPdv);
		
		stavkaRacunaRepository.save(stavkaRacuna);
		
		
		/*
		 * Samo se sa predracuna skida sa stanja kako bi odma imao uvid u skladiste. Ovo mozda nije dobro. Razmisli o ovome
		 * Proizvod proizvod= stavkaRepository.getOne(stavkaRacuna.getProizvodBean().getId());
		
		int kolicinaDaSeSmanji = stavkaRacuna.getKolicina();
		proizvod.setKolicina(proizvod.getKolicina()-kolicinaDaSeSmanji);
		stavkaRepository.save(proizvod);
		*/
		return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
	}
	
	
	
	@CrossOrigin
	@GetMapping("cenaracuna/{id}")
	public Collection<Integer> ukupnacena(@PathVariable Integer id) {
		return stavkaRacunaRepository.VrednostSaPdv(id);
	}
	
	
	@CrossOrigin
	@GetMapping("cenaracunapomesecima/{id}")
	public Collection<Object> ukupnacenapomesecimapokorisniku(@PathVariable Integer id) {
		return stavkaRacunaRepository.VrednostSaPdvPoMesecima(id);
	}
	
	@CrossOrigin
	@GetMapping("ukupnazaradaracuna")
	public Integer ukupnaZarada() {
		return stavkaRacunaRepository.ukupnazarada();
	}
	
	
	@CrossOrigin
	@GetMapping("ukupnazaradapomesecimaracun")
	public Collection<Object> ukupnacenapomesecima(){
		return stavkaRacunaRepository.vrednostSaPdvPoMesecimaUkupno();
	}
	
}



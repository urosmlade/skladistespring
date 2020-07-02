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

import Kum.jpa.Kupac;
import Kum.jpa.Predracun;
import Kum.jpa.Racun;
import Kum.reps.KupacRepository;
import Kum.reps.PoslodavacRepository;
import Kum.reps.PredracunRepository;
import Kum.reps.RacunRepository;

@RestController
public class RacunRestController {
	
	@Autowired
	private RacunRepository racunRepository;
	
	@Autowired
	private PoslodavacRepository poslodavacRepository;
	
	@Autowired
	private KupacRepository kupacRepository;
	
	@Autowired
	private PredracunRepository predracunRepository;

	
	@CrossOrigin
	@GetMapping("racun")
	public Collection<Racun>getAll(){
		return racunRepository.findAllByOrderByIdAsc();
	}
	
	@CrossOrigin
	@GetMapping("racun/{id}")
	public Racun getOne(@PathVariable Integer id) {
		return racunRepository.getOne(id);
	}
	
	
	@CrossOrigin
	@PostMapping("racun")
	public ResponseEntity<HttpStatus> dodajRacun(@RequestBody Predracun predracun){
		Racun racun = new Racun();
		racun.setNaziv(predracun.getNaziv());
		racun.setPlaceno("Da");
		racun.setDatumizdavanjaracuna(predracun.getDatumizdavanjaracuna());
		racun.setDatumprometadobara(predracun.getDatumprometadobara());
		racun.setKupacBean(predracun.getKupacBean());
		racun.setPredracunBean(predracun);
		racun.setRacunotpremnicabroj(predracun.getRacunotpremnicabroj());
		racun.setPoslodavacBean(poslodavacRepository.getOne(1));
		
		
		racun.setStavkaracunas(predracun.getStavkapredracunas());

		
		predracun.setPlaceno("Da");
		
		predracunRepository.save(predracun);
		racunRepository.save(racun);
		return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
	}
	
	@CrossOrigin
	@GetMapping("racunkupac/{id}")
	public Collection<Racun> sviRacuniKupca (@PathVariable Integer id) {
		Kupac kupac = kupacRepository.getOne(id);
		return racunRepository.findByKupacBean(kupac);
	}
	
	
}


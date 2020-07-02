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
import Kum.reps.KupacRepository;
import Kum.reps.PoslodavacRepository;
import Kum.reps.PredracunRepository;

@RestController
public class PredracunRestController {

	@Autowired
	private PredracunRepository predracunRepository;
	
	@Autowired
	private PoslodavacRepository poslodavacRepository;
	
	@Autowired
	private KupacRepository kupacRepository;
	
	@CrossOrigin
	@GetMapping("predracun")
	public Collection<Predracun> getAll(){
		return predracunRepository.findAllByOrderByIdAsc();
	}
	
	@CrossOrigin
	@GetMapping("neplacenipredracun")
	public Collection<Predracun> getAllNeplaceno(){
		return predracunRepository.findAllNeplacene();
	}
	
	
	@CrossOrigin
	@GetMapping("predracun/{id}")
	public Predracun getOne(@PathVariable Integer id) {
		return predracunRepository.getOne(id);
	}
	
	@CrossOrigin
	@PostMapping("predracun")
	public ResponseEntity<HttpStatus> dodajPredracun (@RequestBody Predracun predracun){
		predracun.setPlaceno("Ne");
		predracun.setPoslodavacBean(poslodavacRepository.getOne(1));
		predracunRepository.save(predracun);
		return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
	}
	
	@CrossOrigin
	@GetMapping("predracunkupac/{id}")
	public Collection<Predracun> sviPredracuniKupca (@PathVariable Integer id){
		Kupac kupac = kupacRepository.getOne(id);
		return predracunRepository.findByKupacBean(kupac);
	}
	
}

package Kum.ctrl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Kum.jpa.Kupac;
import Kum.reps.KupacRepository;

@RestController
public class KupacRestController {
	
	@Autowired
	private KupacRepository kupacRepository;
	
	@CrossOrigin
	@GetMapping("kupac")
	public Collection<Kupac>getAll(){
		return kupacRepository.findAllByOrderByIdAsc();
	}
	
	@CrossOrigin
	@GetMapping("kupac/{id}")
	public Kupac getKupac (@PathVariable Integer id) {
		return kupacRepository.getOne(id);
	}
	
	@CrossOrigin
	@PostMapping("kupac")
	public ResponseEntity<HttpStatus> addKupac(@RequestBody Kupac kupac){
		kupacRepository.save(kupac);
		return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
	}
	
	
	@CrossOrigin
	@DeleteMapping("kupac/{id}")
	public ResponseEntity<HttpStatus> deleteKupac (@PathVariable Integer id){
		kupacRepository.deleteById(id);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	
	@CrossOrigin
	@GetMapping("ukupnokupaca")
	public Integer ukupnoKupaca() {
		return kupacRepository.ukupnokupaca();
	}
}

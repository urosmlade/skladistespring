package Kum.ctrl;

import java.util.Collection;

import javax.persistence.OrderBy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Kum.jpa.Kategorija;
import Kum.jpa.Proizvod;
import Kum.reps.KategorijaRepository;
import Kum.reps.ProizvodRepository;

@RestController
public class ProizvodRestController {
	
	@Autowired
	private KategorijaRepository kategorijaRepository;
	
	@Autowired
	private ProizvodRepository proizvodRepository;
	
	
	@CrossOrigin
	@GetMapping("proizvod")
	public Collection<Proizvod> getAll() {
		return proizvodRepository.findAllByOrderById();
	}
	
	@CrossOrigin
	@GetMapping("proizvod/{id}")
	public Proizvod getOne(@PathVariable Integer id) {
		return proizvodRepository.getOne(id);
	}
	
	
	@CrossOrigin
	@GetMapping("proizvodi/{id}")
	public Collection<Proizvod>getProizvodByKategorija (@PathVariable Integer id){
		Kategorija kategorija = kategorijaRepository.getOne(id);
		return proizvodRepository.findByKategorijaBeanOrderById(kategorija);
	}
	
	
	@CrossOrigin
	@OrderBy("id")
	@PutMapping("proizvod/{id}")
	public ResponseEntity<HttpStatus> updateStavku (@PathVariable Integer id, @RequestBody Proizvod proizvod){
		if(proizvodRepository.existsById(id)){
			int kolicina;
			int gramaza;
			kolicina = proizvod.getKolicina();
			gramaza = proizvod.getGramaza();
			proizvod.setUkupnaGramaza(kolicina * gramaza);
			proizvod.setId(id);
			proizvodRepository.save(proizvod);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);		
	}
		
	
	@CrossOrigin
	@PostMapping("proizvod")
	public ResponseEntity<HttpStatus> addStavka(@RequestBody Proizvod proizvod){
		int kolicina;
		int gramaza;
		kolicina = proizvod.getKolicina();
		gramaza = proizvod.getGramaza();
		proizvod.setUkupnaGramaza(kolicina * gramaza);
		proizvodRepository.save(proizvod);
		return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
	}
	
	@CrossOrigin
	@DeleteMapping("proizvod/{id}")
	public ResponseEntity<HttpStatus> deleteStavka (@PathVariable Integer id){
		if(proizvodRepository.existsById(id)) {
			proizvodRepository.deleteById(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}
	
	
	@CrossOrigin
	@GetMapping("ukupnoproizvoda")
	public Integer ukupnoStavki() {
		return proizvodRepository.ukupnoStavki();
	}
	
	
	@CrossOrigin
	@GetMapping("ukupnorazlicitihproizvoda")
	public Integer ukupnoRazlicitihStavki() {
		return proizvodRepository.ukupnoRazlicitihStavki();
	}
	
	
	
}


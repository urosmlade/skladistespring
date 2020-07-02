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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Kum.jpa.Kategorija;
import Kum.reps.KategorijaRepository;

@RestController
public class KategorijaRestController {

	@Autowired
	private KategorijaRepository kategorijaRespository;
	
	@CrossOrigin
	@GetMapping("kategorija")
	public Collection<Kategorija>getAll(){
		return kategorijaRespository.findAll();
	}
	
	@CrossOrigin
	@PostMapping("kategorija")
	public ResponseEntity<HttpStatus>addKategorija(@RequestBody Kategorija kategorija){
		kategorijaRespository.save(kategorija);
		return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
	} 
	
	@CrossOrigin
	@PutMapping("kategorija/{id}")
	public ResponseEntity<HttpStatus>updateKategorija (@RequestBody Kategorija kategorija, @PathVariable Integer id){
		if (kategorijaRespository.existsById(id)) {
			kategorija.setId(id);
			kategorijaRespository.save(kategorija);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}
	
	@CrossOrigin
	@DeleteMapping("kategorija/{id}")
	public ResponseEntity<HttpStatus> deleteKategorija (@PathVariable Integer id){
		kategorijaRespository.deleteById(id);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
}

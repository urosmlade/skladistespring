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

import Kum.jpa.Predracun;
import Kum.jpa.Proizvod;
import Kum.jpa.Stavkapredracuna;
import Kum.reps.PredracunRepository;
import Kum.reps.ProizvodRepository;
import Kum.reps.StavkaPredracunaRepository;

@RestController
public class StavkaPredracunaRestController {

	@Autowired
	private StavkaPredracunaRepository stavkaPredracunaRepository;


	@Autowired
	private ProizvodRepository stavkaRepository;

	@Autowired
	private PredracunRepository racunRepository;


	@CrossOrigin
	@GetMapping("stavkapredracuna/{id}")
	public Collection<Stavkapredracuna> stavkeZaPredracun(@PathVariable Integer id) {
		Predracun predracun = racunRepository.getOne(id);
		return stavkaPredracunaRepository.findByPredracunBeanOrderById(predracun);
	}
	
	

	@CrossOrigin
	@PostMapping("stavkapredracuna")
	public ResponseEntity<HttpStatus> dodajStavkuFakture(@RequestBody Stavkapredracuna stavkapredracuna){
		int stopaPdv;
		int vrednostPdv;
		int vrednostBezPdv;
		int vrednostSaPdv;
		
		stopaPdv = 20;
		vrednostBezPdv = stavkapredracuna.getKolicina() * stavkapredracuna.getProizvodBean().getCena();
		
		vrednostPdv = vrednostBezPdv * stopaPdv / 100;
		
		vrednostSaPdv = (vrednostBezPdv + vrednostPdv);
		
		stavkapredracuna.setStopapdv(stopaPdv);
		stavkapredracuna.setVrednostbezpdv(vrednostBezPdv);
		stavkapredracuna.setVrednostpdv(vrednostPdv);
		stavkapredracuna.setVrednostsapdv(vrednostSaPdv);
		
		stavkaPredracunaRepository.save(stavkapredracuna);
		
		
		Proizvod proizvod= stavkaRepository.getOne(stavkapredracuna.getProizvodBean().getId());
		
		int kolicinaDaSeSmanji = stavkapredracuna.getKolicina();
		proizvod.setKolicina(proizvod.getKolicina()-kolicinaDaSeSmanji);
		stavkaRepository.save(proizvod);
		
		return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
	}



	@CrossOrigin
	@GetMapping("cenapredracuna/{id}")
	public Collection<Integer> ukupnacena(@PathVariable Integer id) {
		return stavkaPredracunaRepository.VrednostSaPdv(id);
	}


	@CrossOrigin
	@GetMapping("cenapredracunapomesecima/{id}")
	public Collection<Object> ukupnacenapomesecimapokorisniku(@PathVariable Integer id) {
		return stavkaPredracunaRepository.VrednostSaPdvPoMesecima(id);
	}

	@CrossOrigin
	@GetMapping("ukupnazaradapredracun")
	public Integer ukupnaZarada() {
		return stavkaPredracunaRepository.ukupnazarada();
	}

	
	@CrossOrigin
	@GetMapping("ukupnazaradapopredracunubezpdv/{id}")
	public Integer ukupnaZaradaPoPredracunuBezPdv(@PathVariable Integer id) {
		return stavkaPredracunaRepository.ukupnazaradapopredracunubezpdv(id);
	}
	

	
	@CrossOrigin
	@GetMapping("ukupnazaradapopredracunusapdv/{id}")
	public Integer ukupnaZaradaPoPredracunuSaPdv(@PathVariable Integer id) {
		return stavkaPredracunaRepository.ukupnazaradapopredracunusapdv(id);
	}
	
	
	
	@CrossOrigin
	@GetMapping("ukupnazaradapomesecimapredracun")
	public Collection<Object> ukupnacenapomesecima(){
		return stavkaPredracunaRepository.vrednostSaPdvPoMesecimaUkupno();
	}
	
	
	@CrossOrigin
	@GetMapping("ukupnopdvpopredracunu/{id}")
	public Integer ukupnopdvpopredracunu(@PathVariable Integer id){
		return stavkaPredracunaRepository.ukupnopdvpopredracunu(id);
	}
	
	
}


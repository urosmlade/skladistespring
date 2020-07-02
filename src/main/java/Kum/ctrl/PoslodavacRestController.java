package Kum.ctrl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import Kum.jpa.Poslodavac;
import Kum.reps.PoslodavacRepository;

@RestController
public class PoslodavacRestController {

	@Autowired
	private PoslodavacRepository poslodavacRepository;
	
	
	@CrossOrigin
	@GetMapping("poslodavac/{1}")
	public Poslodavac getOne() {
		return poslodavacRepository.getOne(1);
	}
}

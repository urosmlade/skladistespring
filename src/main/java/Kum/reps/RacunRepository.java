package Kum.reps;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import Kum.jpa.Kupac;
import Kum.jpa.Racun;

public interface RacunRepository extends JpaRepository<Racun, Integer> {
	
	Collection<Racun> findByKupacBean (Kupac k);

	Collection<Racun> findAllByOrderByIdAsc();
}

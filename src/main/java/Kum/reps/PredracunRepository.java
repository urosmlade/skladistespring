package Kum.reps;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import Kum.jpa.Kupac;
import Kum.jpa.Predracun;

public interface PredracunRepository extends JpaRepository<Predracun, Integer>{
	
	Collection<Predracun> findByKupacBean (Kupac kupac);
	
	Collection<Predracun> findAllByOrderByIdAsc();
	
	@Query("select p from Predracun p where placeno = 'Ne'")
	Collection<Predracun> findAllNeplacene();
}

package Kum.reps;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import Kum.jpa.Kategorija;
import Kum.jpa.Proizvod;

public interface ProizvodRepository extends JpaRepository<Proizvod, Integer> {
	
	Collection<Proizvod> findByKategorijaBeanOrderById (Kategorija k);
	
	Collection<Proizvod> findAllByOrderById();
	
	@Query("select SUM(p.kolicina) from Proizvod p")
	Integer ukupnoStavki();
	
	
	@Query("select count(p) from Proizvod p")
	Integer ukupnoRazlicitihStavki();
	
	
}


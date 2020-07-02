package Kum.reps;


import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import Kum.jpa.Kupac;

public interface KupacRepository extends JpaRepository<Kupac, Integer> {
	Collection<Kupac>  findAllByOrderByIdAsc();
	
	
	@Query("select count(k) from Kupac k")
	Integer ukupnokupaca();
	
}

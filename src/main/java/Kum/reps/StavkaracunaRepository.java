package Kum.reps;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import Kum.jpa.Racun;
import Kum.jpa.Stavkaracuna;

public interface StavkaracunaRepository extends JpaRepository<Stavkaracuna, Integer> {
	
	Collection<Stavkaracuna> findByRacunBeanOrderById (Racun p);

	@Query("select sum(vrednostsapdv) from Stavkaracuna sr where sr.racunBean.kupacBean.id = ?1")
	Collection<Integer> VrednostSaPdv(Integer f);
	
	
	@Query("select extract(month from sr.racunBean.datumizdavanjaracuna), sum(vrednostsapdv) from Stavkaracuna sr where sr.racunBean.kupacBean.id = ?1 group by extract(month from sr.racunBean.datumizdavanjaracuna)")
	Collection<Object> VrednostSaPdvPoMesecima(Integer f);
	
	
	@Query("select sum(sr.vrednostbezpdv) from Stavkapredracuna sr")
	Integer ukupnazarada();
	
	
	@Query("select extract(month from sr.racunBean.datumizdavanjaracuna), sum(vrednostbezpdv) from Stavkaracuna sr group by extract(month from sr.racunBean.datumizdavanjaracuna) order by extract(month from sr.racunBean.datumizdavanjaracuna) ")
	Collection<Object> vrednostSaPdvPoMesecimaUkupno();
	
	
}



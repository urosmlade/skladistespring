package Kum.reps;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import Kum.jpa.Predracun;
import Kum.jpa.Stavkapredracuna;

public interface StavkaPredracunaRepository extends JpaRepository<Stavkapredracuna, Integer>{
	
	Collection<Stavkapredracuna> findByPredracunBeanOrderById (Predracun p);
	
	//ukupna zarada
	
	
	//ukupna zarada po kupcu
	@Query("select sum(vrednostsapdv) from Stavkapredracuna spr where spr.predracunBean.kupacBean.id = ?1")
	Collection<Integer> VrednostSaPdv(Integer f);
	
	
	//ukupna zarada po kupcu po mesecima
	
	
	
	//ukupna zarada po predracunu bez pdv
	@Query("select sum(spr.vrednostbezpdv) from Stavkapredracuna spr where spr.predracunBean.id = ?1")
	Integer ukupnazaradapopredracunubezpdv(Integer i);
	
	
	
	//ukupna zarada po predracunu sa pdv
	@Query("select sum(spr.vrednostsapdv) from Stavkapredracuna spr where spr.predracunBean.id = ?1")
	Integer ukupnazaradapopredracunusapdv(Integer i);
	
	
	//ukupna zarada po predracunu koji imaju racun
	
	
	
	//ukupno pdv po predracunu
	@Query("select sum(spr.vrednostpdv) from Stavkapredracuna spr where spr.predracunBean.id = ?1")
	Integer ukupnopdvpopredracunu(Integer i);
	
	
	
	
	
	
	@Query("select extract(month from spr.predracunBean.datumizdavanjaracuna), sum(vrednostsapdv) from Stavkapredracuna spr where spr.predracunBean.kupacBean.id = ?1 and spr.predracunBean.placeno = 'Da' group by extract(month from spr.predracunBean.datumizdavanjaracuna)")
	Collection<Object> VrednostSaPdvPoMesecima(Integer f);
	
	
	@Query("select sum(spr.vrednostbezpdv) from Stavkapredracuna spr")
	Integer ukupnazarada();
	
	
	@Query("select extract(month from sr.predracunBean.datumizdavanjaracuna), sum(vrednostbezpdv) from Stavkapredracuna sr where sr.predracunBean.placeno = 'Da' group by extract(month from sr.predracunBean.datumizdavanjaracuna) order by extract(month from sr.predracunBean.datumizdavanjaracuna)")
	Collection<Object> vrednostSaPdvPoMesecimaUkupno();

}

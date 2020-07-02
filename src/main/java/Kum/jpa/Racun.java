package Kum.jpa;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the racun database table.
 * 
 */
@Entity
@NamedQuery(name="Racun.findAll", query="SELECT r FROM Racun r")
public class Racun implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="RACUN_ID_GENERATOR", sequenceName="RACUN_SEQ" ,allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RACUN_ID_GENERATOR")
	private Integer id;

	@Temporal(TemporalType.DATE)
	private Date datumizdavanjaracuna;

	@Temporal(TemporalType.DATE)
	private Date datumprometadobara;

	private String naziv;

	private String placeno;

	private Integer racunotpremnicabroj;

	//bi-directional many-to-one association to Kupac
	@ManyToOne
	@JoinColumn(name="kupac")
	//@JsonIgnore
	private Kupac kupacBean;

	//bi-directional many-to-one association to Poslodavac
	@ManyToOne
	@JoinColumn(name="poslodavac")
	//@JsonIgnore
	private Poslodavac poslodavacBean;

	//bi-directional many-to-one association to Stavkaracuna
	@OneToMany(mappedBy="predracunBean")
	@JsonIgnore
	private List<Stavkapredracuna> stavkapredracunas;
	

	

	//bi-directional many-to-one association to Poslodavac
	@ManyToOne
	@JoinColumn(name="predracun")
	//@JsonIgnore
	private Predracun predracunBean;

	


	public Predracun getPredracunBean() {
		return predracunBean;
	}

	public void setPredracunBean(Predracun predracunBean) {
		this.predracunBean = predracunBean;
	}

	public Racun() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDatumizdavanjaracuna() {
		return this.datumizdavanjaracuna;
	}

	public void setDatumizdavanjaracuna(Date datumizdavanjaracuna) {
		this.datumizdavanjaracuna = datumizdavanjaracuna;
	}

	public Date getDatumprometadobara() {
		return this.datumprometadobara;
	}

	public void setDatumprometadobara(Date datumprometadobara) {
		this.datumprometadobara = datumprometadobara;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getPlaceno() {
		return this.placeno;
	}

	public void setPlaceno(String placeno) {
		this.placeno = placeno;
	}

	public Integer getRacunotpremnicabroj() {
		return this.racunotpremnicabroj;
	}

	public void setRacunotpremnicabroj(Integer racunotpremnicabroj) {
		this.racunotpremnicabroj = racunotpremnicabroj;
	}

	public Kupac getKupacBean() {
		return this.kupacBean;
	}

	public void setKupacBean(Kupac kupacBean) {
		this.kupacBean = kupacBean;
	}

	public Poslodavac getPoslodavacBean() {
		return this.poslodavacBean;
	}

	public void setPoslodavacBean(Poslodavac poslodavacBean) {
		this.poslodavacBean = poslodavacBean;
	}

	public List<Stavkapredracuna> getStavkaracunas() {
		return this.stavkapredracunas;
	}

	public void setStavkaracunas(List<Stavkapredracuna> stavkapredracunas) {
		this.stavkapredracunas = stavkapredracunas;
	}

	public List<Stavkapredracuna> getStavkapredracunas() {
		return stavkapredracunas;
	}

	public void setStavkapredracunas(List<Stavkapredracuna> stavkapredracunas) {
		this.stavkapredracunas = stavkapredracunas;
	}

}
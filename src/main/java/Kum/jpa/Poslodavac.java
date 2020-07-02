package Kum.jpa;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the poslodavac database table.
 * 
 */
@Entity
@NamedQuery(name="Poslodavac.findAll", query="SELECT p FROM Poslodavac p")
public class Poslodavac implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="POSLODAVAC_ID_GENERATOR", sequenceName="POSLODAVAC_SEQ" ,allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="POSLODAVAC_ID_GENERATOR")
	private Integer id;

	private String adresa;

	private String ime;
	
	private String firma;

	private Integer maticnibrojposlodavca;

	private Integer pibposlodavca;

	private String prezime;
	
	private String email;
	
	private String telefon;
	
	

	//bi-directional many-to-one association to Predracun
	@OneToMany(mappedBy="poslodavacBean")
	@JsonIgnore
	private List<Predracun> predracuns;

	//bi-directional many-to-one association to Racun
	@OneToMany(mappedBy="poslodavacBean")
	@JsonIgnore
	private List<Racun> racuns;

	public Poslodavac() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAdresa() {
		return this.adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public Integer getMaticnibrojposlodavca() {
		return this.maticnibrojposlodavca;
	}

	public void setMaticnibrojposlodavca(Integer maticnibrojposlodavca) {
		this.maticnibrojposlodavca = maticnibrojposlodavca;
	}


	public String getFirma() {
		return firma;
	}

	public void setFirma(String firma) {
		this.firma = firma;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public Integer getPibposlodavca() {
		return this.pibposlodavca;
	}

	public void setPibposlodavca(Integer pibposlodavca) {
		this.pibposlodavca = pibposlodavca;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public List<Predracun> getPredracuns() {
		return this.predracuns;
	}

	public void setPredracuns(List<Predracun> predracuns) {
		this.predracuns = predracuns;
	}

	public Predracun addPredracun(Predracun predracun) {
		getPredracuns().add(predracun);
		predracun.setPoslodavacBean(this);

		return predracun;
	}

	public Predracun removePredracun(Predracun predracun) {
		getPredracuns().remove(predracun);
		predracun.setPoslodavacBean(null);

		return predracun;
	}

	public List<Racun> getRacuns() {
		return this.racuns;
	}

	public void setRacuns(List<Racun> racuns) {
		this.racuns = racuns;
	}

	public Racun addRacun(Racun racun) {
		getRacuns().add(racun);
		racun.setPoslodavacBean(this);

		return racun;
	}

	public Racun removeRacun(Racun racun) {
		getRacuns().remove(racun);
		racun.setPoslodavacBean(null);

		return racun;
	}

}
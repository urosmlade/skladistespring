package Kum.jpa;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the kupac database table.
 * 
 */
@Entity
@NamedQuery(name="Kupac.findAll", query="SELECT k FROM Kupac k")
public class Kupac implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="KUPAC_ID_GENERATOR", sequenceName="KUPAC_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="KUPAC_ID_GENERATOR")
	private Integer id;

	private String adresakupca;

	private String email;

	private String firma;

	private String ime;

	private Integer maticnibrojkupca;

	private Integer pibkupca;

	private String prezime;

	private String telefon;

	//bi-directional many-to-one association to Predracun
	@OneToMany(mappedBy="kupacBean")
	@JsonIgnore
	private List<Predracun> predracuns;

	//bi-directional many-to-one association to Racun
	@OneToMany(mappedBy="kupacBean")
	@JsonIgnore
	private List<Racun> racuns;

	public Kupac() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAdresakupca() {
		return this.adresakupca;
	}

	public void setAdresakupca(String adresakupca) {
		this.adresakupca = adresakupca;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirma() {
		return this.firma;
	}

	public void setFirma(String firma) {
		this.firma = firma;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public Integer getMaticnibrojkupca() {
		return this.maticnibrojkupca;
	}

	public void setMaticnibrojkupca(Integer maticnibrojkupca) {
		this.maticnibrojkupca = maticnibrojkupca;
	}

	public Integer getPibkupca() {
		return this.pibkupca;
	}

	public void setPibkupca(Integer pibkupca) {
		this.pibkupca = pibkupca;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getTelefon() {
		return this.telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public List<Predracun> getPredracuns() {
		return this.predracuns;
	}

	public void setPredracuns(List<Predracun> predracuns) {
		this.predracuns = predracuns;
	}

	public Predracun addPredracun(Predracun predracun) {
		getPredracuns().add(predracun);
		predracun.setKupacBean(this);

		return predracun;
	}

	public Predracun removePredracun(Predracun predracun) {
		getPredracuns().remove(predracun);
		predracun.setKupacBean(null);

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
		racun.setKupacBean(this);

		return racun;
	}

	public Racun removeRacun(Racun racun) {
		getRacuns().remove(racun);
		racun.setKupacBean(null);

		return racun;
	}

}
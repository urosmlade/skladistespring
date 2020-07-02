package Kum.jpa;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the proizvod database table.
 * 
 */
@Entity
@NamedQuery(name="Proizvod.findAll", query="SELECT p FROM Proizvod p")
public class Proizvod implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PROIZVOD_ID_GENERATOR", sequenceName="PROIZVOD_SEQ" ,allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PROIZVOD_ID_GENERATOR")
	private Integer id;

	private Integer cena;

	private Integer gramaza;
	
	private Integer ukupnagramaza;

	private Integer kolicina;

	private String naziv;

	//bi-directional many-to-one association to Kategorija
	@ManyToOne
	@JoinColumn(name="kategorija")
	private Kategorija kategorijaBean;

	//bi-directional many-to-one association to Stavkapredracuna
	@OneToMany(mappedBy="proizvodBean")
	@JsonIgnore
	private List<Stavkapredracuna> stavkapredracunas;

	//bi-directional many-to-one association to Stavkaracuna
	@OneToMany(mappedBy="proizvodBean")
	@JsonIgnore
	private List<Stavkaracuna> stavkaracunas;

	public Proizvod() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCena() {
		return this.cena;
	}

	public void setCena(Integer cena) {
		this.cena = cena;
	}

	public Integer getKolicina() {
		return this.kolicina;
	}

	public void setKolicina(Integer kolicina) {
		this.kolicina = kolicina;
	}

	public Integer getGramaza() {
		return gramaza;
	}

	public void setGramaza(Integer gramaza) {
		this.gramaza = gramaza;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Integer getUkupnaGramaza() {
		return ukupnagramaza;
	}

	public void setUkupnaGramaza(Integer ukupnagramaza) {
		this.ukupnagramaza = ukupnagramaza;
	}

	public Kategorija getKategorijaBean() {
		return this.kategorijaBean;
	}

	public void setKategorijaBean(Kategorija kategorijaBean) {
		this.kategorijaBean = kategorijaBean;
	}

	public List<Stavkapredracuna> getStavkapredracunas() {
		return this.stavkapredracunas;
	}

	public void setStavkapredracunas(List<Stavkapredracuna> stavkapredracunas) {
		this.stavkapredracunas = stavkapredracunas;
	}

	public Stavkapredracuna addStavkapredracuna(Stavkapredracuna stavkapredracuna) {
		getStavkapredracunas().add(stavkapredracuna);
		stavkapredracuna.setProizvodBean(this);

		return stavkapredracuna;
	}

	public Stavkapredracuna removeStavkapredracuna(Stavkapredracuna stavkapredracuna) {
		getStavkapredracunas().remove(stavkapredracuna);
		stavkapredracuna.setProizvodBean(null);

		return stavkapredracuna;
	}

	public List<Stavkaracuna> getStavkaracunas() {
		return this.stavkaracunas;
	}

	public void setStavkaracunas(List<Stavkaracuna> stavkaracunas) {
		this.stavkaracunas = stavkaracunas;
	}

	public Stavkaracuna addStavkaracuna(Stavkaracuna stavkaracuna) {
		getStavkaracunas().add(stavkaracuna);
		stavkaracuna.setProizvodBean(this);

		return stavkaracuna;
	}

	public Stavkaracuna removeStavkaracuna(Stavkaracuna stavkaracuna) {
		getStavkaracunas().remove(stavkaracuna);
		stavkaracuna.setProizvodBean(null);

		return stavkaracuna;
	}

}
package Kum.jpa;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the kategorija database table.
 * 
 */
@Entity
@NamedQuery(name="Kategorija.findAll", query="SELECT k FROM Kategorija k")
public class Kategorija implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="KATEGORIJA_ID_GENERATOR", sequenceName="KATEGORIJA_SEQ" ,allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="KATEGORIJA_ID_GENERATOR")
	private Integer id;

	private String naziv;

	//bi-directional many-to-one association to Proizvod
	@OneToMany(mappedBy="kategorijaBean")
	@JsonIgnore
	private List<Proizvod> proizvods;

	public Kategorija() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<Proizvod> getProizvods() {
		return this.proizvods;
	}

	public void setProizvods(List<Proizvod> proizvods) {
		this.proizvods = proizvods;
	}

	public Proizvod addProizvod(Proizvod proizvod) {
		getProizvods().add(proizvod);
		proizvod.setKategorijaBean(this);

		return proizvod;
	}

	public Proizvod removeProizvod(Proizvod proizvod) {
		getProizvods().remove(proizvod);
		proizvod.setKategorijaBean(null);

		return proizvod;
	}

}
package Kum.jpa;

import java.io.Serializable;
import javax.persistence.*;



/**
 * The persistent class for the stavkapredracuna database table.
 * 
 */
@Entity
@NamedQuery(name="Stavkapredracuna.findAll", query="SELECT s FROM Stavkapredracuna s")
public class Stavkapredracuna implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="STAVKAPREDRACUNA_ID_GENERATOR", sequenceName="STAVKAPREDRACUNA_SEQ" ,allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="STAVKAPREDRACUNA_ID_GENERATOR")
	private Integer id;

	private Integer kolicina;

	private Integer stopapdv;

	private Integer vrednostbezpdv;

	private Integer vrednostpdv;

	private Integer vrednostsapdv;

	//bi-directional many-to-one association to Predracun
	@ManyToOne
	@JoinColumn(name="predracun")
	private Predracun predracunBean;

	//bi-directional many-to-one association to Proizvod
	@ManyToOne
	@JoinColumn(name="proizvod")
	private Proizvod proizvodBean;

	public Stavkapredracuna() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getKolicina() {
		return this.kolicina;
	}

	public void setKolicina(Integer kolicina) {
		this.kolicina = kolicina;
	}

	public Integer getStopapdv() {
		return this.stopapdv;
	}

	public void setStopapdv(Integer stopapdv) {
		this.stopapdv = stopapdv;
	}

	public Integer getVrednostbezpdv() {
		return this.vrednostbezpdv;
	}

	public void setVrednostbezpdv(Integer vrednostbezpdv) {
		this.vrednostbezpdv = vrednostbezpdv;
	}

	public Integer getVrednostpdv() {
		return this.vrednostpdv;
	}

	public void setVrednostpdv(Integer vrednostpdv) {
		this.vrednostpdv = vrednostpdv;
	}

	public Integer getVrednostsapdv() {
		return this.vrednostsapdv;
	}

	public void setVrednostsapdv(Integer vrednostsapdv) {
		this.vrednostsapdv = vrednostsapdv;
	}

	public Predracun getPredracunBean() {
		return this.predracunBean;
	}

	public void setPredracunBean(Predracun predracunBean) {
		this.predracunBean = predracunBean;
	}

	public Proizvod getProizvodBean() {
		return this.proizvodBean;
	}

	public void setProizvodBean(Proizvod proizvodBean) {
		this.proizvodBean = proizvodBean;
	}

}
package restservice.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "superheroe_poder")
public class SuperheroePoder implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Id
	private SuperheroePoderKey id;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "poder_id", nullable = false, insertable = false, updatable = false)
	private Poder poder;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "superheroe_id", nullable = false, insertable = false, updatable = false)
	private Superheroe superheroe;

	public SuperheroePoderKey getId() {
		return id;
	}

	public void setId(SuperheroePoderKey id) {
		this.id = id;
	}

	public Poder getPoder() {
		return poder;
	}

	public void setPoder(Poder poder) {
		this.poder = poder;
	}

	public Superheroe getSuperheroe() {
		return superheroe;
	}

	public void setSuperheroe(Superheroe superheroe) {
		this.superheroe = superheroe;
	}
}

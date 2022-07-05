package restservice.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;




@Entity
@Table(name = "podersuperheroe")
public class PoderSuperheroe {
	
	@Id
	private PoderSuperheroeKey id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_poder", nullable = false, insertable = false, updatable = false)
	private Poder poder;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_superheroe", nullable = false, insertable = false, updatable = false)
	private Superheroe superheroe;

	public PoderSuperheroeKey getId() {
		return id;
	}

	public void setId(PoderSuperheroeKey id) {
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

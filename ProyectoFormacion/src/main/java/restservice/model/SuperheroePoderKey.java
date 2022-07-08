package restservice.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SuperheroePoderKey implements Serializable{
	private static final long serialVersionUID = 1L;

	@Column(name = "poder_id")
	private Integer poder_id;
	
	@Column(name = "superheroe_id")
	private Integer superheroe_id;

	public Integer getPoder_id() {
		return poder_id;
	}

	public void setPoder_id(Integer poder_id) {
		this.poder_id = poder_id;
	}

	public Integer getSuperheroe_id() {
		return superheroe_id;
	}

	public void setSuperheroe_id(Integer superheroe_id) {
		this.superheroe_id = superheroe_id;
	}
	
}

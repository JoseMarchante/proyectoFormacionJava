package restservice.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;



@Embeddable
public class PoderSuperheroeKey {


	@Column(name = "id_poder")
	private Long poder_id;
	
	@Column(name = "id_superheroe")
	private Long superheroe_id;

	public Long getPoder_id() {
		return poder_id;
	}

	public void setPoder_id(Long poder_id) {
		this.poder_id = poder_id;
	}

	public Long getSuperheroe_id() {
		return superheroe_id;
	}

	public void setSuperheroe_id(Long superheroe_id) {
		this.superheroe_id = superheroe_id;
	}
	
}

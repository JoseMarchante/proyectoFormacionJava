package restservice.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "superheroe")
public class Superheroe {
	
	private Long id;
	private String nombre;
	private List<PoderSuperheroe> poder;
	private Universo universo;
	
public Superheroe() {}
	
	public Superheroe(String nombre) {
		this.nombre=nombre;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "nombre", nullable = false)
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre=nombre;
	}
	
	@OneToMany()
	@JoinColumn(name = "id_poder")
	public List<PoderSuperheroe> getPoder() {
		return poder;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_universo")
	public Universo getUniverso() {
		return universo;
	}

	public void setPoder(List<PoderSuperheroe> poder) {
		this.poder = poder;
	}

	public void setUniverso(Universo universo) {
		this.universo = universo;
	}
	
	
}

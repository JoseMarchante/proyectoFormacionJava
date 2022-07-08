package restservice.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "superheroe")
public class Superheroe implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nombre", unique=true)
	@NotNull(message = "El campo nombre no puede ser nulo")
	private String nombre;
	
	@Column(name = "estado", nullable = false)
	@NotNull
	private boolean estado;
	
	@Column(name = "id_universo", nullable = false)
	private Integer id_universo;
	
	@ManyToMany()
	@NotNull
	private List<Poder> poder;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_universo",nullable = false, insertable = false, updatable = false)
	private Universo universo;
	
	public Superheroe() {
		poder=new ArrayList<>();
	}
	
	public Superheroe(String nombre) {
		this.nombre=nombre;
		poder=new ArrayList<>();
	}
	public Superheroe(String nombre,boolean estado, Universo universo, List<Poder>poderes) {
		this.nombre=nombre;
		this.estado=estado;
		this.universo=universo;
		this.poder=poderes;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre=nombre;
	}
	
	
	public boolean getEstado() {
		return estado;
	}
	
	
	
	public Integer getId_universo() {
		return id_universo;
	}
	
	
	
	public List<Poder> getPoder() {
		return poder;
	}
	
	
	public Universo getUniverso() {
		return universo;
	}

	public void setPoder(List<Poder> poder) {
		this.poder = poder;
	}

	public void setUniverso(Universo universo) {
		this.universo = universo;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public void setId_universo(Integer id_universo) {
		this.id_universo = id_universo;
	}
	
	
}

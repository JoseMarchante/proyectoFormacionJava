package restservice.dto;

import java.io.Serializable;

import restservice.model.Universo;

public class UniversoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String nombre;
	
	public UniversoDTO() {}
	
	public UniversoDTO(Universo universo) {
		this.nombre=universo.getNombre();
	}
	
	public UniversoDTO(String nombre) {
		this.nombre=nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

	@Override
	public String toString() {
		return "Universo [nombre="+ nombre +"]";
	}
}

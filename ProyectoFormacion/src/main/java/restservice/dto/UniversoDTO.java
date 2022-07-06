package restservice.dto;

import java.io.Serializable;

public class UniversoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String nombre;
	
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
		return "UniversoDTO [nombre="+ nombre +"]";
	}
}

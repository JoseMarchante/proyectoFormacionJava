package restservice.dto;

import java.io.Serializable;

import restservice.model.Poder;

public class PoderDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String nombre;
	
	public PoderDTO() {}
	
	public PoderDTO(Poder poder) {
		this.nombre=poder.getNombre();
		
	}
	
	public PoderDTO(String nombre) {
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
		return "Poder [nombre="+ nombre +" ]";
	}
}

package restservice.dto;

import java.io.Serializable;

public class PoderDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String nombre;
	
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
		return "PoderDTO [nombre="+ nombre +" ]";
	}
}

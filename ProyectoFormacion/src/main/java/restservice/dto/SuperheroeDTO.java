package restservice.dto;

import java.io.Serializable;

public class SuperheroeDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private boolean estado;
	private PoderDTO poder;
	private UniversoDTO universo; 
	
	public SuperheroeDTO(String nombre, boolean estado, PoderDTO poder,UniversoDTO universo) {
		this.nombre=nombre;
		this.estado=estado;
		this.poder=poder;
		this.universo=universo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public PoderDTO getPoder() {
		return poder;
	}

	public void setPoder(PoderDTO poder) {
		this.poder = poder;
	}

	public UniversoDTO getUniverso() {
		return universo;
	}

	public void setUniverso(UniversoDTO universo) {
		this.universo = universo;
	}
	
	@Override
	public String toString() {
		return "Superheroe[nombre="+nombre+", poder = "+poder.toString()+", universo= "+universo.getNombre()+"]";
	}
}

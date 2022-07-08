package restservice.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import restservice.model.Superheroe;

public class SuperheroeDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private boolean estado;
	
	private List<Integer> poderes;
	private List<String> poder;
	private Integer universoId;
	private String universo;
	
	public SuperheroeDTO() {
		poder=new ArrayList<>();
		poderes=new ArrayList<>();
	}
	
	public SuperheroeDTO(Superheroe superheroe) {
		poder=new ArrayList<>();
		poderes=new ArrayList<>();
		this.nombre=superheroe.getNombre();
		this.estado=superheroe.getEstado();
		this.universoId=superheroe.getId_universo();
		this.universo=superheroe.getUniverso().getNombre();
		superheroe.getPoder().forEach(pd -> poder.add(pd.getNombre()));
		superheroe.getPoder().forEach(pd -> poderes.add(pd.getId()));
	}
	
	public SuperheroeDTO(String nombre, boolean estado,Integer universoId, List<Integer> poder) {
		this.nombre=nombre;
		this.estado=estado;
		this.poderes=poder;
		this.universoId=universoId;	
		
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


	public void setPoderes(List<Integer> poderes) {
		this.poderes = poderes;
	}

	public List<String> getPoder() {
		return poder;
	}

	public void setPoder(List<String> poder) {
		this.poder = poder;
	}


	public void setUniversoId(Integer universoId) {
		this.universoId = universoId;
	}

	public String getUniverso() {
		return universo;
	}

	public void setUniverso(String universo) {
		this.universo = universo;
	}
	
	
	
	public List<Integer> getPoderes() {
		return poderes;
	}

	public Integer getUniversoId() {
		return universoId;
	}

	@Override
	public String toString() {
		String resEstado="";
		if(this.isEstado()) {
			resEstado="Vivo";
		}
		else {
			resEstado="Muerto";
		}
		return "Superheroe[nombre="+this.getNombre()+", estado: "+resEstado+", poder = "+this.getPoder().toString()+", universo= "+this.getUniverso()+"]";
	}





	
}

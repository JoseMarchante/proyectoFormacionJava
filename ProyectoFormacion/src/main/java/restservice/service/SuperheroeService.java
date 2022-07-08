package restservice.service;

import java.util.List;
import java.util.Optional;

import restservice.exception.ResourceNotFoundException;
import restservice.model.Poder;
import restservice.model.Superheroe;

public interface SuperheroeService {
	Superheroe guardarSuperheroe(Superheroe superheroe);
	void deleteSuperheroe(Integer id_superheroe)throws ResourceNotFoundException;
	List<Superheroe> findAllSuperheroes()throws ResourceNotFoundException;
	Optional<Superheroe> findSuperheroe(Integer id)throws ResourceNotFoundException;
	List<Superheroe> findByNameContaining(String nombre) throws ResourceNotFoundException;
	Optional<Superheroe> agregarPoderSuperheroe(Integer id_superheroe,Poder poder) throws ResourceNotFoundException;
	void morir(Superheroe superheroe);
	void revivir(Superheroe superheroe);
}

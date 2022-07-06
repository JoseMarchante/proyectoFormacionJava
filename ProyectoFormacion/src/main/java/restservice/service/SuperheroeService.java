package restservice.service;

import java.util.List;
import java.util.Optional;

import restservice.exception.ResourceNotFoundException;
import restservice.model.Superheroe;


public interface SuperheroeService {
	Superheroe guardarSuperheroe(Superheroe superheroe);
	void deleteSuperheroe(Long id_superheroe);
	List<Superheroe> findAllSuperheroes()throws ResourceNotFoundException;
	Optional<Superheroe> findSuperheroe(Long id)throws ResourceNotFoundException;
	List<Superheroe> findByNameSuperheroe(String nombre) throws ResourceNotFoundException;
	
	void morir(Superheroe superheroe);
	void revivir(Superheroe superheroe);
}

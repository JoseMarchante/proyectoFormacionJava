package restservice.service;

import java.util.List;

import restservice.exception.ResourceNotFoundException;
import restservice.model.Superheroe;


public interface SuperheroeService {
	Superheroe guardarSuperheroe(Long id);
	void deleteSuperheroe(Long id_superheroe);
	
	List<Superheroe> findAllSuperheroes()throws ResourceNotFoundException;
	Superheroe findSuperheroe(Long id)throws ResourceNotFoundException;
}

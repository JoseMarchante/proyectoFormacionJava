package restservice.service;

import java.util.List;
import java.util.Optional;

import restservice.exception.ResourceNotFoundException;
import restservice.model.Poder;


public interface PoderService {
	Poder guardarPoder(Poder poder);
	void deletePoder(Long id_poder);
	
	List<Poder> findAllPoderes()throws ResourceNotFoundException;
	Optional<Poder> findPoder(Long id)throws ResourceNotFoundException;
}

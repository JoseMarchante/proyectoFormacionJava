package restservice.service;

import java.util.List;

import restservice.exception.ResourceNotFoundException;
import restservice.model.Poder;


public interface PoderService {
	Poder guardarPoder(Long id);
	void deletePoder(Long id_poder);
	
	List<Poder> findAllPoderes()throws ResourceNotFoundException;
	Poder findPoder(Long id)throws ResourceNotFoundException;
}

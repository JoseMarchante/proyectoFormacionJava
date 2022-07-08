package restservice.service;

import java.util.List;
import java.util.Optional;

import restservice.exception.ResourceNotFoundException;
import restservice.model.Poder;

public interface PoderService {
	Poder guardarPoder(Poder poder);
	void deletePoder(Integer id_poder)throws ResourceNotFoundException;
	List<Poder> findAllPoderes()throws ResourceNotFoundException;
	Optional<Poder> findPoder(Integer id)throws ResourceNotFoundException;
	Poder findByNombre(String nombre)throws ResourceNotFoundException;
}

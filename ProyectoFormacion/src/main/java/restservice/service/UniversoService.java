package restservice.service;

import java.util.List;
import java.util.Optional;

import restservice.exception.ResourceNotFoundException;
import restservice.model.Universo;

public interface UniversoService {

	Universo guardarUniverso(Universo universo);
	void deleteUniverso(Long id_universo);
	
	List<Universo> findAllUniversos()throws ResourceNotFoundException;
	Optional<Universo> findUniverso(Long id)throws ResourceNotFoundException;
}

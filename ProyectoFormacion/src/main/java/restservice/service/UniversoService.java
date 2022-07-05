package restservice.service;

import java.util.List;

import restservice.exception.ResourceNotFoundException;
import restservice.model.Universo;

public interface UniversoService {

	Universo guardarUniverso(Long id);
	void deleteUniverso(Long id_universo);
	
	List<Universo> findAllUniversos()throws ResourceNotFoundException;
	Universo findUniverso(Long id)throws ResourceNotFoundException;
}

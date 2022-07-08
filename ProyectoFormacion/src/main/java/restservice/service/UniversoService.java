package restservice.service;

import java.util.List;
import java.util.Optional;

import restservice.exception.ResourceNotFoundException;
import restservice.model.Universo;

public interface UniversoService {

	Universo guardarUniverso(Universo universo);
	void deleteUniverso(Integer id_universo)throws ResourceNotFoundException;
	Universo findByNombre(String nombre)throws ResourceNotFoundException;
	List<Universo> findAllUniversos()throws ResourceNotFoundException;
	Optional<Universo> findUniverso(Integer id)throws ResourceNotFoundException;
}
